pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                script {
                    echo "Testing the application..."
                }
            }
        }
        stage("build") {
            steps {
                script {
                    echo "Building the application..."
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerCmd = 'docker run -d -p 3000:3000 ahmadhaleem/node-hello-world:latest'
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@18.197.26.189 ${dockerCmd}"
                    }
                }
            }
        }
    }   
}