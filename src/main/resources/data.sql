--***STUDENT SAMPLE***
INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type, Enabled) 
--unencrypted password: password123
VALUES ('johnsmith1@gmail.com','$2a$10$vZ/RtzD0705eSsJ86dn.deugz5UqM8QOWCko62zUD6rAh5jp38eFS','John', 'Smith', '123 Example St', 'Oakville', 'L6L 7U9', 'ON', '2005-11-17', 'S', 1);

INSERT INTO student (Rating, Bio)
 VALUES (8.5, 'Example');

--Update [dbo].[Account] SET StudentID = (SELECT s.StudentID FROM [dbo].[Student] as s, [dbo].[Account] as a WHERE a.AccountID = s.AccountID) WHERE AccountID = 1;

--Update [dbo].[Student] SET ParentID = ((SELECT ParentID from [dbo].[Parent] where ParentID = 2)) WHERE AccountID = 1;


--***PARENT SAMPLE***
INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type, Enabled) 
--unencrypted password: Password321
VALUES ('janesmith13@yahoo.ca','$2a$10$fyZko8rd8PdAc6NEk68JFe8mGI9QFiLELtnlXwXMp0.l67KRhyk36','Jane', 'Smith', '123 Example St', 'Oakville', 'L6L 7U9', 'ON', '1973-06-03', 'P', 1);


--INSERT INTO parent (StudentID, AccountID) 
--VALUES ((SELECT StudentID from [dbo].[Student] where StudentID = 2),(SELECT AccountID FROM [dbo].[Account] WHERE AccountID = 2));


--Update [dbo].[Account] SET ParentID = (SELECT p.ParentID FROM [dbo].[Parent] as p, [dbo].[Account] as a WHERE a.AccountID = p.AccountID) WHERE AccountID = 4;


--***JOB POSTER SAMPLE***
INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type, Enabled) 
--Unencrypted password: AdvancedPaSWord
VALUES ('jeremylerkin@protonmail.com','$2a$10$CihMqn17jge8cAM8xQcszuICJaQqxna8ZoluFHRgNTvQSLUDvwiWm','Jeremy', 'Lerkin', '356 Oak Lane', 'Oakville', 'L6L 9B6','ON', '1952-03-24', 'J', 1);

--INSERT INTO jobposter (AccountID) 
--VALUES ((SELECT AccountID from [dbo].[Account] where AccountID = 5));

--Update [dbo].[Account] SET PosterID = (SELECT p.PosterID FROM [dbo].[JobPoster] as p, [dbo].[Account] as a WHERE a.AccountID = p.AccountID) WHERE AccountID = 4;

--***JOB AD SAMPLE DATA***
INSERT INTO job (title, description, province, city, street, postal_Code, underage) 
VALUES ('Lawn moving needed','In need of someone to mow my lawn', 'Ontario', 'Oakville', 'Randrom Street 123','L6M0M1', 'Yes');

--***Admin sample account (password is: admin)
INSERT INTO account (email,password) VALUES ('admin@pennyjobs.ca', '$2a$10$LchoNWvrW/TGqO1Isahqc.bPuW.cFlfI/4ra8DbZLu2eD7QIWks8O');

insert into Role (rolename)
values ('ROLE_STUDENT');
 
insert into Role (rolename)
values ('ROLE_PARENT');

insert into Role (rolename)
values ('ROLE_POSTER');

insert into Role (rolename)
values ('ROLE_ADMIN');

--student
insert into account_roles (accounts_id, roles_id)
values (1,1);

--parent
insert into account_roles (accounts_id, roles_id)
values (2,2);

--poster
insert into account_roles (accounts_id, roles_id)
values (3,3);

--admin
insert into account_roles (accounts_id, roles_id)
values (4,4);
