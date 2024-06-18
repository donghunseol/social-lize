INSERT INTO user_tb (email, password, nickname, image, phone, birth, role, created_at)
VALUES ('ssar@nate.com', '1234', 'ssar', 'image.png', '010-1234-5678', '1994-12-26', 'USER', now()),
       ('cos@nate.com', '1234', 'cos', 'image.png', '010-1234-5678', '1994-12-26', 'USER', now()),
       ('love@nate.com', '1234', 'love', 'image.png', '010-1234-5678', '1994-12-26', 'USER', now()),
       ('egdg@nate.com', '1234', 'egdg', 'image.png', '010-1234-5678', '1994-12-26', 'USER', now()),
       ('roqkq33@nate.com', '1234', 'roqkq33', 'image.png', '010-1234-5678', '1994-12-26', 'USER', now());

-- INSERT INTO chat_tb (social_id, user_id, comment)
-- VALUES (1, 1, '밴드에 가입 하신것을 환영합니다.'),
--        (1, 2, '안녕하세요!');
--        (1, 3, '와 뉴비다.');

INSERT INTO social_tb (name, image, info, created_at)
VALUES ('소셜 이름', 'image.png', '소셜 소개글', now());

INSERT INTO board_tb (social_id, user_id, title, content, role, created_at)
VALUES (1, 1, '반갑습니다.', '안녕하세요.', 'POST', now());

-- INSERT INTO reply_tb (user_id, board_id, content, created_at)
-- VALUES (1, 1, '저도 반갑습니다.', now());
--        (2, 2, 'ㅋㅋㅋㅋㅋㅋ', now()),
--        (3, 3, '영광의 첫번 째 댓글', now()),
--        (4, 4, '영광의 첫번 째 댓글', now()),
--        (5, 5, '영광의 첫번 째 댓글', now());

INSERT INTO qna_tb (user_id, title, content, reply_content, reply_created_at, created_at)
VALUES (1, '카테고리 추가해주세요', '공포 카테고리 추가해주세요', '빠른 시일내로 추가해 드리겠습니다.', '2024-06-17', '2024-06-16'),
       (2, '대댓글에도 댓글을 달고 싶어요', '기능 추가 가능한가요?', '죄송합니다 대댓글에 댓글을 다는 기능은 추가해드리기 어렵습니다.', '2024-06-15', '2024-06-14'),
       (3, '오늘 점심 뭐먹죠?', '관리자님! 짜장면 vs 볶음밥 뭐먹죠?', '저는 오늘 밀면 먹었습니다.', '2024-06-14', '2024-06-13'),
       (4, '멤버 추가가 안되요 ㅠㅠ', '고쳐 주세요 ㅠㅠ', '오류 화면을 캡쳐하여 알려주시면 빠른 시일내로 고쳐드리겠습니다.', '2024-06-13', '2024-06-12'),
       (5, '오늘 가입했는데요', '다른 사람 소셜에는 어떻게 들어가나요?', null, null, '2024-06-18');

INSERT INTO notification_tb(user_id, role, created_at)
VALUES (1, 'BOARD', now());