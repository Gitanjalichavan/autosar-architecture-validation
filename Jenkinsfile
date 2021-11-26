pipeline {
    agent any   
    options {
       skipDefaultCheckout true
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
         stage('email-report') {
            steps {
                script{
                    try{
                        def config= '[]'
                        config = readJSON file: "${WORKSPACE}\\mail.json"
                        emailext    attachLog: false,
                                    body: "\nHi Team,\n ${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                                    subject: 'Status of Jenkins Build',
                                     to: "${config.email}"
                    }
                    catch(err){
                         echo 'Check the jenkinslog for the error..!'
                         skipRemainingStages = false
                        echo "next stage skip: = ${skipRemainingStages}"
                }
            }
        }        
    }
        
        stage('clean'){
            steps{
                      cleanWs cleanWhenSuccess: false, notFailBuild: true 
            }
        }
    }
}
  
