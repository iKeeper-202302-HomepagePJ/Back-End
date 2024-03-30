SELECT * FROM ikeeper_homepage.field_table;
INSERT INTO field_table (field_name)
VALUES ('i-Keeper'),
       ('개발'),
       ('CERT');

SELECT * FROM ikeeper_homepage.authority_table;
INSERT INTO authority_table (authority_name, authority_staff)
VALUES ('일반회원', 0),
       ('회장', 1),
       ('부회장', 1),
       ('CERT장', 1),
       ('CERT부장', 1),
       ('교육부장', 1),
       ('장비부장', 1),
       ('총무', 1),
       ('OB', 0),
       ('탈퇴회원', 0);

SELECT * FROM ikeeper_homepage.major_table;
INSERT INTO major_table (major_name)
VALUES ('없음'),
       ('컴퓨터소프트웨어학부'),
       ('컴퓨터공학전공'),
       ('사이버보안전공'),
       ('게임공학전공'),
       ('소프트웨어융합학과'),
       ('인공지능빅데이터공학과');

SELECT * FROM ikeeper_homepage.status_table;
INSERT INTO status_table (status_name)
VALUES ('신입'),
       ('재학'),
       ('휴학'),
       ('졸업'),
       ('제명'),
       ('블랙리스트');

SELECT * FROM ikeeper_homepage.grade_table;
INSERT INTO grade_table (grade_name)
VALUES ('1학년 1학차'),
       ('1학년 2학차'),
       ('2학년 3학차'),
       ('2학년 4학차'),
       ('3학년 5학차'),
       ('3학년 6학차'),
       ('4학년 7학차'),
       ('4학년 8학차'),
       ('그 이상');

SELECT * FROM headline_table;
INSERT INTO headline_table (headline_name)
VALUES ('머릿말 없음');