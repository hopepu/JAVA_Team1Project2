package DTO;

public class ShowDTO {
	private String showNo;
	private String platform;
	private String category;
	private String title;
	private String director;
	private String actor;
	private String contents;
	private String main_jpg;
	private String info_jpg;
	private String info_day;
	
	public ShowDTO() {}

	public String getMain_jpg() {
		return main_jpg;
	}

	public void setMain_jpg(String main_jpg) {
		this.main_jpg = main_jpg;
	}

	public String getInfo_jpg() {
		return info_jpg;
	}

	public void setInfo_jpg(String info_jpg) {
		this.info_jpg = info_jpg;
	}

	
	public String getShowNo() {
		return showNo;
	}

	public String getPlatform() {
		return platform;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String getActor() {
		return actor;
	}

	public String getContents() {
		return contents;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getInfo_day() {
		return info_day;
	}

	public void setInfo_day(String info_day) {
		this.info_day = info_day;
	}

	
	
	

}
