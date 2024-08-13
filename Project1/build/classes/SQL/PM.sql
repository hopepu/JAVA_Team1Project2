CREATE TABLE author(
	alevel NUMBER(2) PRIMARY KEY, 
	authorname NVARCHAR2(10) NOT NULL
);

select * from author;

--author테이블 : 데이터 입력(고정값)=enum 데이터
insert into author values(0,'GUEST');
insert into author values(1,'USER');
insert into author values(2,'MANAGER');
insert into author values(3,'ADMIN');

--member 테이블
CREATE table member(
	mno NUMBER(5) ,
	id NVARCHAR2(10) CONSTRAINT MT_ID_UQ UNIQUE,
	pw NVARCHAR2(15) CONSTRAINT MT_PW_NN NOT NULL,
	name NVARCHAR2(10) CONSTRAINT MT_NAME_NN NOT NULL,
	nickName NVARCHAR2(10) CONSTRAINT MT_NICK_PK PRIMARY KEY, --24.07.30수정. 닉네임을 pk로 수정
	birth Date,
	sex NVARCHAR2(2),
	phone NVARCHAR2(20),
	mail NVARCHAR2(50),
	ath NUMBER(2) );    --24.07.30수정. 숫자입력을 위해 권한 번호로 입력
	
alter table member add constraint MT_AT_FK foreign key (ath) REFERENCES author(alevel);	--외래키 제약조건 추가
alter table member modify name NVARCHAR2(10);
alter table member rename column author to ath;

--member 테이블 : mno 시퀀스 생성	
CREATE SEQUENCE member_seq
	INCREMENT BY 1
	START WITH 1
	NOCYCLE
	NOCACHE;
	
select * from MEMBER;	
delete from member where id = 'whdydwo2';
delete from member where id ='aaa1' cascade;
drop sequence member_seq;
drop table member;

select count(*) as cnt from member where id=aaa and pw=aaa;

--더미데이터
insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) 
VALUES (member_seq.nextval, 'aaa1', 'aaa1', 'anam1', 'anick', TO_DATE('2020/01/01','YYYY/MM/DD'),'여','010-123-4567','aaa@mail.com',2);
insert into MEMBER (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) 
VALUES (member_seq.nextval, 'bbb', 'bbb', 'bname', 'bnick', TO_DATE('1999/12/31','YYYY/MM/DD'),'남','010-123-4567','bbb@mail.com',1);
insert into MEMBER (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) 
VALUES (member_seq.nextval, 'kkk', '1234', '김지선', 'admin', TO_DATE('1977/10/17','YYYY/MM/DD'),'여','010-123-4567','admin@mail.com',3);
insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) 
VALUES (member_seq.nextval, 'ccc', 'ccc', 'ccc', 'ccc', TO_DATE('2000/02/28','YYYY/MM/DD'), '남', '010-123-4567', 'ccc@mail.com', 1);
insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) 
VALUES (member_seq.nextval, 'DDD', 'DDD', 'DDD', 'DDD', TO_DATE('2000/02/28','YYYY/MM/DD'), '남', '010-123-4567', 'ccc@mail.com', 1);
insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) 
VALUES (member_seq.nextval, 'aaa', 'aaa', 'aaa', 'aaa', TO_DATE('2020/01/01','YYYY/MM/DD'),'여','010-123-4567','aaaa@mail.com',2);

update member set mail='aaa2@gmail.com', pw='abcdefg', phone='010-1234-4567', name='aaa', nickName='aaa' where id='aaa';

insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) values (mem_seq.nextval, ?, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'), ?, ?, ?, ?)

-----탈퇴회원용 백업테이블 & 트리거
CREATE table del_member AS select * from member where 1<>1; --백업용 테이블 생성
Alter table del_member add deldate Date;--삭제일 컬럼 추가

CREATE OR REPLACE TRIGGER del_backup 
AFTER
DELETE ON member
FOR EACH ROW 
BEGIN 
    IF DELETING THEN
    INSERT INTO del_member 
    VALUES (:old.mno, :old.id, :old.pw, :old.name, :old.nickName, :old.birth, :old.sex, :old.phone, :old.mail, :old.author, sysdate) ;
    END IF;
 END;



 
 drop trigger del_backup
 
 
 

select * from show.board;
grant select on member to show;
select id from member where name = 'aname' and birth = '2020/01/01' and sex = '여';
select * from member where id ='aaa' and birth='2020/01/01' and phone = '010-123-4567';
update member set nickName = 'newnick' , phone='010-123-4567', mail='aaa@mail.com' where id='aaa';
delete from member where id='bbb' and pw='bbb';
--변경하지 않은 값은 예전 값으로 넣으면 알아서 들어감



--alter table author RENAME column alevel TO level; //level은 필드명 생성 안됨


select * from author;

--동의어 만들기
CREATE public SYNONYM M for member; 
CREATE public SYNONYM A for author;



 
select *from member; 

-- 뷰 테이블 생성(member+author)->조인검색용
CREATE VIEW member_view As select M.mno, M.id, M.pw, M.name, M.nickName, M.birth, M.sex, M.phone, M.mail, A.alevel from member M, author A where M.author = A.authorname;
select * from member_view;

select * from member_view where id='aaa' and birth='2020/01/01' and phone='010-123-4567';
select * from (select M.mno, M.id, M.pw, M.name, M.nickName, M.birth, M.sex, M.phone, M.mail, A.alevel from member M, author A where M.author = A.authorname) where id ='aaa' and birth='2020/01/01' and phone = '010-123-4567';

update member set pw='aa' where id='aaa' and nickname='anick';
update member set pw='15587' where id='aaa' and phone='010-123-4567';

insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail)
VALUES (mem_seq.nextval, 'ccc', 'ccc', 'cname', 'cnick', TO_DATE('1998/12/31','YYYY/MM/DD'),'여','0101234567','ccc@mail.com');

update member set author=(select authorname from author where alevel=1) where id='ccc' and nickname='cnick';


drop table member;
drop table author;
drop sequence mem_seq;


create table show_tbl(
showNo number(6) primary key,       -- 번호(시퀀스 사용)
platform nvarchar2(500) not null,   -- OTT 종류 
category nvarchar2(500) not null,   -- 장르 (로맨스, 코미디, 예능, SF 등등)
title nvarchar2(500) not null,      -- 제목
director nvarchar2(500) not null,   -- 감독
actor nvarchar2(500) not null,      -- 배우
contents nvarchar2(2000) not null,   -- 작품 설명
main_jpg varchar2(100) not null,
info_jpg varchar2(100) not null,
info_day varchar2(200) not null
);

alter table show_tbl rename column into_day to info_day;

-- show 테이블 시퀀스번호 생성 / 1 부터 시작
create sequence show_seq minvalue 1 maxvalue 99999 increment by 1 start with 1 nocycle nocache noorder;
drop sequence show_seq;
delete from show_tbl;
select * from show_tbl;

