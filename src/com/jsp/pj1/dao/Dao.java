package com.jsp.pj1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jsp.pj1.dto.CDto;
import com.jsp.pj1.dto.Dto;
import com.jsp.pj1.dto.MDto;
import com.jsp.pj1.dto.RDto;

public class Dao {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int PHONE_NONEXISTENT = 0;
	public static final int PHONE_EXISTENT = 1;
	public static final int DISTANCE_NONEXISTENT = 0;
	public static final int DISTANCE_EXISTENT = 1;
	public static final int CDN_NONEXISTENT = 0;
	public static final int CDN_EXISTENT = 1;
	public static final int MEMBER_JOIN_FIAL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int TABLE_NONEXISTENT =0;
	public static final int TABLE_EXISTENT = 1;
	public static final int BOOKUP_SUCCESS = 1;
	public static final int DELETE_SUCCESS = 1;
	
	private static Dao instance = new Dao();	// �ڱⰡ �ڱ�Ŭ�������� �ڽ��� �����ϰ� �װ��� �����ϰ� �ִ� ���� 
	
	public Dao() {

	}


	public static Dao getInstance() {	// getInstance�� ������ �� �ֵ��� static �޼ҵ带 ����� ����
		
		return instance;
	}
	
	
	private Connection getConnection() {
		Context cont = null;
		DataSource dataSource = null;
		Connection conn = null;
		
		try {			
			cont = new InitialContext();
			dataSource = (DataSource) cont.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		return conn;
	}
	
/*	//Cancel book
	public int deleteReservation(String b_seq) {
		System.out.println("DAO측 b_seq : " + b_seq);
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("dao.deleteReservation()");
		
		try {
			conn = getConnection();
			
			String query = "DELETE BOOKUP WHERE b_seq = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b_seq);
			ri = pstmt.executeUpdate();
			System.out.println("deleteReservation ri : " + ri);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
		
	}*/
	
	public ArrayList<CDto> schedule(String day, String time) {
		System.out.println(day + " "+ time);
		ArrayList<CDto> CDto = new ArrayList<CDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("dao.schedule()");
		String query = "INSERT INTO calendar VALUES(?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, day);
				pstmt.setString(2, time);
				pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return CDto;
	}
	
	public ArrayList<RDto> bookupInfo() {
		
		ArrayList<RDto> bki = new ArrayList<RDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("dao.bookupInfo()");
				
		try {
			conn = getConnection();
			
			String query = "SELECT * FROM bookup";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Timestamp b_seq = rs.getTimestamp("b_seq");
				String t_index = rs.getString("t_index");
				String email = rs.getString("email");
				String menuname = rs.getString("menuname");
				String withdraw = rs.getString("withdraw");
				String distance = rs.getString("distance");
				String reserved_wmy = rs.getString("reserved_wmy");
				String reserved_day = rs.getString("reserved_day");
				String reserved_Time = rs.getString("reserved_Time");				

				RDto dto = new RDto(b_seq, t_index, email, menuname, withdraw, distance, reserved_wmy, reserved_day, reserved_Time);
				bki.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bki;
		
	}
		
	public ArrayList<RDto> purchaseList() {
		
		ArrayList<RDto> pcl = new ArrayList<RDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("dao.purchaseList()");
				
		try {
			conn = getConnection();
			
			String query = "SELECT * FROM bookup ORDER BY b_seq DESC";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Timestamp b_seq = rs.getTimestamp("b_seq");
				String t_index = rs.getString("t_index");
				String email = rs.getString("email");
				String menuname = rs.getString("menuname");
				String withdraw = rs.getString("withdraw");
				String distance = rs.getString("distance");
				String reserved_wmy = rs.getString("reserved_wmy");
				String reserved_day = rs.getString("reserved_day");
				String reserved_Time = rs.getString("reserved_Time");				

				RDto dto = new RDto(b_seq, t_index, email, menuname, distance, withdraw, reserved_wmy, reserved_day, reserved_Time);
				pcl.add(dto);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pcl;
		
	}
	
	public ArrayList<RDto> clientbookList(String email) {
		System.out.println("DAO email : " + email);
		ArrayList<RDto> pcl = new ArrayList<RDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("dao.clientbookList()");
				
		try {
			conn = getConnection();
			
			String query = "SELECT * FROM bookup WHERE email = ? ORDER BY b_seq DESC";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Timestamp b_seq = rs.getTimestamp("b_seq");
				String t_index = rs.getString("t_index");
				String menuname = rs.getString("menuname");
				String withdraw = rs.getString("withdraw");
				String distance = rs.getString("distance");
				String reserved_wmy = rs.getString("reserved_wmy");
				String reserved_day = rs.getString("reserved_day");
				String reserved_Time = rs.getString("reserved_Time");				

				RDto dto = new RDto(b_seq, t_index, email, menuname, withdraw, distance, reserved_wmy, reserved_day, reserved_Time);
				pcl.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return pcl;
		
	}
	
	/*
	 * 
	 * Try transaction to Payment from Bookup
	 * 
	 * */
	@SuppressWarnings("resource")
	public int bookup(String t_index, String email, String menuname, String withdraw,
					String distance, String reserved_wmy, String reserved_day, 
					String reserved_time, String creditCardFirm, String cdn, 
					String customer_name, String inval, String cvc, String phone,
					String installment, String p_withdraw) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query1 = "INSERT INTO bookup VALUES(sysdate,?,?,?,?,?,?,?,?)";	
		String query2 = "INSERT INTO creditcardcompany VALUES(sysdate, ?, ?,"
						+ " ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);	//Change autoCommit function manually.
			System.out.println("getConnection");			
			pstmt = conn.prepareStatement(query1);
			System.out.println("prepareStatement for query values");	
			
			pstmt.setString(1, t_index);	
			pstmt.setString(2, email);
			pstmt.setString(3, menuname);
			pstmt.setString(4, withdraw);
			pstmt.setString(5, distance);
			pstmt.setString(6, reserved_wmy);
			pstmt.setString(7, reserved_day);
			pstmt.setString(8, reserved_time);
			
			ri = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query2);
			
			pstmt.setString(1, creditCardFirm);
			pstmt.setString(2, cdn);
			pstmt.setString(3, customer_name);
			pstmt.setString(4, email);
			pstmt.setString(5, inval);
			pstmt.setString(6, cvc);
			pstmt.setString(7, phone);
			pstmt.setString(8, installment);
			pstmt.setString(9, distance);
			pstmt.setString(10, p_withdraw);
			
			ri += pstmt.executeUpdate();
			System.out.println("ri ::" + ri);
			
			// It can only be commit when all the work is completed without any error.
			if (ri != 2) {
				try {
					conn.rollback();
					conn.setAutoCommit(true);
					
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}

			}else {
				conn.commit();
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		  
		
		return ri;
	}
	
	
	/*public int paymentCard(String creditCardFirm, String cdn, String customer_name, 
							String email, String inval, String cvc, String phone,
							String installment, String distance, String withdraw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("dao.paymentCard()");
		int ri = 0;
		
		String query = "INSERT INTO creditcardcompany VALUES(sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, creditCardFirm);
			pstmt.setString(2, cdn);
			pstmt.setString(3, customer_name);
			pstmt.setString(4, email);
			pstmt.setString(5, inval);
			pstmt.setString(6, cvc);
			pstmt.setString(7, phone);
			pstmt.setString(8, installment);
			pstmt.setString(9, distance);
			pstmt.setString(10, withdraw);
			
			ri = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
		
	}*/
	
	public void insertMenu(String menuImg, String menuName, 
							String content, String price) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			System.out.println("dao.menuImg:"+menuImg);
			
		String query = "INSERT INTO product VALUES(p_seq.nextval,?,?,?,?)";	
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuImg);
			pstmt.setString(2, menuName);
			pstmt.setString(3, content);
			pstmt.setString(4, price);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void deleteMenu(String menuName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("dao.insertMenu(menuName) : " + menuName);
		
		String query = "DELETE product WHERE menuname = ?";	
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
			
			pstmt.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
			}
		}
	}
	
	
	public ArrayList<MDto> menuInfo(){
		
		ArrayList<MDto> dtos = new ArrayList<MDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("menuInfo()");
				
		try {
			conn = getConnection();
			
			String query = "SELECT p_seq, menuImg, menuName, menuContent, price "
						 + "FROM product "
						 + "ORDER BY p_seq";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int seq = rs.getInt("p_seq");
				String menuImg = rs.getString("menuImg");
				String menuName = rs.getString("menuName");
				String content = rs.getString("menuContent");
				String price = rs.getString("price");
				
				MDto dto = new MDto(seq, menuImg, menuName, content, price);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
		
	}
	
	public int insertCustomer(Dto dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "INSERT INTO customer VALUES(m_seq.nextval,?,?,?,?,?,?,sysdate,0)";	
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getFirstName());
			pstmt.setString(2, dto.getLastName());
			pstmt.setString(3, dto.getCustomerEmail());
			pstmt.setString(4, dto.getCustomerPwd());
			pstmt.setString(5, dto.getCustomerPhone());
			pstmt.setString(6, dto.getCustomerAddr());
			pstmt.executeUpdate();

			ri = Dao.MEMBER_JOIN_SUCCESS;	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int updateDistance(String dist, String email) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("dist : " + dist);

		String query = "UPDATE customer "
					+ "SET customer_distance_info= ?"
					+ " WHERE customer_email= ?";	
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dist);
			pstmt.setString(2, email);
			ri = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	

