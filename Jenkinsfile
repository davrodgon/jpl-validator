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
		    branch 'triggered-branch-only'
                }
            }
            steps {
                script {
                    projectConfig = pipelineConfig('./.sqa/config.yml', null, null, 'eoscsynergy/jpl-validator:feature/triggered-branch-only')
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
