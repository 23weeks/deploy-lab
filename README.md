### url : <a>https://d1edx29o6lkwr1.cloudfront.net/</a>

### 백엔드 데모 프로젝트

### 프로젝트 목표 :
    실시간 데이터 시각화 대시보드 구현
    백엔드 자동 배포(CI/CD) - GitHub Actions + EC2
    데이터베이스 연동 - MyBatis (xml로 구현)    

### 프론트엔드
    front-react (repository)
    react - Recharts 로 구현 예정
    프론트엔드 자동 배포(CI/CD) - GitHub Actions + S3 + CloudFront
    url : https://d1edx29o6lkwr1.cloudfront.net/
    현재 미연동 상태

### 프레임워크
    Spring-Boot

### 데이터베이스
    Oracle - RDS에 올릴 예정이었으나 비용 이슈로 로컬에 설치
    이슈 발생
        - 회사 네트워크를 사용하다보니 사설IP 영역이라 EC2에서 로컬로 접속이 불가,
          AWS EC2에 설치하려 해봤지만 프리티어의 금액을 벗어나 일단 중지.
        - 서버에 임시 데이터를 만들어두고 이를 통해 프론트엔드에 response 해주는 방식으로 변경
        - 로컬 환경에서는 DB에서 데이터를 가져오고 배포 환경에서는 임시 데이터를 가져오게 연동하려는 중 도메인 생성 사이트의 기술적 오류로 일단 lock
