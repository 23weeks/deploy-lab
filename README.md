# 백엔드 데모 프로젝트

이 프로젝트는 주식 데이터를 제공하는 RESTful API 서버입니다.
Spring Boot 기반으로 개발되었으며, AWS EC2와 GitHub Actions를 활용한 CI/CD를 적용하였습니다.

## 프로젝트 목표
    - RESTful API를 통한 주식 데이터 제공
    - 1시간마다 자동으로 주식 데이터 업데이트
    - ROWNUM 파라미터를 통한 데이터 개수 조절
    - AWS EC2 + GitHub Actions를 활용한 CI/CD 자동 배포

## 기술 스택
    - 프론트엔드 : React + TypeScript
    - 라이브러리 : Recharts
    - UI : Styled-Components
    - 빌드 툴 : npm
    - 배포 : AWS S3 + CloudFront

### 추가 설명
    - API 연동 방식(RESTful API)
    - CI/CD 방식(Github Actions)

# 🚀 CI/CD with AWS EC2

## AWS EC2 환경
    - OS : Ubuntu 22.04
    - 웹 서버 : Nginx