	public int confirmID(String email) {
		
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT customer_email FROM customer WHERE customer_email = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = Dao.MEMBER_EXISTENT;
			}else {
				ri = MEMBER_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmCdn(String cdn) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT credit_cdn FROM bananabank WHERE credit_cdn = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cdn);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = Dao.CDN_EXISTENT;
			}else {
				ri = CDN_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmInval(String inval) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT credit_inval FROM bananabank WHERE credit_inval = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inval);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = Dao.CDN_EXISTENT;
			}else {
				ri = CDN_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmCvc(String cvc) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT credit_cvc FROM bananabank WHERE credit_cvc = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cvc);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = Dao.CDN_EXISTENT;
			}else {
				ri = CDN_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	public Dto confirmPhone(String email) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dto dto = null;
		String query = "SELECT customer_phone FROM customer WHERE customer_email = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new Dto();
				dto.setCustomerPhone((rs.getString("customer_phone")));
				ri = Dao.PHONE_EXISTENT;
			}else {
				ri = PHONE_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public Dto confirmDistance(String email) {
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dto dto = null;
		String query = "SELECT customer_distance_info FROM customer WHERE customer_email = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new Dto();
				dto.setCustomerDistanceInfo((rs.getString("customer_distance_info")));
				ri = Dao.DISTANCE_EXISTENT;
			}else {
				ri = DISTANCE_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int existTable(String reserved_Time) {
		System.out.println("디에이오 리저브타임 : " + reserved_Time);
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT reserved_Time FROM bookup WHERE reserved_Time = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reserved_Time);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ri = Dao.TABLE_EXISTENT;
			}else {
				ri = TABLE_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	// ȸ������ ����
		public int userCheck(String email, String pw) {	//login.jsp���������� �����ڷκ��� �Էµ� id�� pw�� �Ѱܹ���
			int ri = 0;
			String dbPw;	//DB���� ������ �н�����
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT customer_pwd FROM customer WHERE customer_email = ?";	//DB���� �н����� ����
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email); //pstmt : �ش� ���� email�� �������� ='?'�� ���Ե�
				rs = pstmt.executeQuery();	//rs�� �н����� ����

				if(rs.next()) {
					dbPw = rs.getString("customer_pwd");
					// ���ϵ� ri���� �������� ���� : DB�� ����Ǿ� �ִ� dbPw ���� �����ڰ� �Է��� pw�� ���Ͽ� ��ġ���� �ǵ�
					if(dbPw.equals(pw)) {		
						ri = Dao.MEMBER_LOGIN_SUCCESS; //=1
					}else {
						ri = Dao.MEMBER_LOGIN_PW_NO_GOOD; //=0
					}
				}else {
					ri = Dao.MEMBER_LOGIN_IS_NOT; //=-1
					if (!rs.isBeforeFirst() ) {    //resultset ���� ������ ��µ�
					    System.out.println("No data"); 
					} 
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return ri;
		}
	
	
		//ȸ������ ����	
		public int updateCustomer(Dto dto) {
			int ri = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String query = "UPDATE customer SET customer_pwd=?, customer_phone=?, customer_addr=? WHERE customer_email= ?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getCustomerPwd());
				pstmt.setString(2, dto.getCustomerPhone());
				pstmt.setString(3, dto.getCustomerAddr());
				pstmt.setString(4, dto.getCustomerEmail());
				ri = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return ri;
		}
		
		
	//ȸ������ ��������
		public Dto getCustomer(String email) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT * FROM customer WHERE customer_email = ?";
			Dto dto = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto = new Dto();
					dto.setFirstName(rs.getString("first_name"));
					dto.setLastName(rs.getString("last_name"));
					dto.setCustomerEmail((rs.getString("customer_email")));
					dto.setCustomerPwd((rs.getString("customer_pwd")));
					dto.setCustomerPhone((rs.getString("customer_phone")));
					dto.setCustomerAddr((rs.getString("customer_addr")));
					dto.setRegJointime((rs.getTimestamp("reg_joinTime")));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			return dto;
		}

	
		// time fo bookup * 6		public ArrayList<CDto> calendar() {
			ArrayList<CDto> ccc = new ArrayList<CDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			try {
				conn = getConnection();
				
				String query = "SELECT * FROM calendar "
							 + "WHERE calendar_day='today' and calendar_time "
							 + "NOT IN (SELECT DISTINCT reserved_Time FROM bookup"
							 + " WHERE reserved_day='today') ORDER BY calendar_time ASC";
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				System.out.println("calendar() rs : " + rs);
				
				while(rs.next()) {
					String calendar_day = rs.getString("calendar_day");
					String calendar_time = rs.getString("calendar_time");
					
					System.out.println("calendar_day :" + calendar_day);
					
					CDto cdto = new CDto(calendar_day, calendar_time);
					ccc.add(cdto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return ccc;
			
		}
		public ArrayList<CDto> calendar_manana() {
			
			ArrayList<CDto> ccc = new ArrayList<CDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			try {
				conn = getConnection();
				
				String query = "SELECT * FROM calendar "
						+ "WHERE calendar_day='manana' and calendar_time "
						+ "NOT IN (SELECT DISTINCT reserved_time FROM bookup"
						+ " WHERE reserved_day='manana') ORDER BY calendar_time ASC";
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String calendar_day = rs.getString("calendar_day");
					String calendar_time = rs.getString("calendar_time");
					
					//System.out.println("time :" + time);
					
					CDto cdto = new CDto(calendar_day, calendar_time);
					ccc.add(cdto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return ccc;
			
		}
		public ArrayList<CDto> calendar_third() {
			
			ArrayList<CDto> ccc = new ArrayList<CDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			try {
				conn = getConnection();
				
				String query = "SELECT * FROM calendar "
						+ "WHERE calendar_day='today' and calendar_time "
						+ "NOT IN (SELECT DISTINCT reserved_time FROM bookup "
						+ "WHERE reserved_day='today') ORDER BY calendar_time ASC";
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String calendar_day = rs.getString("calendar_day");
					String calendar_time = rs.getString("calendar_time");
					
					//System.out.println("time :" + time);
					
					CDto cdto = new CDto(calendar_day, calendar_time);
					ccc.add(cdto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return ccc;	
		}
		
		public ArrayList<CDto> calendar_fourth() {
			
			ArrayList<CDto> ccc = new ArrayList<CDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			try {
				conn = getConnection();
				
				String query = "SELECT * FROM calendar "
						+ "WHERE calendar_day='fourth' and calendar_time "
						+ "NOT IN (SELECT DISTINCT reserved_time FROM bookup "
						+ "WHERE reserved_day='fourth') ORDER BY calendar_time ASC";
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String calendar_day = rs.getString("calendar_day");
					String calendar_time = rs.getString("calendar_time");
					
					//System.out.println("time :" + time);
					
					CDto cdto = new CDto(calendar_day, calendar_time);
					ccc.add(cdto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return ccc;	
		}	
		
	public ArrayList<CDto> calendar_fifth() {
		
		ArrayList<CDto> ccc = new ArrayList<CDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String query = "SELECT * FROM calendar "
					+ "WHERE calendar_day='fifth' and calendar_time "
					+ "NOT IN (SELECT DISTINCT reserved_time FROM bookup "
					+ "WHERE reserved_day='fifth') ORDER BY calendar_time ASC";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String calendar_day = rs.getString("calendar_day");
				String calendar_time = rs.getString("calendar_time");
				
				//System.out.println("time :" + time);
				
				CDto cdto = new CDto(calendar_day, calendar_time);
				ccc.add(cdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ccc;
		
	}	
	
	public ArrayList<CDto> calendar_sixth() {
		
		ArrayList<CDto> ccc = new ArrayList<CDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String query = "SELECT * FROM calendar "
					+ "WHERE calendar_day='sixth' and calendar_time "
					+ "NOT IN (SELECT DISTINCT reserved_time FROM bookup "
					+ "WHERE reserved_day='sixth') ORDER BY calendar_time ASC";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String calendar_day = rs.getString("calendar_day");
				String calendar_time = rs.getString("calendar_time");
				
				//System.out.println("time :" + time);
				
				CDto cdto = new CDto(calendar_day, calendar_time);
				ccc.add(cdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ccc;
		
	}	
}
