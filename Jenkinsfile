pipeline {
    agent any

    environment {
        WAR_SOURCE = 'java-web-app/target/java-web-app-1.0-SNAPSHOT.war'
        WAR_DEST = 'java-web-app/ROOT.war'
        DOCKER_IMAGE = 'java-web-app'
        DOCKER_BUILD_DIR = 'java-web-app'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'ls -la java-web-app' // Debug: confirm pom.xml is present
            }
        }

        stage('Build WAR') {
            steps {
                dir('java-web-app') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Prepare WAR for Docker') {
            steps {
                sh "cp ${WAR_SOURCE} ${WAR_DEST}"
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${DOCKER_BUILD_DIR}") {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d -p 8081:8080 ${DOCKER_IMAGE}"
            }
        }

        stage('Docker Push') {
            when {
                expression { return currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                echo 'Docker image ready to be pushed.'
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
