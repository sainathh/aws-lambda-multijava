
LOCAL_IP=`ipconfig getifaddr en0`
docker run -it --name java-app --rm \
-p 8081:8080 \
-e REPOSITORY=http://artifactory-toolsd2.dev.stacklynx.com/artifactory/aero-repo \
-e GROUPID=com.acme.mortgage \
-e ARTIFACT=mortgage-api \
-e APP_NAME=mortgage-api \
-e TARGET_PATH=/opt/apps/lib/app.jar \
stackaero/aero-openjdk:1.0.5 /bin/bash


