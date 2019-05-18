//import java.sql.* ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import configs.DatabaseConfiguration;
import configs.DatabaseQueries;
import utils.DBTablePrinter;

public class PaperReviewDriver {
	private Connection conn = null;
	private PreparedStatement  stmt = null;
	
	public static void main(String[] args) {
		PaperReviewDriver paperReviewer = new PaperReviewDriver();
		paperReviewer.executeQueries();
	}
	/**
	 * This method executes the the queries as SQL
	 */
	public void executeQueries() {
		
		try {
			setMySqlConnection();
			getPaperDetailsByAuthorEmailId("jsmith@gmail.com");
			getReviewsForPaper("1");
			getPaperSubmissionCount();
			submitNewPaper(getNewPaper(), getNewAuthor());
			deleteAuthor("jclark@gmail.com");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * This method sets up a connection to the existing database
	 */
	private void setMySqlConnection() {
		System.out.println("Connecting to database...");
		try {
			if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(DatabaseConfiguration.DB_URL,
					DatabaseConfiguration.USER,DatabaseConfiguration.PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * QUERY 1:
	 * This method implements a SQL query to get the paper details by Author's primary key 
	 * @param authorEmailId
	 * @throws SQLException
	 */
	private void getPaperDetailsByAuthorEmailId(String authorEmailId) throws SQLException {
		System.out.println("Creating statement for PaperDetailsByAuthorEmailId");
		stmt = conn.prepareStatement(DatabaseQueries.paperDetailByAuthor);
		stmt.setString(1, authorEmailId);
		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			System.out.println("No Paper details were found for this author");
		} else {
			System.out.println("QUERY 1 \n");
			DBTablePrinter.printResultSet(rs);
		}
		rs.close();
		stmt.close();
	}
	
	/**
	 * QUERY 2:
	 * This method implements an SQL query to get all the paper reviews for a Paper by Paper's Id
	 * @param paperId
	 * @throws SQLException
	 */
	private void getReviewsForPaper(String paperId) throws SQLException {
		System.out.println("Creating statement for ReviewsForPaper");
		stmt = conn.prepareStatement(DatabaseQueries.reviewsForPaper);
		stmt.setString(1, paperId);
		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			System.out.println("No Reviews were found for this paper");
		} else {
			System.out.println("QUERY 2 \n");
			DBTablePrinter.printResultSet(rs);
		}
		rs.close();
		stmt.close();
	}
	
	/**
	 * QUERY 3:
	 * This method implements a SL query to count the total number of papers submitted
	 * @throws SQLException
	 */
	private void getPaperSubmissionCount() throws SQLException {
		System.out.println("Creating statement for PaperSubmissionCount");
		stmt = conn.prepareStatement(DatabaseQueries.paperSubmissionCount);
		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			System.out.println("No Papers are submitted by any Author");
		} else {
			System.out.println("QUERY 3 \n");
			DBTablePrinter.printResultSet(rs);
		}
		rs.close();
		stmt.close();
	}
	/**
	 * QUERY 4:
	 * This method implements a SQL queiry to add a new Paper submitted by a new author entry
	 * @param paper
	 * @param author
	 * @throws SQLException
	 */
	private void submitNewPaper(Paper paper, Author author) throws SQLException {
		System.out.println("Submitting new paper");
		String authorInsertSql = DatabaseQueries.newPaperSubmission[0];
		
		stmt = conn.prepareStatement(authorInsertSql);
		stmt.setString(1, author.getEmailId());
		stmt.setString(2, author.getFirstName());
		stmt.setString(3, author.getLastName());
		stmt.execute();
		stmt.close();
		
		String paperInsertSql = DatabaseQueries.newPaperSubmission[1];
		stmt = conn.prepareStatement(paperInsertSql);
		stmt.setString(1, paper.getTitle());
		stmt.setString(2, paper.getAbs());
		stmt.setString(3, paper.getFileName());
		stmt.setString(4, paper.getAuthorId());
		stmt.execute();
		stmt.close();
		System.out.println("QUERY 4 \n");
		System.out.println("Successfully submitted new paper and new author \n");
	}
	/**
	 * This method creates new paper
	 * @return
	 */
	private Paper getNewPaper() {
		return new Paper("HashSet", "Generate New HashSet", "hashSet.pdf", "jfernando@gmail.com");
	}
	/**
	 * This method creates new author
	 * @return
	 */
	private Author getNewAuthor() {
		return new Author("jfernando@gmail.com", "James", "Fernandez");
	}
	
	/**
	 * QUERY 5:
	 * This method implements a SQL query to delete the first row of Author table
	 * @param authorId
	 * @throws SQLException
	 */
	private void deleteAuthor (String authorId) throws SQLException {
		System.out.println("Creating statement for deleteAuthor");
		stmt = conn.prepareStatement(DatabaseQueries.deleteAuthor);
		stmt.setString(1, authorId);
		System.out.println("QUERY 5 \n");
		try {
			stmt.execute();
			System.out.println("Successfully deleted the author because"
					+ " it is not refrenced as foreigrn key somewhere else");
		} catch (Exception e)
	    {
		    System.err.println("Got an exception! ");
    		System.err.println(e.getMessage());
	    }
		stmt.close();
		
	}
}
