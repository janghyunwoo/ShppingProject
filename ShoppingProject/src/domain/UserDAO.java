package domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnectionPool;

public class UserDAO {

    private DBConnectionPool connPool;
    private static final String RETRIEVE_STMT
            = "SELECT * FROM shoppinguser WHERE UserType=? AND UserName=? AND Password =?";
    private static final String GETID_STMT = "SELECT COUNT(UserID) FROM shoppinguser";
    private static final String CREATE_STMT = "INSERT INTO shoppinguser VALUES(?,?,?,?,?,?,?,?,?)";

    User userRetrieve(String usertype, String username, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        int rows = 0;
        try {
            conn = connPool.getPoolConnection();
            stmt = conn.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, usertype);
            stmt.setString(2, username);
            stmt.setString(3, password);
            rset = stmt.executeQuery();
            while (rset.next()) {
                int UserID = rset.getInt("UserID");
                String UserType = rset.getString("UserType");
                String UserName = rset.getString("UserName");
                String Password = rset.getString("Password");
                Date BirthDate = rset.getDate("BirthDate");
                String Gender = rset.getString("Gender");
                String Email = rset.getString("Email");
                String Contact = rset.getString("Contact");
                String Address = rset.getString("Address");
                rows++;
                if (rows > 1) {
                    throw new SQLException("Too many rows were returned.");
                }
                user = new User(UserID, UserType, UserName, Password, BirthDate, Gender, Email, Contact, Address);
            }
            return user;
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

    void userCreate(String usertype, String username, String password, Date birthdate, String gender, String email, String contact, String address) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            conn = connPool.getPoolConnection();
            stmt = conn.prepareStatement(GETID_STMT);
            rset = stmt.executeQuery();
            int ID = -1;
            rset.next();
            ID = rset.getInt("COUNT(UserID)");
            ID++;
            stmt = conn.prepareStatement(CREATE_STMT);
            stmt.setInt(1, ID);
            stmt.setString(2, usertype);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.setDate(5, birthdate);
            stmt.setString(6, gender);
            stmt.setString(7, email);
            stmt.setString(8, contact);
            stmt.setString(9, address);
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