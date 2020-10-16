node {
    
    stage('git url'){
    git credentialsId: 'bitbucket', url: 'https://stacklynx1@bitbucket.org/stacklynx1/crud-app.git'  
    }
    
    stage(" Maven Clean Package"){
      def mavenHome =  tool name: "MAVEN_HOME", type: "maven"
      def mavenCMD = "${mavenHome}/bin/mvn"
      sh "${mavenCMD} clean package"

    }    
    
}