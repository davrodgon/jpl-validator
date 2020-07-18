@Library(['github.com/indigo-dc/jenkins-pipeline-library@feature/triggered-branch-only']) _

def projectConfig

pipeline {
    agent any

    stages {
        stage('SQA baseline dynamic stages') {
            when {
                anyOf {
                    branch 'master'
                    branch 'jib-with-jpl'
                    buildingTag()
                    changeRequest branch: 'triggered-branch-only'
                }
            }
            steps {
                script {
                    projectConfig = pipelineConfig('./.sqa/config.yml', null, null, 'worsica/jpl-validator:triggered-branch-only')
                    //projectConfig = pipelineConfig('./.sqa/config.yml')
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
