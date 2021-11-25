pipeline {
    agent any
    environment {
        def config = readJSON file: 'mail.json'
//         baseUrl = "${mail.email}"
    }
    stages {
        stage('rte-generation') {
            steps {
                script{
                   echo 'rte-generation..'
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
                                     to: "${mail.email}"
                         
                               cleanWs cleanWhenSuccess: false, notFailBuild: true
                                   
                     
                                }  
    
                            }
}
