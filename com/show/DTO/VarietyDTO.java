package com.show.DTO;

public class VarietyDTO extends ShowDTO {
	   
	   @Override
	   public String toString() {
	      return super.toString();
	   }
	   
	   public VarietyDTO() {
	      // 기본생성자
	   }

	   public VarietyDTO(String program, String category, String title, String director, String actor, String contents) {
	      super(program, category, title, director, actor, contents);
	      // TODO Auto-generated constructor stub
	   } // 예능
	   
	}