/*
  -- 일련(회원)번호 관리객체
create sequence seq_member_idx

-- 회원테이블
create table member
(
   mem_idx   	int,							-- 회원번호
   mem_name  	varchar2(100) not null,			-- 회원명
   mem_id    	varchar2(100) not null,			-- 아이디
   mem_pwd   	varchar2(100) not null,			-- 비밀번호
   mem_zipcode 	char(5)     not null,			-- 우편번호
   mem_addr  	varchar2(1000) not null,		-- 주소
   mem_ip    	varchar2(100) not null,			-- 아이피
   mem_regdate 	date  default sysdate,			-- 가입일자
   mem_grade 	varchar2(100) default '일반' 	-- 회원등급
)

-- 기본키
alter table member
  add constraint  pk_member_idx  primary key(mem_idx) ;

-- 아이디(unique)
alter table member
  add constraint unique_member_id unique(mem_id) ;

-- 회원등급(check)
alter table member
  add constraint ck_member_grade  check(mem_grade in('일반','관리자')) ;
  
  
  -- sample data
insert into member values(seq_member_idx.nextVal,
                          '김관리',
                          'admin',
                          'admin',
                          '00000',
                          '서울시 관악구',
                          '127.0.0.1',
                          sysdate,
                          '관리자'
                          ) 
                          
insert into member values(seq_member_idx.nextVal,
                          '일길동',
                          'one',
                          '1234',
                          '00000',
                          '서울시 관악구',
                          '127.0.0.1',
                          default,
                          default
                          )       
                          
insert into member values(seq_member_idx.nextVal,
                          '홍길동',
                          'hong',
                          '1234',
                          '00000',
                          '서울시 관악구',
                          '127.0.0.1',
                          default,
                          default
                          )                      
  
 */




/*
--게시판 일련번호 관리 객체
create sequence seq_board_idx


 --게시판DB
 
create table board
(
	b_idx		int,					-- 일련번호
	b_subject   varchar2(200) not null,	-- 제목
	b_content   clob not null,			-- 내용
	b_ip		varchar2(100) not null,	-- 아이피
	b_regdate	date,					-- 작성일자
	b_readhit   int      default 0,		-- 조회수
	b_use		char(1)  default 'y',	-- 사용유무
	mem_idx		int,					-- 작성자회원번호
	mem_name	varchar2(100),			-- 작성자명
	b_ref		int,					-- 참조글번호
	b_step		int,					-- 글순서
	b_depth		int						-- 글깊이
)


select * from member

--기본키 지정
alter table board
	add constraint pk_board_idx primary key(b_idx);
	
--외래키 지정
alter table board
   add constraint  fk_board_mem_idx foreign key(mem_idx)  
                                    references member(mem_idx) ;


--새글쓰기
insert into board values(seq_board_idx.nextVal,
                         '내가 1등...',
                         '내가 첫번째로 등록했네...',
                         '192.168.10.123',
                         sysdate,
                         0,
                         'y',
                         22,
                         '일길동',
                         seq_board_idx.currVal,
                         0,
                         0
                         )  

--답글쓰기
insert into board values(seq_board_idx.nextVal,
						'답글쓰기1의 제목',
						'답글쓰기1의 내용',
						'192.168.10.2',
						sysdate,
						0,
						'y',
						23,
						'홍길동',
						1,
						1,
						1
						)
						
--메인답글에 대댓글
insert into board values(seq_board_idx.nextVal,
						'답글쓰기1의 댓글 제목1',
						'답글쓰기1의 내용',
						'192.168.10.2',
						sysdate,
						0,
						'y',
						22,
						'일길동',
						1,
						2,
						2
						)

select * from board	order by b_ref desc,b_step asc


create table newsItem
(

)


select * from newsitem


*/