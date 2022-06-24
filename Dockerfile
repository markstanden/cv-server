FROM gradle AS build

# Gets the latest commit information for the main branch.  Effectively cache busts if latest commit is different from cached version.
ADD https://api.github.com/repos/markstanden/cv-server/git/refs/heads/main version.json
RUN git clone -b main https://github.com/markstanden/cv-server.git /app

WORKDIR /app
RUN ./gradlew --no-daemon shadowJar

FROM openjdk AS prod
EXPOSE 8080

COPY --from=build /app/build/libs/shadow.jar /app/

WORKDIR /app
ENTRYPOINT ["java","-jar","shadow.jar"]