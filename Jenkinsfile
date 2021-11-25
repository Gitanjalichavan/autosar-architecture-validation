pipeline {
    agent any

    stages {
        stage('Ok') {
            steps {
                echo "Ok"
                 
            }
        }
    }
  
        post {
    
         always {

                                emailext    attachLog: true,
                                    body: "\nHi Team,\n ${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                                    subject: 'Status of Jenkins Build',
                                     to: '${DEFAULT_RECIPIENTS}'
                                   
                     
                                }  
    
                            }
}
