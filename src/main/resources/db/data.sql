-- 유저 테이블 (비밀번호는 암호호되서 저장됨. 1234임)
INSERT INTO user_tb (email, password, nickname, image, phone, birth, role, created_at, provider)
VALUES ('ssar@nate.com', '$2a$10$MXM315UryOr9MSS8FoEQLuCvxpxtXjg198i5N39QbaKodES42qUbi', '하승진', '/images/userprofile.jpeg', '010-1234-5678', '1994-12-26', 'USER', now(), null),
       ('1@1.com', '$2a$10$MXM315UryOr9MSS8FoEQLuCvxpxtXjg198i5N39QbaKodES42qUbi', '김세운', '/images/userprofile.jpeg', '010-2345-6789', '1992-10-24', 'USER', now(), null),
       ('jane@kakao.com', '$2a$10$MXM315UryOr9MSS8FoEQLuCvxpxtXjg198i5N39QbaKodES42qUbi', '박제인', '/images/userprofile.jpeg', '010-3456-7890', '1995-07-19', 'USER', now(), 'KAKAO'),
       ('john@naver.com', '$2a$10$MXM315UryOr9MSS8FoEQLuCvxpxtXjg198i5N39QbaKodES42qUbi', '설조온', '/images/userprofile.jpeg', '010-4567-8901', '1993-03-15', 'USER', now(), 'NAVER'),
       ('lucy@gmail.com', '$2a$10$MXM315UryOr9MSS8FoEQLuCvxpxtXjg198i5N39QbaKodES42qUbi', '김루씨', '/images/userprofile.jpeg', '010-5678-1234', '1996-08-30', 'USER', now(), null),
       ('manager@gmail.com', '$2a$10$MXM315UryOr9MSS8FoEQLuCvxpxtXjg198i5N39QbaKodES42qUbi', '소셜 매니저', '/images/userprofile.jpeg', null, null, 'MANAGER', now(), null);

-- 소셜 테이블
INSERT INTO social_tb (name, image, info, status, created_at)
VALUES ('그린컴퓨터학원 수강생 모임', '/images/maincom1.png',
        '컴퓨터 학원에서 만난 수강생들이 기술과 지식을 공유하며 서로를 격려하는 네트워킹 모임입니다. 이 모임은 최신 기술 트렌드에 대한 토론, 공동 프로젝트 수행, 그리고 전문 기술 스킬 향상을 목표로 하고 있습니다. 여러분의 커리어 발전을 위한 완벽한 기회를 제공하며, 동시에 새로운 친구를 만날 수 있는 활기찬 모임입니다. 여러분의 참여를 기다립니다!',
        'ACTIVE', now()),
       ('Springboot 스터디 모임 - 서면', '/images/maincom2.png',
        '스프링부트 스터디 모임에 여러분을 초대합니다! 함께 배우고 성장할 수 있는 공간에서 스프링부트의 핵심 기능을 마스터하고, 실제 프로젝트에 적용해보세요. 체계적인 학습과 협업을 통해 Java 및 스프링 생태계에서의 전문성을 높여갈 수 있는 기회를 제공합니다. 매주 정기적으로 만나, 각자의 경험과 지식을 공유하며 실력을 쌓아가는 시간이 될 것입니다.',
        'ACTIVE', now()),
       ('수제 담배 만들기 모임- 연산동', '/images/smoking.png',
        '수제 담배 만들기 모임에 여러분을 초대합니다! 담배 잎 선별부터 롤링 기술까지, 전문가와 함께 수제 담배의 모든 과정을 배우고 직접 만들어 보세요. 이 모임은 수제 담배에 관심 있는 모든 분들에게 개방되어 있으며, 서로의 노하우를 공유하고 즐거운 시간을 보낼 수 있는 완벽한 기회입니다. 함께 특별한 경험을 만들어가며 담배 만들기의 예술을 체험해 보세요.',
        'ACTIVE', now()),
       ('아이폰 사용자 모임', '/images/iphone.png',
        '아이폰 사용자 모임에 여러분을 초대합니다! 이 모임은 아이폰 사용자들이 모여 최신 기능, 팁, 앱 등을 공유하고 서로의 경험을 나누는 자리입니다. 아이폰의 숨겨진 기능을 발견하고, 문제 해결 방법을 배우며, 새로운 앱을 추천받을 수 있는 기회를 제공합니다. 아이폰을 더 효율적으로 사용하는 방법을 배우고 싶다면 이 모임이 정답입니다. 매주 정기적으로 만나는 우리 모임에 참여해 보세요!',
        'ACTIVE', now()),
       ('아이스아메리카노를 사랑하는 사람들', '/images/coffee.png',
        '아이스 아메리카노를 사랑하는 사람들의 모임에 여러분을 초대합니다! 이 모임은 커피 애호가들이 모여 아이스 아메리카노의 매력에 대해 토론하고, 다양한 카페를 탐방하며 최고의 아이스 아메리카노를 찾아다니는 모험을 함께하는 자리입니다. 커피에 대한 깊은 이해와 함께, 새로운 친구들을 만나고 즐거운 시간을 보내며 일상에 작은 활력을 더할 수 있는 기회를 제공합니다. 커피와 대화를 사랑하는 모든 분들의 참여를 기다립니다!',
        'ACTIVE', now());

