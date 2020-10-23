#!/bin/bash
# downloads latest version of an artifact from artifactory
set -e
usage(){
echo "Usage: $*" >&2
exit 64
}

echo "running as user":`id`
REPOSITORY={{REPOSITORY_URL}}
GROUPID={{GROUPID}}
ARTIFACT={{ARTIFACT}}
APP_CONTEXT={{APP_NAME}}

#shift $(( $OPTIND - 1 ))
if [ -z "${REPOSITORY}" ] || [ -z "${GROUPID}" ] || [ -z "${ARTIFACT}" ]; then
    usage "-r REPOSITORY -g GROUPID -a GROUPID [-c ARTIFACT]"
fi

# Maven artifact location
ga=${GROUPID//./\/}/$ARTIFACT
repopath=$REPOSITORY/$ga
#Get the latest version from the root metadata file and get latest version
version=`curl -s $repopath/maven-metadata.xml | grep latest | sed "s/.*<latest>\([^<]*\)<\/latest>.*/\1/"`
#get the metadata file from the latest version file and find the latest build
build=`curl -s $repopath/$version/maven-metadata.xml | grep '<value>' | head -1 | sed "s/.*<value>\([^<]*\)<\/value>.*/\1/"`
#get the metadata file from the latest version file and find the latest extension
extension=`curl -s $repopath/$version/maven-metadata.xml | grep '<extension>' | grep -v pom | head -1 | sed "s/.*<extension>\([^<]*\)<\/extension>.*/\1/"`

if [ -z "${CLASSIFIER}" ]; then
  artifactFile=$ARTIFACT-$build.$extension
else
  artifactFile=$ARTIFACT-$build-$CLASSIFIER.$extension
fi

url=$repopath/$version/$artifactFile

APP_CONTEXT=${APP_CONTEXT:-$ARTIFACT}
WEB_APPS_FOLDER=${WEB_APPS_FOLDER:-/usr/share/tomcat/webapps}



cat <<EOF > /tmp/application.properties
spring.datasource.url=jdbc:mysql://{{DB_HOST}}:3306/{{DB_NAME}}
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username={{DB_USER}}
spring.datasource.password={{DB_PASSWORD}}
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=gram.bachu@gmail.com
spring.mail.password=thospbifoldzqpkj
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000

EOF

chmod 777 /tmp/application.properties
echo "mkdir -p $WEB_APPS_FOLDER/../lib/$APP_CONTEXT" > /tmp/deploywar.sh
echo "cp /tmp/application.properties $WEB_APPS_FOLDER/../lib/$APP_CONTEXT/application.properties" >> /tmp/deploywar.sh
echo "curl -o $WEB_APPS_FOLDER/$APP_CONTEXT.$extension $url" >> /tmp/deploywar.sh
chmod 755 /tmp/deploywar.sh
cat /tmp/deploywar.sh

sudo su - tomcat -c '/tmp/deploywar.sh'
rm /tmp/application.properties