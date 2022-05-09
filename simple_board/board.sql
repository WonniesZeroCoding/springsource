create table spring_board(
	bno number(10,0),
	title varchar2(200) not null,
	content varchar2(2000) not null,
	writer varchar2(50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);

create sequence seq_board;

select * from spring_board;

-- 댓글 수 컬럼 추가 // 테이블 수정은 alter
alter table spring_board add(replycnt number default 0);

-- 기존 댓글 업데이트
update spring_board set replycnt = (select count(rno) from SPRING_REPLY where spring_board.bno = SPRING_REPLY.bno);

-- oracle에서 페이지 나누기

-- 더미 데이터
insert into spring_board(bno,title,content,writer)
(select seq_board.nextval, title, content,writer from spring_board);
--   --> rownum 사용하겠다 rownum == 가상 칼럼

select rownum, bno, title from spring_board;

-- rownum 은 order by 절과 함께 사용 시 주의
-- order by 절에서 사용하는 칼럼이 index가 아닐 때 주의
-- 임의로 행을 가지고 나온 후 번호를 붙임 --> 인라인 쿼리
-- 1) rownum 사용 방식
select rownum, bno, title, writer 
from (select bno, title, writer from spring_board order by bno desc)
where rownum<=10;

-- select, rownum, ......
-- from(select * from board where bno>0 order by re_ref desc)

-- 2) order by 칼럼이 인덱스라면 오라클 힌트 이용가능
-- * 아래 힌트 문구는 사이 띄기 하면 안된다
select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum, bno, title, writer 
from spring_board
where rownum <= 10;

-- 1page 최신글 가져오기
select bno, title, writer, regdate, updatedate
from (
	select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	from spring_board
	where rownum <= 10
)
where rn > 0;

-- 2page 최신글 
select bno, title, writer, regdate, updatedate
from (
	select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	from spring_board
	where rownum <= 20
)
where rn > 10;

-- 페이지 나누기 + 검색

--1) 타이틀이 ~~ 인 것을 검색하고 싶다
-- 검색 시 주소줄 ) pageNum=1&amount=10&type=T&keyword=~~
select bno, title, writer, regdate, updatedate
from(select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title, writer, regdate, updatedate 
	 from spring_board
	 where bno>0 and (title like '%~~%' || content like '%~~%' ) and rownum <= (1*30)
)
where rn > (1-1) * 30

-- 댓글
create table spring_reply(
	rno number(10,0), -- 댓글 번호
	bno number(10,0) not null, -- 원본글 번호
	reply varchar2(1000) not null, -- 댓글 내용
	replyer varchar2(50) not null, -- 댓글 작성자
	replydate date default sysdate, -- 댓글 작성 날짜
	updatedate date default sysdate -- 댓글 수정 날짜
	
);

--댓글 시퀀스
create sequence seq_reply;

--댓글 테이블 pk 설정 후 이름 지정
alter table spring_reply add constraint pk_reply primary key(rno);

--외래키 제약
alter table spring_reply add constraint fk_reply_board foreign key(bno)
references spring_board(bno);

select * from spring_board where bno=1307;

-- index 생성
create index idx_reply on spring_reply(bno desc,rno asc);

-- 첨부파일 테이블 생성
create table spring_attach(
	uuid varchar2(100) not null,
	uploadpath varchar2(200) not null,
	filename varchar2(100) not null,
	filetype char(1) default '1',
	bno number(10,0)
);

alter table spring_attach add constraint pk_attach primary key(uuid);
alter table spring_attach add constraint fk_board_attach foreign key(bno) references spring_board(bno);


select * from spring_attach;
