pipeline {
    agent any

    environment {
        IMAGE_NAME = "omkarmemane9/flight-reservation-pls-19-20"
        APP_DIR = "FlightReservationApplication"
        IMAGE_TAG = "${BUILD_NUMBER}"
        AWS_REGION = "us-east-2"
    }

    stages {

        stage('Code Pull') {
            steps {
                git branch: 'main', url: 'https://github.com/OmkarMemane09/SkyReserve.git'
            }
        }

        stage('Build') {
            steps {
                dir("${APP_DIR}") {
                    sh '''
                        set -e
                        mvn clean package -DskipTests
                    '''
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                dir("${APP_DIR}") {
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )]) {
                        sh '''
                            set -e

                            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                            docker build -t $IMAGE_NAME:$IMAGE_TAG .
                            docker tag $IMAGE_NAME:$IMAGE_TAG $IMAGE_NAME:latest

                            docker push $IMAGE_NAME:$IMAGE_TAG
                            docker push $IMAGE_NAME:latest

                            docker logout
                        '''
                    }
                }
            }
        }

        stage('Deploy') {
    steps {
        dir("${APP_DIR}") {
            withCredentials([usernamePassword(
                credentialsId: 'aws-creds',
                usernameVariable: 'AWS_ACCESS_KEY_ID',
                passwordVariable: 'AWS_SECRET_ACCESS_KEY'
            )]) {
                sh '''
                    set -e

                    export AWS_DEFAULT_REGION=$AWS_REGION

                    aws eks update-kubeconfig --region $AWS_REGION --name cbz-cluster

                    # Apply first (create deployment if not exists)
                    kubectl apply -f k8s/deployment.yaml
                    kubectl apply -f k8s/service.yaml

                    # Then update image
                    kubectl set image deployment/flight-app \
                    flight-app=$IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }
    }
}
    }
}
