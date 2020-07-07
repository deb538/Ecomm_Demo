node {
    def app

    stage('Clone repository') {
        
        checkout scm
        
        echo "Cloned the Repository to our Workspace"
    }

    stage('Build image') {
        echo "Building the actual image"

        app = docker.build("deb538/catalogue")
        
        echo "Image build complete"
    }

    stage('Test image') {
        
        app.inside {
            echo "Tests passed"
        }
    }

    stage('Push image') {
        /* 
			You would need to first register with DockerHub before you can push images to your account
		*/
        docker.withRegistry('https://registry.hub.docker.com', 'deb538') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
            } 
                echo "Trying to Push Docker Build to DockerHub"
    }
}