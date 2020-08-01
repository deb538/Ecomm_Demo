	def getDockerTag(){
		return '0.0.2-SNAPSHOT';
	}

	/*
		Create the kubernetes namespace
	 */
	def createNamespace (namespace) {
		echo "Creating namespace ${namespace} if needed"

		sh "[ ! -z \"\$(kubectl get ns ${namespace} -o name 2>null)\" ] ||kubectl create namespace ${namespace}"
	}

	/*
		Helm install
	 */
	def helmInstall (namespace) {
		echo "Installing in ${namespace}"

		script {
			sh "helm repo add e_comm_538 ${HELM_REPO}; helm repo update"
			sh "helm repo update"
			sh "helm upgrade --install catalogue-service e_comm_538/catalogue-service"
			sh "sleep 5"
		}
	}

	/*
		Helm delete (if exists)
	 */
	def helmDelete (namespace) {
		echo "Deleting in ${namespace} if deployed"

		script {
			sh "helm delete e_comm_538/catalogue-service --namespace=${namespace}"
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
        string (name: 'HELM_REPO', defaultValue: 'https://storage.googleapis.com/my-gcs-bucket-538/', description: 'Your helm repository')
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
            	/*sh 'mvn install package'*/
            	echo "PATH = ${PATH}"
            }
        }
        stage('Docker Build'){
            steps{
            	container('docker') {
                	sh 'docker --version'
                }
            }
        }
        stage('Docker Push'){
            steps{
				echo "PATH = ${PATH}"
            }
        }
        stage('Helm Build'){
			
			steps{
			
				container('gcloud') {
				
					echo "gcloud start"
					sh "gcloud compute instances set-service-account jenkins --service-account jenkins-sa@aerial-yeti-281414.iam.gserviceaccount.com -zone us-central1-c --scopes compute-rw,storage-ro"
					sh 'gcloud container clusters get-credentials cluster-1 --zone us-central1-c --project aerial-yeti-281414'
					echo "gcloud connected"
					sh 'kubectl get all'
					echo "gcloud end"
				}
				container('helm') {
				
					script{
					
						namespace = 'dit'
						sh 'helm version'
			
						/*createNamespace (namespace)*/
		
						// Remove release if exists
						/*helmDelete (namespace)*/
		
						// Deploy with helm
						echo "Deploying"
						helmInstall(namespace)
					}
				}
			}
        }
    }
}