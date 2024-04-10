drop table if exists calendar;
drop table if exists category_list_table;
drop table if exists bookmark_table;
drop table if exists comment_table;
drop table if exists post_table;
drop table if exists attendance_list_table;
drop table if exists library_table;
drop table if exists user;
drop table if exists field;
drop table if exists major;
drop table if exists score;
drop table if exists status;
drop table if exists grade;
drop table if exists category_table;
drop table if exists headline_table;
drop table if exists attendance_table;
drop table if exists keeper_information_table;
drop table if exists keeper_history_table;
drop table if exists keeper_award_table;
drop table if exists hyperlink_table;
drop table if exists ledger_table;
drop table if exists ledger_category_table;
drop table if exists authority_table;

CREATE TABLE field
(
    field_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    field_name VARCHAR(10) DEFAULT 'i-Keeper' NOT NULL
);

CREATE TABLE calendar
(
    calendar_id    SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    calendar_title VARCHAR(30) DEFAULT '일정명이 존재하지 않습니다.' NOT NULL,
    calendar_field TINYINT UNSIGNED NOT NULL,
    calendar_day   DATE                                  NOT NULL,
    calendar_time  TIME                                  NOT NULL,
    calendar_place VARCHAR(10) DEFAULT '미정'              NOT NULL,
    calendar_check BOOLEAN     DEFAULT '0'               NOT NULL
);

CREATE TABLE major
(
    major_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    major_name VARCHAR(20)
);

CREATE TABLE status
(
    status_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    status_name VARCHAR(20) DEFAULT '신입' NOT NULL
);

CREATE TABLE grade
(
    grade_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    grade_name VARCHAR(10) DEFAULT '1학년 1학차' NOT NULL
);

CREATE TABLE score
(
    score_id            TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_score_ikeeper  TINYINT UNSIGNED,
    user_score_field    TINYINT UNSIGNED,
    user_score_activity TINYINT UNSIGNED,
    user_score_etc      TINYINT UNSIGNED,
    user_score_sum      TINYINT UNSIGNED
);

CREATE TABLE user
(
    user_id           TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    student_id        VARCHAR(9)                              NOT NULL,
    user_name         VARCHAR(10)  DEFAULT '이름없음'             NOT NULL,
    user_role         VARCHAR(5)                              NOT NULL,
    user_phone_number VARCHAR(15)  DEFAULT '010-0000-0000'    NOT NULL,
    user_birth        VARCHAR(10)  DEFAULT 'YYYYMMDD'         NOT NULL,
    user_email        VARCHAR(30)  DEFAULT 'iKeeper@cu.ac.kr' NOT NULL,
    user_password     VARCHAR(255) DEFAULT 'i-KeeperD2509'    NOT NULL,
    user_field        TINYINT UNSIGNED NOT NULL,
    user_status       TINYINT UNSIGNED NOT NULL,
    user_grade        TINYINT UNSIGNED NOT NULL,
    major_main        VARCHAR(20)                             NOT NULL,
    major_double      VARCHAR(20),
    minor_first       VARCHAR(20),
    minor_second      VARCHAR(20),
    user_score        TINYINT UNSIGNED,
    user_warning      BOOLEAN      DEFAULT '0'
);

