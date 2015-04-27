package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements Serializable {

	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619700647002508164L;

	public void addPublisher(Publisher publisher) throws SQLException {
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)",
				new Object[] { publisher.getName(),publisher.getAddress(),publisher.getPhoneNumber() });

	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		save("update tbl_publisher set publisherName = ?,publisherAddress=?,publisherPhone=? where publisherId = ?",
				new Object[] { publisher.getName(),publisher.getAddress(),publisher.getPhoneNumber(),publisher.getId() });
	}

	public void removePublisher(Publisher publisher) throws SQLException {
		save("delete from tbl_publisher where publisherId=?",
				new Object[] { publisher.getId() });
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAll() throws SQLException {
		return (List<Publisher>) read("select * from tbl_publisher", null);
	}

	public Publisher readOnePubById(int publisherId) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Publisher> publishers = (List<Publisher>) read(
				"select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId });
		if (publishers != null && publishers.size() > 0) {
			return publishers.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Publisher> mapResults(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher();
			a.setId(rs.getInt("publisherId"));
			a.setName(rs.getString("publisherName"));
			a.setAddress(rs.getString("publisherAddress"));
			a.setPhoneNumber(rs.getString("publisherPhone"));

			publishers.add(a);
		}
		return publishers;
	}

	@Override
	protected List<?> mapResultsFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
