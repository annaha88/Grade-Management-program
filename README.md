# Grade-Management-program-

1. Overview of the Design

1.1 Project description
*Note: This project is a modified version of the program from the material MySQLWithNetBeansAndJDBCTutorial.pdf in this course.
The program will check whether a student is eligible for a degree or a certificate based on the course information they input.
It will check things as follows
 1. A student must take 2 core courses (course number: CO100, CO200) with at least B grade.
 2. A student must take at least 5 courses with at least B grade.
This program is implemented by Java with GUI interfaces using swing. And the data is stored in MySQL DB using JDBC.


1.2 Brief Use case and sequence diagram 
Main actor: Student
A student turns on the GUI program. A window pops up displaying the course list and the grade the user took over the last quarters. 
There are buttons to add, delete or update a course. When the user clicks these buttons, it updates course list in database and display updated list in the GUI window.
There is also a button to calculate whether the student is eligible for the certificate based on the database.
When the button is clicked, additional window pops up saying ‘You are eligible!” or “You are not qualified yet”

![image](https://user-images.githubusercontent.com/69747899/228985403-c62056f0-3c80-420d-a342-4bff46105363.png)

  
1.3 UML Class Diagram

 ![image](https://user-images.githubusercontent.com/69747899/228985369-fe17cf8e-b4ba-4e7f-8530-42b717e612b7.png)

▲	Class diagram