-- 소셜 게시판 테이블
INSERT INTO board_tb (social_id, user_id, content, role, created_at)
VALUES
    (1, 1, '이 커뮤니티에 오신 것을 환영합니다.<br>감사합니다.', 'POST', now()),
    (1, 2, '저는 아이스 아메리카노가 좋더라고요. <br>여러분은요?', 'POST', now()),
    (1, 3, '초보자들을 위한 Java 팁을 공유합니다.<br>감사합니다.', 'POST', now()),
    (1, 5, '이번 주말에 뭘 해야 할지... 좋은 게임 있나요?<br>감사합니다.', 'POST', now()),
    (2, 1, '지구 온난화에 대해 심각하게 생각해 볼 때입니다.<br>감사합니다.', 'POST', now()),
    (2, 2, '최근에 읽은 흥미로운 책이 있어요.<br>감사합니다.', 'POST', now()),
    (2, 4, '집에서 간단히 만들 수 있는 건강 레시피!<br>감사합니다.', 'POST', now()),
    (2, 5, '최근에 좋아하는 팝송 리스트입니다.<br>감사합니다.', 'POST', now()),
    (3, 1, '긍정적인 생각이 긍정적인 삶을 만듭니다.<br>감사합니다.', 'POST', now()),
    (3, 3, '새로운 기술 트렌드를 소개합니다.<br>감사합니다.', 'POST', now()),
    (3, 4, '어제 다녀온 요리 클래스 후기입니다.<br>감사합니다.', 'POST', now()),
    (3, 5, '직장인 스트레스 관리법을 공유합니다.<br>감사합니다.', 'POST', now()),
    (4, 2, '이번 주에 본 영화에 대한 개인적인 생각입니다.<br>스포일러 없이 감상 포인트를 공유합니다.', 'POST', now()),
    (4, 3, '더 나은 자신을 위한 자기개발 팁을 공유합니다.<br>모든 사람이 시도해 볼 수 있는 간단한 방법들입니다.', 'POST', now()),
    (4, 4, '최근 설치한 스마트 홈 기기에 대한 리뷰입니다.<br>생활이 얼마나 편리해졌는지 이야기해봅시다.', 'POST', now()),
    (4, 5, '집에서 쉽게 할 수 있는 가드닝 팁을 공유합니다.<br>집안을 더 아름답게 꾸밀 수 있는 방법을 알려드려요.', 'POST', now()),
    (5, 1, '커뮤니티의 최신 소식과 업데이트를 공유합니다.<br>회원님들의 활동을 기다립니다.', 'POST', now()),
    (5, 2, '심리학에 관심 있는 분들을 위한 강좌 정보입니다.<br>마음을 이해하는 시간을 가져보세요.', 'POST', now()),
    (5, 3, '저렴하게 여행할 수 있는 팁을 공유합니다.<br>비용을 절약하면서 여행의 즐거움을 누려보세요.', 'POST', now()),
    (5, 4, '건강을 유지하는 데 필요한 헬스케어 정보를 공유합니다.<br>건강한 생활을 위한 팁을 알려드립니다.', 'POST', now());

-- 소셜 마다 공지사항 등록
INSERT INTO board_tb (social_id, user_id, content, role, created_at)
VALUES
    (1, 6, '모든 회원은 서로를 존중해 주시기 바랍니다.<br>불쾌한 언행은 삼가해 주세요.', 'NOTICE', now()),
    (2, 6, '새로 가입하신 모든 분께 소정의 선물을 드립니다.<br>활동을 시작해 보세요!', 'NOTICE', now()),
    (3, 6, '이번 주 금요일에 사이트 점검이 있을 예정입니다.<br>서비스 이용에 참고해 주세요.', 'NOTICE', now()),
    (4, 6, '사용자의 편의성을 위해 새 기능이 추가되었습니다.<br>많은 이용 바랍니다.', 'NOTICE', now()),
    (5, 6, '모임장소에서의 안전 수칙을 준수해 주세요.<br>모두의 안전을 위해 협력해 주시길 바랍니다.', 'NOTICE', now());

