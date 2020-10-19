node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/HarshiniKoduru/Real-Estate-Property.git', credentialsId: 'harshi17', branch: 'main'        }
        stage('Build') {
         
			bat 'mvn clean install'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }
        
           

    }

	}

