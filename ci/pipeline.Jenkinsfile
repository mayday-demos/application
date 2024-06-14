pipeline {
    agent any
    tools {
        jdk 'java-21'
    }
    
    stages {
        stage('Checkout Main Repo') {
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
                   docker.build("mayday24/bnl-application:${env.BUILD_ID}", "-f Containerfile .")
                }
            }
        }
        
        stage('Package') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials-id') {
                        docker.image("mayday24/bnl-application:${env.BUILD_ID}").push()
                    }
                }
            }
        }
        
          stage('Checkout Helm Repo') {
            steps {
                git branch: 'main', url: 'https://github.com/mayday-demos/application-chart.git'
            }
        }
         
        stage('Apply Helm') {
            steps {
                script {
                    withKubeCredentials(kubectlCredentials: [[caCertificate: '', clusterName: 'kind-kind', contextName: 'kind-kind', credentialsId: 'kind', namespace: '', serverUrl: 'https://kind-control-plane:6443']]) {
                        
                        docker.image("alpine/helm:latest").inside('--network kind --entrypoint=""') {
                        sh "helm upgrade bnl-application . --install --namespace bnl-project --create-namespace --set env.DYNAMIC_STRING=${params.DYNAMIC_ENV} --set image.tag=${env.BUILD_ID}"
                    }
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

