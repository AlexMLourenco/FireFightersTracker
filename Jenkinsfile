pipeline {
    agent any 
    parameters {
        choice(choices: 'yes\nno', description: 'Are you sure you want to execute this test?', name: 'run_test_only')
        string(defaultValue: "lhsantos@ua.pt", description: 'email for notifications', name: 'notification_email')
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -f service-layer/pom.xml -B -DskipTests clean package' 
            }
        }
	stage('Test') {
            when {
                 environment name: 'run_test_only', value: 'yes'
            }
            steps {
                sh 'mvn -f service-layer/ test'
            }
            
        }
        stage('Artifactory Deployment') { 
            steps {
                sh 'mvn deploy -f service-layer/pom.xml -s service-layer/settings.xml' 
            }
        }
	stage('Build Docker Image') { 
            steps {
                sh "docker rmi -f esp11-service-layer"
                sh "docker build -t esp11-service-layer ."
                sh "docker tag esp11-service-layer 192.168.160.99:5000/esp11-service-layer"
                sh "docker push 192.168.160.99:5000/esp11-service-layer"
		 
            }
        }
	stage('Runtime Deployment') { 
            steps {
                sshagent(credentials: ['esp11_ssh_credentials']){
                    //sh "ssh -o 'StrictHostKeyChecking=no' -l esp11 192.168.160.103 docker stop esp11-service-layer"
                    //sh "ssh -o 'StrictHostKeyChecking=no' -l esp11 192.168.160.103 docker rm esp11-service-layer"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp11 192.168.160.103 docker run -d -p 11000:11080 --name esp11-service-layer 192.168.160.99:5000/esp11-service-layer"
                }
            }
        }
    }
    // post {
    //     success {
    //         echo "Test succeeded"
    //         script {

    //             mail(bcc: '',
    //                  body: "Run ${JOB_NAME}-#${BUILD_NUMBER} succeeded. To get more details, visit the build results page: ${BUILD_URL}.",
    //                  cc: '',
    //                  from: 'jenkins-admin@gmail.com',
    //                  replyTo: '',
    //                  subject: "${JOB_NAME} ${BUILD_NUMBER} succeeded",
    //                  to: env.notification_email)
    //         }
    //     }
    //     failure {
    //         echo "Test failed"
    //         mail(bcc: '',
    //             body: "Run ${JOB_NAME}-#${BUILD_NUMBER} succeeded. To get more details, visit the build results page: ${BUILD_URL}.",
    //              cc: '',
    //              from: 'jenkins-admin@gmail.com',
    //              replyTo: '',
    //              subject: "${JOB_NAME} ${BUILD_NUMBER} failed",
    //              to: env.notification_email)
    //     }
    // }
}
