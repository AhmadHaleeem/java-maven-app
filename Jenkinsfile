#!/usr/bin/env groovy

//@Library('jenkins-shared-library')
library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/AhmadHaleeem/jenkins-shared-library.git',
    credentialsId: 'github-credentials'
    ]
)
//@Library('jenkins-shared-library@master') 

def gv

pipeline {
    agent any
    tools {
        maven "maven-3.8"
    }
    environment {
        IMAGE_NAME = 'ahmadhaleem/demo-app:1.0'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    //gv.buildJar()
                    buildJar()
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    //gv.buildImage()
                    //buildImage 'ahmadhaleem/my-repo:jma-3.0'
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    echo "Successfully logged in"
                    dockerPush(env.IMAGE_NAME)
                    //dockerPush 'ahmadhaleem/my-repo:jma-3.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    //gv.deployApp()
                    def ec2Instance = "ec2-user@18.197.26.189"
                    echo "deploying docker image to EC2..."
                    //def dockerCmd = "docker run -d -p 8080:8080 ${IMAGE_NAME}"
                    //def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
                    def shellCmd = "bash ./server-cmds.sh"
                    sshagent(['ec2-server-key']) {
                        sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
                        sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"
                    }
                }
            }
        }
    }   
}
