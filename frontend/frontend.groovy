pipeline{
    agent any
    stages{
        stage('Code-Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/OmkarMemane09/SkyReserve.git'    
            }
        }
        stage('Code-Build'){
            steps{
                sh '''
                    cd frontend
                    npm install
                    npm run build
                '''
            }
        }
        stage('Deploy'){
    steps{
        withCredentials([usernamePassword(
            credentialsId: 'aws-creds',
            usernameVariable: 'AWS_ACCESS_KEY_ID',
            passwordVariable: 'AWS_SECRET_ACCESS_KEY'
        )]) {
            sh '''
                set -e
                export AWS_DEFAULT_REGION=us-east-2

                cd frontend
                aws s3 sync dist/ s3://cbz-buxxx-12-xyt/
            '''
        }
    }
}
    }
}
