# co-mento-server
명지대학교 24-2 캡스톤디자인 2. Team. Co-mento Server

# **캡스톤 디자인2**  
**코멘토(Co-Mento) - AI 기반 코딩 테스트 플랫폼**

<br/>

## 프로젝트 개요  

**코멘토**는 알고리즘 문제 풀이와 AI 리뷰를 결합한 코딩 테스트 플랫폼입니다.  
사용자는 문제를 풀고 AI로부터 풀이 리뷰를 받아 코드의 개선점을 확인할 수 있습니다.  
학습 효율을 높이고, 문제 해결력을 키우는 차별화된 코딩 학습 경험을 제공합니다.

<br/>

---

## 기능 소개

### 1. 문제 목록
- 알고리즘 문제를 알고리즘별, 난이도별, 성공 여부별로 분류하여 제공
- 문제 유형별 필터링 및 검색 기능 제공
- 비슷한 문제를 묶어 문제집 형태로 제공

### 2. 문제 풀이
- C, C++, Python, Java 등 10개의 프로그래밍 언어 지원
- 사용자 경험을 고려한 코드 에디터 제공
- 제출 후 채점 로딩 화면 표시

### 3. AI 리뷰
- 문제 풀이 후 AI 기반 리뷰 기능 제공
- 코드의 효율성 및 가독성 개선 포인트 제시

### 4. 사용자 프로필 관리
- 즐겨찾는 문제, 맞힌 문제, 틀린 문제 등 개인화된 기록 제공
- 제출한 문제 목록 및 AI 리뷰 기록 저장

### 5. 랭킹
- 사용자 랭킹 시스템 제공
- 경험치(포인트) 표시로 성취감 제공


<br/>

---

## **결과물**

