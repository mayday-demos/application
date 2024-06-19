pipeline {
    agent any
    tools {
        jdk 'java-21'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mayday-demos/application.git'
            }
        }
        
        stage('Build') {
            steps {
                script {
                    sh './mvnw -B -DskipTests clean package'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                   docker.build("mayday24/bnl-application:latest", "-f Containerfile .")
                }
            }
        }
        
        stage('Package') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials-id') {
                        docker.image("mayday24/bnl-application:latest").push()
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline completata.'
        }
    }
}
