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

SELECT * FROM ikeeper_homepage.parentcategory;
INSERT INTO parentcategory (parentcategory_name)
VALUES ('세미나'),
       ('스터디/멘토링'),
       ('건의함');

SELECT * FROM ikeeper_homepage.introduce;
INSERT INTO introduce (introduce_content)
VALUES ('');

SELECT * FROM ikeeper_homepage.ledger;
INSERT INTO ledger (ledger_field, ledger_title, ledger_useday,
                    ledger_use, ledger_amount, ledger_sum)
VALUES ('1', 'default', '2024-05-11', '1', '0', '0');

/* 테스트용 (추후 삭제 예정) */

SELECT * FROM ikeeper_homepage.category;
INSERT INTO category (category_parent, category_name)
VALUES ('1', '써트 세미나'),
       ('2', 'C언어 멘토링'),
       ('2', '포렌식 멘토링');

SELECT * FROM ikeeper_homepage.headline;
INSERT INTO headline (headline_name)
VALUES ('AI'),
       ('WEB'),
       ('REPORT');

SELECT * FROM ikeeper_homepage.lecture;
INSERT INTO lecture (lecture_name)
VALUES ('C언어 멘토링'),
       ('JS 불법 스터디'),
       ('웹 멘토링');