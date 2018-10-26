pipeline
{
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', 
                          branches: [[name: '*/master']], 
                          doGenerateSubmoduleConfigurations: false, 
                          extensions: [[$class: 'CleanBeforeCheckout'], 
                                       [$class: 'LocalBranch', 
                                       localBranch: 'master']], 
                          submoduleCfg: [], 
                          userRemoteConfigs: [[credentialsId: '29a4da57-5c5e-493c-8890-5f39d82f6f71', 
                                               url: 'https://github.com/atulaphale/printer.git']]])
            }
        }
        
        stage('Build'){
            steps{
                sh 'mvn clean install'   
            }
        }
    }
}