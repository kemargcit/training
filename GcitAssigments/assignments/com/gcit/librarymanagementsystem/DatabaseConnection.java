/**
 * DatabaseConnection.java
 */
package com.gcit.librarymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author kemar
 *Apr 16, 201511:32:00 PM
 */
public class DatabaseConnection {
 
	
	
	public Connection databaseConnection() throws SQLException{
		Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/library?","root","stpatrick876");

			return conn;
	}
}
