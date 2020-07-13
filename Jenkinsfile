@Library(['github.com/indigo-dc/jenkins-pipeline-library@test/credentials-into-docker-compose']) _

def projectConfig

pipeline {
    agent any

    stages {
        stage('SQA baseline dynamic stages') {
            steps {
                script {
                    projectConfig = pipelineConfig('./.sqa/config.yml', null, null, 'eoscsynergy/jpl-validator:jib-with-jpl')
                    buildStages(projectConfig)
                }
            }
            post {
                cleanup {
                    cleanWs()
                }
            }
        }
    }
}
