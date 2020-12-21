# films-backend

This project was make in Java 8, Spring boot 2.4.1 and Maven.

##Setup MySQL
Run the `schema.sql` for the initial script on the DB.

The administrator user is `admin` and the password is `admin`

In th file `src/main/resources/application.properties`

change the next proterties by your sql credencial

`spring.datasource.url=`

`spring.datasource.username=`

`spring.datasource.password=`


## Running

Run `mvn clean install -DskipTests=true`.

Run `java -jar target/films-0.0.1-SNAPSHOT.jar`.

## Heroku Url

`https://cartrrz-films.herokuapp.com/api/auth/signup`
