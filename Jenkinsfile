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
        IMAGE_NAME = 'ahmadhaleem/my-repo:1.1.5-3'
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
                    buildImage(ENV.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(ENV.IMAGE_NAME)
                    //dockerPush 'ahmadhaleem/my-repo:jma-3.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    //gv.deployApp()
                    echo "deploying docker image to EC2..."
                    def dockerCmd = "docker run -d -p 8080:8080 ${IMAGE_NAME}"
                    sshagent(['ec2-server-key']) {
                       sh "ssh -o StrictHostKeyChecking=no ec2-user@18.197.26.189 ${dockerCmd}"
                    }
                }
            }
        }
    }   
}
