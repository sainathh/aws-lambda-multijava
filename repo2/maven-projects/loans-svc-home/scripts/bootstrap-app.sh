#!/bin/bash 
  

if [ ! -z "${DB_HOST}" ] && [ ! -z "${DB_NAME}" ]; then

  mkdir -p $TOMCAT_HOME/lib/$ARTIFACT


cat <<EOF > $TOMCAT_HOME/lib/$ARTIFACT/application.properties
spring.datasource.url=jdbc:mysql://${DB_HOST}:3306/${DB_NAME}
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
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

fi

