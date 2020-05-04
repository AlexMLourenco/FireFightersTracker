pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -f service-layer/pom.xml -B -DskipTests clean package' 
            }
        }
	stage('Test') {
            steps {
                sh 'mvn -f service-layer/ test'
            }
            
        }
        stage('Artifactory Deploy') { 
            steps {
                sh 'mvn deploy -f service-layer/pom.xml -s service-layer/settings.xml' 
            }
        }
	stage('Build Docker Image') { 
            steps {
                sh "docker build -t esp11-service-layer ."
                sh "docker tag esp11-service-layer 192.168.160.99:5000/esp11-service-layer"
                sh "docker push 192.168.160.99:5000/esp11-service-layer"
		 
            }
        }
	stage('Runtime Deploy') { 
            steps {
                sshagent(credentials: ['esp11_ssh_credentials']){
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp11 192.168.160.103 docker run -d -p 11080:8080 --name esp11-service-layer 192.168.160.99:5000/esp11-service-layer"
                }
            }
        }
    }
}
