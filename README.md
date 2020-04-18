# spring-oauth2-1744-mcve

client folder contains a Spring Boot web application acting as an OAuth2 client using the Authorization Server implemented in bad-auth-server folder.

Change the `working` flag in Authorization Server `MainController` class to see that if the token expiration time is correct the example works fine.
