INSERT INTO user_tb (email, password, nickname, image, phone, birth, role, created_at, provider)
VALUES ('ssar@nate.com', '1234', 'ssar', 'image1.png', '010-1234-5678', '1994-12-26', 'USER', now(), null),
       ('cos@daum.net', '5678', 'cos', 'image2.png', '010-2345-6789', '1992-10-24', 'USER', now(), null),
       ('jane@kakao.com', 'abcd', 'jane', 'image3.png', '010-3456-7890', '1995-07-19', 'MANAGER', now(), 'KAKAO'),
       ('john@naver.com', 'efgh', 'john', 'image4.png', '010-4567-8901', '1993-03-15', 'USER', now(), 'NAVER'),
       ('lucy@gmail.com', 'ijk9', 'lucy', 'image5.png', '010-5678-1234', '1996-08-30', 'USER', now(), null);
