./gradlew clean assemble &&
rm -rf /opt/tomcat/webapps/br.com.task.api &&
rm -r /opt/tomcat/webapps/br.com.task.api.war &&
cp br.com.task.api/build/libs/*.war /opt/tomcat/webapps