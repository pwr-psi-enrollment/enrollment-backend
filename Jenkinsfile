pipeline {

  agent {
    kubernetes {
      label 'enrollment-backend'
      idleMinutes 5
      yamlFile '.jenkins-config/maven36-pod.yaml'
      defaultContainer 'maven36'
    }
  }

  stages {

    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }


  }
}