-- 댓글 테이블
INSERT INTO reply_tb (board_id, user_id, content, created_at)
VALUES
    (1, 1, '오늘도 좋은 하루 시작하셨나요? 활기찬 하루 되세요!', now()),
    (2, 2, '이 포스트 정말 유익하네요. 더 많은 정보 기대할게요!', now()),
    (3, 3, '동감합니다. 이 주제에 대해 더 깊이 있는 논의를 해보고 싶어요.', now()),
    (4, 4, '안녕하세요, 여러분! 오늘의 토픽이 정말 흥미로워요.', now()),
    (5, 5, '저도 경험해본 바로는 정말 그렇더라고요. 공감합니다.', now()),
    (6, 1, '좋은 글 감사합니다. 다음 글도 기대됩니다!', now()),
    (7, 2, '이 논점에 대해 조금 더 설명해 주실 수 있나요?', now()),
    (8, 3, '오, 이건 정말 새로운 시각이네요. 생각해볼만한 주제입니다.', now()),
    (9, 4, '제 생각과는 조금 다르네요. 하지만 인사이트는 좋습니다.', now()),
    (10, 5, '이 정보 정말 도움이 되었어요. 공유해주셔서 감사합니다!', now()),
    (11, 1, '이런 의견도 있군요. 저는 조금 다른 생각인데요...', now()),
    (12, 2, '정말 흥미로운 점을 짚어주셨네요. 더 알아보고 싶어요.', now()),
    (13, 3, '이 부분에 대한 더 많은 정보가 필요할 것 같습니다.', now()),
    (14, 4, '글 잘 읽었습니다. 다음 내용도 기대하고 있겠습니다!', now()),
    (15, 5, '여러분의 의견은 어떤가요? 저는 이렇게 생각해요.', now()),
    (16, 1, '오늘 내용 정말 유용했습니다. 다들 어떻게 생각하시나요?', now()),
    (17, 2, '사실 이 주제에 대해 더 공부해보고 싶어졌어요.', now()),
    (18, 3, '이 주제에 대한 더 많은 논의가 필요한 듯 싶습니다.', now()),
    (19, 4, '아주 좋은 글이었습니다. 작성자님께 감사드려요.', now()),
    (20, 5, '글 내용 중 이 부분이 특히 인상적이었습니다.', now());

-- 대댓글 테이블
INSERT INTO rereply_tb (reply_id, user_id, content, created_at)
VALUES
    (1, 1, '정말 좋은 의견 감사합니다!', now()),
    (2, 2, '아주 도움이 되는 정보네요!', now()),
    (3, 3, '이 내용에 동의하기 어렵네요.', now()),
    (4, 4, '더 자세한 설명이 필요할 것 같아요.', now()),
    (5, 5, '좋은 점을 잘 지적해주셨네요.', now()),
    (6, 1, '저도 비슷한 경험을 했습니다.', now()),
    (7, 2, '더 많은 정보를 공유해주실 수 있나요?', now()),
    (8, 3, '이 주제에 대해 더 알고 싶어졌어요.', now()),
    (9, 4, '아주 유익한 내용이었습니다!', now()),
    (10, 5, '글쓴이의 의견에 전적으로 동의합니다.', now()),
    (11, 1, '이 주제는 항상 관심을 가지고 있던 것이었어요.', now()),
    (12, 2, '조금 더 구체적인 예시를 들어주시면 좋겠습니다.', now()),
    (13, 3, '완전히 새로운 시각을 제공해주셨네요.', now()),
    (14, 4, '이 의견에는 반대하고 싶습니다.', now()),
    (15, 5, '정말 흥미롭고 생각할 거리를 주는 글이네요.', now()),
    (16, 1, '이 주제에 대한 더 많은 논의가 필요해 보입니다.', now()),
    (17, 2, '저는 이와 다른 경험을 했습니다. 다른 시각도 고려해주세요.', now()),
    (18, 3, '더 깊이 있는 분석이 이루어졌으면 좋겠습니다.', now()),
    (19, 4, '좋은 정보 감사합니다. 더 많이 배우고 싶네요.', now()),
    (20, 5, '이견이 있지만 토론이 재미있을 것 같네요.', now());

-- 문의사항 테이블
INSERT INTO qna_tb (user_id, title, content, reply_content, state, reply_created_at, created_at)
VALUES (1, '카테고리 추가해주세요', '공포 카테고리 추가해주세요', '빠른 시일내로 추가해 드리겠습니다.', 'ANSWER', '2024-06-17', '2024-06-16'),
       (2, '대댓글에도 댓글을 달고 싶어요', '기능 추가 가능한가요?', '죄송합니다 대댓글에 댓글을 다는 기능은 추가해드리기 어렵습니다.', 'ANSWER', '2024-06-15', '2024-06-14'),
       (3, '오늘 점심 뭐먹죠?', '관리자님! 짜장면 vs 볶음밥 뭐먹죠?', '타협해서 짜장밥 어떠십니까.', 'ANSWER', '2024-06-14', '2024-06-13'),
       (4, '멤버 추가가 안되요 ㅠㅠ', '고쳐 주세요 ㅠㅠ', '오류 화면을 캡쳐하여 알려주시면 빠른 시일내로 고쳐드리겠습니다.', 'ANSWER', '2024-06-13', '2024-06-12'),
       (5, '오늘 가입했는데요', '다른 사람 소셜에는 어떻게 들어가나요?', null, 'WAITING', null, '2024-06-18'),
       (1, '여기 답장 제대로 해주냐?', '테스트 해본다 ㅋ', null, 'WAITING', null, '2024-06-20'),
       (1, '여기 답장 제대로 해주냐?', '테스트 해본다 ㅋ', null, 'DELETE', null, '2024-06-20');

