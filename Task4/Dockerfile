FROM gradle:5.4.1-jdk8

ENV APP_HOME=/home/goodline/spring

RUN mkdir -p $APP_HOME
COPY . $APP_HOME
WORKDIR $APP_HOME

USER root
RUN chown -R gradle $APP_HOME
USER gradle
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/task4-0.0.1.jar"]