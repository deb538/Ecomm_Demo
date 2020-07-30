	def getDockerTag(){
		return '0.0.2-SNAPSHOT';
	}

	/*
		Create the kubernetes namespace
	 */
	def createNamespace (namespace) {
		echo "Creating namespace ${namespace} if needed"

		bat "kubectl create namespace ${namespace}"
	}

	/*
		Helm install
	 */
	def helmInstall (namespace) {
		echo "Installing in ${namespace}"

		script {
			bat "helm repo add helm ${HELM_REPO}; helm repo update"
			bat "helm upgrade --install catalogue-service --namespace=${namespace} chartmuseum/catalogue-service"
			bat "sleep 5"
		}
	}

	/*
		Helm delete (if exists)
	 */
	def helmDelete (namespace) {
		echo "Deleting in ${namespace} if deployed"

		script {
			bat "helm delete chartmuseum/catalogue-service --namespace=${namespace}"
		}
	}

pipeline{
    agent {
        kubernetes {
            defaultContainer 'jnlp'
            yamlFile 'build.yaml'
            idleMinutes 5
        }
    }
    
    environment {
        DOCKER_TAG = getDockerTag()
    }
    
    parameters {
        string (name: 'HELM_REPO', defaultValue: 'http://localhost:8090', description: 'Your helm repository')
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
                /*echo "helm_repo = ${HELM_REPO}"*/
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
            }
        }
        stage('Docker Push'){
            steps{
				echo "PATH = ${PATH}"
            }
        }
        stage('Helm Build'){
			steps{
				container('helm') {
					bat 'helm version'
				}
			
				script {
					namespace = 'dit'
					
					createNamespace (namespace)

					// Remove release if exists
					helmDelete (namespace)

					// Deploy with helm
					echo "Deploying"
					helmInstall(namespace)
				}
			}
        }
    }
}