## 1. 데이터베이스 모델링

## pre. 요구사항 분석

**[인스타그램 서비스 분석]**

### 1. 서비스 개요

인스타그램은 사용자들이 게시글을 작성하고, 댓글을 달며, 좋아요를 누르고, 메시지를 주고받을 수 있는 기능을 제공합니다.

### 2. 기능 요구사항

### 2.1 게시글 기능

- **게시글 조회**: 사용자는 타인의 게시글을 조회할 수 있습니다.
- **게시글 작성**: 사용자는 텍스트와 사진을 포함하여 게시글을 작성할 수 있습니다.
- **게시글 수정 및 삭제**: 사용자는 자신이 작성한 게시글을 수정하거나 삭제할 수 있습니다.

### 2.2 댓글 기능

- **댓글 작성**: 사용자는 게시글에 댓글을 작성할 수 있습니다.
- **대댓글 기능**: 사용자는 댓글에 대한 대댓글을 작성할 수 있습니다.
- **댓글 수정 및 삭제**: 사용자는 자신이 작성한 댓글 및 대댓글을 수정하거나 삭제할 수 있습니다.

### 2.3 좋아요 기능

- **좋아요**: 사용자는 게시글에 좋아요를 누를 수 있습니다.
- **좋아요 취소**: 사용자는 자신이 누른 좋아요를 취소할 수 있습니다.

### 2.4 Direct Message (DM) 기능

- **DM 작성**: 사용자는 다른 사용자와 1:1로 메시지를 주고받을 수 있습니다.
- **DM 삭제**: 사용자는 자신이 보낸 DM을 삭제할 수 있습니다.

### 구현 계획

### Domain (Entity) 클래스

- 각 테이블에 대응하는 Entity 클래스를 정의하고, JPA 어노테이션을 통해 MySQL 데이터베이스와 매핑합니다.

### Repository 계층

- **JpaRepository**를 확장한 Repository 인터페이스를 정의하여, 기본 CRUD 작업을 자동으로 수행합니다.

### 서비스 계층

- 비즈니스 로직을 처리하며, Repository를 통해 데이터베이스와 상호작용합니다.

### 컨트롤러 계층

- 사용자 요청을 처리하고, 클라이언트와 서버 간의 데이터 교환을 관리합니다.

## 1) 개념적 설계

**1-1) 개체(entity)와 속성(attribute) 추출**

**1. 사용자 (Users)**

- **UserID** (PK) : 사용자 고유 식별자
- **Username** : 사용자 이름
- **Email** : 이메일 주소
- **Password** : 암호
- **ProfilePicture** : 프로필 사진
- **Bio** : 사용자 소개

**2. 게시글 (Post)**

- **PostID** (PK) : 게시글 고유 식별자
- **UserID** (FK) : 게시글 작성자
- **Image** : 이미지 파일
- **Caption** : 게시글 내용
- **Timestamp** : 작성 시간

**3. 댓글 (Comment)**

- **CommentID** (PK) : 댓글 고유 식별자
- **PostID** (FK) : 댓글이 달린 게시글
- **UserID** (FK) : 댓글 작성자
- **Text** : 댓글 내용
- **Timestamp** : 댓글 작성 시간

**4. 대댓글 (Reply)**

- **ReplyID** (PK) : 대댓글 고유 식별자
- **CommentID** (FK) : 대댓글이 달린 댓글
- **UserID** (FK) : 대댓글 작성자
- **Text** : 대댓글 내용
- **Timestamp** : 대댓글 작성 시간

**5. 좋아요 (Likes)**

- **LikeID** (PK) : 좋아요 고유 식별자
- **PostID** (FK) : 좋아요가 달린 게시글
- **UserID** (FK) : 좋아요를 누른 사용자
- **Timestamp** : 좋아요 시간

**6. 다이렉트 메시지 (DirectMessage)**

- **MessageID** (PK) : 메시지 고유 식별자
- **SenderID** (FK) : 메시지 발신자
- **ReceiverID** (FK) : 메시지 수신자
- **Text** : 메시지 내용
- **Timestamp** : 메시지 전송 시간

## 2) 논리적 설계

릴레이션(스키마)

개체 → 릴레이션 이름

속성 → 릴레이션의 속성

![erd](https://github.com/user-attachments/assets/49eea983-2974-45d1-aa0c-f19a31790f66)

## 3) 물리적 설계

**SQL문 작성 예시**

-- 사용자 테이블
CREATE TABLE User (
UserID INT PRIMARY KEY AUTO_INCREMENT,
Username VARCHAR(50) NOT NULL,
Email VARCHAR(100) NOT NULL UNIQUE,
Password VARCHAR(100) NOT NULL,
ProfilePicture VARCHAR(255),
);

-- 게시글 테이블
CREATE TABLE Post (
PostID INT PRIMARY KEY AUTO_INCREMENT,
UserID INT,
Image VARCHAR(255),
Caption TEXT,
Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (UserID) REFERENCES User(UserID)
);


테이블이름 user로 했더니 단위테스트에서 오류계속발생..

다른 분들은 잘되는것같길래 안바꾸고 해봤는데 계속 안돼서 users로 테이블 이름 변경하니 성공. .


![image (2)](https://github.com/user-attachments/assets/ace93beb-c781-4ab6-a3d2-c5a2ad068f8c)

# REPOSITORY 단위테스트

- `given` : 테스트 실행을 준비하는 단계
- `when` : 테스트를 진행하는 단계
- `then` : 테스트 결과를 검증하는 단계

