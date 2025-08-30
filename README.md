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
Skapande av dockerfil som bygger koden och skapar själva imagen orsakade problem som jag inte lyckades lösa inom tidsramen. Men 
så här skapade och körde jag en docker-image som fungerar fint.
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
## Curl-kommandon
```shell script
// För att lista alla kunder:
curl -X 'GET' 'http://localhost:8080/digg/user?page=0&size=20' -H 'accept: application/json'
// För att lägga till en ny kund:
curl -X 'POST' 'http://localhost:8080/digg/user' -H 'accept: */*' -H 'Content-Type: application/json' -d '{
  "name": "Udo Dirkschneider",
  "address": "Wegnerstraße 7, 42275 Wuppertal, Deutschland",
  "email": "udo@dirkschneider.de",
  "telephone": "+49 2025 631"
}'
// För att ta bort alla kunder:
curl -X 'DELETE' 'http://localhost:8080/digg/user/all' -H 'accept: */*'
// För att fylla på med kunder igen:
curl -X 'POST' 'http://localhost:8080/digg/user/seed' 
```
## Loggning
Loggning sker i consolen och loggfiler hamnar i katalogen /logs