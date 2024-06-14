package com.green.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Enum 클래스를 상속받아서 각 상수명의 객체(class)가 생성된다.
//정한 이름을 가지는 상수들의 집합을 정의 static final로 선언됨
@Getter
@RequiredArgsConstructor
public enum Division {
	
	//상수필드 고정값, 에러를 줄일수 있다. String과 int(auto increment)가 자동으로 부여된다.(int 값은 0부터 1씩 증가)
	USE(1, "영화관 이용"),			//String name="USE", int ordinal=0
	SPECIAL(2, "스페셜관"),		//String name="SPECIAL", int ordinal=1
	LPOINT(3, "L.POINT"),			//String name="LPOINT", int ordinal=2
	MEMBER(4, "회원"),			//String name="MEMBER", int ordinal=3
	MEMBER_SHIP(5, "멤버쉽"),	//String name="MEMBER_SHIP", int ordinal=4
	ONLINE(6, "온라인"),			//String name="ONLINE", int ordinal=5
	BENEPIT(7, "할인혜택"),		//String name="BENEPIT", int ordinal=6
	TICKET(8, "관람권"),			//String name="TICKET", int ordinal=7
	STORE(9, "스토어"),			//String name="STORE", int ordinal=8
	LOTTHY(10, "롯시클럽");			//String name="LOTTHY", int ordinal=9
	
	private final int no;
	private final String koName;

}
