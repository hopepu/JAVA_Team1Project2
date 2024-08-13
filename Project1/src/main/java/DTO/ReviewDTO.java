package DTO;

import java.util.Date;

public class ReviewDTO {
	@Override
	public String toString() {
		return "ReviewDTO [nickName=" + rnickName + ", starPoint=" + starPoint + ", review=" + review + ", rtitle="
				+ rtitle + ", rno=" + rno + ", rdate=" + rdate + "]";
	}

	// 필드
	private String rnickName;
	private String starPoint; // 별점
	private String review;
	private String rtitle;
	private int rno; 
	private Date rdate;

	public ReviewDTO() {}
	
	public ReviewDTO(String rnickName, String starPoint, String review, String rtitle, int rno) {
		this.rnickName = rnickName;
		this.starPoint = starPoint;
		this.review = review;
		this.rtitle = rtitle;
		this.rno = rno;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getRtitle() {
		return rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	// 메서드
	public String getRnickName() {
		return rnickName;
	}

	public String getStarPoint() {
		return starPoint;
	}

	public String getReview() {
		return review;
	}

	public void setRnickName(String rnickName) {
		this.rnickName = rnickName;
	}

	public void setStarPoint(String starPoint) {
		this.starPoint = starPoint;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

}