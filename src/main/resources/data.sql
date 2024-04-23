SELECT * FROM ikeeper_homepage.field;
INSERT INTO field (field_name)
VALUES ('i-Keeper'),
       ('DEV'),
       ('CERT');

SELECT * FROM ikeeper_homepage.grade;
INSERT INTO grade (grade_name)
VALUES ('1학년 1학차'),
       ('1학년 2학차'),
       ('2학년 3학차'),
       ('2학년 4학차'),
       ('3학년 5학차'),
       ('3학년 6학차'),
       ('4학년 7학차'),
       ('4학년 8학차'),
       ('그 이상'),
       ('졸업');

SELECT * FROM ikeeper_homepage.status;
INSERT INTO status (status_name)
VALUES ('신입'),
       ('재학'),
       ('휴학'),
       ('졸업'),
       ('제명'),
       ('블랙리스트');

SELECT * FROM ikeeper_homepage.major;
INSERT INTO major (major_name)
VALUES ('없음'),
       ('컴퓨터소프트웨어학부'),
       ('컴퓨터공학전공'),
       ('사이버보안전공'),
       ('게임공학전공'),
       ('소프트웨어융합학과'),
       ('인공지능빅데이터공학과');

/* 테스트용 (추후 삭제 예정) */

SELECT * FROM ikeeper_homepage.categorylarge;
INSERT INTO categorylarge (categorylarge_name)
VALUES ('중간세미나'),
       ('개발세미나'),
       ('써트세미나');

SELECT * FROM ikeeper_homepage.categorysmall;
INSERT INTO categorysmall (categorysmall_name)
VALUES ('2023_1'),
       ('2023_2'),
       ('2024_1');