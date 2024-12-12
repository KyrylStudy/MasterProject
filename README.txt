To work with the Architecture Platform for Designing Secure Automotive Systems:


1. Build and run docker containers(run in command line in the "Docker" directory):


docker compose up --build --force-recreate


2. After first run you have to create an Architekture. Only after that you can create elements and connect it.


Note:   1. You can't connect ECU elements directly to each other. ECU must first be connected to the network and then to another ECU.

	2. Before to create Data Streams in the ECUs you need to connect these ECUs to the Network.




------------------------------------------------------------------------------------------------------------------------


To run the Architecture Platform for Designing Secure Automotive Systems in development mode:

1. Install the development environment:

	a) Visual Studio for "frontend"
	b) Spring Tool Suit for "backend"
	c) MySQL Workbench for database 

2. Im MySQL Workbench create database with the name "autotech_agil". Set passwort "root". Set host localhost:3306.
3. In file backend/src/main/resources/application.properties you see spring.datasource.url=jdbc:mysql://db:3306/autotech_agil?useSSL=false&allowPublicKeyRetrieval=true

db in the link is to run the Project in Docker.

change spring.datasource.url to jdbc:mysql://localhost:3306/autotech_agil?useSSL=false&allowPublicKeyRetrieval=true

4. Download the Project.
5. Open the MySQL Workbench and run autotech_agil database.
5. Onen Spring Tool Suit and run "backend".
6. Open Visual Studio and run "frontend".
	a) run npm install
	b) run npm start



