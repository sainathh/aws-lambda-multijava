find . -name '*.zip'| cut -d/ -f2- | cut -d . -f-1 > function-name.txt
echo function-name.txt
REGION="us-east-1"
while read p; do
	echo "Function Name: $p"
	lambda=`aws lambda list-functions  --query 'Functions[].FunctionName' | grep $p | wc -l`
	echo $lambda
	if [[ `aws lambda list-functions  --query 'Functions[].FunctionName' | grep $p | wc -l` -ne 0 ]]
	then
		echo "Lambda Function Available"
		aws lambda update-function-code --function-name $p 	--zip-file fileb://$p.zip
		echo "Publish Lambda Function Version"
		version=`aws lambda publish-version --function-name $p --query "Version" | xargs`
		echo "Version to Create Alias:`aws lambda publish-version --function-name $p --query "Version" | xargs`"
		if [[ `aws lambda get-alias --function-name $p --name $1 | wc -l` == 0 ]]
		then
			echo "Alias Not Found for $p Creating Alias with name: $1"
			aws lambda create-alias --function-name $p \
			 --name $1 --function-version $version 
		else
			echo "Alias found for $p with name: $1"
			echo "Updating Alias of $1 with version $version"
			aws lambda update-alias --function-name $p --name $1 --function-version $version
		fi
	else
		echo "No Lambda Function Available of name: $p"
		echo " Create Lambda: $p"
		aws lambda create-function --function-name $p --zip-file fileb://$p.zip	\
		--handler package.Class::method  --runtime java11 \
		--role arn:aws:iam::872154951115:role/lambda
		version=`aws lambda publish-version --function-name $p --query "Version" | xargs`
		echo "Create Alias for $p"
		aws lambda create-alias --function-name $p \
                 --name $1 --function-version $version
	fi
done <function-name.txt
