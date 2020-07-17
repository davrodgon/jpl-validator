@Library(['github.com/indigo-dc/jenkins-pipeline-library@release/2.1.0']) _

def projectConfig

pipeline {
    agent any

    stages {
        stage('SQA baseline dynamic stages') {
            when {
                anyOf {
                    branch 'master'
                    buildingTag()
                }
            }
            steps {
                script {
                    //projectConfig = pipelineConfig('./.sqa/config.yml', null, null, 'eoscsynergy/jpl-validator:jib-with-jpl')
                    projectConfig = pipelineConfig('./.sqa/config.yml')
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
