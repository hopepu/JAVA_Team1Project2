package com.show.DTO;

public class ReviewDTO {
	// 필드
	private String nickName;
	private String starPoint; // 별점
	private String review;
	private String title;
	private int rno; 

	public ReviewDTO() {}
	
	public ReviewDTO(String nickName, String starPoint, String review, String title, int rno) {
		this.nickName = nickName;
		this.starPoint = starPoint;
		this.review = review;
		this.title = title;
		this.rno = rno;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// 메서드
	public String getNickName() {
		return nickName;
	}

	public String getStarPoint() {
		return starPoint;
	}

	public String getReview() {
		return review;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setStarPoint(String starPoint) {
		this.starPoint = starPoint;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
