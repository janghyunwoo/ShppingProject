package domain;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import util.DBConnectionPool;

public class PaymentDAO {

	private DBConnectionPool connPool;

	private static final String ALLRETRIEVE_STMT

			= "SELECT * FROM shoppingpayment";

	private static final String RETRIEVE_STMT

			= "SELECT * FROM shoppingpayment WHERE UserID = ?";

	private static final String GETID_STMT = "SELECT COUNT(PaymentID) FROM shoppingpayment";

	private static final String ADD_STMT = "INSERT INTO shoppingpayment VALUES(?,?,?,?,?,?,?,?)";

	ArrayList<Payment> allpaymentRetrieve() throws SQLException {

		ArrayList<Payment> payments = new ArrayList<Payment>();

		Connection conn = null;

		PreparedStatement stmt = null;

		ResultSet rset = null;

		try {

			conn = connPool.getPoolConnection();

			stmt = conn.prepareStatement(ALLRETRIEVE_STMT);

			rset = stmt.executeQuery();

			while (rset.next()) {

				int PaymentID = rset.getInt(1);

				int UserID = rset.getInt(2);

				int ProductID = rset.getInt(3);

				int Numbers = rset.getInt(4);

				String Address = rset.getString(5);

				String Contact = rset.getString(6);

				String CreditCardNumber = rset.getString(7);

				String CreditCardPassword = rset.getString(8);

				payments.add(new Payment(PaymentID, UserID, ProductID, Numbers, Address, Contact, CreditCardNumber,
						CreditCardPassword));

			}

			return payments;

		} catch (SQLException se) {

			throw new RuntimeException(

					"A database error occurred. " + se.getMessage());

		} catch (Exception e) {

			throw new RuntimeException("Exception: " + e.getMessage());

		} finally {

			if (rset != null) {

				try {

					rset.close();

				} catch (SQLException se) {

					se.printStackTrace(System.err);

				}

			}

			if (stmt != null) {

				try {

					stmt.close();

				} catch (SQLException se) {

					se.printStackTrace(System.err);

				}

			}

			if (conn != null) {

				try {

					conn.close();

				} catch (Exception e) {

					e.printStackTrace(System.err);

				}

			}

		}

	}

	ArrayList<Payment> paymentRetrieve(int userid) throws SQLException {

		ArrayList<Payment> payments = new ArrayList<Payment>();

		Connection conn = null;

		PreparedStatement stmt = null;

		ResultSet rset = null;

		try {

			conn = connPool.getPoolConnection();

			stmt = conn.prepareStatement(RETRIEVE_STMT);

			stmt.setInt(1, userid);

			rset = stmt.executeQuery();

			while (rset.next()) {

				int PaymentID = rset.getInt(1);

				int UserID = rset.getInt(2);

				int ProductID = rset.getInt(3);

				int Numbers = rset.getInt(4);

				String Address = rset.getString(5);

				String Contact = rset.getString(6);

				String CreditCardNumber = rset.getString(7);

				String CreditCardPassword = rset.getString(8);

				payments.add(new Payment(PaymentID, UserID, ProductID, Numbers, Address, Contact, CreditCardNumber,
						CreditCardPassword));

			}

			return payments;

		} catch (SQLException se) {

			throw new RuntimeException(

					"A database error occurred. " + se.getMessage());

		} catch (Exception e) {

			throw new RuntimeException("Exception: " + e.getMessage());

		} finally {

			if (rset != null) {

				try {

					rset.close();

				} catch (SQLException se) {

					se.printStackTrace(System.err);

				}

			}

			if (stmt != null) {

				try {

					stmt.close();

				} catch (SQLException se) {

					se.printStackTrace(System.err);

				}

			}

			if (conn != null) {

				try {

					conn.close();

				} catch (Exception e) {

					e.printStackTrace(System.err);

				}

			}

		}

	}

	void paymentAdd(int userid, int productid, int numbers, String address, String contact, String creditcardnumber,
			String creditcardpassword) {

		Connection conn = null;

		PreparedStatement stmt = null;

		ResultSet rset = null;

		try {

			conn = connPool.getPoolConnection();

			stmt = conn.prepareStatement(GETID_STMT);

			rset = stmt.executeQuery();

			int ID = -1;

			rset.next();

			ID = rset.getInt("COUNT(PaymentID)");

			ID++;

			stmt = conn.prepareStatement(ADD_STMT);

			stmt.setInt(1, ID);

			stmt.setInt(2, userid);

			stmt.setInt(3, productid);

			stmt.setInt(4, numbers);

			stmt.setString(5, address);

			stmt.setString(6, contact);

			stmt.setString(7, creditcardnumber);

			stmt.setString(8, creditcardpassword);

			stmt.executeQuery();

		} catch (SQLException se) {

			throw new RuntimeException(

					"A database error occurred. " + se.getMessage());

		} finally {

			if (stmt != null) {

				try {

					stmt.close();

				} catch (SQLException se) {

					se.printStackTrace(System.err);

				}

			}

			if (conn != null) {

				try {

					conn.close();

				} catch (Exception e) {

					e.printStackTrace(System.err);

				}

			}

		}

	}

}