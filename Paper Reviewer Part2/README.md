## Main Class 
<b>PaperReviewDriver.java</b>

This class contains the main method and implementation of all the query methods.

## Resource Files:

1. DatabaseConfiguration: Interface containing all the DB connection information like user name, password, db url and jdbc driver strings.
2. DatabaseQueries: All the parametrized queries are defined in this interface as final strings.
3. Paper Entity: Class defined to capture the paper information. Used in case when a new paper is created in database.
4. Author Entity: Class defined to capture the author information. Used in case when a new author is created in database.

## Utility Files:
1. DBTablePrinter: This is open sourced script used to beautify the query responses. (Printing Resultset in a meaningful manner).

## Queries
1. QUERY1
   select Paper.Id, Paper.Title, Paper.Abstract, Author.EmailAddr, Author.FirstName, Author.LastName
	 from paper, author
	 where paper.AuthorId = author.EmailAddr and author.EmailAddr = "jsmith@gmail.com";

2. QUERY2
   select review.Id, review.Recommendation, review.PaperId, review.ReviewerId, review.MeritScore, review.ReadabilityScore, review.OriginalityScore, review.RelevanceScore
   from paper, review
   where paper.Id = review.paperId and paper.Id = 1;
   
3. QUERY3
   select COUNT(*) from paper
   
4. QUERY4
   INSERT INTO author
   VALUES ("jfernando@gmail.com", "James", "Fernando");
   
   INSERT INTO paper
   VALUES (3, "Hash map", "Generetaing a new form of Hash map", "hash.pdf", "jfernando@gmail.com");
   
5. QUERY5
   DELETE from paper where paper.AuthorId = "jclark@gmail.com"
   
 To run the queries run the PaperReviewDriver.java file
   
