select * from tab;
select * from product;
select * from board;
select * from member;
 
 select * from business;
 
create table QNA_BOARD(
    no number,
    title varchar2(300) not null,
    writer varchar2(100),
    password varchar2(100),
    content varchar2(4000) not null,
    read_count number default 0,
    reply_level number default 1,  
    reply_ref number,  
    category_a varchar2(200),
    category_b varchar2(200),
    reg_date date default sysdate,
    constraint pk_qna_board_no primary key(no),
    constraint fk_qna_board_writer foreign key(writer) references member(member_id) on delete set null,
    constraint fk_qna_board_reply_ref foreign key(reply_ref) references qna_Board(no) on delete cascade

);
    
 
create sequence seq_qna_board_no;

commit;
select * from member;
select * from qna_board;

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문있습니다!!','honggd','1234','물건 언제 배송되나요?!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문1있습니다!!','honggd','1234','로그인안됩니다!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문2있습니다!!','honggd','1234','로그인안됩니다!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문3있습니다!!','honggd','1234','로그인안됩니다!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문4있습니다!!','honggd','1234','로그인안됩니다!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문5있습니다!!','honggd','1234','로그인안됩니다!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문6있습니다!!','honggd','1234','로그인안됩니다!!' ,0);


insert into qna_board 
(no,title,writer,password,content,read_count)
values (seq_board_no.nextval,'질문7있습니다!!','honggd','1234','로그인안됩니다!!' ,0);

insert into qna_board 
(no,title,writer,password,content,read_count,category_a)
values (seq_board_no.nextval,'질문8있습니다!!','honggd','1234','로그인안됩니다!!' ,0,'로그인관련');

insert into qna_board 
(no,title,writer,password,content,read_count,category_a)
values (seq_board_no.nextval,'질문9있습니다!!','honggd','1234','로그인안됩니다!!' ,0,'로그인관련');

commit;

 
select * from qna_board order by reg_date desc;

select * from (select row_number () over(order by no desc) rnum, B.* from QNA_BOARD B) where rnum between 1 and 5;


--답변
insert into
    qna_board
values(
    seq_qna_board_no.nextval,
    '[답변] 질문있습니다',
    'honggd',
    '1234',
    '답변을 드리겠습니다',
    null,
    2,
    null,
    null,
    null,
    default
    );
    
insert into
    qna_board
values(
    seq_qna_board_no.nextval,
    '[답변] 질문있습니다',
    'honggd',
    '1234',
    '답변을 드리겠습니다',
    null,
    2,
    55,
    null,
    null,
    default
    );

insert into
    qna_board
values(
    seq_qna_board_no.nextval,
    '[답변] 1질문있습니다',
    'honggd',
    '1234',
    '답변을 드리겠습니다',
    null,
    2,
    55,
    null,
    null,
    default
    );


select
   * 
from
    qna_board 
start with 
    reply_level = 1 
connect by
    prior no = reply_ref--부모의 no컬럼이 자식행의 reply_ref
order siblings by
    no ;
    
select writer from qna_board start with 
reply_level = 1 
connect by  prior no = reply_ref order siblings by
    no ;

select * from qna_board;


create table FAQ_BOARD(
    no number,
    title varchar2(300) not null,
    writer varchar2(100),
    content varchar2(4000) not null,
    category_a varchar2(200),
    reg_date date default sysdate,
    constraint pk_faq_board_no primary key(no),
    constraint fk_faq_board_writer foreign key(writer) references member(member_id) on delete set null
);
create sequence seq_faq_board_no;
select * from faq_board;

select count(*) from faq_board;
insert into
    faq_board
values(
    seq_faq_board_no.nextval,
    '회원가입이 안됩니다.',
    'honggd',
    '회원가입버튼은 화면 상단에 위치~~~~~~~',
    '[회원가입관련]',
    default
    );

insert into
    faq_board
values(
    seq_faq_board_no.nextval,
    '회원정보 변경하고 싶어요.',
    'honggd',
    '회원정보변경버튼은 화면 상단에 위치~~~~~~~',
    '[회원가입관련]',
    default
    );
 
    
--select * from (select row_number() over(order by no desc) rnum, b.* from faq_board b) where rnum between ? and ?
 
 delete from faq_board;
select * from faq_board;
    
select * from faq_board where category_a  = '[회원가입관련]';
    
insert into faq_board values( seq_faq_board_no.nextval, '회원정보 변경하고 싶어요.', 'honggd', '회원정보변경버튼은 화면 상단에 위치~~~~~~~',  '[회원정보문의]', default );
insert into faq_board values( seq_faq_board_no.nextval, '결제금액이 이상해요.', 'honggd', '결제금액이 이상한 경우~~~~~~~~~~~',  '[결제문의]', default );
insert into faq_board values( seq_faq_board_no.nextval, '회원정보 수정을 하고 싶어요.', 'honggd', '신고~~~~~~~~~~~',  '[회원정보문의]', default );
insert into faq_board values( seq_faq_board_no.nextval, '회원 탈퇴를 하고 싶어요.', 'honggd', '신고~~~~~~~~~~~',  '[회원정보문의]', default );
insert into faq_board values( seq_faq_board_no.nextval, '포인트 조회를 하고 싶어요.', 'honggd', '신고~~~~~~~~~~~',  '[포인트문의]', default );
 
 

select * from member;
select * from qna_board;
select count(*) from qna_board where reply_ref=181;
select * from qna_board where no=reply_ref;

select count(*)
from(
select * from qna_board where no =181
union 
select * from qna_board where reply_ref = 181)
where reply_ref = 181;

select count(*)
from(
select * from qna_board where no =122
union 
select * from qna_board where reply_ref = 122)
where reply_ref = 122;

select * from qna_board where no=308;

update qna_board set category_b='OK' where no = 188;
 
create table CLIENT_REPORT(
    report_no number,
    title varchar2(500) not null,
    content varchar2(2000) not null,
    report_code number,
    report_check varchar2(10) default 'U' ,
    member_id varchar2(100),
    business_no varchar2(100),
    constraint pk_report_no primary key(report_no),
    constraint uni_report_code_unique unique(report_code),
    constraint ck_report_check check(report_check in('U','C')),
    constraint fk_business_no foreign key(business_no)  references business(business_no) on delete cascade,
    constraint fk_member_id foreign key(member_id)  references member(member_id) on delete cascade 
);
select * from CLIENT_REPORT;
SELECT * FROM MEMBER;
select * from business;

alter table CLIENT_REPORT drop column business_no;
 
create sequence SEQ_CLIENT_REPORT_REPORT_NO;

COMMIT;

insert into CLIENT_REPORT
(report_no,title,content,reg_date,member_id)
values (SEQ_CLIENT_REPORT_REPORT_NO.nextval,'부적절한 게시글 올렸어요','신고합니다',default,'mirim');
 
alter table client_report add reg_date date default sysdate;

select * from member;
 
select * from qna_board;
select * from CLIENT_REPORT;
commit;


select * from qna_board where reply_ref = 194;

select * from qna_board where no= 324;

update client_report set report_check = 'C' where report_no = 6;

rollback;

select * from (select row_number() over(order by report_no asc) rnum, b.* from client_report b) where rnum between 1 and 3;
delete client_report where report_no = 32;