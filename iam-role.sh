if [[ `aws iam get-role --role-name lambda --query 'Role.RoleName' | tr -d '"'` =  lambda ]]
then
     echo "lambda role is avilable"
else
     echo "lambda role is not avilable"
     echo "lambda role is creating"
     aws iam create-role --role-name lambda --assume-role-policy-document file://iam-policy.json
     aws iam attach-role-policy --policy-arn arn:aws:iam::aws:policy/AdministratorAccess --role-name lambda
     sleep 30s
fi
