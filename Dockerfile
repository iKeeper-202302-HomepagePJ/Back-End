FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
  
ENTRYPOINT ["java", "-jar", "/app.jar"]
  #<- jar 파일 실행 명령어
