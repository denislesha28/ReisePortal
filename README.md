#Overview 
This is a Spring Boot backend application that aims to implement a news portal.
Authors can publish articles and they can get paid every month according to the clicks 
they generate. A statistics service running in the background takes care of 
collecting clicks and ranking articels in the main page according to popularity.
#Techstack
This application utilizes Spring Boot 2.5.7 and microservices in order to implement 
efficient scalability and flexibility. This application utilizes a Spring Registry 
where all the microservices are registered and a Gateway where all the microservices 
can be accessed from. Spring JPA with Hibernate is used as Object Mapper in order to 
manage database entries.