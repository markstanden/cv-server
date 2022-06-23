FROM gradle AS build

# Gets the latest commit information for the main branch.  Effectively cache busts if latest commit is different from cached version.
ADD https://api.github.com/repos/markstanden/markstanden-dev/git/refs/heads/main version.json
RUN git clone -b main https://github.com/markstanden/markstanden-dev.git /app

WORKDIR /app
RUN ./gradlew build --no-daemon

FROM openjdk AS prod
EXPOSE 8080:8080

COPY --from=build /app/build/libs/*.jar /app/build/lib/
COPY --from=build /app/build/scriptsShadow /app/build/scriptsShadow
COPY --from=build /app/static /app/static

WORKDIR /app
ENTRYPOINT ["./build/scriptsShadow/cv-server"]