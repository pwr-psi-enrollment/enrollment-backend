pipeline {

  agent {
    kubernetes {
      label 'enrollment-backend'
      idleMinutes 5
      yamlFile '.jenkins-config/python38-pod.yaml'
      defaultContainer 'maven36'
    }
  }

  stages {

    stage('Build') {
      steps {
        sh 'mvn package'
      }
    }


  }
}
