FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD build/libs/atlas-0.0.1-SNAPSHOT.jar atlas.jar
RUN sh -c 'touch /atlas.jar'
RUN apk --update add curl
EXPOSE 8080
RUN addgroup -S app && adduser -S -g app app
USER app
ENV JAVA_SECURITY="-Djava.security.egd=file:/dev/./urandom"
ENV JAVA_OPTS="-Xmx64m -Xss32m"
HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["sh", "-c", "java $JAVA_SECURITY $JAVA_OPTS -jar atlas.jar"]
