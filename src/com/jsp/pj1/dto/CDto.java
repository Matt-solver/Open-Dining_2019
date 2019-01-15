package com.jsp.pj1.dto;

public class CDto {
	
    // 일정 일자 
    private String calendarDay;

    // 일정 시간 
    private String calendarTime;
	
	public CDto(String calendarDay, String calendarTime) {
		this.calendarDay = calendarDay;
		this.calendarTime = calendarTime;
		
	}


    public String getCalendarDay() {
        return calendarDay;
    }

    public void setCalendarDay(String calendarDay) {
        this.calendarDay = calendarDay;
    }

    public String getCalendarTime() {
        return calendarTime;
    }

    public void setCalendarTime(String calendarTime) {
        this.calendarTime = calendarTime;
    }

}
