package com.show;

public class NoExistException extends Exception{
	// 사용자 정의 Exception - 존재하지 않는 계정에 대한 예외처리
	
	//생성자1 - 기본생성자
	public NoExistException() {}
	
	// 생성자2  - 메시지 처리용 생성자
	public NoExistException(String message) {
		super(message); // catch{} 블록의 예외 처리 코드에서 이요하기 위해서 메시지를 받는다.
	}

}
