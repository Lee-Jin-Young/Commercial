### 1. 프로젝트의 주요 기능과 목적

해당 레포지토리 "Commercial" 프로젝트는 MSA(Microservices Architecture) 기반으로, Spring Boot, Java, MySQL, Docker를 사용하여 다양한 서비스(order-service, product-service, user-service)를 구현한 개인 프로젝트 입니다.

![SpringBoot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=SpringBoot&logoColor=white)
![Java21+](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=java&color=808080)
![mysql](https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=MySql&logoColor=white)
![docker](https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white)

![제목 없는 다이어그램 (1)](https://github.com/Lee-Jin-Young/Commercial/assets/129915982/6875b7dd-2dfd-48a2-92bf-f4195808aa79)

---
## 2. 설치 방법

docker-compose

docker-compose, Dockerfile, script 등의 파일을 tar로 제공 할 예정

---
## 3. 문제 해결 방법

- redis 적용 이후 테스트데이터 작성 예정


docker-compose를 통해 db와 spring boot를 실행 할 수 있도록 하려는 과정에서 ConnectException 발생

#### 첫번째 가정) db가 켜지지 않아서 발생 하는 문제 일 것이다
-> 켜져 있는 상태에서도 발생
```bash
> docker ps
CONTAINER ID   IMAGE          COMMAND                   CREATED         STATUS         PORTS                               NAMES
b4c70efc1785   mysql:latest   "docker-entrypoint.s…"   8 minutes ago   Up 8 minutes   0.0.0.0:3307->3306/tcp, 33060/tcp   user-service-mysql-1
```

#### 두번째 가정) 서버의 방화벽 등에서 차단 되어 있을 것이다.
-> 인바운드 포트를 모두 열 경우 보안상에 문제가 생길 수 있지만, 개발 과정 중의 편의를 위해 일단 개방 하기로 하였다. 단, 허용ip를 주 개발장소의 ip로 지정하여 외부 접속을 최소화 한다.
-> 포트 개방 후 동일한 문제가 발생 함을 확인, 해당 가정 외에 다른 문제가 있음을 추정 할 수 있다.

#### 세번째 가정) mysql에서 접근 권한이 막혀 있을 것이다.
```bash
mysql> SELECT * FROM information_schema.TABLE_PRIVILEGES;
+-----------------------------+---------------+--------------+------------+----------------+--------------+
| GRANTEE                     | TABLE_CATALOG | TABLE_SCHEMA | TABLE_NAME | PRIVILEGE_TYPE | IS_GRANTABLE |
+-----------------------------+---------------+--------------+------------+----------------+--------------+
| 'mysql.sys'@'localhost'     | def           | sys          | sys_config | SELECT         | NO           |
| 'mysql.session'@'localhost' | def           | mysql        | user       | SELECT         | NO           |
+-----------------------------+---------------+--------------+------------+----------------+--------------+
2 rows in set (0.00 sec)
```
-> mysql상에서는 권한이 허용 되어있으며, 실행 된 database를 로컬에서 접속 할 수 있음을 확인 하였다.

#### 네번째 가정) application.properties 상에서 잘못 작성 되어 있을 것이다.
-> 서버 ip로 작성 시 / localhost로 작성 시 동일 한 오류 발생

최종 해결 방법 :
1, 3, 4번의 이유가 복합적으로 작용하였다.
1 -> 처음 docker-compose 실행 시 db를 먼저 실행 후, 올바르게 실행 되었을 때 spring boot를 실행 하도록 함
```bash
#!/bin/bash

set -e

host="$1"
shift
cmd="$@"

# MySQL 서버에 접속이 가능한지 확인
until mysqladmin ping -h "$host" -u root -p"1234" --silent; do
  >&2 echo "MySQL is unavailable - sleeping"
  sleep 1
done

# MySQL 서버에 접속 가능해지면 명령어 실행
>&2 echo "MySQL is up - executing command"
exec $cmd

```
4 ->docker-compose상에서 외부 포트를 3307, 내부 포트를 3306으로 작성했기 때문에 properties상에서 서버 ip로 접속할 때는 3307 localhost로 접속 할 때는 3306로 작성 했어야 함.

---
## 4. 사용 예시

---
## 5. 심화 자료 및 문서 링크

---
## 6. 사전 요구 사항

---
[Postman](https://documenter.getpostman.com/view/32012062/2sA3JT3yfd)

![image](https://github.com/Lee-Jin-Young/Commercial/assets/129915982/2f017ded-3fe4-49a3-927b-be8dc588a4e2)

