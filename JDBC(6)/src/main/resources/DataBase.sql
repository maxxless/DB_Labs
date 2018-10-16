CREATE DATABASE IF NOT EXISTS db_jdbc;

USE db_jbdc;

CREATE TABLE University
(
  university_name VARCHAR(25) NOT NULL,
  PRIMARY KEY (university_name)
);

CREATE TABLE student
(
  id_student INT NOT NULL AUTO_INCREMENT,
  surname VARCHAR(25) NOT NULL,
  name VARCHAR(25) NOT NULL,
  commission_number int NOT NULL,
  university_name VARCHAR(25) NOT NULL,
  email VARCHAR(45) NULL,
  PRIMARY KEY (id_student),
  FOREIGN KEY (university_name)
  REFERENCES University (university_name)
);

CREATE TABLE Commission
(
  id_commission INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  student VARCHAR(45) NOT NULL,
  teacher INT NOT NULL,
  PRIMARY KEY (id_commission)
);

CREATE TABLE Teacher (
  id_student INT NOT NULL,
  id_commission1 INT NOT NULL,
  PRIMARY KEY (id_student, id_commission1),
  FOREIGN KEY (id_student)
  REFERENCES  student (id_student),
  FOREIGN KEY (id_commission1)
  REFERENCES  Commission (id_commission)
);

  INSERT INTO `Commission`  (name, student, teacher) VALUES
('TIMS','Ivan',5),
('DB','Liubomyr ',4),
('Algo','Andriy',1),
('Philosophy','Ihor',2);

INSERT INTO `University`(university_name) VALUES ('NU LP'),('UCU'),('LNU FRANKA'),('INFIZ'),('MNS');

INSERT INTO `student` (surname, name, commission_number, university_name, email) VALUES
  ('Sapiha','Liubomyr',2,'NU LP','kgkfldly@gmail.com'),
  ('Korol','Yura',4,'UCU','afaak@gmail.com'),
  ('Dovhan','Ivan',1,'LNU FRANKA','aafafk@gmail.com'),
  ('Mochurad','Roman',6,'MNS','alalal@gmail.com');

INSERT INTO `Teacher` (id_student, id_commission1) VALUES (4,1),(2,1),(1,1),(3,2);


