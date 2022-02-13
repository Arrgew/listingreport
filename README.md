# listingreport

Spring boot command line based application using Spring shell.
It gets data from a mock api, validates it, saves it to a local MySql database and could create a report json file and upload it to an ftp server.
The invalid data is saved in a Importlog.csv file.
You can change the database, api, ftp server access details in the /listingreport/src/main/resources/application.properties.
You can create the database the application uses following the /listingreport/sql lines.txt files Sql queries.
The tables are automatically created using Hibernate.

The databases' tables will look like this.
![alt text](https://github.com/Arrgew/listingreport/blob/master/listingsreportdatabase.PNG?raw=true)
