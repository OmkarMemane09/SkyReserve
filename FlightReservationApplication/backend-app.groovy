pipeline {
    agent any 
    stages {
        stage('Code-Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/mayurmwagh/flight-reservation-app.git'    
            }
        }
        stage('Code-Build'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    mvn clean package 
                '''
            }
        }
        stage('QA-Test'){
            steps{
                withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-token') {
                 sh '''
                    cd FlightReservationApplication
                    mvn sonar:sonar -Dsonar.projectKey=flight-reservation
                 '''
                }
            }
        }
        stage('Docker-Build'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    docker build -t mayurwagh/flight-reservation-pls-18:latest . 
                    docker push mayurwagh/flight-reservation-pls-18:latest 
                    docker rmi mayurwagh/flight-reservation-pls-18:latest 
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    kubectl apply -f k8s/
                '''
            }
        }
    }
}