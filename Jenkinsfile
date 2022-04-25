#!/usr/bin/env groovy

pipeline {
    agent any
    // parameters {
    //     choice(name: 'VERSION', choices: ['1.0.0', '1.2.0', '1.3.0'], description: '')
    //     booleanParam(name: 'executeTests', defaultValue: true, description: '')
    // }
    tools {
        maven "maven-3.8"
    }
    stages {
//         stage("init") {
//             steps {
//                 script {
//                     gv = load "script.groovy"
//                 }
//             }
//         }
//         stage('increment version') {
//             steps {
//                 script {
//                     echo 'incrementing app version...'
//                     sh 'mvn build-helper:parse-version versions:set \
//                         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
//                         versions:commit'
//                     def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
//                     def version = matcher[0][1]
//                     env.IMAGE_NAME = "$version-$BUILD_NUMBER"
//                 }
//             }
//         }
//         stage("build jar") {
//             steps {
//                 script {
//                     echo "building the application..."
//                     sh 'mvn clean package'
//                 }
//             }
//         }
//         stage("build image") {
//             steps {
//                 script {
//                     echo "building the docker image..."
//                     withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//                         sh "docker build -t ahmadhaleem/my-repo:${IMAGE_NAME} ."
//                         sh "echo $PASS | docker login -u $USER --password-stdin"
//                         //sh "docker push ahmadhaleem/my-repo:${IMAGE_NAME}"
//                     }
//                 }
//             }
//         }
//         stage("deploy") {
//             steps {
//                 script {
//                     echo 'deploying the application.....'
//                 }
//             }
//         }
        stage("commit version update") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        //sh 'git config --global user.email "jenkins@example.com"'
                        //sh 'git config --global user.name "jenkins"'

                        //sh 'git status'
                        //sh 'git branch'
                        //sh 'git config --list'
                        sh "touch test1.txt"
                        //sh "git remote set-url origin https://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@github.com/${USER}:${PASS}/AhmadHaleeem/java-maven-app.git"
                        sh 'git remote remove origin'
                        sh 'git remote add origin  https://${USER}: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@github.com/${USER}/java-maven-app.git'
                        sh 'git add .'
                        sh 'git commit -m "version bump"'
                        sh 'git push origin HEAD:jenkins-jobs'
                    }
                }
            }
        }
    }   
}