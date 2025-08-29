# DIGG_BENNY
## För att bygga och köra endast Quarkus (och testerna)
```shell script
// Gå till projektroten
// För att säkerställa att inga frontend-filer ligger i src/main/resources/META-INF/resources/
rm -rf src/main/resources/META-INF/resources/assets
rm  src/main/resources/META-INF/resources/favicon.ico
rm  src/main/resources/META-INF/resources/index.html
./mvnw clean install 

// Nu kan du köra appen på http://loccalhost:8080
// Swagger UI finns på http://localhost:8080/q/swagger-ui/
```
## För att bygga och köra frontend
```shell script
// Börja med att bygga och starta Quarkus
// Gå till projektroten
cd frontend/
npm install
npm run build

// Nu kan du köra appen på http://localhost:5173/
```
## För att bygga och köra docker image
```shell script
// Gå till projektroten
./mvnw clean package -DskipTests
cd frontend/
npm install
npm run build
cd ..
cp -r frontend/dist/* src/main/resources/META-INF/resources/
docker build -f ./src/main/docker/Dockerfile.jvm -t digg .
docker run --rm -p 8080:8080 digg

// Nu kan du köra appen på http://loccalhost:8080
```


# code-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
# bennys