/*

CREATE TABLE lecture_table
(
    lecture_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    lecture_name VARCHAR(30) DEFAULT NOT NULL
);

CREATE TABLE attendance_table
(
    attendance_id TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_id       TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_table (user_id)
        lecture_id TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (lecture_id) REFERENCES lecture_table (lecture_id)
        attendance_check BOOLEAN DEFAULT '1' NOT NULL
);

CREATE TABLE category_table
(
    category_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    category_name VARCHAR(50) DEFAULT '카테고리명이 존재하지 않습니다.' NOT NULL
);

CREATE TABLE category_list_table
(
    category_list_table_id SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_id                CHAR(8) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_table (user_id),
    category_id            TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category_table (category_id)
);

CREATE TABLE headline_table
(
    headline_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    headline_name VARCHAR(20) DEFAULT '머릿말 없음' NOT NULL
);

CREATE TABLE post_table
(
    post_id                SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    category_id            TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category_table (category_id),
    writer                 CHAR(8)                                    NOT NULL,
    FOREIGN KEY (writer) REFERENCES user_table (student_id_id),
    post_title             VARCHAR(50)   DEFAULT '게시물 제목이 존재하지 않습니다.' NOT NULL,
    post_headline          TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (post_headline) REFERENCES headline_table (headline_id),
    post_timestamp         TIMESTAMP     DEFAULT CURRENT_TIMESTAMP    NOT NULL,
    post_rewrite_timestamp TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NULL,
    post_content           VARCHAR(5000) DEFAULT '게시물 내용이 존재하지 않습니다.' NOT NULL,
    post_nondisclosure     BOOLEAN       DEFAULT '0'                  NOT NULL,
    post_comment_allowed   BOOLEAN       DEFAULT '1'                  NOT NULL,
    post_important         BOOLEAN       DEFAULT '0'                  NOT NULL
);

CREATE TABLE bookmark_table
(
    user_id CHAR(8) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_table (user_id),
    post_id SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post_table (post_id)
);

CREATE TABLE comment_table
(
    comment_id        SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    post_id           SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post_table (post_id),
    writer            CHAR(8)                                  NOT NULL,
    FOREIGN KEY (writer) REFERENCES user_table (user_id),
    comment_timestamp TIMESTAMP    DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    comment_content   VARCHAR(500) DEFAULT '댓글 내용이 존재하지 않습니다.' NOT NULL
);

CREATE TABLE attendance_table
(
    attend_id   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    attend_name VARCHAR(30) DEFAULT '출석리스트명이 존재하지 않습니다.' NOT NULL
);

CREATE TABLE attendance_list_table
(
    user_id      CHAR(8)             NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_table (user_id),
    attend_id    SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (attend_id) REFERENCES attendance_table (attend_id),
    attend_check BOOLEAN DEFAULT '0' NOT NULL
);

CREATE TABLE keeper_information_table
(
    keeper_information VARCHAR(10000) NULL,
    user_authority     TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_authority) REFERENCES authority_table (authority_id)
);

CREATE TABLE keeper_history_table
(
    history_id      SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_authority  TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_authority) REFERENCES authority_table (authority_id),
    history_year    CHAR(4)     DEFAULT '0000'           NOT NULL,
    history_content VARCHAR(50) DEFAULT '내용이 존재하지 않습니다.' NOT NULL
);

CREATE TABLE keeper_award_table
(
    award_id       SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_authority TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_authority) REFERENCES authority_table (authority_id),
    award_day      DATE        DEFAULT '1000-01-01'        NOT NULL,
    award_content  VARCHAR(50) DEFAULT '수상 내역이 존재하지 않습니다.' NOT NULL,
    award_people   VARCHAR(30) DEFAULT '수상자가 존재하지 않습니다.'   NOT NULL
);

CREATE TABLE hyperlink_table
(
    hyper_id       TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_authority TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_authority) REFERENCES authority_table (authority_id),
    hyper_img      VARCHAR(100) NULL,
    hyper_link     VARCHAR(100) NULL
);

CREATE TABLE library_table
(
    book_id         SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    user_authority  TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_authority) REFERENCES authority_table (authority_id),
    book_borrower   CHAR(8)                                NOT NULL,
    FOREIGN KEY (book_borrower) REFERENCES user_table (user_id),
    book_name       VARCHAR(30) DEFAULT '책 제목이 존재하지 않습니다.' NOT NULL,
    book_rental     BOOLEAN     DEFAULT '0'                NOT NULL,
    book_rental_day DATE NULL
);

CREATE TABLE ledger_category_table
(
    ledger_category_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ledger_category_name VARCHAR(30) DEFAULT '회비 카테고리명이 존재하지 않습니다.' NOT NULL
);

CREATE TABLE ledger_table
(
    ledger_id          SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ledger_category_id TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (ledger_category_id) REFERENCES ledger_category_table (ledger_category_id),
    user_authority     TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_authority) REFERENCES authority_table (authority_id),
    ledger_title       VARCHAR(30) DEFAULT '회비 내역이 존재하지 않습니다.' NOT NULL,
    ledger_useday      DATE        DEFAULT '1000-01-01'        NOT NULL,
    ledger_field       TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (ledger_field) REFERENCES field_table (field_id),
    ledger_minus       MEDIUMINT UNSIGNED NULL,
    ledger_plus        MEDIUMINT UNSIGNED NULL,
    ledger_sum         MEDIUMINT UNSIGNED NULL,
    ledger_evidence    VARCHAR(100) NULL
);

*/