### 주요 화면  
- 로그인 페이지
  - ![로그인](https://github.com/user-attachments/assets/2dba5933-4b2e-4bfa-88fd-57f85b9020dc)
- 메인 페이지
  - <img width="693" alt="image" src="https://github.com/user-attachments/assets/26ed4baf-4e56-4c9f-b01a-bb6e60b0bc6e">
  - <img width="697" alt="image" src="https://github.com/user-attachments/assets/4411b474-5a82-44ca-9761-7db4bb5169c2">

- 문제 목록 페이지
  - <img width="1029" alt="image" src="https://github.com/user-attachments/assets/66ce859e-de42-4bde-93b5-f58178c16607">
  - <img width="1035" alt="image" src="https://github.com/user-attachments/assets/27626903-4b6f-4a57-89b5-d03f1d3a9ef0">

  
- 문제 풀이 에디터
  - <img width="779" alt="image" src="https://github.com/user-attachments/assets/1d76fc41-05d5-43a6-bb77-c43a64c09542">
  - <img width="930" alt="image" src="https://github.com/user-attachments/assets/79d75217-1674-4057-a178-8d5e5e1b600b">


- AI 리뷰 페이지
  - <img width="777" alt="image" src="https://github.com/user-attachments/assets/d140c174-db66-4159-8f29-cfe6359db89a">
  - <img width="786" alt="image" src="https://github.com/user-attachments/assets/a7c6165d-7177-4eaf-95c8-71bc1e0a8f51">

- 랭킹 페이지
  - <img width="1030" alt="image" src="https://github.com/user-attachments/assets/b6a5e5cb-4d18-4e4c-9dab-eb0fcb680ac5">

- 마이페이지
  - <img width="1034" alt="image" src="https://github.com/user-attachments/assets/b39a39d9-92b7-4f2d-ae02-2615a2b437c4">
  - <img width="1033" alt="image" src="https://github.com/user-attachments/assets/5d784ee3-593d-4c6c-b7f6-5c8e451a0e05">
  - <img width="1033" alt="image" src="https://github.com/user-attachments/assets/46a67d1f-d842-455a-b767-2c430a11f652">




<br/>

---

## **스택 (Tech Stacks)**

### Environment

![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-007ACC?style=for-the-badge&logo=Visual%20Studio%20Code&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)
![Github](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white)

### Config

![yarn](https://img.shields.io/badge/yarn-2C8EBB?style=for-the-badge&logo=yarn&logoColor=white)

### Development

![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=Javascript&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)

### Communication

![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white)
![GoogleMeet](https://img.shields.io/badge/GoogleMeet-00897B?style=for-the-badge&logo=Google%20Meet&logoColor=white)


<br/>

---



## 📠 Convention

### 🤝 Branch Naming Convention

| 머릿말  | 설명                               |
| ------- | ---------------------------------- |
| main    | 서비스 브랜치                      |
| develop | 배포 전 작업 기준                  |
| feature | 기능 단위 구현                     |
| hotfix  | 서비스 중 긴급 수정 건에 대한 처리 |

<details>
<summary>Branch Naming Convention Detail</summary>
<div markdown="1">

```
master(main) ── develop ── feature
└── hotfix
```

- [ ] [깃 플로우](https://techblog.woowahan.com/2553/)를 베이스로 하여 프로젝트 사이즈에 맞게 재정의했습니다.
- [ ] 브랜치 이름은 `cabab-case`를 따릅니다.
- [ ] 이슈 번호는 가장 마지막에 적습니다.

#### master(main)

- [ ] 실제 서비스가 이루어지는 브랜치입니다.
- [ ] 이 브랜치를 기준으로 develop 브랜치가 분기됩니다.
- [ ] 배포 중, 긴급하게 수정할 건이 생길시 hotfix 브랜치를 만들어 수정합니다.

#### develop

- [ ] 개발, 테스트, 릴리즈 등 배포 전 작업의 기준이 되는 브랜치입니다.
- [ ] 해당 브랜치를 default로 설정합니다.
- [ ] 이 브랜치에서 feature 브랜치가 분기됩니다.

#### feature

- [ ] 개별 개발자가 맡은 작업을 개발하는 브랜치입니다.
- [ ] feature/(feature-name) 과 같이 머릿말을 feature, 꼬릿말을 개발하는 기능으로 명명합니다.
- [ ] feature-name의 경우 cabab-case를 따릅니다.
- [ ] ex) feature/login-validation

#### hotfix

- [ ] 서비스 중 긴급히 수정해야 할 사항이 발생할 때 사용합니다.
- [ ] master에서 분기됩니다.

</div>
</details>

### 🤝 Commit Convention

| 머릿말           | 설명                                                                      |
| ---------------- | ------------------------------------------------------------------------- |
| feat             | 새로운 기능 추가                                                          |
| fix              | 버그 수정                                                                 |
| design           | CSS 등 사용자 UI 디자인 변경                                              |
| !BREAKING CHANGE | 커다란 API 변경의 경우                                                    |
| !HOTFIX          | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우                     |
| refactor         | 프로덕션 코드 리팩토링업                                                  |
| comment          | 필요한 주석 추가 및 변경                                                  |
| docs             | 문서 수정                                                                 |
| test             | 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X)                        |
| setting          | 패키지 설치, 개발 설정                                                    |
| chore            | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |
| rename           | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우                        |
| remove           | 파일을 삭제하는 작업만 수행한 경우                                        |

<details>
<summary>Commit Convention Detail</summary>
<div markdown="1">

- `<타입>`: `<제목> - <이슈번호>` 의 형식으로 제목을 아래 공백줄에 작성
- 제목은 50자 이내 / 변경사항이 "무엇"인지 명확히 작성 / 끝에 마침표 금지
- 예) feat: 로그인 기능 추가 - #2
- 본문(구체적인 내용)을 아랫줄에 작성
- 여러 줄의 메시지를 작성할 땐 "-"로 구분 (한 줄은 72자 이내)
- 제목과 본문은 한 줄 띄워 분리

</div>
</details>
