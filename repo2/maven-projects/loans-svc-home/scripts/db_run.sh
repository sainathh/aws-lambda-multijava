
LOCAL_IP=`ipconfig getifaddr en0`
docker run -d --name mortgage-db \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=r00tP@ss \
mysql:latest 
