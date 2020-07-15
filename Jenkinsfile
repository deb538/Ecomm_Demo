def getDockerTag(){
    return '0.0.2-SNAPSHOT';
}

pipeline{
    agent any
    
    environment {
        DOCKER_TAG = getDockerTag()
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
                echo "DOCKER_TAG = ${DOCKER_TAG}"
            }
        }
        stage('Maven Unit Test and Package'){
            steps{
                bat 'mvn install package'
            }
        }
        stage('Docker Build'){
            steps{
            
                bat 'docker --version'
                bat "docker build . -t deb538/catalogue:${DOCKER_TAG}"
            }
        }
        stage('Docker Push'){
            steps{
                
                withDockerRegistry(credentialsId: 'deb538', url: "") {
                    bat "docker push deb538/catalogue:${DOCKER_TAG}"
                }
            }
        }
    }
}