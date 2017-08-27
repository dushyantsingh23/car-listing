Dropwizard project for car listings

1.Clone or download the project

2.In side the directory use `mvn clean install` to install project dependencies.

3.Run Mysql Server on local.

4. Create new database name zoomcar_db, details about the user can be found at zoomcar-server.yml

5. Create table using the following queries : 

      ```create table car_details( 
      id integer not null auto_increment, 
      brand varchar(50), 
      model varchar(50), 
      licenseNum varchar(50),
      primary key(id)) engine=INNODB;


      create table car_block(
      id integer not null auto_increment,
      carId varchar(50),
      startTime DateTime,
      endTime DateTime,
      primary key(id)) engine = INNODB;
      
      
      create table car_listing(
      id integer not null auto_increment,
      carId varchar(50),
      startTime DateTime,
      endTime DateTime,
      primary key(id)) engine = INNODB;```
     
     
6. Create a log file for the server by using the following commands.

      ``sudo touch /var/log/server-logs.log``
      
      ``sudo chmod 775 /var/log/server-logs.log``

Run the jar file as `java -jar target/car-listing-1.0_SNAPSHOT.jar server  zoomcar-server.yml`. The server runs at `http://localhost:9090/`.

7. Following are the APIs and their purpose, also below added curl statements to populate those:
 
            `POST /v1/cars` - To create a carDetail entity
      
                  curl --request POST \
              --url http://localhost:9090/v1/cars \
              --header 'content-type: application/json' \
              --data '{
                  "brand" : "Mercedez",
                  "model" : "A",
                  "licenseNum" : "KA01QW1122"
            }'

      
            `POST /v1/listings` - To create a listing

                  curl --request POST \
              --url http://localhost:9090/v1/listing \
              --header 'content-type: application/json' \
              --data '{
                  "carId": "2",
                  "startTime": "2017-08-26 03:00:00",
                  "endTime": "2017-08-26 12:15:00"
            }'
      
      
      
            `POST /v1/block` - To create a block
      
            curl --request POST \
              --url http://localhost:9090/v1/block \
              --header 'content-type: application/json' \
              --data '{
                  "carId" : "1",
                  "startTime" : "2017-08-26 03:00:00",
                  "endTime" : "2017-08-26 04:08:00"
            }'
      
      
            `GET /v1/search?time={epoch time in seconds}` - to a list of Car details currently active. if no Query param given then cucrrent time will be considered
      
                  curl --request GET \
              --url http://localhost:9090/v1/search
