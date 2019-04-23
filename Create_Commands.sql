/* Create a Database */
CREATE DATABASE PAPER_REVIEWER;
USE PAPER_REVIEWER;

/*Create AUTHOR table*/
CREATE TABLE AUTHOR (
  EmailAddr varchar(50) NOT NULL PRIMARY KEY,
  FirstName varchar(30) NOT NULL,
  LastName varchar(30) NOT NULL
);

/*Create PAPER table*/
CREATE TABLE PAPER (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Title varchar(100) NOT NULL,
  Abstract varchar(100) NOT NULL,
  FileName varchar(30) NOT NULL,
  AuthorId varchar(50) DEFAULT NULL,
  FOREIGN KEY (AuthorId) REFERENCES AUTHOR(EmailAddr)
);

/*Create table REVIEWER*/
CREATE TABLE REVIEWER (
  EmailAddr varchar(50) NOT NULL PRIMARY KEY,
  FirstName varchar(30) NOT NULL,
  LastName varchar(30) NOT NULL,
  AuthorFeedback varchar(100),
  CommiteeFeedback varchar(100),
  PhoneNum varchar(10),
  Affilation varchar(40)
);

/*Create table REVIEWER_ASSIGNED*/
CREATE TABLE REVIEWER_ASSIGNED (
  PaperId int NOT NULL,
  ReviewerId varchar(50) NOT NULL,
  PRIMARY KEY (PaperId,ReviewerId),
  FOREIGN KEY (PaperId) REFERENCES PAPER(Id),
  FOREIGN KEY (ReviewerId) REFERENCES REVIEWER(EmailAddr)
);

/*Create table TOPIC*/
CREATE TABLE TOPIC (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  TopicName varchar(100) NOT NULL,
  ReviewerId varchar(50) NOT NULL,
  FOREIGN KEY (ReviewerId) REFERENCES REVIEWER(EmailAddr)
);

/*Create table REVIEW*/
CREATE TABLE REVIEW (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Recommendation varchar(200),
  MeritScore int NOT NULL,
  PaperId int NOT NULL,
  ReadabilityScore int NOT NULL,
  ReviewerId varchar(255) NOT NULL,
  OriginalityScore int(11) NOT NULL,
  RelevanceScore int(11) NOT NULL,
  FOREIGN KEY (PaperId) REFERENCES PAPER(Id),
  FOREIGN KEY (ReviewerId) REFERENCES REVIEWER(EmailAddr),
  CHECK(MeritScore <= 10 && MeritScore >= 1),
  CHECK(ReadabilityScore <= 10 && ReadabilityScore >= 1),
  CHECK(RelevanceScore <= 10 && RelevanceScore >= 1),
  CHECK(OriginalityScore <= 10 && OriginalityScore >= 1)
);
