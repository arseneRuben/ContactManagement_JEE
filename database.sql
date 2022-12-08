DROP DATABASE if  exists  contactdb;
CREATE DATABASE if not exists  contactdb;
USE contactdb;
CREATE TABLE if not exists contacts (id int(11) NOT NULL AUTO_INCREMENT, name VARCHAR(50),  primary key(ID));
INSERT INTO contacts( name) VALUES 
("Robert Cailliau"),
("Linux Torvald"),
 ("Tim Berners-Lee"),
 ("Alain Herzt"), 
 ("Mark Zukerberg");

CREATE TABLE if not exists phonenumbers (id int(11) NOT NULL AUTO_INCREMENT, num VARCHAR(50)  , type Enum('MAISON','TRAVAIL', 'CELLULAIRE')  NOT NULL, contact_id int(11),  primary key(ID));
ALTER TABLE phonenumbers 
ADD CONSTRAINT FK_PHONENUMBERS_CONTACTS FOREIGN KEY(contact_id) REFERENCES contacts(id);
INSERT INTO phonenumbers( num, type, contact_id) VALUES 
("+2589745611","MAISON",1),
("+14546567845","MAISON",2),
("+4546567845", "TRAVAIL",3),
("+4598762115","CELLULAIRE",4);
 
 CREATE TABLE if not exists emails (id int(11) NOT NULL AUTO_INCREMENT, address VARCHAR(50)  , type Enum('PERSONNEL', 'PROFESSIONNEL')  NOT NULL, contact_id int(11),  primary key(ID));
ALTER TABLE emails 
ADD CONSTRAINT FK_EMAILS_CONTACTS FOREIGN KEY(contact_id) REFERENCES contacts(id);
INSERT INTO emails( address, type, contact_id) VALUES 
("alain@contact.ca","PERSONNEL",1),
("torvald@contact.ca", "PROFESSIONNEL",2),
 ("mark@contact.ca","PERSONNEL",3),
 ("ndakic@contact.ca","PERSONNEL",4);

