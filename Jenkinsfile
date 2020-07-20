pipeline {
    agent {
        docker { image 'gradle' }
    }

    stages {
        stage('Publish jpl-validator image') {
            when {
                anyOf {
                    branch 'refs/heads/*'
                    buildingTag()
                    not { changeRequest() }
                }
            }
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'orviz-dockerhub',
                        passwordVariable: 'DOCKER_PASS',
                        usernameVariable: 'DOCKER_USER'
                    )
                ]) {
                    sh './gradlew jib'
                }
            }
        }
    }
}
