
select * from user;
delete from user where id='ssafy1';
delete from user;

SELECT * FROM jblog.blog;
delete from blog where blog_id='ssafy1';
delete from blog;
insert blog values('ssafy', 'ssafy의 블로그', '/assets/images/spring-logo.jpg');

select * from category;
delete from category;
insert category values(null, '일상', '테스트용 일상 카테고리입니다.', 'ssafy');

select * from post;
delete from post;
insert post values(null, 'ssafy', 'ssafy의 블로그', now(), 2);
