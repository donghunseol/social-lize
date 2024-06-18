INSERT INTO user_tb (email, password, nickname, image, phone, birth, role, created_at, provider)
VALUES ('ssar@nate.com', '1234', 'ssar', 'image1.png', '010-1234-5678', '1994-12-26', 'USER', now(), null),
       ('cos@daum.net', '1234', 'cos', 'image2.png', '010-2345-6789', '1992-10-24', 'USER', now(), null),
       ('jane@kakao.com', '1234', '박제인', 'image3.png', '010-3456-7890', '1995-07-19', 'USER', now(), 'KAKAO'),
       ('john@naver.com', '1234', '설조온', 'image4.png', '010-4567-8901', '1993-03-15', 'USER', now(), 'NAVER'),
       ('lucy@gmail.com', '1234', '김루씨', 'image5.png', '010-5678-1234', '1996-08-30', 'USER', now(), null),
       ('manager@gmail.com', '1234', '소셜 매니저', 'image5.png', null, null, 'MANAGER', now(), null);


INSERT INTO social_tb (name, image, info, created_at)
VALUES ('그린컴퓨터학원 수강생 모임', '/usr/local/repos/img.jpg',
        '컴퓨터 학원에서 만난 수강생들이 기술과 지식을 공유하며 서로를 격려하는 네트워킹 모임입니다. 이 모임은 최신 기술 트렌드에 대한 토론, 공동 프로젝트 수행, 그리고 전문 기술 스킬 향상을 목표로 하고 있습니다. 여러분의 커리어 발전을 위한 완벽한 기회를 제공하며, 동시에 새로운 친구를 만날 수 있는 활기찬 모임입니다. 여러분의 참여를 기다립니다!',
        now()
       ),
       ('Springboot 스터디 모임 - 서면', '/usr/local/repos/springboot.jpg',
        '스프링부트 스터디 모임에 여러분을 초대합니다! 함께 배우고 성장할 수 있는 공간에서 스프링부트의 핵심 기능을 마스터하고, 실제 프로젝트에 적용해보세요. 체계적인 학습과 협업을 통해 Java 및 스프링 생태계에서의 전문성을 높여갈 수 있는 기회를 제공합니다. 매주 정기적으로 만나, 각자의 경험과 지식을 공유하며 실력을 쌓아가는 시간이 될 것입니다.',
        now()
       ),
       ('수제 담배 만들기 모임- 연산동', '/usr/local/repos/tobaco.jpg',
        '수제 담배 만들기 모임에 여러분을 초대합니다! 담배 잎 선별부터 롤링 기술까지, 전문가와 함께 수제 담배의 모든 과정을 배우고 직접 만들어 보세요. 이 모임은 수제 담배에 관심 있는 모든 분들에게 개방되어 있으며, 서로의 노하우를 공유하고 즐거운 시간을 보낼 수 있는 완벽한 기회입니다. 함께 특별한 경험을 만들어가며 담배 만들기의 예술을 체험해 보세요.',
        now()
       ),
       ('아이폰 사용자 모임', '/usr/local/repos/iphone.jpg',
        '아이폰 사용자 모임에 여러분을 초대합니다! 이 모임은 아이폰 사용자들이 모여 최신 기능, 팁, 앱 등을 공유하고 서로의 경험을 나누는 자리입니다. 아이폰의 숨겨진 기능을 발견하고, 문제 해결 방법을 배우며, 새로운 앱을 추천받을 수 있는 기회를 제공합니다. 아이폰을 더 효율적으로 사용하는 방법을 배우고 싶다면 이 모임이 정답입니다. 매주 정기적으로 만나는 우리 모임에 참여해 보세요!',
        now()
       ),
       ('아이스아메리카노를 사랑하는 사람들', '/usr/local/repos/americano.jpg',
        '아이스 아메리카노를 사랑하는 사람들의 모임에 여러분을 초대합니다! 이 모임은 커피 애호가들이 모여 아이스 아메리카노의 매력에 대해 토론하고, 다양한 카페를 탐방하며 최고의 아이스 아메리카노를 찾아다니는 모험을 함께하는 자리입니다. 커피에 대한 깊은 이해와 함께, 새로운 친구들을 만나고 즐거운 시간을 보내며 일상에 작은 활력을 더할 수 있는 기회를 제공합니다. 커피와 대화를 사랑하는 모든 분들의 참여를 기다립니다!',
        now()
       )
    ;

