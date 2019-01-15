<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.jsp.pj1.dao.Dao"%>
<%@page import="com.jsp.pj1.dto.RDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    	request.setCharacterEncoding("UTF-8");
%> 
<%
	//preparences administer account
	String email = (String) session.getAttribute("email");
	log("email : " + email);
	String admin = "exorsa@naver.com";
	
	Dao dao = Dao.getInstance();
	ArrayList<RDto> cbl = dao.clientbookList(email);

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="../style/payment_style.css" type="text/css"	media="screen" />
<link href="https://fonts.googleapis.com/css?family=Bungee+Hairline" rel="stylesheet"> 
<script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../script/popup.js"></script>
<script type="text/javascript" src="../script/init.js"></script>
<script type="text/javascript" src="../script/paymentInput.js"></script>
<script type="text/JavaScript" src="../script/menuContent.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="cancel.do" id="bookup" name="cancel" method="post">
		<div class="container-fluid" >
			<div class="row">
				<div class="col-xs-12">

					<c:forEach var="i" items="${pcl}" begin="0"	end="23" varStatus="st">
							<span>${i.t_index }</span>&nbsp;
							<span>${i.menuname }</span>&nbsp;
							<span>${i.reserved_wmy }</span>&nbsp;
							<span>${i.reserved_Time }</span>&nbsp;
					</c:forEach>

				</div>
			</div>
		</div>
	</form>

</body>
</html>