pipeline {
    agent any
     tools {
        maven 'Maven363'
     }
    stages{
        stage("clone") {
            steps {
                git ' https://github.com/sainathh/loans-svc-home.git '
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean package"
            }
        }
    }
}
