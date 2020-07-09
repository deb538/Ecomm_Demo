pipeline{
    agent any
    
    environment {
        registryCredential = 'Docker_Hub_password'
    }
    
    tools { 
        maven 'Maven3.6.3' 
        jdk 'Java9' 
    }
    
    stages{
        stage('Git Checkout') {
            steps{
               git credentialsId: 'GitHub', url: 'https://github.com/deb538/Ecomm_Demo.git'
                echo "Git Checkout Done.."
            }
        }
        stage ('Initialize') {
            steps {
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
            }
        }
        stage('Maven Unit Test And Package'){
            steps{
                
                bat 'mvn install clean'
            }
        }
        stage('Docker Build'){
            steps{
                
                bat 'docker --version'
                bat 'docker build . -t deb538/catalogue:0.0.1-SNAPSHOT'
            }
        }
        stage('Docker Push'){
            steps{
                
                echo 'registryCredential'
                
                withDockerRegistry(credentialsId: 'deb538', url: "") {
                    bat 'docker push deb538/catalogue:0.0.1-SNAPSHOT'
                }
            }
        }
    }
}

