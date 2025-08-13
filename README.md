## 일정 관리 앱 과제
### 1. 일정(Schedule) API

1-1. 일정 생성

| 항목            | 내용                                                                                 |
|---------------|------------------------------------------------------------------------------------|
| URL           | `POST /schedules`                                                                  |
| Request Body  | `title`(String)<br/>`content`(String)<br/>`author`(String)<br/> `password`(String) |
| Response      | `200 OK`                                                                           |
| Response Body | `id`,`title`,`content`,`auther`,`createdAt`,`modifiedAt`                           |
| Error         | `400 Bad Request`(필수값 누락, 글자 수 초과)                                                 | 

POST/users

Request

{
    "title": "제목",
    "content": "내용",
    "auther": "이름",
    "password": "비밀번호"
}

Response

{
    "id": 1,
    "author": "이름",
    "title": "제목",
    "content": "내용",
    "password": "비밀번호",
    "createdAt": "2025-10-14T00:00:00Z",
    "modifiedAt": "2025-10-14T00:00:00Z"
}

1-2. 일정 목록 조회

| 항목              | 내용                                                                   |
|-----------------|----------------------------------------------------------------------|
| URL             | `GET/schedules`                                                      |
| Query Parameter | `author` (String, 선택) - 작성자명으로 필터링                                   |
| Response        | `200 OK`                                                             |
| Response Body   | 일정 목록 배열(`id`,`title`,`content`,`auther`,`createdAt`,`modifiedAt`)   |   

GET/schedules
Response

[
    {
        "id": 1,
        "author": "이름",
        "title": "제목",
        "content": "내용",
        "password": "비밀번호",
        "createdAt": "2025-10-14T00:00:00Z",
        "modifiedAt": "2025-10-14T00:00:00Z"
    },
    {
        "id": 2,
        "author": "이름",
        "title": "제목2",
        "content": "내용2",
        "password": "비밀번호2",
        "createdAt": "2025-10-14T00:00:00Z",
        "modifiedAt": "2025-10-14T00:00:00Z"
    }
]

1-3. 일정 단건 조회

| 항목             | 내용                             |
|----------------|--------------------------------|
| URL            | `GET /schedules /{scheduleid}` |
| Path           | `scheduleid`(Long)             |
| Response       | `200 OK`                       |
| Response Body  | 일정 정보 + `comments` 배열          |

GET/schedules/{scheduleid}

Respones

{
    "id": 1,
    "author": "이름",
    "title": "제목",
    "content": "내용",
    "password": "비밀번호",
    "createdAt": "2025-10-14T00:00:00Z",
    "modifiedAt": "2025-10-14T00:00:00Z"
}

1.4. 일정 수정

| 항목            | 내용                                                                                 |
|---------------|------------------------------------------------------------------------------------|
| URL           | `PUT /schedules /{scheduleid}`                                                     |
| Path Variable | `scheduleid`(Long)                                                                 |
| Request Body  | `title`(String)<br/>`content`(String)<br/>`author`(String)<br/> `password`(String) |
| Response      | `200 OK`                                                                           |
| Response Body | 수정된 일정 정보                                                                          |
| Error         | `400 Bad Request` - 비밀번호 불일치                                                       |


PUT/schedules/{scheduleid}


Request

{
    "auther": "이름",
    "title": "제목",
    "password": "비밀번호"
}

Response

{
    "auther": "이름",
    "title": "제목",
    "password": "비밀번호",
    "createdAt": "2025-10-14T00:00:00Z",
    "modifiedAt": "2025-10-14T00:00:00Z"
}

1.5. 일정 삭제 

| 항목              | 내용                                 |
|-----------------|------------------------------------|
| URL             | `DELETE /schedules /{scheduleid}`  |
| Path Variable   | `scheduleid`(Long)                 |
| Response        | `200 OK`                           |
| Error           | `400 Bad Request` - 비밀번호 불일치       |

DELETE/schedules/{scheduleid}

Request

{
    "password": "비밀번호"
}

Response

{
    "message": "삭제가 완료 되었습니다."
}

### 2. 유저(User) API

2-1. 유저 생성

| 항목            | 내용                                                            |
|---------------|---------------------------------------------------------------|
| URL           | `POST /users`                                                 |
| Request Body  | `author`(String)<br/> `password`(String)<br/> `email`(String) |
| Response      | `200 OK`                                                      |
| Response Body | `id`, `author`                                                |
| Error         | `400 Bad Request`(필수값 누락, 글자 수 초과)                            |

POST/users

Request

{
    "author": "이름",
    "password": "비밀번호"
    "email": "이메일"
}

Response

{
    "id": 1,
    "author": "이름"
    "email": "이메일"
    "message": "회원가입이 완료 되었습니다."
    "createdAt": "2025-10-14T00:00:00Z",
    "modifiedAt": "2025-10-14T00:00:00Z"
}

2-2. 유저 목록 조회

| 항목              | 내용                               |
|-----------------|----------------------------------|
| URL             | `GET /users`                     |
| Response        | `200 OK`                         |
| Response Body   | 유저 정보 배열                         |
| Error           | `400 Bad Request` (해당 유저는 없습니다.) |

GET/ users

Response

[
    {
        "id": 1,
        "author": "이름",
        "email": "이메일",
        "createdAt": "2025-10-14T00:00:00Z",
        "modifiedAt": "2025-10-14T00:00:00Z"
    },
    {
        "id": 2,
        "author": "이름2",
        "email": "이메일2",
        "createdAt": "2025-10-14T00:00:00Z",
        "modifiedAt": "2025-10-14T00:00:00Z"
    }
]

2-3. 유저 단건 조회 

| 항목            | 내용                              |
|---------------|---------------------------------|
| URL           | `GET /users `                   |
| Path Variable | `userid`(Long)                  |
| Response      | `200 OK`                        |
| Response Body | 유저 정보 배열                        |
| Error         | `400 Bad Request` (해당 유저는 없습니다.) |

GET/ users

Response

{
    "id": 1,
    "author": "이름",
    "email": "이메일",
    "message": "회원가입이 완료 되었습니다",
    "createdAt": "2025-10-14T00:00:00Z",
    "modifiedAt": "2025-10-14T00:00:00Z"
}

2-4. 유저 수정

| 항목            | 내용                                                            |
|---------------|---------------------------------------------------------------|
| URL           | `PUT /users /{userid}`                                        |
| Path Variable | `userid`(Long)                                                |
| Request Body  | `author`(String)<br/> `password`(String)<br/> `email`(String) |
| Response      | `200 OK`                                                      |
| Response Body | 유저 정보 배열                                                      |
| Error         | `400 Bad Request` (해당 유저는 없습니다.)                              |

PUT/ users/ {userid}

Request

{
    "author": "이름",
    "password": "비밀번호",
    "email": "이메일"
}

Response

{
    "id": 1,
    "author": "이름",
    "email": "이메일",
    "message": "계정 수정이 완료되었습니다.",
    "createdAt": "2025-10-14T00:00:00Z",
    "modifiedAt": "2025-10-14T00:00:00Z"
}

2-5. 유저 삭제


| 항목              | 내용                                 |
|-----------------|------------------------------------|
| URL             | `DELETE /users /{userid}`          |
| Path Variable   | `userid`(Long)                     |
| Response        | `200 OK`                           |
| Error           | `400 Bad Request` - 비밀번호 불일치       |

DELETE/schedules/{scheduleid}

Request

{
    "password": "비밀번호"
}

Response

{
    "message": "삭제가 완료 되었습니다."
}