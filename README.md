# ENMU Spring CS472 Semester Project - Group 3

This project was created as part of a semester long project for ENMU CS472 where we were given instructions to create a basic Hotel Reservation System in order to allow for customers to make and manage their own reservations, as well as allow for Hotel Staff to perform administrative duties for the site.

## Description

This project is created within the Java Framework and specifically developed to be based off of Jakarta EE. It is backed up by a MySQL database to handle data, and it is run off of Tomcat as a web front end for the site. The site allows for two different login sites for Admins as well as for Customers. Customers are granted access to view their own reservations and account information, and to make new reservations as well as pay for them through the site. Admins are granted access to an administrative portal where they are able to view and modify customer reservations, as well as modify, disable, and add new rooms to the site for customers to be able to browse.

## Getting Started

### Dependencies

* Java 20+ as well as Java EE SDK 8.0 or higher.
* Tomcat 9.0 (10 will not work)
* MySQL Community Version
* Ant
* Send Grid

### Installing

* Install required depencies as listed above.
* Clone the repository here to your local machine.
* Navigate to the directory that the project was cloned to.
* Run the database_init.sql script within your MySQL instace.
* Manually create an account in the Admin table in order to be able to log into that side, as customer account creation has a form on the site.
* Modify the settings.json in src/main/resources to include the username, password, and host that you have configured for your MySQL instance.
* Modify the apisettings.json in src/main/resources to include the username and API Key from Send Grid for email confirmations.
* Run the below commands:
```
ant clean
ant build
```
* Copy the HotelReservationApp.war file from the directory to the "webapps" folder for your tomcat installation.
* Run the shutdown.bat (or .sh) and the startup.bat (or .sh) from the tomcat/bin directory in order to restart the Tomcat server.
* Browse to the host that you have tomcat installed on with the URI belonging to :8080/HotelReservationApp and ensure that the website is now available.

### Executing Program

* All facets of the program should be available through the GUI, and are fairly self explanatory.
* You have the ability to create a user, log in as that user, make reservations, modify reservations, as well as cancel reservations. As an admin you are able to modify customer reservations, as well as make changes to hotel room information and availability.

## Help

* Should you receive errors relating to accounts not found even though your email and password is correct, look to ensure that you have validated the DB connection information in the settings.json mentioned above.
* Should you feel that certain facets of the code are not working appropriately, JUnit and JaCoCo are included in the project. New Tests to be added can be added in the test/ folder, and should be added to the build.xml file, and then you can run the below codes to run the tests, as well as to create JaCoCo coverage reports:
```
ant test
ant report
```

## Authors

* Joshua Espana
* Noah Gumm
* Zachary Marrs

## Version History

* Final Implementation
    * Finished out User Stories and validated the functionality of the core features, ensured all databases changes are up to date.

* 1.d4
    * Expanded checkout functionality. Accidentally reverted some changes on database connectivity.

* Demo 1.d3
    * Added admin pages and functionality. Admins can view reservations and rooms along with make modifications or delete said rooms and reservations. User checkout process has been improved upon and partial functionality has been implemented.

* Initial Release
    * Includes basic views, controllers, styles, the user model, and database connectivity. Only functionality at this point is user log in and registration. Registration needs some work.

## Project Structure

* lib
    * Includes all of the required .jar files for the functionality of the site, along with the required JUnit and JaCoCo jars for Unit and Coverage Testing.

* src/main
    * Includes the below sub folders for most of the actual .java code files.
        * java/com/hotelreservationapp
            * controllers
                * Includes the different Controllers for different functionalities, handles the incoming HTTPServlets from the Views, as well as the database connections to pull and update information in the database.
            * models
                * Database
                    * Prepared
                        * Functionality for certain features that require cross table information, as well as specific functionality not tied to a specific table.
                    * Tables
                        * Models for specific functionality, ties in with the Controllers for providing information that needs to be displayed.
                * DatabaseLogic
                    * This includes files for the actual connection and statement information for the different tables, and the different commands that need to be run against the database to set model information.
                * Settings
                    * Models for handling the connection to the API as well as the MySQL database, creates readers and sets the database connection information.
        * resources
            * Includes the settings JSON files for the API as well as the Database Connection information.
        * webapp
            * Includes all of the resources needed for the Views as well as the configuration information for the different styles for the sites.

* target
    * This includes the compiled .class files, as well as the JUnit and JaCoCo output files.

* database_init.sql
    * This is the sql script necessary to create the required tables for the database, as well as preload it with certain room information.

* build.xml
    * This is the ant build script which handles the compilation and .war creation, also has JUnit and JaCoCo testing options.
