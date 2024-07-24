Drop table member;
--member 테이블 생성
CREATE table member(
	mno NUMBER(5) CONSTRAINT MT_NO_PK PRIMARY KEY,
	id NVARCHAR2(8) CONSTRAINT MT_ID_UQ UNIQUE,
	pw NVARCHAR2(15) CONSTRAINT MT_PW_NN NOT NULL,
	name NVARCHAR2(5) CONSTRAINT MT_NAME_NN NOT NULL,
	nickName NVARCHAR2(10) CONSTRAINT MT_NICK_UQ UNIQUE,
	birth Date,
	sex NVARCHAR2(2),
	phone NVARCHAR2(20),
	mail NVARCHAR2(50),
	author NVARCHAR2(10) );
--mno 시퀀스 생성	
CREATE SEQUENCE mem_seq
	INCREMENT BY 1
	START WITH 1
	NOCACHE;
--alter table member MODIFY sex constraint MT_SEX_CK CHECK sex IN ('남','여'); 
--sql2는 도메인을 명시적으로 정의하지만, 오라클은 지원하지 않음

--alter table member rename Column author To authorname; --필드명 변경
--alter table member add constraint MT_AT_FK foreign key authorname REFERENCES author(authorname);


--더미데이터
insert into MEMBER (mno, id, pw, name, nickName, birth, sex, phone, mail, author) 
VALUES (mem_seq.nextval, 'aaa', 'aaa', 'aname', 'anick', TO_DATE('2020/01/01','YYYY/MM/DD'),'여','010-123-4567','aaa@mail.com','MANAGER');
insert into MEMBER (mno, id, pw, name, nickName, birth, sex, phone, mail, author) 
VALUES (mem_seq.nextval, 'bbb', 'bbb', 'bname', 'bnick', TO_DATE('1999/12/31','YYYY/MM/DD'),'남','010-123-4567','bbb@mail.com','USER');

update member set mail = 'bbb@mail.com' where id='bbb';

select * from MEMBER;
select * from author;

select * from show.board;
grant select on member to show;
select id from member where name = 'aname' and birth = '2020/01/01' and sex = '여';
select * from member where id ='aaa' and birth='2020/01/01' and phone = '010-123-4567';
update member set nickName = 'newnick' , phone='010-123-4567', mail='aaa@mail.com' where id='aaa';
delete from member where id='bbb' and pw='bbb';
--변경하지 않은 값은 예전 값으로 넣으면 알아서 들어감

--author테이블(외래키용으로 했으나 잘 연결되지 않아 사용하지 않음)
CREATE TABLE author(
	alevel NUMBER(2) NOT NULL,
	authorname NVARCHAR2(10) PRIMARY KEY
);

--alter table author RENAME column alevel TO level; //level은 필드명 생성 안됨
--enum 데이터 입력(고정값)
insert into author values(0,'GUEST');
insert into author values(1,'USER');
insert into author values(2,'MANAGER');
insert into author values(3,'ADMIN');

select * from author;

--동의어 만들기
CREATE public SYNONYM M for member; 
CREATE public SYNONYM A for author;

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