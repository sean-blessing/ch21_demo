package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBUtil;

public class StatusDB {
	
	public StatusDB() {
		
	}
	
	public ArrayList<Status> getAll() {
		ArrayList<Status>  all = new ArrayList<>();
		String sql = "select * from status";
		try (Connection conn = DBUtil.getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql))
		{
			while (rs.next()) {
				// create an instance of Status and add to 'all' ArrayList
				int id = rs.getInt(1);
				String desc = rs.getString(2);
				Status status = new Status(id,desc);
				all.add(status);
			}
		}
		catch (SQLException sqle) {
			System.out.println("Error getting all status rows."); 
			sqle.printStackTrace();
		}
		
		return all;
	}

	public Status getStatus(int statusId) {
		Status s = null;
		String sql = "select * from status where id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql))
		{	
			ps.setInt(1, statusId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				// create an instance of Status and add to 'all' ArrayList
				int id = rs.getInt(1);
				String desc = rs.getString(2);
				s = new Status(id,desc);
			}
			rs.close();
		}
		catch (SQLException sqle) {
			System.out.println("Error getting status for id:"+statusId+"."); 
			sqle.printStackTrace();
		}
		
		return s;
	}
}
