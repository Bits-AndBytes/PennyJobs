
--***STUDENT SAMPLE***
INSERT INTO student (Rating, Bio)
 VALUES (8.5, 'Example');

INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type, Enabled, student_id) 
--unencrypted password: password123
VALUES ('johnsmith1@gmail.com','$2a$10$yK7cXzCyk2xIxwIvAZwvi.RKlaSMBuY1xXkxdNMKYyCQbVa2w.sBG','John', 'Smith', '123 Example St', 'Oakville', 'L6L 7U9', 'ON', '2005-11-17', 'S', 1, 1);

UPDATE student SET account_id = (SELECT id FROM account WHERE student_id = 1);


--***PARENT SAMPLE***
INSERT INTO parent (account_id) VALUES ((SELECT id FROM account WHERE parent_id = 1)); --this will initially be blank but needed to add a record with an id

INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type, Enabled, parent_id) 
--unencrypted password: Password321
VALUES ('janesmith13@yahoo.ca','$2a$10$fyZko8rd8PdAc6NEk68JFe8mGI9QFiLELtnlXwXMp0.l67KRhyk36',
'Jane', 'Smith', '123 Example St', 'Oakville', 'L6L 7U9', 'ON', '1973-06-03', 'P', 1, (SELECT id FROM parent WHERE id = 1));

UPDATE parent SET account_id = (SELECT id FROM account WHERE parent_id = 1); --overwrite the blank account_id


--***JOB POSTER SAMPLE***
INSERT INTO job_poster (account_id) values ((SELECT id FROM account WHERE poster_id = 1)); --this will initially be blank but needed to add a record with an id

INSERT INTO account (Email, Password, First_Name, Last_Name, street, city, postal_Code, province, Birth_Date, Account_Type, Enabled, poster_id) 
--Unencrypted password: AdvancedPaSWord
VALUES ('jeremylerkin@protonmail.com','$2a$10$CihMqn17jge8cAM8xQcszuICJaQqxna8ZoluFHRgNTvQSLUDvwiWm',
'Jeremy', 'Lerkin', '356 Oak Lane', 'Oakville', 'L6L 9B6','ON', '1952-03-24', 'J', 1, (SELECT id FROM job_poster WHERE id = 1));

UPDATE job_poster SET account_id = (SELECT id FROM account WHERE poster_id = 1); --overwrite the blank account_id


--***JOB AD SAMPLE DATA***
INSERT INTO job (title, description, price, province, city, street, postal_Code, underage, job_poster_id) 
VALUES ('Lawn moving needed','In need of someone to mow my lawn', 15.00 ,'Ontario', 'Oakville', 'Randrom Street 123','L6M0M1', 'Yes', (SELECT id FROM job_poster WHERE id = 1));


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
