package com.jsp.pj1.Services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jsp.pj1.dao.Dao;
import com.jsp.pj1.dto.CDto;
import com.jsp.pj1.dto.MDto;
import com.jsp.pj1.dto.RDto;

public class MenuInfoService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	System.out.println("MenuInfoService");
		Dao dao = new Dao();
		//make menu information list
		ArrayList<MDto> dtos = dao.menuInfo();
		request.setAttribute("list", dtos);
		System.out.println("list : " + dtos);
		//make calendar information list
		ArrayList<CDto> today = dao.calendar();
		request.setAttribute("today_TList", today);
		System.out.println("today_TList : " + today);
		
		ArrayList<CDto> manana = dao.calendar_manana();
		request.setAttribute("manana_TList", manana);
		System.out.println("manana_TList : " + manana);	
		
		ArrayList<CDto> third = dao.calendar_third();
		request.setAttribute("third_TList", third);
		System.out.println("third_TList : " + third);	

		ArrayList<CDto> fourth = dao.calendar_fourth();
		request.setAttribute("fourth_TList", fourth);
		System.out.println("fourth_TList : " + fourth);	
		
		ArrayList<CDto> fifth = dao.calendar_fifth();
		request.setAttribute("fifth_TList", fifth);
		System.out.println("fifth_TList : " + fifth);	
		
		ArrayList<CDto> sixth = dao.calendar_sixth();
		request.setAttribute("sixth_TList", sixth);
		System.out.println("sixth_TList : " + sixth);	
		
		ArrayList<RDto> bki = dao.bookupInfo();
		request.setAttribute("bookupList", bki);
		System.out.println("bookupList : " + bki);

		// is exist reserved time
		
		String reserved_Time = request.getParameter("reserved_Time");
		int re = dao.existTable(reserved_Time);
		System.out.println("reserved_Time"+ reserved_Time);		
		request.setAttribute("reserved_Time", re);


	}

}
