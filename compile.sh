./gradlew clean assemble &&
rm -rf /opt/tomcat/webapps/br.com.task.api &&
rm -r /opt/tomcat/webapps/br.com.task.api.war &&
cp build/libs/*.war /opt/tomcat/webapps
sudo -u postgres /usr/lib/postgresql/9.6/bin/./psql fluo < '/home/admin/task/task-api/src/main/java/br/com/task/dao/script.sql'