# SWEN-440:  Project 2
Fall 2018

- Bryan Quinn
- Ryan Connors
- Peter Howard
- Ian Taylor

## Online Ordering System

### Overview:

The system consists of three main components:  a command line order 
entry module, a central processing module and a data storage module. 
 
 - The order entry module handles all communication with the user
 - The data storage module handles all communication with the underlying 
   file system.  
 - The central processing system coordinates the activities across 
   the system taking requests from the order entry module and
   interacting with the data storage layer to fulfill the request.
   
## Architecture
Model-View-Controller with the use of Repositories acting as the middle man between the Data Layer
and their respective controllers. The Repositories read/write/delete/update the models to the
database. 


### Dependencies
 - Java 8 
 - Lombok Library: Lombok annotations are used in places to auto-generate getters and
 setters.  
 - JUnit Library:  For the unit tests.
 - MySQL 8: Used for the database

### Building
The development team choose Maven as their build tool.  Maven 3.1.0 and 3.3.9 and 3.5.2
have both been successfully used.  

With Maven installed, running **mvn clean install** from the root install directory should 
successfully build the program.  It is expected that every build will also execute the test
lifecycle ensuring that unit tests all still pass.

After that you must install MySQL 8.0 and set it up in order to connect to it successfully, make sure to name the database schema `ordersys`. 
Once MySQL is successfully set up, go into the file `hibernate.cfg.xml` 
and replace the part of the code that says `your password here` with your MySQL password.

### Testing
Unit tests are included to test the functionality of our Data Layer, Controller and Repository.  

## Running the Online Ordering System
Run OrderSystem to start the program and make sure that you have a valid connection to a MySQL database.


