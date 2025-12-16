-- profile
INSERT INTO profile (created, updated, enabled, slug, name, contact_email, github, blog, birthday, profile_image_url)
VALUES (now(), now(), true, 'my', '김태영', 'nullnull.kim@gmail.com', 'https://github.com/nullnull-kim', 'https://nullnull-kim.github.io/', '1993-01-23', '');

-- education
INSERT INTO education (created, updated, enabled, profile_id, school_name, started, ended, major )
VALUES (now(), now(), true, 1, '선문대학교', '2011-03', '2018-08', '컴퓨터공학');
INSERT INTO education (created, updated, enabled, profile_id, school_name, started, ended, major )
VALUES (now(), now(), true, 1, '인천고등학교', '2008-02', '2011-02', '인문계');

-- certifications
INSERT INTO certification (created, updated, enabled, profile_id, name, issued_by, issued_at)
VALUES (now(), now(), true, 1, 'SQLD', '한국데이터베이스진흥센터', '2024-06-21');

INSERT INTO certification (created, updated, enabled, profile_id, name, issued_by, issued_at)
VALUES (now(), now(), true, 1, '정보처리기사', '한국산업인력공단', '2018-08-17');

-- other_experience
INSERT INTO other_experience (created, updated, enabled, profile_id, title, role, started, ended, description)
VALUES (now(), now(), true, 1, '정보통신산업진흥원 주관 다국적 해커톤', '팀장', '2017-06-26', '2017-06-27', '외국학생과 한 팀을 이뤄 "홍익인간 기술"을 주제로 ICTㆍ친환경 그린테크ㆍ융합테크 등 4차 산업혁명 시대의 전반적인 영역에 관한 경연 및 시제품 제작');

INSERT INTO other_experience (created, updated, enabled, profile_id, title, role, started, ended, description)
VALUES (now(), now(), true, 1, '인프런, "재고시스템으로 알아보는 동시성이슈" 해결방법 수료', '', '2022-08-13', '2017-08-24', '재고시스템을 작접 만들면서 Application Level, Database Lock, Redis Distributed Lock을 통해 동시성 이슈를 해결하는 방법을 학습');

-- skills

-- work_experience