
LOCAL_IP=`ipconfig getifaddr en0`
echo LOCAL_IP:$LOCAL_IP
docker run --rm -it \
-p 8080:8080 \
-e DB_URL=jdbc:mysql://$LOCAL_IP:3306/mortgage_db \
-e DB_USERNAME=root \
-e DB_PASSWORD=r00tP@ss \
-e REPOSITORY=http://artifactory-standalone-repodev5.corp.stacklynx.com/artifactory/aero-repo \
-e GROUPID=com.acme.mortgage \
-e ARTIFACT=mortgage-api \
stackaero/aero-tomcat:1.0.0
