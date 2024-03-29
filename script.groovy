def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ahmadhaleem/my-repo:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push ahmadhaleem/my-repo:jma-2.0'
    }
} 

def buildApp() {
    echo 'building the application...'
}

def testApp() {
    echo 'testing the application...'
} 

def deployApp() {
    //echo "deploying version ${params.VERSION}"
    echo 'deploying the application...'
}

return this
