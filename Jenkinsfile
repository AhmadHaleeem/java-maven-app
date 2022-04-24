def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.0.0', '1.2.0', '1.3.0'], description: '')
        boolean(name: 'executeTests', defaultValue: true, description: '')
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build") {
            steps {
                script {
                    //echo "building...."
                    gv.buildApp()
                    //gv.buildJar()
                }
            }
        }
        stage("test") {
            when {
                expression {
                    params.defaultValue
                }
            }
            steps {
                script {
                    //echo "Testing..."
                    gv.testApp()
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    //echo "deploying..."
                   
                    gv.deployApp()
                }
            }
        }
    }   
}
