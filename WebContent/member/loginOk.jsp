<%@page import="java.sql.Timestamp"%>
<%@page import="com.jsp.pj1.dto.Dto"%>
<%@page import="com.jsp.pj1.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8"); 
%>  
<%	
	String email = null;
	String pw = null;
	String dist = null;
	String phone = null;
	
	email = request.getParameter("email");
	pw = request.getParameter("pw");
	dist = request.getParameter("distance");
	
	Dao dao = Dao.getInstance();
	int checkNum = dao.userCheck(email, pw);
	
	if(checkNum == -1) {
%>
	<script lang="Javascript">
		alert("not exist Email ID.");
		history.go(-1);
	</script>
<%		
	}else if(checkNum == 0) {
%>
	<script lang="Javascript">
		alert("Do not matched password.");
		history.go(-1)
	</script>
<%		
	}else if(checkNum == 1) {
		Dto dto = dao.getCustomer(email);
		if(dto == null) {
%>
	<script lang="Javascript">
		alert("Please input your ID");
		history.go(-1);
	</script>
<%			
		}else{
			String firstName = dto.getFirstName();
			String lastName = dto.getLastName();
			session.setAttribute("email", email);
			session.setAttribute("firstName", firstName);
			session.setAttribute("lastName", lastName);			
			
			email = (String)session.getAttribute("email");
			
			// use for checking login status whether or not 
			session.setAttribute("ValidMem", "yes"); 
%>
			<script lang="Javascript">
			var email = '<%=email%>';
			alert('Connect into ' + email);
			opener.parent.location.reload();
			self.close();
			</script>
			
<%
	dto.setRegJointime((new Timestamp(System.currentTimeMillis())));
	phone = dao.confirmPhone(dto.getCustomerEmail()).getCustomerPhone();
	
	// It will be re-update every time any user connects
	dao.updateDistance(dist, email);
	
	/* 
	* Bring back location value for comparison login user location with payment user location
	* for security.
	*/
	String userDistance = null;
	userDistance = dao.confirmDistance(dto.getCustomerEmail()).getCustomerDistanceInfo();	
	
	// Define phone and distance values as a session
	session.setAttribute("phone", phone);
	session.setAttribute("userDistance", userDistance);
	
	log("phone : " + phone);
	log("distance : " + dist);
	log("userDistance : " + userDistance);		
			
		}
	}
%>


