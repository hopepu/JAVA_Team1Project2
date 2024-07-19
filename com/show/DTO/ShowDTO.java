package com.show.DTO;

public class ShowDTO {
	@Override
	   public String toString() {
	      return "[" + program + "]" + title + " | " + category + "\n감독:" + director + "\n출연진:" + actor + "\n" + contents;
	   }


	// 필드
	private String title;
	private String contents; // 내용설명
	private String program; // 프로그램 종류 -> 티빙, 넷플릭스, 디즈니
	private String category; // 장르종류 -> 로맨스, 액션 등등
	private String director;
	private String actor;
	private String no;

	// 생성자
	public ShowDTO() {
		// 기본생성자
	}

	public ShowDTO(String program, String category, String title, String director, String actor, String contents) {
		this.program = program;
		this.category = category;
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.contents = contents;

	}

	public void showList(String i) {
		setNo(i);
		System.out.println(i+".  [" + getProgram() + "]" + " | " + getTitle());

	}

	public void showInfo() {
		System.out.println("[" + getProgram() + "]" + " | " + getTitle());
		System.out.println(getCategory());
		System.out.println("감독:" + getDirector());
		System.out.println("출연진:" + getActor());
		System.out.println("---------------------------------------");

	}

	// 메서드
	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getProgram() {
		return program;
	}

	public String getDirector() {
		return director;
	}

	public String getActor() {
		return actor;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setPrograme(String program) {
		this.program = program;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}
