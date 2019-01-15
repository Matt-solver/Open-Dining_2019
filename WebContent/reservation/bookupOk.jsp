<%@page import="com.jsp.pj1.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>	
<%
	if(session.getAttribute("ValidMem") == null){
		
	}else{

		//preparences administer account
    	String email = (String) session.getAttribute("email");
    	String t_index = request.getParameter("t_index");
    	String menuname = request.getParameter("menuname");
    	String distance = request.getParameter("distance");
    	String reserved_wmy = request.getParameter("reserved_wmy");
    	String reserved_day = request.getParameter("reserved_day");
    	String reserved_time = request.getParameter("reserved_time");
    	
    	String creditCardFirm = request.getParameter("creditCardFirm");
    	String cdn = request.getParameter("cdn");
    	String customer_name = request.getParameter("customer_name");
    	String inval = request.getParameter("inval");
    	String cvc = request.getParameter("cvc");
    	String phone = request.getParameter("phone");
    	String installment = request.getParameter("installment");
    	String withdraw = request.getParameter("withdraw");
    	
    	
    	Dao dao = Dao.getInstance();		// 싱글톤 패턴의 특징 : 클래스로부터 객체를 getInstance()로 가져오는 것
    	
    	String userDistance = null;
    	userDistance = (String)session.getAttribute("userDistance");
    	log("bookupOk.userDistance :  " + userDistance);
    	
    	if (!distance.equals(userDistance)) {
    		
    	%>
    		<script type="text/javascript">
    			alert('Compared your location information with location information in server, As a result did not match!');
    			history.go(-1);
    		</script>
    	<%
    		
    	}else{
    		
    		int ri = dao.confirmCdn(cdn);
    		log("cdn ri = " + ri);
    		ri += dao.confirmInval(inval);
    		log("cdn ri = " + ri);
    		ri += dao.confirmCvc(cvc);
    		log("cdn ri = " + ri);
    		
    		if( ri != 3) {
    	%>
	    		<script language="javascript">
					alert("Not exist credit card informaion.");
					document.location.href = "http://localhost:8181/Project1_KGITBANK/index.do";
				</script>
    	<%		
    			
    		}else{
    		
	     	ri = 0;
	     	
	     	ri = dao.bookup(t_index, email, menuname, withdraw, distance, reserved_wmy, 
	     			reserved_day, reserved_time, creditCardFirm, cdn,  customer_name, 
	     			inval, cvc, phone, installment, withdraw);
	     	log("bookupOk ri : " + ri);
        	
     	  	if(ri == 2){
%>
				<script type="text/javascript">
					alert("completed bookup.");
					history.go(-1);
					
					var popUrl = "payment/complet_payment.jsp";
					var popOption = "top=150, left=650, width=580, height=400, resizable=no, menubar=no, location=no, directoryies=no, resizable=no, toolbar=no, scrollbars=no, status=no;";
					window.open(popUrl, "", popOption);
				</script>
<%		
			}else{
%>	
				<script type="text/javascript">
				alert("Failed bookup.");
				history.go(-1);
				</script>
<%
			}
		}
	}
}
%> 
