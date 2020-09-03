The whole project `best-commerce` is made of 3 microservices: 

- product listening module
- sign up module
- sign in module

-------------------------------------------------------------------------

**1. Run project**

*To run everything up type `docker-compose build && docker-compose up`*.

Make sure that you are in parent folder (for example `cd ~/Desktop/best-commerce`)

-------------------------------------------------------------------------


**2. Requests**

After running everything up there is possibility to:

- **register new user**:

`curl -X POST "http://localhost:8080/sign-up/merchant" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"address\": \"mail3@mail.pl\",  \"email\": \"mail3@mail.pl\",  \"name\": \"mail3@mail.pl\",  \"ownerName\": \"mail3@mail.pl\",  \"password\": \"string\",  \"phoneNumber\": \"string\",  \"type\": \"string\"}"`

- **login as this user**:

`curl -v --location --request POST 'http://localhost:8081/login' --header 'Content-Type: application/json' --data-raw '{"login": "mail3@mail.pl","password": "string","rememberMe": "true"}'`

*After login request there is returned bearer token*

- **paginate over products**
`curl --location --request GET 'http://localhost:8082/products?rows=1&page=3&orderProperty=description&sortDirection=desc' \
 --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWlsMkBtYWlsLnBsIiwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJpYXQiOjE1OTkxNDIzNTgsImV4cCI6MTU5OTc0NzE1OH0.-TL2XjWGSU2hNCq7Nd4p_klfBfapWF-TW8hlw5LhW1I56Fp7jFhrDcdF42BbROJwPDnEZAXaG2AwrSZL5DbjQg'`
 
*It is necessary to paste the correct bearer token to `Authorization:` value*

-------------------------------------------------------------------------
**3. About modules**
 - product listening module:

*The entity class structure is as simple as its necessary. 
Instead of creating tables from custom objects enums were chosen.
There is only one endpoint and it consumes query params needed to paginate properly.
It is required to have correct jwt token in header to send requests to `/products` endpoint.
The swagger documentation is available at `http://localhost:8082/swagger-ui.html` an it doesnt need authorization*

 - sign in module:
 
*This module allows user to log in. User gets token after logging up and can send requests to Product listening*

- sign up module:

*This module also has only one endpoint and it consumes MetchantDTO object. 
It saves object to database and puts notification on queue (activeMq). 
Module generates JWT token, time to live depends on rememberMeFlag*
The swagger documentation is available at `http://localhost:8082/swagger-ui.html



-------------------------------------------------------------------------
**4. Decisions made, and field for improvements**
- password is encrypted by default mechanism taken from spirng security (bcrypt)
- new password validation is implemented solely in sign-up component
- session is stateless based on JWT token only
- logging is configured at info level and it outputs logs both on standard output and to file
- database (H2 database) runs in memory, so it is not persistent between restarts
- sorting functionality was implemented by all of the fields, not only by two of them
- no retry or compensation mechanisms were implented around asynchrounous communication
- no CI/CD were implemented due to lack of time
- because of the fact that this is just a MVP, no sophisticated exception handling were implemented
-------------------------------------------------------------------------
