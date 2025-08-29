# DIGG_BENNY
## För att bygga och köra endast Quarkus (och testerna)
```shell script
// Gå till projektroten
// För att säkerställa att inga frontend-filer ligger i src/main/resources/META-INF/resources/
rm -rf src/main/resources/META-INF/resources/assets
rm  src/main/resources/META-INF/resources/favicon.ico
rm  src/main/resources/META-INF/resources/index.html
./mvnw clean install 
./mvnw quarkus:dev

// Nu kan du köra quarkus-delen av appen på http://localhost:8080
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
## Om du vill köra hela appen på port 8080
```shell script
// Gå till projektroten
./mvnw clean install
cd frontend/
npm install
npm run build
cd ..
cp -r frontend/dist/* src/main/resources/META-INF/resources/
./mvnw quarkus:dev

// Nu kan du köra appen på http://localhost:8080
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

// Nu kan du köra appen på http://localhost:8080
```
## Om du vill ladda upp en docker-image i form av .tar och köra i docker
```shell script
// Gå till katalogen där du har tar-filen (digg.tar)
docker load -i digg.tar
docker run --rm -p 8080:8080 digg:latest

// Sen är det bara att köra appen på http://localhost:8080
```