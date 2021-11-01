Enterprise Systems Development - UFCF85-30-3
GROUP COURSEWORK ASSIGNMENT

We were required to design and implement a web-based software system using the 
features and functionality  using JSP/Servlets using the MVC design pattern on 
Java Enterprise Edition (EE), and deploy to a server with a local Java DB Relational database backend.


Clone the github repo
git clone https://github.com/miattey/rider-webapp.git
	
Open the project in intellij IDE and configure the glassfish server configurations
https://www.jetbrains.com/help/idea/creating-run-debug-configuration-for-application-server.html

Make sure apache derby is intalled, and create a database named 'rider-webapp'

Start the networkserver in the apache derby bin directory

Configure the apache derby database setup using the following steps
In the Database tool window (View | Tool Windows | Database), click the Data Source Properties icon The Data Source Properties icon.

On the Data Sources tab in the Data Sources and Drivers dialog, click the Add icon (The Add icon) and select Apache Derby.

From the Driver list, select the type of the JDBC driver that you want to use. For remote connections, select Apache Derby (Remote). 

At the bottom of the data source settings area, click the Download missing driver files link. As you click this link, IntelliJ IDEA downloads drivers that are required to interact with a database. The IDE does not include bundled drivers in order to have a smaller size of the installation package and to keep driver versions up-to-date for each IDE version.

Specify database connection details. Alternatively, paste the JDBC URL in the URL field.
jdbc:derby://localhost:1527/rider-webapp

To ensure that the connection to the data source is successful, click the Test Connection link.


Now that the database has been created, you can populate the database tables using the database_table.sql file in the repo


After that Build and run the project
