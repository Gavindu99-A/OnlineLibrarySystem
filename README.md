The borrowing_records table contains user borrowed books. The user and book table has many to many relationship and by mapping userId from user table and bookId from book table making the borrowing_records table. 
A user able to register to the system by using username and email. After registering the user can see all books in the system and available books to borrow in the system. 
Also, able to filter books using author and published year. 
Using the system can borrow books. When borrowing a book, a new record is added to borrowing_record table. When borrowing a book userId and bookId pass from frontend. 
Then check whether a copy of book is available to borrow. When borrowing successful reduce the copies of book by 1. Until a borrowed book is returning the returnDate column is null in the borrowing_record table. 
At the time returning the book update the returnDate column. 
Added a basic authentication to all API s of borrowing_record controller and book controller. But a user can get all the books in the system without authentication. 
To make the solution optimal added indexes to tables. 
