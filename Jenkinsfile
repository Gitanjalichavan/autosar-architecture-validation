pipeline {
    agent any
    environment {
        def config = readJSON file: 'mail.json'
//          a = "${mail.email}"
    }
    stages {
        stage('rte-generation') {
            steps {
                script{
                   echo 'rte-generation..'
                    echo "${env.config.email}"
                }
            }
        }
        stage('build') {
            steps {
                script{
                   echo 'Pipeline script implementation..'
                }
            }
        }        
    }
  
        post {
    
         always {

                                emailext    attachLog: false,
                                    body: "\nHi Team,\n ${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                                    subject: 'Status of Jenkins Build',
                                     to: "${env.config.email}"
                         
                               cleanWs cleanWhenSuccess: false, notFailBuild: true
                                   
                     
                                }  
    
                            }
}