update show_tbl set imagename=Poster_1 where showNo=1;
-- 더미데이터
insert into show_tbl values (show_seq.nextval,'Netflix  Tving', '로맨스', '선재업고튀어', '윤종호, 이시은', '변우석, 김혜윤, 송건희, 이승협, 정영주, 성병숙 등', '만약, 당신의 최애를 구할 수 있는 기회가 된다면? 삶의 의지를 놓아버린 순간, 자신을 살게 해줬던 유명아티스트 류선재 그의 죽음으로 절망했던 열성팬 임솔이 최애를 살리기 위해 시간을 거슬러 2008년으로 돌아간다! 다시 살게 된 열아홉, 목표는 최애 류선재를 지키는 것!', 'Poster1','info1','2024.04.08. ~ 2024.05.28(16부작)');
insert into show_tbl values (show_seq.nextval, 'Netflix', '로맨스', '이번 생도 잘 부탁해', '이나정, 최영림, 한아름', '신혜선, 안보현, 하윤경, 안동구', '반지음에겐 전생을 기억할 수 있는 신비한 능력이 있다. 18회차 인생이 이른 나이에 끝난 후 다시 태어난 그녀. 이번 생은 어느덧 어른이 된 어린 시절의 사랑을 찾는데 올인한다.', 'Poster2','info2','2023.06.17. ~ 2023.07.23(12부작)');
insert into show_tbl values (show_seq.nextval, 'Tving  Netflix', '로맨스', '낮과 밤이 다른 그녀', '이형민, 박지하', '이정은, 정은지, 최전혁 등','어느날 갑자기 노년탕임에 갇혀버린 취준생과 낮과 밤을 올타임 그녀에게 휘말린 능력캐 검사의 기상천외한 인턴쉽과 앙큼달콤 로맨틱 코미디를 그리는 드라마', 'Poster3','info3','2024.06.15. ~ 2024.08.04(16부작)');
insert into show_tbl values (show_seq.nextval, 'Tving  Netflix', '판타지', '미씽:그들이있었다', '민연홍, 반기리', '고수, 허준호, 안소희, 하준, 서은수, 송건희 등','실종된 사망자들이 모인 영혼 마을을 배경으로, 사라진 시체를 찾고 사전배후의 진일을 쫓는 미스터리 추적 판타지', 'Poster4','info4','2022.12.19. ~ 2023.01.31(14부작)');
insert into show_tbl values(show_seq.nextval, 'Tving  Netflix', '판타지', '경이로운 소문', '유선동, 김새봄', '조병규, 유준상, 김세정, 엄혜란 등','악귀 사냥꾼 카운터 들이 국수집 직원으로 위장해, 지상의 악귀들을 물리치는 통쾌하고 땀내나는 악귀타파 히어로물', 'Poster5','info5','2020.11.28. ~ 2021.01.24(16부작)');
insert into show_tbl values(show_seq.nextval, 'Tving', '액션', '스틸러:일곱 개의 조선통보', '최준배, 신경일', '주원, 이주우, 조한철, 김재원, 최화정, 이덕화 등','베일에 싸인 문화재 도둑 스컹크와 비공식 문화재 환수팀 카르마가 뭉쳐, 법이 심판하지 못하는 자들을 상대로 펼치는 케이퍼 코믹액션', 'Poster6','info6','2023.04.12. ~ 2023.05.18(12부작)');
insert into show_tbl values(show_seq.nextval, 'Tving', '판타지', '이재, 곧 죽습니다', '하병훈', '서인국, 박소담, 김지훈, 이재욱, 이도현, 고윤정 등','지옥으로 떨어지기 직전의 이재가 12번의 죽음과 삶을 경험하게 되는 인생 환승 드라마', 'Poster7', 'info7','파트1 2023.12.15 | 파트2 2024.01.05 (총8부작)');
insert into show_tbl values(show_seq.nextval, 'Netflix', '액션', '마이네임', '김바다, 김진민', '한소희, 박희순, 안보현, 김상호, 이학주, 장률','겉으로는 마약 범죄를 소탕하는 경찰. 하지만 실제로 그녀가 충성을 바치는 인물은 따로 있다. 그것은 바로, 그녀에게 새로운 삶을 주고 복수의 길을 열어준 범죄 조직의 보스', 'Poster8', 'info8','2021.10.15(8부작)');
insert into show_tbl values(show_seq.nextval, 'Tving', '스릴러', '운수 오진날', '필감성, 김민성, 송한나', '이성민, 유연석, 이정은','평범한 택시기사 오택이 고액을 제시하는 지방행 손님을 태우고 가다 그가 연쇄살인마임을 깨닫게 되면서 공포의 주행을 시작하게 되는 이야기', 'Poster9','info9','파트1 2023.11.24  |  파트2 2023.12.08(총10부작)');
insert into show_tbl values(show_seq.nextval, 'Netflix', '예능', '지구마불 세계여행', '김태호', '노홍철, 주우재, 주현영, 빠니보틀, 원지, 곽준빈','주사위를 던져 그 결과에 따라 다음 목적지가 정해지는 일생일대의 모험을 시작한다.우승자에게는 우주여행이 기다리고 있다!!', 'Poster10','info10','2023.03.04. ~ 2023.05.06(10부작)');


drop table show_tbl;

create table review(

rno number(5) primary key,
rtitle nvarchar2(500) not null,
rnickname nvarchar2(10) not null,
starpoint number(5) not null,
review nvarchar2(1000) not null,
rdate date not null

);

create sequence re_seq minvalue 1 maxvalue 99999 increment by 1 start with 1 nocycle nocache noorder;

select * from review ;


insert into review (rno,rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval,'qq', 'dd', '3', 'dd', sysdate);

select * from review where rnickname='dd' order by rno desc;

insert into review (rno, rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval, '선재업고튀어','aaa', '5', 'ㅂㅈㄷ', sysdate);
insert into review (rno, rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval, '선재업고튀어','aaa', '5', 'ㅂㅈㄷ2', sysdate);
insert into review (rno, rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval, '경이로운 소문','aaa', '5', 'ㅂㅈㄷ3', sysdate);
insert into review (rno, rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval, '운수 오진날','aaa', '5', 'ㅂㅈㄷ4', sysdate);

select * from review;


