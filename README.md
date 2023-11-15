# Yorkshire Arboretum Developer Docs
## Overview
## Initiating the database and website
### There are two property files the repository requires
In order to create a new database or run a new one we have two properties files to be located in
"src/main/resources/"; these two files are included in the .gitignore file as they contain sensitive information.

#### 1) "admin.properties"
Contains the initial user for the database username and password. The fields "Username" and "password" should be
set to the admins initial username and password, respectively. The files example contents are below:

```
admin.initialUsername=Username

admin.initialPassword=password
```

#### 2) "application.properties"
This contains the base URL of the hosted database that the website accesses. It also contains the username and
password for the database.  The database user and password fields (which are designated by <mysql-username> and
<mysql-password> should be changed before initialising the database.

References to the public and private key are listed on the last two lines. In order to create these to keys the
folder "src/main/resources/certs" should be navigated to and the contents of "make-keys.sh" should be copied and ran
in the terminal.

Example contents of application.properties is below:
```
spring.datasource.url=jdbc:mysql://localhost:3306/<DataBase_Path>?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true  
spring.jpa.hibernate.ddl-auto=update  
spring.datasource.username=<mysql-username>  
spring.datasource.password=<mysql-password>  

rsa.rsa-private-key=classpath:certs/private.pem  
rsa.rsa-public-key=classpath:certs/public.pem  
```

### Function of initialisation

For the website to function, we need to create an initial admin so that other admins can be created. When the
server is initialised a command line runner in the main file runs a method in the UserService. This method checks if
the initial user is there, if it is there it does nothing, but if it is not and initial username and password is
initialised.

Four main files carry out this function. **application.properties** and **admin.properties** have been discussed
above. The file **src/main/resources/AdminProperties.java** retrieves the initial admin username and password from
**admin.properties**.

**src/main/com.example.security/YorkshireArboretum** contains the command line runner that sets the initial the
admin username and password, if it is not already present.

**src/main/com.example.security/service/UserService.java** contains the method that is called in YorkshireArboretum
to create the initial username and password.

### Testing of initialisation

- Testing of this section is in Mockito. It consists of checking if the user does or does not already exist when 
  initialising the database.  
- There is also testing for the correct response when a user logs in for both if they exist and do not exist.  

## Making each admin

Currently admins can be made, but not deleted. 

### Creating each admin

### Testing of creating each admin



## Calling each sign

There is no app to call each sign, the entry point to the tree information is from the qr codes from signs. The QR
code is a link to a web address, this is the base URL and the signs database ID for that sign. In future there may 
be a map page that could link each page, and that could be incorporated into an app.

### Code to call each sign

Each sign is called from the sign SQL database as individual sign DTO's. The QR code provided on the sign contains 
the address of the base URL followed by the sign ID. SignResource.java contains a routing of ```@GetMapping java "/
{id}" ``` which calls the individual sign DTO's , based on the sign ID provided. This is referred to as getSign in 
SignService.java. The sign DTO object consists of the sign ID, title, description, location latitude and longitude. 

### Testing for calling each sign

Testing is carried out with Mockito and Cypress. Mockito tests the unit of  in the xx to do xx. Cypress tests the 
integration of the internal logic and the extraction from the database.

