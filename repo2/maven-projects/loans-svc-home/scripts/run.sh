
LOCAL_IP=`ipconfig getifaddr en0`
docker run --rm -it \
-w="/code" \
-p 8080:8080 \
-e DB_URL=jdbc:mysql://$LOCAL_IP:3306/mortgage_db \
-e DB_USERNAME=root \
-e DB_PASSWORD=r00tP@ss \
-v `pwd`:/code \
mortgage-api:latest 
