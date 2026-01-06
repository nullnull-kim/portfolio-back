-- profile
INSERT INTO profile (created, updated, enabled, slug, name, contact_email, github, blog, birthday, profile_image_url)
VALUES (now(), now(), true, 'my', '김태영', 'nullnull.kim@gmail.com', 'https://github.com/nullnull-kim', 'https://nullnull-kim.github.io/', '1993-01-23', '');

-- education
INSERT INTO education (created, updated, enabled, profile_id, school_name, start_date, end_date, major )
VALUES (now(), now(), true, 1, '선문대학교', '2011-03', '2018-08', '컴퓨터공학');
INSERT INTO education (created, updated, enabled, profile_id, school_name, start_date, end_date, major )
VALUES (now(), now(), true, 1, '인천고등학교', '2008-02', '2011-02', '인문계');

-- certifications
INSERT INTO certification (created, updated, enabled, profile_id, name, issued_by, issued_date)
VALUES (now(), now(), true, 1, 'SQLD', '한국데이터베이스진흥센터', '2024-06-21');

INSERT INTO certification (created, updated, enabled, profile_id, name, issued_by, issued_date)
VALUES (now(), now(), true, 1, '정보처리기사', '한국산업인력공단', '2018-08-17');

-- other_experience
INSERT INTO other_experience (created, updated, enabled, profile_id, title, role, start_date, end_date, description)
VALUES (now(), now(), true, 1, '정보통신산업진흥원 주관 다국적 해커톤', '팀장', '2017-06-26', '2017-06-27', '외국학생과 한 팀을 이뤄 "홍익인간 기술"을 주제로 ICTㆍ친환경 그린테크ㆍ융합테크 등 4차 산업혁명 시대의 전반적인 영역에 관한 경연 및 시제품 제작');

INSERT INTO other_experience (created, updated, enabled, profile_id, title, role, start_date, end_date, description)
VALUES (now(), now(), true, 1, '인프런, "재고시스템으로 알아보는 동시성이슈" 해결방법 수료', '', '2022-08-13', '2017-08-24', '재고시스템을 작접 만들면서 Application Level, Database Lock, Redis Distributed Lock을 통해 동시성 이슈를 해결하는 방법을 학습');

-- skills

-- work_experience
INSERT INTO work_experience (
    profile_id,
    company_name,
    company_headline,
    title,
    start_date,
    end_date,
    is_current,
    location,
    tech_stack,
    summary,
    achievements,
    sort_order,
    created,
    updated,
    enabled
) VALUES (
             1,
             '비케이엔소프트',
             '하나은행 대외계 펌뱅킹 시스템 운영',
             '백엔드 개발자',
             DATE '2023-04-10',
             NULL,
             TRUE,
             '인천',
             'Kotlin, Spring Boot, Oracle, JPA, REST API',
             '하나은행 대외계 펌뱅킹 서비스 운영 및 안정화',
             '대외계 펌뱅킹 시스템 운영 및 개선
         기업 고객사 ERP 연계 API 안정화
         초당 100~200 TPS 실시간 거래 처리',
             1,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             TRUE
         );

INSERT INTO work_experience (
    profile_id,
    company_name,
    company_headline,
    title,
    start_date,
    end_date,
    is_current,
    location,
    tech_stack,
    summary,
    achievements,
    sort_order,
    created,
    updated,
    enabled
) VALUES (
             1,
             '어드바이저',
             '응용 소프트웨어 개발',
             '백엔드 개발자',
             DATE '2022-05-12',
             DATE '2023-01-01',
             FALSE,
             '서울',
             'Java, Spring Framework, JSP, JavaScript, Oracle',
             '전자정부 프레임워크 기반 백엔드 개발',
             '공인인증서 기반 사용자 인증 모듈 설계
         REST API 설계 및 유지보수
         JSP 기반 화면 연동',
             2,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             TRUE
         );

INSERT INTO work_experience (
    profile_id,
    company_name,
    company_headline,
    title,
    start_date,
    end_date,
    is_current,
    location,
    tech_stack,
    summary,
    achievements,
    sort_order,
    created,
    updated,
    enabled
) VALUES (
             1,
             '아비토스',
             '시스템 통합(SI) 및 유지보수',
             '웹 개발자',
             DATE '2021-06-28',
             DATE '2022-03-16',
             FALSE,
             '인천',
             'Spring, HTML, CSS, JSP, Oracle',
             '공공기관 웹 서비스 유지보수',
             '관리자 페이지 기능 개선
         정기 보안 점검 대응
         XSS / CSRF / SQL Injection 취약점 조치',
             3,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             TRUE
         );

INSERT INTO work_experience (
    profile_id,
    company_name,
    company_headline,
    title,
    start_date,
    end_date,
    is_current,
    location,
    tech_stack,
    summary,
    achievements,
    sort_order,
    created,
    updated,
    enabled
) VALUES (
             1,
             '창훈인터넷',
             '서비스 및 소프트웨어 개발',
             '웹 개발자',
             DATE '2019-12-09',
             DATE '2021-06-19',
             FALSE,
             '서울',
             'Struts, JSP, HTML, CSS, JavaScript, Oracle',
             '기업 홈페이지 및 관리자 시스템 유지보수',
             'CMS 기반 콘텐츠 관리 기능 개발
         관리자 페이지 성능 개선
         기존 레거시 코드 유지보수',
             4,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             TRUE
         );

INSERT INTO work_experience (
    profile_id,
    company_name,
    company_headline,
    title,
    start_date,
    end_date,
    is_current,
    location,
    tech_stack,
    summary,
    achievements,
    sort_order,
    created,
    updated,
    enabled
) VALUES (
             1,
             '삼호플러스',
             '정보처리 시스템 및 네트워크 운영',
             '시스템 엔지니어',
             DATE '2018-07-18',
             DATE '2019-10-01',
             FALSE,
             '인천',
             'Linux, Network, Server, Monitoring',
             '서버 및 네트워크 운영',
             '24시간 운영 환경 인프라 관리
         장애 대응 및 복구 작업
         네트워크 장비 유지보수',
             5,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             TRUE
         );
