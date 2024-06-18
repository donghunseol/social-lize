INSERT INTO user_tb (email, password, nickname, image, phone, birth, role, created_at, provider)
VALUES ('ssar@nate.com', '1234', 'ssar', 'image1.png', '010-1234-5678', '1994-12-26', 'USER', now(), null),
       ('cos@daum.net', '5678', 'cos', 'image2.png', '010-2345-6789', '1992-10-24', 'USER', now(), null),
       ('jane@kakao.com', 'abcd', 'jane', 'image3.png', '010-3456-7890', '1995-07-19', 'MANAGER', now(), 'KAKAO'),
       ('john@naver.com', 'efgh', 'john', 'image4.png', '010-4567-8901', '1993-03-15', 'USER', now(), 'NAVER'),
       ('lucy@gmail.com', 'ijk9', 'lucy', 'image5.png', '010-5678-1234', '1996-08-30', 'USER', now(), null);


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