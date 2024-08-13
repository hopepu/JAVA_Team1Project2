package Utils;

public class CalenderDTO {
	private String year;
	private String month;
	private String day;
	private boolean leapYear;
	
	public CalenderDTO() {
		this.leapYear=false;
	}//기본생성자

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


	public boolean isLeapYear() {
		return leapYear;
	}

	public void setLeapYear(boolean leapYear) {
		this.leapYear = leapYear;
	}

	@Override
	public String toString() {
		return "CalenderDTO [year=" + year + ", month=" + month + ", day=" + day 
				+ ", leapYear=" + leapYear + "]";
	}
	
	

}
