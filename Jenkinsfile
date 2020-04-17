pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
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
        stage('Deploy') { 
            steps {
		sh 'pwd'
		sh 'cd /var/jenkins_home/workspace/es-2019-2020-P11_master/service-layer'
		sh 'pwd'
                sh 'mvn deploy' 
            }
        }
    }
}
