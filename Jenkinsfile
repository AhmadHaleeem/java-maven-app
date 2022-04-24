#!/usr/bin/env groovy

@Library('jenkins-shared-library')
def gv

pipeline {
    agent any
    tools {
        maven "maven-3.8"
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
                    buildImage 'ahmadhaleem/my-repo:jma-3.0'
                    dockerLogin()
                    dockerPush 'ahmadhaleem/my-repo:jma-3.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }   
}
