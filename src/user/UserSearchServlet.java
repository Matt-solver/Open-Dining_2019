package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.com")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		String conPath = request.getContextPath();
		System.out.println(conPath);
		String com = uri.substring(conPath.length());
		System.out.println("com:" + com);	
		
		//service�������� �� ������ �б��ϰ� ���⼭ DAO �۾��� �� �ϴ� ���� �ƴ϶�
		if(com.equals("/index.com")) {
			String keyword = request.getParameter("keyword");
			response.getWriter().write(getJSON(keyword));
		}else if(com.equals("/cancel.com")) {
			String bseq = request.getParameter("bseq");
			response.getWriter().write(getCancel(bseq));
			
		}else if(com.equals("/deleteMenu.com")) {
			System.out.println("servlete deletemenu");
			String menuname = request.getParameter("menuname");
			getDeleteMenu(menuname);
			
		}else if(com.equals("/deleteCalendar.com")) {
			String time = request.getParameter("time");
			String day = request.getParameter("day");
			System.out.println("day : " + day);
			getDeleteToday(time, day);
		}
	}
	
	private void getDeleteToday(String time, String day) {
		
		if(time == null) time = "";
		AjaxDAO ajaxDAO = new AjaxDAO();
		ajaxDAO.deleteToday(time, day);
		
	}

	public void getDeleteMenu(String menuname) {
		System.out.println("getdeletemenu : " + menuname);
		if(menuname == null) menuname = "";
		AjaxDAO ajaxDAO = new AjaxDAO();
		ajaxDAO.deleteMenu(menuname);
	}
	
	// Delete Reservation
	public String getCancel(String bseq) {
		if(bseq == null) bseq = "";
		StringBuffer result = new StringBuffer("");		
		
		AjaxDAO ajaxDAO = new AjaxDAO();
		ArrayList<URDto> userList = ajaxDAO.cancel(bseq);
		System.out.println(userList);
		
		result.append("{\"result\":[");

		for(int i=0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + userList.get(i).getB_seq() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getReserved_wmy() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getReserved_Time() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getT_index() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getMenuname() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEmail() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDistance() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDistance() + "\"}],");
			
		}
		
		result.append("]}");
		System.out.println(result);
		return result.toString();
	}

	//제이슨으로 변환
	public String getJSON(String keyword) {
		
		if(keyword == null) keyword = "";
		StringBuffer result = new StringBuffer("");
		
		result.append("{\"result\":[");
		
		AjaxDAO ajaxDAO = new AjaxDAO();
		ArrayList<URDto> userList = ajaxDAO.search(keyword);
		System.out.println(userList);
		
		for(int i=0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + userList.get(i).getB_seq() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getReserved_wmy() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getReserved_Time() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getT_index() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getMenuname() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEmail() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDistance() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getDistance() + "\"}],");
			
		}
		
		result.append("]}");
		System.out.println(result);
		return result.toString();
	}

}
