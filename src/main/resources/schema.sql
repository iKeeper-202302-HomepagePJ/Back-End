drop table if exists field;
drop table if exists calendar;
drop table if exists major;
drop table if exists score;
drop table if exists member;
drop table if exists category;
drop table if exists categorylarge;
drop table if exists categorysmall;
drop table if exists post;
drop table if exists comment;
drop table if exists attendance;
drop table if exists introduce;
drop table if exists history;
drop table if exists award;
drop table if exists hyperlink;
drop table if exists book;
drop table if exists lecture;
drop table if exists ledger;
drop table if exists bookmark;
drop table if exists grade;
drop table if exists headline;
drop table if exists status;

CREATE TABLE `field`
(
    `field_id`   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `field_name` VARCHAR(10) NOT NULL
);

CREATE TABLE status
(
    status_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    status_name VARCHAR(20) DEFAULT '신입' NOT NULL
);

CREATE TABLE calendar
(
    calendar_id    SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    calendar_field TINYINT     NOT NULL,
    calendar_title VARCHAR(30) NOT NULL,
    calendar_day   DATE        NOT NULL,
    calendar_time  TIME        NOT NULL,
    calendar_place VARCHAR(10) NOT NULL DEFAULT '미정',
    calendar_check BOOLEAN     NOT NULL
);

CREATE TABLE `score`
(
    `score_id`       TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `score_main`     TINYINT NULL,
    `score_field`    TINYINT NULL,
    `score_activity` TINYINT NULL,
    `score_etc`      TINYINT NULL,
    `score_sum`      TINYINT NULL
);

CREATE TABLE `member`
(
    `member_student_id`   VARCHAR(10)  NOT NULL PRIMARY KEY,
    `member_name`         VARCHAR(10)  NOT NULL,
    `member_role`         VARCHAR(8)   NOT NULL,
    `member_password`     VARCHAR(255) NOT NULL,
    `member_phone_number` VARCHAR(16)  NOT NULL,
    `member_birth`        VARCHAR(10)  NOT NULL,
    `member_email`        VARCHAR(30)  NOT NULL,
    `member_field`        TINYINT      NOT NULL,
    `member_grade`        TINYINT NULL,
    `member_status`       TINYINT      NOT NULL,
    `member_major1`       TINYINT NULL,
    `member_major2`       TINYINT NULL,
    `member_major3`       TINYINT NULL,
    `member_minor`        TINYINT NULL,
    `member_warning`      BOOLEAN      NOT NULL DEFAULT '0'
);

CREATE TABLE `category`
(
    `category_id`    SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `category_large` SMALLINT NOT NULL,
    `category_small` SMALLINT NOT NULL
);

CREATE TABLE `categorylarge`
(
    `categorylarge_id`   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `categorylarge_name` VARCHAR(20) NOT NULL
);

CREATE TABLE `categorysmall`
(
    `categorysmall_id`   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `categorysmall_name` VARCHAR(20) NOT NULL
);

CREATE TABLE `post`
(
    `post_id`                SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `post_student_id`        VARCHAR(10)   NULL,
    `post_user`              VARCHAR(10)   NULL,
    `post_category`          SMALLINT      NULL,
    `post_headline`          TINYINT       NULL,
    `post_title`             VARCHAR(50)   NULL,
    `post_timestamp`         TIMESTAMP     NULL,
    `post_content`           VARCHAR(5000) NULL,
    `post_update_check`      BOOLEAN       NOT NULL,
    `post_disclosure`        BOOLEAN       NOT NULL,
    `post_comment_whether`   BOOLEAN       NOT NULL,
    `post_fix`               BOOLEAN       NOT NULL
);

CREATE TABLE `comment`
(
    `comment_id`        SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `comment_post_id`           SMALLINT     NOT NULL,
    `comment_student_id`      VARCHAR(10)  NOT NULL,
    `comment_username`      VARCHAR(10)  NOT NULL,
    `comment_timestamp` TIMESTAMP    NOT NULL,
    `comment_content`   VARCHAR(500) NOT NULL
);

CREATE TABLE `attendance`
(
    `attendance_id`    SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `attendance_student_id`     VARCHAR(10) NOT NULL,
    `attendance_lecture_id`       SMALLINT    NOT NULL,
    `attendance_check` BOOLEAN     NOT NULL DEFAULT '0'
);

CREATE TABLE `introduce`
(
    `introduce_id`   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `introduce_content` VARCHAR(10000) NOT NULL
);

CREATE TABLE `history`
(
    `history_id`      SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `history_year`    CHAR(4)     NOT NULL DEFAULT '0000',
    `history_content` VARCHAR(50) NOT NULL DEFAULT '내용이 존재하지 않습니다.'
);

CREATE TABLE `award`
(
    `award_id`      SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `award_day`     DATE        NOT NULL DEFAULT '1000-01-01',
    `award_content` VARCHAR(50) NOT NULL DEFAULT '수상 내역이 존재하지 않습니다.',
    `award_people`  VARCHAR(30) NOT NULL DEFAULT '수상자가 존재하지 않습니다.'
);

CREATE TABLE `hyperlink`
(
    `hyperlink_id`   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `hyperlink_img`  VARCHAR(100) NULL,
    `hyperlink_url` VARCHAR(100) NULL
);

CREATE TABLE `book`
(
    `book_id`         SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `book_name`       VARCHAR(30) NOT NULL DEFAULT '책 제목이 존재하지 않습니다.',
    `book_rental`     BOOLEAN     NOT NULL DEFAULT '0',
    `book_borrower`   CHAR(8)     NOT NULL,
    `book_rental_day` DATE NULL
);

CREATE TABLE `lecture`
(
    `lecture_id`   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `lecture_name` VARCHAR(30) NOT NULL
);

CREATE TABLE `ledger`
(
    `ledger_id`       SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `field_id`        TINYINT     NOT NULL,
    `ledger_category` TINYINT     NOT NULL,
    `ledger_title`    VARCHAR(30) NOT NULL DEFAULT '회비 내역이 존재하지 않습니다.',
    `ledger_useday`   DATE        NOT NULL DEFAULT '1000-01-01',
    `ledger_minus`    SMALLINT NULL,
    `ledger_plus`     SMALLINT NULL,
    `ledger_sum`      SMALLINT NULL,
    `ledger_evidence` VARCHAR(100) NULL
);

CREATE TABLE `bookmark`
(
    `bookmark_id`  SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `bookmark_student_id` VARCHAR(10) NOT NULL,
    `bookmark_post_id`      SMALLINT    NOT NULL
);

CREATE TABLE `grade`
(
    `grade_id`   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `grade_name` VARCHAR(10) NOT NULL
);

CREATE TABLE `headline`
(
    `headline_id`   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `headline_name` VARCHAR(10) NOT NULL
);

CREATE TABLE `major`
(
    `major_id`   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `major_name` VARCHAR(20) NOT NULL
);