-- 알림 테이블
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

-- 소셜 채팅 테이블
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

-- 소셜 라이즈 멤버 테이블
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

-- 소셜 카테고리 이름 테이블
INSERT INTO category_name_tb (name, image_path, status)
VALUES (''💻 컴퓨터'', '이미지', 'ACTIVE'),
       ('🚬 담배', '이미지', 'ACTIVE'),
       ('☕ 카페', '이미지', 'ACTIVE'),
       ('📱 아이폰', '이미지', 'ACTIVE'),
       ('💪 운동', '이미지', 'ACTIVE'),
       ('🔴 습관', '이미지', 'ACTIVE'),
       ('📚 독서', '이미지', 'ACTIVE'),
       ('📖 스터디', '이미지', 'ACTIVE'),
       ('🌐 외국어', '이미지', 'ACTIVE'),
       ('✏️ 글쓰기', '이미지', 'ACTIVE'),
       ('🥗 다이어트/식단', '이미지', 'ACTIVE'),
       ('🎨 그림/공예', '이미지', 'ACTIVE'),
       ('📱 안드로이드폰', '이미지', 'ACTIVE');


-- 소셜 카테고리 테이블
INSERT INTO category_tb (social_id, category_name_id, created_at)
VALUES (1, 1, now()),
       (2, 1, now()),
       (3, 2, now()),
       (4, 4, now()),
       (5, 3, now());

-- 게시글 좋아요 테이블
INSERT INTO like_tb (user_id, board_id, created_at)
VALUES (1, 2, now()),
       (1, 3, now()),
       (1, 4, now()),
       (2, 3, now()),
       (3, 3, now()),
       (4, 3, now()),
       (1, 11, now()),
       (1, 12, now()),
       (2, 12, now()),
       (3, 12, now()),
       (2, 1, now()),
       (2, 4, now()),
       (3, 4, now()),
       (2, 13, now()),
       (2, 14, now()),
       (3, 15, now()),
       (3, 16, now()),
       (4, 7, now()),
       (4, 8, now()),
       (4, 17, now()),
       (4, 18, now()),
       (5, 9, now());

-- 게시글 북마크 테이블
INSERT INTO bookmark_tb (user_id, board_id, created_at)
VALUES (1, 2, now()),
       (2, 1, now()),
       (1, 3, now()),
       (3, 15, now()),
       (4, 7, now()),
       (5, 10, now());

-- 게시글 해시태그 테이블
INSERT INTO hashtagk_tb (board_id, name, created_at)
VALUES (4, '게임추천', now()),
       (8, '노래', now()),
       (12, '스트레스', now()),
       (16, '인테리어', now()),
       (20, '절약', now());

-- 신고 테이블
INSERT INTO report_tb (user_id, report_user_id, board_id, reply_id, rereply_id, content, result, created_at)
VALUES (1, 2, 21, null, null, '2번 유저가 1번 유저의 21번 게시글을 신고함', '게시글 삭제 처리', now()),
       (2, 1, 22, null, null, '1번 유저가 2번 유저의 22번 게시글을 신고함', '게시글 수정 처리', now());


-- 첨부 파일 테이블
INSERT INTO file_tb (social_id, user_id, name, path, created_at)
VALUES (1, 5, '첨부파일1', 'file1', now()),
       (2, 1, '첨부파일2', 'file2', now()),
       (3, 5, '첨부파일3', 'file3', now()),
       (4, 2, '첨부파일4', 'file4', now()),
       (5, 4, '첨부파일5', 'file5', now());


-- 이미지 및 영상 앨범 테이블
INSERT INTO album_tb (user_id, board_id, path, type, created_at)
VALUES (5, 4, '/upload/image1.jpg', 'IMAGE', now()),
       (5, 4, '/upload/video1.mp4', 'VIDEO', now()),
       (1, 8, './upload/image2.png', 'IMAGE', now()),
       (5, 12, './upload/image3.png', 'IMAGE', now()),
       (2, 16, './upload/image4.png', 'IMAGE', now()),
       (4, 20, './upload/image5.png', 'IMAGE', now());