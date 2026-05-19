****************************************
How to run the application
****************************************


To run this project, you need Java 17+ and Maven installed.

You can start the application by opening it in IntelliJ and running the main class VodafoneProjectApplication, or by using the terminal with the command:

mvn clean install
mvn spring-boot:run

After starting, the application will run on:
http://localhost:8080  (configured port on application.properties)



****************************************
How the data is stored and loaded
****************************************

This project uses an H2 in-memory database, so there is no need to install or configure any external database.

The user balance data is automatically inserted when the application starts using the data.sql file under resources directory. 
For example, some users and balances are preloaded like this:

INSERT INTO user_balance (id, balance) VALUES (1, 777.1);
INSERT INTO user_balance (id, balance) VALUES (2, 7777.00);

Spring Boot automatically runs this file on startup and fills the database with initial data.



****************************************
External API information
****************************************

The external API is also implemented inside the same application for simplicity and testing purposes under ExternalUsageController class.

It is exposed at:

GET /usage/{id}

Even though it is part of the same project, it behaves like an external service. The main service calls it using HTTP (RestTemplate) like this:

http://localhost:8080/usage/{id}

This endpoint returns usage information for minutes, SMS, and internet, including how much is spent and the total allowed amount.

****************************************

The main endpoint of the application is:

GET /customers/{id}/account-summary

Example:
http://localhost:8080/customers/1/account-summary

This endpoint combines:
user balance from the database usage data from the external API and returns a single aggregated response.
