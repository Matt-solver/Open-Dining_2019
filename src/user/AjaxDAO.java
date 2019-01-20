package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jsp.pj1.dao.JdbcUtil;

public class AjaxDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AjaxDAO() {
		
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID = "exorsa";
			String dbPW = "tldhstks12";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<URDto> search(String keyword) {
		String query = "SELECT * FROM bookup "
				+ "WHERE t_index||menuname||email||"
				+ "reserved_wmy||reserved_day||reserved_Time LIKE ?";
		ArrayList<URDto> userList = new ArrayList<URDto>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%"); //와일드카드 : 특정한 요소가 포함된 모든 요소가 출력
			rs = pstmt.executeQuery();
			System.out.println(rs);
			
			while (rs.next()) {
				URDto dto = new URDto();
				dto.setB_seq(rs.getTimestamp(1));
				dto.setT_index(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setMenuname(rs.getString(4));
				dto.setDistance(rs.getString(5));
				dto.setReserved_wmy(rs.getString(6));
				dto.setReserved_day(rs.getString(7));
				dto.setReserved_Time(rs.getString(8));
				
				userList.add(dto);
				System.out.println("DAO LIST : " + userList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
		
	}
	
	public ArrayList<URDto> cancel(String bseq) {
		System.out.println("cancel()문진입");System.out.println("bseq : " + bseq);
		String query = "DELETE bookup WHERE b_seq = TO_DATE(?, 'yymmddhh24miss')";
		ArrayList<URDto> userList = new ArrayList<URDto>();
		
		try {
			System.out.println("try문 집입");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bseq); //와일드카드 : 특정한 요소가 포함된 모든 요소가 출력
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			System.out.println(rs);
			
			while (rs.next()) {
				System.out.println("while문 집입");
				URDto dto = new URDto();
				dto.setB_seq(rs.getTimestamp(1));
				dto.setT_index(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setMenuname(rs.getString(4));
				dto.setDistance(rs.getString(5));
				dto.setReserved_wmy(rs.getString(6));
				dto.setReserved_day(rs.getString(7));
				dto.setReserved_Time(rs.getString(8));
				
				userList.add(dto);
				System.out.println("DAO LIST : " + userList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
		
	}
	
	public void deleteMenu(String menuName) {
		System.out.println("dao.insertMenu(menuName) : " + menuName);
		
		String query = "delete product where menuname = ?";	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
			
			pstmt.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			
			}finally {
				JdbcUtil.close(conn);
				JdbcUtil.close(pstmt);
		}
	}

	public void deleteToday(String table, String time, String day) {
		System.out.println("dao.deleteToday(table) : " + table);
		
		String query = "delete calendar where calendar_table = ? and calendar_time = ? and calendar_day= ?";	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, table);
			pstmt.setString(2, time);
			pstmt.setString(3, day);
			
			pstmt.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			
			}finally {
				JdbcUtil.close(conn);
				JdbcUtil.close(pstmt);
		}
	}
	
	
	
}
