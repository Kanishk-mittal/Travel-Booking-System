# Travel Booking System
Hi I am Kanishk mittal (2023BCY0003) and this is my mini project for following problem statment
![alt text](image.png)
## Key Features
- Booking Flights
- Booking Hotels
- Creating multiple users
- Managing multiple bookings
- Cancelling bookings
- Viewing bookings
## Folder Structure
![alt text](image-1.png)
## Basic Components
- User
- Flight
- Hotel
- Data
- Custom Exceptions
- Data Handler
- Main
## Working of each component
### 1) Main
This is the main file which is used to run the whole project. It is the entry point of the project and manage how different componets interact with each other.
### 2) User
This file contains the user class which is used to create user objects and maintain a record of all users in ths system like managing there booking and their data and all that stuff.
### 3) DataHandler
This is the most import and most used package in the project and it is further subdivided into two parts:
#### 3.1) Read Data 
This part is used to read data from the files and store it in the form of objects in the system.
#### 3.2) Write Data
This part is used to take objects as input and write them in their spefic csv files and if that file is not present then it will create a new file with some dummy data in it which we can replace with actual data in reald scenario.
### 4) Flight
This file contains the flight class which is used to create flight objects and maintain a record of all flights in the system like managing there booking and their data and all that stuff.
### 5) Hotel
This file contains the hotel class which is used to create hotel objects and maintain a record of all hotels in the system like managing there booking and their data and all that stuff.