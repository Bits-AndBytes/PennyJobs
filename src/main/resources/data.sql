--***STUDENT SAMPLE***
INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type) 
VALUES ('johnsmith1@gmail.com','password123','John', 'Smith', '123 Example St', 'Oakville', 'L6L 7U9', 'ON', '2005-11-17', 'S');

INSERT INTO student (Rating, Bio)
 VALUES (8.5, 'Example');

--Update [dbo].[Account] SET StudentID = (SELECT s.StudentID FROM [dbo].[Student] as s, [dbo].[Account] as a WHERE a.AccountID = s.AccountID) WHERE AccountID = 1;

--Update [dbo].[Student] SET ParentID = ((SELECT ParentID from [dbo].[Parent] where ParentID = 2)) WHERE AccountID = 1;


--***PARENT SAMPLE***
INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type) 
VALUES ('janesmith13@yahoo.ca','Password321','Jane', 'Smith', '123 Example St', 'Oakville', 'L6L 7U9', 'ON', '1973-06-03', 'P');


--INSERT INTO parent (StudentID, AccountID) 
--VALUES ((SELECT StudentID from [dbo].[Student] where StudentID = 2),(SELECT AccountID FROM [dbo].[Account] WHERE AccountID = 2));


--Update [dbo].[Account] SET ParentID = (SELECT p.ParentID FROM [dbo].[Parent] as p, [dbo].[Account] as a WHERE a.AccountID = p.AccountID) WHERE AccountID = 4;


--***JOB POSTER SAMPLE***
INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type) 
VALUES ('jeremylerkin@protonmail.com','AdvancedPaSWord','Jeremy', 'Lerkin', '356 Oak Lane', 'Oakville', 'L6L 9B6','ON', '1952-03-24', 'J');

--INSERT INTO jobposter (AccountID) 
--VALUES ((SELECT AccountID from [dbo].[Account] where AccountID = 5));

--Update [dbo].[Account] SET PosterID = (SELECT p.PosterID FROM [dbo].[JobPoster] as p, [dbo].[Account] as a WHERE a.AccountID = p.AccountID) WHERE AccountID = 4;

--***JOB AD SAMPLE DATA***
INSERT INTO job (title, description, underage) 
VALUES ('Lawn moving needed','In need of someone to mow my lawn', 'yes');

insert into Role (rolename)
values ('ROLE_STUDENT');
 
insert into Role (rolename)
values ('ROLE_PARENT');

insert into Role (rolename)
values ('ROLE_POSTER');

insert into Role (rolename)
values ('ROLE_ADMIN');

