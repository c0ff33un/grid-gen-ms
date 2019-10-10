FROM openjdk:jre-alpine
LABEL maintainer=root
WORKDIR /app
COPY libs libs/
COPY resources resources/
COPY classes classes/
ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "gridgenerationms.game.GridGenerationApplicationKt"]
EXPOSE 8080
