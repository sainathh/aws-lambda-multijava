pipeline {
    agent any
     tools {
        maven 'Maven363'
     }
     stages{
        stage("clone") {
            steps {
                git branch: 'develop', url: 'https://github.com/sainathh/aws-lambda-multijava.git'
            }
        }
        stage("Build and Zip") {
            steps {
             sh '''
			    REGION="us-east-1"
                find ./ -name pom.xml | rev | cut -d/ -f2- | rev > location.txt
                echo "Zip POM File Location!"
                WORK_DIR=`pwd`
                echo $WORK_DIR
                while read p; do
                  echo "Zip File Path: $p"
                  export v=`echo "$p" | awk -F/ '{print $NF}'`
                  echo "Zip File Name: $v"
                  echo "Checkout to Lambda Path: $p"
                  cd $p
                  pwd
                  mvn clean package
                  cd ./target/
                  zip  "$v.zip"  *.*
				  lambda=`aws lambda list-functions  --query 'Functions[].FunctionName' | grep $v | wc -l`
				  echo $lambda
				  
				  if [[ `aws lambda list-functions  --query 'Functions[].FunctionName' | grep $v | wc -l` -ne 0 ]]
					then
						echo "Lambda Function Available"
						aws lambda update-function-code --function-name $v 	--zip-file fileb://$v.zip
						echo "Publish Lambda Function Version"
						version=`aws lambda publish-version --function-name $v --query "Version" | xargs`
						echo "Version to Create Alias:`aws lambda publish-version --function-name $v --query "Version" | xargs`"
						if [[ `aws lambda get-alias --function-name $v --name $alias | wc -l` == 0 ]]
						then
							echo "Alias Not Found for $v Creating Alias with name: $alias"
							aws lambda create-alias --function-name $v \
							 --name $alias --function-version $version 
						else
							echo "Alias found for $v with name: $alias"
							echo "Updating Alias of $alias with version $version"
							aws lambda update-alias --function-name $v --name $alias --function-version $version
						fi
					else
						echo "No Lambda Function Available of name: $v"
						echo " Create Lambda: $v"
						aws lambda create-function --function-name $v --zip-file fileb://$v.zip	\
						--handler package.Class::method  --runtime java11 \
						--role arn:aws:iam::872154951115:role/admin_role
						version=`aws lambda publish-version --function-name $v --query "Version" | xargs`
						echo "Create Alias for Lambda Function of $v"
						aws lambda create-alias --function-name $v \
								 --name $alias --function-version $version
					fi
					
                  mv $v.zip $WORK_DIR
                  echo "Checking Out to Root Directory"
                  cd $WORK_DIR
                done <location.txt
             '''
            }
        }
        /**stage("lambda creation"){
            steps {
            sh '''
            bash lambda-publish.sh $alias
            '''
            }
        }**/
     }
}
