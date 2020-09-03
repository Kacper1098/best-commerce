The whole project `best-commerce` is made of 3 microservices: 

- product listening module
- sign up module
- sign in module

-------------------------------------------------------------------------
**1. Run project**

*To run everything up type `docker-compose build && docker-compose up`*.

Make sure that you are in parent folder (for example `cd ~/Desktop/best-commerce`)

-------------------------------------------------------------------------
**2. About modules**
 - product listening module:

*The entity class structure is as simple as its necessary. 
Instead of creating tables from custom objects enums were chosen.
There is only one endpoint and it consumes query params needed to paginate properly
The swagger documentation is available at `http://localhost:8082/swagger-ui.html`*

 - sign in module:

*This module also has only one endpoint and it consumes MetchantDTO object. 
It saves object to database and puts notification on queue (activeMq). 
Module generates JWT token, time to live depends on rememberMeFlag*

- sign up module

**3. Decisions made, and field for improvements**
- password is encrypted by default mechanism taken from spirng security (bcrypt)
- new password validation is implemented solely in sign-up component
- session is stateless based on JWT token only
- logging is configured at info level and it outputs logs both on standard output and to file
- database (H2 database) runs in memory, so it is not persistent between restarts
- no retry or compensation mechanisms were implented around asynchrounous communication
- no CI/CD were implemented due to lack of time