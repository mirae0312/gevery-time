--========================================================
--조회커리들 
select * from member;
select * from board;
select * from board_comment;
--========================================================
-- member생성 쿼리
insert into member(member_id, password, member_name, phone, address, email, member_role, member_type, birthday)
values('honggd',1234,'홍길','01011111111','대한민국','honggd@naver.com','U','N','1996/03/09');

-- board생성 쿼리
-- delete from board;
insert into board(no, or_code, title, writer, content, read_count,like_count,reg_date)values
(seq_board_no.nextval,'bb1b-' || to_char(seq_board_no.currval),to_char(seq_board_no.currval)||'번 게시물 - 강아지','hyungzin0309','안녕하세요',default,default,default);
insert into board(no, or_code, title, writer, content, read_count,like_count,reg_date)values
(seq_board_no.nextval,'bb2b-' || to_char(seq_board_no.currval),to_char(seq_board_no.currval)||'번 게시물 - 고양이','hyungzin0309','안녕하세요',default,default,default);
insert into board(no, or_code, title, writer, content, read_count,like_count,reg_date)values
(seq_board_no.nextval,'bb3b-' || to_char(seq_board_no.currval),to_char(seq_board_no.currval)||'번 게시물 - 가는거','hyungzin0309','안녕하세요',default,default,default);
insert into board(no, or_code, title, writer, content, read_count,like_count,reg_date)values
(seq_board_no.nextval,'bb4b-' || to_char(seq_board_no.currval),to_char(seq_board_no.currval)||'번 게시물 - 먹는거','hyungzin0309','안녕하세요',default,default,default);
insert into board(no, or_code, title, writer, content, read_count,like_count,reg_date)values
(seq_board_no.nextval,'bb5b-' || to_char(seq_board_no.currval),to_char(seq_board_no.currval)||'번 게시물 - 쓰는거','hyungzin0309','안녕하세요',default,default,default);

-- board_comment생성 쿼리
--delete from board_comment where no = 16;
--insert into board_comment(no,or_code,comment_level,writer,content,board_no,comment_ref,like_count,reg_date) values
--(seq_board_comment_no.nextval,'bb1c-'||to_char(seq_board_comment_no.currval),2,'hyungzin0309','반갑습니다.',146,11,default,default);


--========================================================
-- 테이블 생성 쿼리들
select * from tab;
select * from member;

create table board(
    no number,
    or_code varchar2(100),
    title varchar2(100) not null,
    writer varchar2(20),
    content varchar2(4000) not null,
    read_count number default 0,
    like_count number default 0,
    reg_date date default sysdate,
    constraint pk_board_no primary key(no),
    constraint fk_board_writer foreign key(writer)  references member(member_id) on delete set null
);

--drop sequence seq_board_no;
create sequence seq_board_no;
create sequence seq_board_or_code;

-- drop table board_comment;
create table board_comment(
    no number,
    or_code varchar2(100),
    comment_level number default 1, -- 댓글 1, 대댓글 2
    writer varchar2(15),
    content varchar2(2000),
    board_no number,
    comment_ref number, -- 대댓글인 경우, 참조하는 댓글번호, 댓글인 경우 NULL
    like_count number default 0,
    reg_date date default sysdate,
    constraint pk_board_comment_no primary key(no),
    constraint fk_board_comment_writer foreign key(writer) references member (member_id) on delete set null,
    constraint fk_board_comment_board_no foreign key(board_no) references board(no) on delete cascade,
    constraint fk_board_comment_comment_ref foreign key (comment_ref) references board_comment(no) on delete cascade
);


create sequence seq_board_comment_no;
create sequence seq_board_comment_or_code;

commit;

select * from board;
select * from board where or_code like 'bb1b%';
update board set like_count=97 where no=204;
select * from (select row_number() over(order by like_count desc) rnum, b.* from board b where or_code like 'bb4b%') where rnum between 11 and 20;

select * from (select row_number() over(order by no desc) rnum, b.* from board b where REGEXP_LIKE(or_code, 'bb1b|bb2b'));

select * from (select row_number() over(order by no desc) rnum, b.* from board b where REGEXP_LIKE(or_code,'bb1b|bb2b'));
select * from (select row_number() over(order by like_count desc) rnum, b.* from board b where REGEXP_LIKE(or_code,'bb1b|bb2b')) where rnum between 11 and 20;
