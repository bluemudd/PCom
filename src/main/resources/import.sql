INSERT INTO USERS(ID, EMAIL, NICKNAME, PASSWORD) VALUES(2, 'parkjm0817@naver.com', '앗싸리정민', '$2a$10$4D1CybuuJcRPsSmtKK4ZFOwnEThHb/xISX0UQ2NgrnDVUrZJmAWC.');
INSERT INTO USERS_ROLES(USERS_ID, ROLES) VALUES(2,'ROLE_USER');
INSERT INTO USERS(ID, EMAIL, NICKNAME, PASSWORD) VALUES(3, 'sudin@naver.com', '앗싸리수딘', '$2a$10$4D1CybuuJcRPsSmtKK4ZFOwnEThHb/xISX0UQ2NgrnDVUrZJmAWC.');
INSERT INTO USERS_ROLES(USERS_ID, ROLES) VALUES(3,'ROLE_USER');

INSERT INTO AGORA_TABLE(AGORA_ID, CONTENTS, CREATE_DATE, UPDATE_DATE, LIKES, TITLE, VIEWS, USERS_ID) VALUES(1, '서울시의회 계약투명성심의회는 시의회사무처가 발주하는 사업에 대한 입찰 참가 자격 제한, 계약체결 방법, 낙찰자 결정방법 등에 관한 사항을 자문하는 심의 기구다.', '2023-04-01T11:44:30.327959', NULL, 3, '계약투명성심의위원 신규 위촉', 3, 2);
INSERT INTO AGORA_TABLE(AGORA_ID, CREATE_DATE, UPDATE_DATE, CONTENTS, LIKES, TITLE, VIEWS, USERS_ID ) VALUES(2, '2023-04-01T11:44:30.327959', NULL, '서울시의회 계약투명성심의회는 시의회사무처가 발주하는 사업에 대한 입찰 참가 자격 제한, 계약체결 방법, 낙찰자 결정방법 등에 관한 사항을 자문하는 심의 기구다.', 2, '계약투명성심의위원', 3, 2);
INSERT INTO AGORA_TABLE(AGORA_ID, CREATE_DATE, UPDATE_DATE, CONTENTS, LIKES, TITLE, VIEWS, USERS_ID ) VALUES(3, '2023-04-01T11:44:30.327959', NULL, '서울시의회 계약투명성심의회는 시의회사무처가 발주하는 사업에 대한 입찰 참가 자격 제한, 계약체결 방법, 낙찰자 결정방법 등에 관한 사항을 자문하는 심의 기구다.', 4, '계약투명성심의위원', 10, 2);
INSERT INTO AGORA_TABLE(AGORA_ID, CREATE_DATE, UPDATE_DATE, CONTENTS, LIKES, TITLE, VIEWS, USERS_ID ) VALUES(4, '2023-04-01T11:44:30.327959', NULL, '서울시의회 계약투명성심의회는 시의회사무처가 발주하는 사업에 대한 입찰 참가 자격 제한, 계약체결 방법, 낙찰자 결정방법 등에 관한 사항을 자문하는 심의 기구다.', 8, '계약투명성심의위원', 12, 2);
INSERT INTO AGORA_TABLE(AGORA_ID, CREATE_DATE, UPDATE_DATE, CONTENTS, LIKES, TITLE, VIEWS, USERS_ID ) VALUES(5, '2023-04-01T11:44:30.327959', NULL, '서울시의회 계약투명성심의회는 시의회사무처가 발주하는 사업에 대한 입찰 참가 자격 제한, 계약체결 방법, 낙찰자 결정방법 등에 관한 사항을 자문하는 심의 기구다.', 3, '계약투명성심의위원', 9, 2);

INSERT INTO AGORA_COMMENT(ID, CREATE_TIME, UPDATE_TIME, CONTENT, AGORA_TABLE_ID, USERS_ID ) VALUES(1, '2023-04-01T11:44:30.327959', NULL, '와 동감합니다', 1, 3);