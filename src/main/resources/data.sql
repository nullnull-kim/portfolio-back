INSERT INTO profile (created, updated, enabled, slug, name, contact_email, github, blog, birthday, profile_image_url)
VALUES (now(), now(), true, 'my', '김태영', 'nullnull.kim@gmail.com', 'https://github.com/nullnull-kim', 'https://nullnull-kim.github.io/', '1993-01-23', '');

INSERT INTO education (created, updated, enabled, profile_id, school_name, started, ended, major )
VALUES (now(), now(), true, 1, '선문대학교', '2011-03', '2018-08', '컴퓨터공학');
INSERT INTO education (created, updated, enabled, profile_id, school_name, started, ended, major )
VALUES (now(), now(), true, 1, '인천고등학교', '2008-02', '2011-02', '인문계');