INSERT INTO board_tb (social_id, user_id, title, content, role, created_at)
VALUES
    (1, 1, '환영합니다!', '이 커뮤니티에 오신 것을 환영합니다.<br>감사합니다.', 'POST', now()),
    (1, 2, '커피 좋아하세요?', '저는 아이스 아메리카노가 좋더라고요. <br>여러분은요?', 'POST', now()),
    (1, 3, '프로그래밍 팁', '초보자들을 위한 Java 팁을 공유합니다.<br>감사합니다.', 'POST', now()),
    (1, 5, '게임 추천해주세요', '이번 주말에 뭘 해야 할지... 좋은 게임 있나요?<br>감사합니다.', 'POST', now()),
    (2, 1, '오늘의 뉴스', '지구 온난화에 대해 심각하게 생각해 볼 때입니다.<br>감사합니다.', 'POST', now()),
    (2, 2, '좋은 책 추천', '최근에 읽은 흥미로운 책이 있어요.<br>감사합니다.', 'POST', now()),
    (2, 4, '건강한 식사', '집에서 간단히 만들 수 있는 건강 레시피!<br>감사합니다.', 'POST', now()),
    (2, 5, '음악 추천', '최근에 좋아하는 팝송 리스트입니다.<br>감사합니다.', 'POST', now()),
    (3, 1, '오늘의 명언', '긍정적인 생각이 긍정적인 삶을 만듭니다.<br>감사합니다.', 'POST', now()),
    (3, 3, '기술 뉴스', '새로운 기술 트렌드를 소개합니다.<br>감사합니다.', 'POST', now()),
    (3, 4, '요리 클래스 후기', '어제 다녀온 요리 클래스 후기입니다.<br>감사합니다.', 'POST', now()),
    (3, 5, '직장인 스트레스', '직장인 스트레스 관리법을 공유합니다.<br>감사합니다.', 'POST', now()),
    (4, 2, '영화 리뷰', '이번 주에 본 영화에 대한 개인적인 생각입니다.<br>스포일러 없이 감상 포인트를 공유합니다.', 'POST', now()),
    (4, 3, '자기개발 팁', '더 나은 자신을 위한 자기개발 팁을 공유합니다.<br>모든 사람이 시도해 볼 수 있는 간단한 방법들입니다.', 'POST', now()),
    (4, 4, '스마트 홈 기기', '최근 설치한 스마트 홈 기기에 대한 리뷰입니다.<br>생활이 얼마나 편리해졌는지 이야기해봅시다.', 'POST', now()),
    (4, 5, '가드닝 팁', '집에서 쉽게 할 수 있는 가드닝 팁을 공유합니다.<br>집안을 더 아름답게 꾸밀 수 있는 방법을 알려드려요.', 'POST', now()),
    (5, 1, '커뮤니티 소식', '커뮤니티의 최신 소식과 업데이트를 공유합니다.<br>회원님들의 활동을 기다립니다.', 'POST', now()),
    (5, 2, '심리학 강좌', '심리학에 관심 있는 분들을 위한 강좌 정보입니다.<br>마음을 이해하는 시간을 가져보세요.', 'POST', now()),
    (5, 3, '여행 팁', '저렴하게 여행할 수 있는 팁을 공유합니다.<br>비용을 절약하면서 여행의 즐거움을 누려보세요.', 'POST', now()),
    (5, 4, '헬스케어 정보', '건강을 유지하는 데 필요한 헬스케어 정보를 공유합니다.<br>건강한 생활을 위한 팁을 알려드립니다.', 'POST', now()),
    (1, 1, '유저 번호 2번에게 신고당한 게시글', '유저 번호 2번에게 신고당한 게시글 입니다.', 'POST', now()),
    (1, 2, '유저 번호 1번에게 신고당한 게시글', '유저 번호 1번에게 신고당한 게시글 입니다.', 'POST', now());


