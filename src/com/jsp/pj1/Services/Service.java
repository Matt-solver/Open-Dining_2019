package com.jsp.pj1.Services;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�߻�޼ҵ� execute
//�������̽��� �����Ͽ� �� Ŀ�ǵ忡 implements���ش�.
public interface Service {
	void execute(HttpServletRequest request, HttpServletResponse response);	//�� Ŀ�ǵ�� ����Ǹ� ��Ʈ�ѷ����� Ŀ�ǵ忡 ���ø� �ϱ����� execute ��ü�� ȣ��ȴ�
	
}
