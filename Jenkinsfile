@Library(['github.com/indigo-dc/jenkins-pipeline-library@release/2.1.0']) _

def projectConfig

pipeline {
    agent any

    stages {
        stage('SQA baseline dynamic stages: qc-style') {
            steps {
                script {
                    projectConfig = pipelineConfig('./.sqa/config_qc_style.yml', null, null, null, 'eoscsynergy/jpl-validator:jib-with-jpl')
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
        // stage('SQA baseline dynamic stages: qc-doc') {
        //     when {
        //         anyOf {
        //             branch 'refs/heads/*'
        //             buildingTag()
        //             not { changeRequest() }
        //         }
        //     }
        //     steps {
        //         script {
        //             projectConfig = pipelineConfig('./.sqa/config_qc_doc.yml', null, null, null, 'eoscsynergy/jpl-validator:jib-with-jpl')
        //             //projectConfig = pipelineConfig('./.sqa/config.yml')
        //             buildStages(projectConfig)
        //         }
        //     }
        //     post {
        //         cleanup {
        //             cleanWs()
        //         }
        //     }
        // }
    }
}
