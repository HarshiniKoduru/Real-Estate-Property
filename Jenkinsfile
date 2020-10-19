node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/HarshiniKoduru/microservice-jenkinsn.git', credentialsId: 'harshi17', branch: 'main'        }
        stage('Build') {
         
			bat 'mvn clean install'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }
        
           

    }

	}
pipeline {
    agent any
    
	stages {
        stage('Checkout') {
           steps { 
				git url: 'https://github.com/HarshiniKoduru/microservice-jenkinsn.git', credentialsId: 'harshi17', branch: 'main'
		   }
        }

        stage('Build') {
			steps {
				bat 'mvn clean install'
				
				}
			}       
		}
	}
