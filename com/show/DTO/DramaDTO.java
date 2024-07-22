package com.show.DTO;

public class DramaDTO extends ShowDTO {


		@Override
	public String toString() {
		return super.toString();
	}

		// 드라마에 장르 추가.
		private String romance;	// 로맨스
		private String comedy;	// 코미디
		private String action;	// 액션
		private String sf;		// SF(Science fiction)
		private String mystery;	// 미스터리(추리, 공포, 호러 등)
		private String fantasy;	// 판타지


		public DramaDTO() {
			// 기본생성자
		}

		public DramaDTO(String program, String category, String title, String director, String actor, String contents) {
			// ShowDTO 부모생성자 받음.
			super(program, category, title, director, actor, contents);
		}

		// 메서드 -> getter/setter
		public String getRomance() {
			return romance;
		}

		public String getComedy() {
			return comedy;
		}

		public String getAction() {
			return action;
		}

		public String getSf() {
			return sf;
		}

		public String getMystery() {
			return mystery;
		}

		public String getFantasy() {
			return fantasy;
		}

		public void setRomance() {
			this.romance = "로맨스";
		}

		public void setComedy() {
			this.comedy = "코미디";
		}

		public void setAction() {
			this.action = "액션";
		}

		public void setSf() {
			this.sf = "SF";
		}

		public void setMystery() {
			this.mystery = "미스터리";
		}

		public void setFantasy() {
			this.fantasy = "판타지";
		}

	}