--소셜 마다 공지사항 등록
INSERT INTO board_tb (social_id, user_id, title, content, role, created_at)
VALUES
    (1, 6, '커뮤니티 규칙 강화 안내', '모든 회원은 서로를 존중해 주시기 바랍니다.<br>불쾌한 언행은 삼가해 주세요.', 'NOTICE', now()),
    (2, 6, '회원 가입 기념 이벤트', '새로 가입하신 모든 분께 소정의 선물을 드립니다.<br>활동을 시작해 보세요!', 'NOTICE', now()),
    (3, 6, '사이트 점검 일정 공지', '이번 주 금요일에 사이트 점검이 있을 예정입니다.<br>서비스 이용에 참고해 주세요.', 'NOTICE', now()),
    (4, 6, '새로운 기능 업데이트', '사용자의 편의성을 위해 새 기능이 추가되었습니다.<br>많은 이용 바랍니다.', 'NOTICE', now()),
    (5, 6, '안전한 사용을 위한 안내', '모임장소에서의 안전 수칙을 준수해 주세요.<br>모두의 안전을 위해 협력해 주시길 바랍니다.', 'NOTICE', now());

-- INSERT INTO reply_tb (user_id, board_id, content, created_at)
-- VALUES (1, 1, '저도 반갑습니다.', now());
--        (2, 2, 'ㅋㅋㅋㅋㅋㅋ', now()),
--        (3, 3, '영광의 첫번 째 댓글', now()),
--        (4, 4, '영광의 첫번 째 댓글', now()),
--        (5, 5, '영광의 첫번 째 댓글', now());

INSERT INTO qna_tb (user_id, title, content, reply_content, reply_created_at, created_at)
VALUES (1, '카테고리 추가해주세요', '공포 카테고리 추가해주세요', '빠른 시일내로 추가해 드리겠습니다.', '2024-06-17', '2024-06-16'),
       (2, '대댓글에도 댓글을 달고 싶어요', '기능 추가 가능한가요?', '죄송합니다 대댓글에 댓글을 다는 기능은 추가해드리기 어렵습니다.', '2024-06-15', '2024-06-14'),
       (3, '오늘 점심 뭐먹죠?', '관리자님! 짜장면 vs 볶음밥 뭐먹죠?', '타협해서 짜장밥 어떠십니까.', '2024-06-14', '2024-06-13'),
       (4, '멤버 추가가 안되요 ㅠㅠ', '고쳐 주세요 ㅠㅠ', '오류 화면을 캡쳐하여 알려주시면 빠른 시일내로 고쳐드리겠습니다.', '2024-06-13', '2024-06-12'),
       (5, '오늘 가입했는데요', '다른 사람 소셜에는 어떻게 들어가나요?', null, null, '2024-06-18');

INSERT INTO notification_tb(user_id, role, created_at)
VALUES (1, 'BOARD', now()),
       (1, 'REPLY', now()),
       (1, 'REREPLY', now()),
       (2, 'BOARD', now()),
       (2, 'REPLY', now()),
       (2, 'REREPLY', now()),
       (3, 'BOARD', now()),
       (3, 'REPLY', now()),
       (3, 'REREPLY', now()),
       (4, 'BOARD', now()),
       (4, 'REPLY', now()),
       (4, 'REREPLY', now()),
       (5, 'BOARD', now()),
       (5, 'REPLY', now()),
       (5, 'REREPLY', now());

INSERT INTO chat_tb (social_id, user_id, comment, created_at)
VALUES (1, 1, '소셜에 가입 하신것을 환영합니다.', now()),
       (1, 2, '안녕하세요!', now()),
       (1, 3, '와 뉴비다.', now()),
       (2, 4, '스프링 부트 어떻게 해야 잘할 수 있나요?', now()),
       (2, 5, '저도 잘 모르겠습니다..', now()),
       (3, 5, '수제 담배 잘 만드는 사람 있나요? 저좀 알려주세요!', now()),
       (3, 1, '나도 알려줘요!', now()),
       (4, 2, '아이폰 처음 써보는데 꿀팁좀 주실분!', now()),
       (5, 3, '여기 사람들은 아아만 마시나요?', now()),
       (5, 2, '저는 다른것도 마십니다 아아는 싼맛에..', now());

