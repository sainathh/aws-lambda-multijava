
LOCAL_IP=`ipconfig getifaddr en0`
echo LOCAL_IP:$LOCAL_IP
docker run --rm -it --name mortgage-db \
-p 3306:3306 \
-e ALLOW_EMPTY_PASSWORD=yes \
-e MYSQL_USER=appuser \
-e MYSQL_PASSWORD=fgK0guTEWp \
-e MYSQL_DATABASE=mortgage-db \
bitnami/mysql:latest
