/*Insert data to 'AUTHOR' table*/
INSERT INTO AUTHOR (EmailAddr, FirstName, LastName) VALUES 
('jsmith@gmail.com', 'John', 'Smith'), 
('jclark@gmail.com', 'Judy', 'Clark'), 
('rjain@yahoo.com', 'Rahul', 'Jain');

/*Insert data to 'PAPER' table*/
INSERT INTO PAPER (Title, Abstract, FileName, AuthorId) VALUES 
('Architectire of Database', 'Explanation of how to implement a relational database', 'architecture.pdf', 'jsmith@gmail.com'), 
('Artificially Intelligent Graph Database', 'Generate an AI driven Graph based database', 'graph.pdf', 'rjain@yahoo.com');


/*Insert data to 'REVIEWER' table*/
 INSERT INTO REVIEWER (EmailAddr, FirstName, LastName, AuthorFeedback, CommiteeFeedback, PhoneNum, Affilation) VALUES 
 ('mscott@yahoo.com', 'Mary', 'Scott', 'The research paper meets all the requirements.', 'The paper is approved by our committe', '7155611011', 'Copyright 2018'), 
 ('rsharma@gmail.com', 'Ritu', 'Sharma', 'The research paper meets all the requirements', 'The paper is approved by our committe', '8152345678', 'Copyright 2016');
 
 
 /*Insert data to 'REVIEWER_ASSIGNED' table*/
 INSERT INTO REVIEWER_ASSIGNED (PaperId, ReviewerId) VALUES 
 (1, 'mscott@yahoo.com'), 
 (1, 'rsharma@gmail.com');
 
 
 /*Insert data to 'TOPIC' table*/
 INSERT INTO TOPIC (TopicName, ReviewerId) VALUES 
 ('Artificial Intelligence', 'mscott@yahoo.com'), 
 ('Relational Database', 'rsharma@gmail.com');
 
 
 /*Insert data to 'REVIEW' table*/
 INSERT INTO REVIEW (Recommendation, MeritScore, PaperId, ReadabilityScore, ReviewerId, OriginalityScore, RelevanceScore) VALUES 
 ('Need to add Bibliography', 7, 1, 8, 'mscott@yahoo.com', 9, 6), 
 ('Upto the mark', 8, 1, 9, 'rsharma@gmail.com', 9, 9);