# JSP 블로그 프로젝트

## 환경

- windows10 or Mac
- jdk1.8
- tomcat9.0
- sts tool
- mysql8.0
- lombok
- gson (json파싱)
- 인코딩 utf-8
- git

## MySQL 데이터베이스 생성 및 사용자 생성

```sql
create user 'user'@'%' identified by '1234';
GRANT ALL privileges on *.* to 'user'@'%';
CREATE database users;
```

## MySQL 테이블 생성

```sql
use users;
CREATE TABLE user (
	id int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    email varchar(100),
    role varchar(5),
    createDate timestamp,
    updateDate timestamp
);
```
