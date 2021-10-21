FROM openjdk:17
RUN mkdir /opt/app
COPY bank-srv/target/bank-srv-*.jar /opt/app/bank.jar
ENV THE_APP_JAR opt/app/bank.jar
CMD ["sh", "-c", "java -jar $THE_APP_JAR"]
LABEL "project"="bank"
