select * from tab;
select * from product;
select * from board;
select * from member;
 
 
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


select * from qna_board;






