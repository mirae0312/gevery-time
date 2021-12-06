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
(seq_board_no.nextval,'bb1b' ||'-'|| to_char(seq_board_no.currval),to_char(seq_board_no.currval)||'번 게시물 - 강아지','hyungzin0309','안녕하세요',default,default,default);
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
insert into board_comment(no,or_code,comment_level,writer,content,board_no,comment_ref,like_count,reg_date) values
(seq_board_comment_no.nextval,'bb1c-'||to_char(seq_board_comment_no.currval),2,'hyungzin0309','반갑습니다.',649,21,default,default);
commit;
select * from board_comment;
--========================================================
-- 테이블 생성 쿼리들
select * from tab;
select * from member;
commit;

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
select * from attachment;
select * from member;

select * from attachment;


select * from attachment where or_no = 'bb1b-649';

select b.*, (select count(*) from attachment where or_no = b.or_code) attach_count from board b;
select * from (select row_number() over(order by no desc)  rnum, b.*,(select count(*) from attachment where or_no = b.or_code) attach_count,(select count(*) from board_comment where board_no = b.no) comment_count from board b where REGEXP_LIKE(or_code,'bb1b|bb2b')) where rnum between ? and ?;
update board   set title = 'asdf', content = 'asdf' where no = 200;

delete from board where no = 201;
commit;

select * from board where no = (select seq_board_no.currval from dual);
select seq_board_no.currval from dual;
select * from seq;

alter sequence seq_board_no increment by -1;
select  seq_board_no.currval from dual;
alter sequence seq_board_no increment by 1;
commit;

select*from board_comment;
select * from board_comment start with comment_level = 1 connect by prior no = comment_ref order siblings by no;
insert into board_comment(no, or_code, comment_level, writer, content, board_no, comment_ref, like_count, reg_date) values(seq_board_comment_no.nextval,'bb1c' || '-' || to_char(seq_board_comment_no.currval, 1, 'hyungzin0309', 'gdgd', '649', null, default, default);


select * from member;
select * from info_review;

commit;

where REGEXP_LIKE(or_code,\'bb3b|bb4b|bb5b\')

select * from (select row_number () over (order by like_count desc) rnum, b.* from board b where REGEXP_LIKE(or_code,'bb1b|bb2b') and reg_date between sysdate-7 and sysdate ) where rnum between 1 and 10;

select * from board where reg_date between DATE_SUB(CURDATE(), INTERVAL 3 DAY)  AND CURDATE() );

select sysdate from dual;

select * from (select row_number () over (order by like_count desc) rnum, b.* from board b where REGEXP_LIKE(or_code,'bb1b|bb2b') and reg_date between (sysdate-7) and sysdate) where rnum between 1 and 10;
select * from (select row_number () over (order by like_count desc) rnum, (select count(*) from attachment where or_no in (select or_code from board where no = b.no)) attach_count, (select count(*) from board_comment where board_no = b.no) comment_count, b.* from board b where REGEXP_LIKE(or_code,'bb1b|bb2b') and reg_date between (sysdate-7) and sysdate) where rnum between 1 and 10;

select count(*) from attachment where or_no = (select or_code from board b where no = 701);

select or_code from board b where no = 200;
select or_code from board b where no = b.no;


select * from (select row_number() over(order by reg_date desc) rnum, b.* from board b where writer = 'hyungzin0309') where rnum between 1 and 10;
select * from (select row_number() over(order by reg_date desc) rnum, (select count(*) from attachment where or_no = (select or_code from board where no = b.no)) attach_count, (select count(*) from board_comment where board_no = b.no) comment_count, b.* from board b where writer = 'hyungzin0309') where rnum between 1 and 10;
select * from (select row_number() over(order by no desc)  rnum, b.*,(select count(*) from attachment where or_no = b.or_code) attach_count,(select count(*) from board_comment where board_no = b.no) comment_count from board b where writer = ?) where rnum between ? and ?;

create table board_like(
    no_and_id varchar2(1000),
    constraints pk_board_like_no_and_id primary key(no_and_id)
);

insert into comment_like values ('81-hyungzin0309');
select * from comment_like;

create or replace trigger trig_board_point
    after
    insert on board
    for each row
begin
    if inserting then
         insert into pointhistory values(seq_pointhistory_no.nextval,:new.writer,null,5,default,:new.or_code,null,'I');
    end if;
end;
/
create or replace trigger trig_point_in
    after
    insert on pointhistory
    for each row
    when (new.div='I')
begin
    if inserting then
         update point set balance = balance + :new.deposit where member_id = :new.member_id;
    end if;
end;
/
create or replace trigger trig_point_out
    after
    insert on pointhistory
    for each row
    when (new.div='O')
begin
    if inserting then
         update point set balance = balance + :new.deposit where member_id = :new.member_id;
    end if;
end;
/
create or replace trigger trig_point_list
    after
    insert on member
    for each row
begin
    if inserting then
         insert into point values(0,:new.member_id);
    end if;
end;
/
commit;

select count(*) from board where where or_code like 'bb1b%';
select count(*) from board where or_code like 'bb1b%';
select * from (select row_number () over (order by like_count desc) rnum, (select count(*) from attachment where or_no = (select or_code from board where no = b.no)) attach_count, (select count(*) from board_comment where board_no = b.no) comment_count, b.* from board b where REGEXP_LIKE(or_code,'bb3b|bb4b|bb5b') and reg_date between (sysdate-7) and sysdate) where rnum between 1 and 10;

select*from pointhistory;

SELECT * 

FROM ALL_TAB_COLUMNS

WHERE TABLE_NAME = 'POINTHISTORY';
--drop table pointhistory;
create table pointhistory(
 no number not null,
 member_id varchar2(100),
 withdraw number,
 deposit number,
 reg_date date default sysdate,
 history varchar2(200),
 purchase_uid varchar2(300),
 div varchar2(1),
 constraint uq_pointhistory_no unique(no),
 constraint ck_pointhistory_div check (div in ('I', 'O')),
 constraint pk_pointhistory_user foreign key(member_id)  references member(member_id) on delete set null
);
