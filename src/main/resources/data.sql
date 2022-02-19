insert into `user`(`user_id`, `name`, `created_at`, `updated_at`, `deleted_at`)
values (1, '김제로', now(), now(), null),
       (2, '박완주', now(), now(), null),
       (3, '이패캠', now(), now(), null);


insert into `book` (`book_id`, `title`, `sale`, `min_age`, `created_at`, `updated_at`)
values (1, '리팩토링', 'N', 0, now(), now()),
       (2, '리팩토링2', 'Y', 0, now(), now()),
       (3, '엔터프라이즈 애플리케이션 아키텍처 패턴', 'Y', 0, now(), now()),
       (4, 'Clean Code 클린 코드', 'Y', 0, now(), now()),
       (5, '클린 아키텍처', 'Y', 0, now(), now()),
       (6, '자바 ORM 표준 JPA 프로그래밍', 'Y', 0, now(), now()),
       (7, '새로쓴 대용량 데이터베이스 솔루션', 'Y', 0, now(), now()),
       (8, '테스트 주도 개발', 'Y', 0, now(), now());

insert into `book_stock` (`book_stock_id`, `book_id`, `stock`, `created_at`, `updated_at`)
values (1, 1, 0, now(), now()),
       (2, 2, 5, now(), now()),
       (3, 3, 5, now(), now()),
       (4, 4, 3, now(), now()),
       (5, 5, 1, now(), now()),
       (6, 6, 10, now(), now()),
       (7, 7, 0, now(), now()),
       (8, 8, 10, now(), now());