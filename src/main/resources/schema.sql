drop table if exists calendar_table;
drop table if exists field_table;

CREATE TABLE field_table
(
    field_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    field_name VARCHAR(10) DEFAULT 'i-Keeper' NOT NULL
);

CREATE TABLE calendar_table
(
    calendar_id    SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    calendar_field TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (calendar_field) REFERENCES field_table (field_id),
    calendar_title VARCHAR(30) DEFAULT '일정명이 존재하지 않습니다.' NOT NULL,
    calendar_day   DATE                                  NOT NULL,
    calendar_time  TIME                                  NOT NULL,
    calendar_place VARCHAR(10) DEFAULT '미정'              NOT NULL,
    calendar_check BOOLEAN     DEFAULT '0'               NOT NULL
);