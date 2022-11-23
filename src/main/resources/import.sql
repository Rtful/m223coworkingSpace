INSERT INTO "Role"("id", "name")
VALUES (1, 'Admin'), (2, 'Member');
INSERT INTO "User"("email", "firstname", "lastname", "birthdate", "gender", "password", "role_id")
VALUES('tbertschi.icloud.com', 'Tobias', 'Bertschi', '2004-07-12', 'f', 'testpw', 2),
      ('test@example.com', 'aa', 'bb', '1999-09-13', 'm', 'password', 2),
      ('aholenstein.icloud.com', 'Aaron', 'Holenstein', '2002-07-12', 'm', 'testpw', 1);

INSERT INTO "Workstation"("id", "floor")
VALUES(1, 3), (2, 3), (3, 4);

INSERT INTO "Booking"("user_id", "start", "end", "workstation_id")
VALUES(1, TO_TIMESTAMP('2022-11-22 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2022-11-22 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1),
      (2, TO_TIMESTAMP('2022-11-22 06:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2022-11-22 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
