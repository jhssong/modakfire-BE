# 모닥불: 이웃을 위한 작은 불씨
## 📱 배포 링크
https://walkak-modakfire.web.app/
## 🔥 프로젝트 소개
지역을 위해 기부금을 모금하고, 모금된 기부금은 지역 곳곳에 필요한 시설에 기부되는 프로젝트입니다.
## 🥺 Team Walkak
|<img src="https://avatars.githubusercontent.com/u/86557146?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/71973291?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/151692917?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/66457807?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|송재훈<br/>[@jhssong](https://github.com/jhssong)|심준성<br/>[@MOJAN3543](https://github.com/MOJAN3543)|이원준<br/>[@lwj0831](https://github.com/lwj0831)|조재용<br/>[@WithJo](https://github.com/WithJo)|
## 📚 기술 스택 
* Spring Framework (Spring Boot, Spring JPA Data)
* MariaDB
* FireBase
## 🔎 주요 기능
* Google 계정으로 로그인
* 지역별 기부 가능한 센터 표기
* 센터에서 필요한 물품 표기
* 센터 정보 표기
* 센터에서 필요한 물품에 기부
* 특정 지역, 센터 종류에 전체적으로 기부
* 정기적으로 설정한 날짜에 기부
* 계정 설정
## 🤔 사용자 플로우
1. 처음 서비스 이용시, 사용자의 Google 계정으로 로그인을 한다.
2. 사용자의 지역을 입력 하고(`ex. 대구/북구/보육원`) 근처의 센터를 검색한다.
3. 사용자가 원하는 센터를 클릭하여, 센터에 필요한 물품을 확인한다.
4. 사용자가 사주고 싶은 물품을 선택하고, 기부 금액을 입력하여 기부한다.
5. 만약 기부 금액이 모두 만족 되었다면, 물품이 센터에서 더이상 표시되지 않고, 물품의 상태가 `전달 대기중`으로 변한다.
6. 사용자가 이전에 진행했던 기부를 확인하고, 어떻게 진행 되고 있는지 확인한다.
7. 사용자가 정기 기부를 등록한다.
*정기 기부를 생성/삭제/변경 할 수 있다.
      
## ⚙ API 엔드포인트
### 정기 기부 API

#### 1. 정기 기부 생성

- **엔드 포인트**:  `POST /periodicalDonations`
- **API Function**: `createPeriodicalDonation`

#### 2. 정기 기부 읽기

- **엔드 포인트**:  `GET /periodicalDonations/{memberId}`
- **API Function**: `viewPeriodicalDonationListByMemberId`

#### 3. 정기 기부 수정

- **엔드 포인트**:  `PUT /periodicalDonations/{periodicalDonationId}`
- **API Function**: `editPeriodicalDonation`

#### 4. 정기 기부 삭제

- **엔드 포인트**:  `DELETE /periodicalDonations/{periodicalDonationId}`
- **API Function**: `deletePeriodicalDonation`

### 유저 API

#### 1. 유저 생성

- **엔드 포인트**:  `POST /members`
- **API Function**: `createMember`

#### 2. 정기 기부 읽기

- **엔드 포인트**:  `GET /members/{memberId}`
- **API Function**: `getMember`

#### 3. 정기 기부 수정

- **엔드 포인트**:  `PUT /members/{memberId}`
- **API Function**: `updateMember`

#### 4. 정기 기부 삭제

- **엔드 포인트**:  `DELETE /members/{memberId}`
- **API Function**: `deleteMember`

### 센터 API

#### 1. 센터 쿼리로 검색

- **엔드 포인트**:  `GET /centers`
- **API Function**: `findCentersByCond`

#### 2. 센터 ID로 검색

- **엔드 포인트**:  `GET /centers/{centerID}`
- **API Function**: `findCenterById`

### 기부 API

#### 1. 특정 아이템에 기부

- **엔드 포인트**:  `POST /donations`
- **API Function**: `createDonation`

#### 2. 특정 시, 구, 센터에 기부(빠른기부)

- **엔드 포인트**:  `POST /donations/fast`
- **API Function**: `createFastDonation`

#### 3. 기부 목록 읽기

- **엔드 포인트**:  `GET /donations/{donationId}`
- **API Function**: `getDonationDetailByDonationId`

### 좋아요 API

#### 1. 좋아요 눌렀는지 확인

- **엔드 포인트**:  `GET /likes/{centerId}/{memberId}`
- **API Function**: `checkLike`

#### 2. 좋아요 / 좋아요 취소

- **엔드 포인트**:  `POST /likes/{centerId}/{memberId}`
- **API Function**: `createOrDeleteLike`

### 아이템 API

#### 1. 아이템 진행도 읽기

- **엔드 포인트**:  `GET /items/status/{itemId}`
- **API Function**: `getItemStatus`

#### 2. 아이템 id로 아이템 정보 읽기

- **엔드 포인트**:  `GET /items/{itemId}`
- **API Function**: `getItemDetail`

#### 3. 특정 센터의 아이템 리스트 읽기

- **엔드 포인트**:  `GET /items/center/{centerId}`
- **API Function**: `findItemsByCenterId`

#### 4. 특정 아이템의 진행도 수정

- **엔드 포인트**:  `PUT /items/status/{itemId}`
- **API Function**: `updateItemStatus`

#### 5. 기부 id로 아이템 정보 읽기

- **엔드 포인트**:  `PUT /items/detail/{donationId}`
- **API Function**: `getItemInfoByDonationId`