INSERT INTO social_member_tb(social_id, user_id, role, state, created_at)
VALUES (1, 1, 'MANAGER', 'APPROVED', now()),
       (1, 2, 'MEMBER', 'APPROVED', now()),
       (1, 3, 'MEMBER', 'APPROVED', now()),
       (1, 5, 'MEMBER', 'APPROVED', now()),
       (2, 1, 'MEMBER', 'APPROVED', now()),
       (2, 2, 'MANAGER', 'APPROVED', now()),
       (2, 4, 'MEMBER', 'APPROVED', now()),
       (2, 5, 'MEMBER', 'APPROVED', now()),
       (3, 1, 'MEMBER', 'APPROVED', now()),
       (3, 3, 'MANAGER', 'APPROVED', now()),
       (3, 4, 'MEMBER', 'APPROVED', now()),
       (3, 5, 'MEMBER', 'APPROVED', now()),
       (4, 2, 'MEMBER', 'APPROVED', now()),
       (4, 3, 'MEMBER', 'APPROVED', now()),
       (4, 4, 'MANAGER', 'APPROVED', now()),
       (4, 5, 'MEMBER', 'APPROVED', now()),
       (5, 1, 'MEMBER', 'APPROVED', now()),
       (5, 2, 'MEMBER', 'APPROVED', now()),
       (5, 3, 'MEMBER', 'APPROVED', now()),
       (5, 4, 'MANAGER', 'APPROVED', now()),
       (1, 4, 'MEMBER', 'WAITING', now()),
       (2, 3, 'MEMBER', 'WAITING', now()),
       (3, 2, 'MEMBER', 'WAITING', now()),
       (4, 1, 'MEMBER', 'WAITING', now()),
       (5, 5, 'MEMBER', 'RESIGN', now());

INSERT INTO category_name_tb (name)
VALUES ('컴퓨터'),
       ('공부'),
       ('흡연'),
       ('수제'),
       ('휴대폰'),
       ('아이폰'),
       ('안드로이드폰'),
       ('커피'),
       ('카페');

INSERT INTO category_tb (social_id, category_name_id, created_at)
VALUES (1, 1, now()),
       (1, 2, now()),
       (2, 1, now()),
       (2, 2, now()),
       (3, 3, now()),
       (3, 4, now()),
       (4, 5, now()),
       (4, 6, now()),
       (5, 8, now()),
       (5, 9, now());

INSERT INTO like_tb (user_id, board_id, created_at)
VALUES (1, 2, now()),
       (1, 3, now()),
       (1, 11, now()),
       (1, 12, now()),
       (2, 1, now()),
       (2, 4, now()),
       (2, 13, now()),
       (2, 14, now()),
       (3, 15, now()),
       (3, 16, now()),
       (4, 7, now()),
       (4, 8, now()),
       (4, 17, now()),
       (4, 18, now()),
       (5, 9, now()),
       (5, 10, now());

INSERT INTO bookmark_tb (user_id, board_id, created_at)
VALUES (1, 2, now()),
       (2, 1, now()),
       (3, 15, now()),
       (4, 7, now()),
       (5, 10, now());

INSERT INTO hashtagk_tb (board_id, name, created_at)
VALUES (4, '게임추천', now()),
       (8, '노래', now()),
       (12, '스트레스', now()),
       (16, '인테리어', now()),
       (20, '절약', now());

INSERT INTO report_tb (user_id, report_user_id, board_id, reply_id, rereply_id, content, result, created_at)
VALUES (1, 2, 21, null, null, '2번 유저가 1번 유저의 21번 게시글을 신고함', '게시글 삭제 처리', now()),
       (2, 1, 22, null, null, '1번 유저가 2번 유저의 22번 게시글을 신고함', '게시글 수정 처리', now());

INSERT INTO file_tb (social_id, user_id, board_id, path, created_at)
VALUES (1, 5, 4, 'file1', now()),
       (2, 1, 8, 'file2', now()),
       (3, 5, 12, 'file3', now()),
       (4, 2, 16, 'file4', now()),
       (5, 4, 20, 'file5', now());

INSERT INTO album_tb (user_id, board_id, path, created_at)
VALUES (5, 4, 'image1.png', now()),
       (1, 8, 'image2.png', now()),
       (5, 12, 'image3.png', now()),
       (2, 16, 'image4.png', now()),
       (4, 20, 'image5.png', now());

