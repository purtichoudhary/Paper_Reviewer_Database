package configs;

/**
 * Use this Interface to define all the Queries here in String format
 * @author Purti Choudhary
 *
 */
public interface DatabaseQueries {
	String paperDetailByAuthor = "select Paper.Id, Paper.Title, " +
			"Paper.Abstract, Author.EmailAddr, Author.FirstName, Author.LastName \r\n" + 
			"from paper, author\r\n" + 
			"where paper.AuthorId = author.EmailAddr and " +
			"author.EmailAddr = ?;";
	
	String reviewsForPaper = "select review.Id, review.Recommendation, " +
			"review.PaperId, review.ReviewerId, review.MeritScore, " +
			"review.ReadabilityScore, review.OriginalityScore, review.RelevanceScore \r\n" +
			"from paper, review\r\n" +
			"where paper.Id = review.paperId and " +
			"paper.Id = ?;";
	
	String paperSubmissionCount = "select COUNT(*) from paper";
	
	String[] newPaperSubmission = {
			"INSERT INTO author(EmailAddr, FirstName, LastName)" +
			"VALUES (?, ?, ?);",
			
			"INSERT INTO paper(Title, Abstract, FileName, AuthorId)" +
			"VALUES (?, ?, ?, ?);"
	};
	
	String deleteAuthor = "DELETE from author where author.EmailAddr = ?;";
	
	
}
