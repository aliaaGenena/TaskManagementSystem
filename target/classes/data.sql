INSERT INTO "user" (USER_ID,USERNAME,EMAIL,PASSWORD) VALUES 
(1,'zain','admin@gmail.com','$2a$10$tkunPVKw2LIKq6huKZavzedK6M8nbQI24/ZKINks3GiD41ufHTmYy'),
(2,'farah','user@gmail.com','$2a$10$SdqDal4Sgv7Pmm4xK2g08uLFS30rgHcbZuoKOSi5CB1Fjfk2Et87i');


INSERT INTO "role" (ROLE_ID,NAME,TYPE,DESCRIPTION) VALUES 
(1,'ADMIN','create-task','role for create task'),
(2,'ADMIN','assign-task','role for assign task'),
(3,'ADMIN','edit-task','role for edit task'),
(4,'USER','view-task','role for view task'),
(5,'ADMIN','delete-task','role for delete task');


INSERT INTO "user_role" (USER_ID,ROLE_ID) VALUES
(1,1),
(1,2),
(1,3),
(1,5),
(2,4);

INSERT INTO TASK (ID,TITLE,DESCRIPTION,STATUS,PRIORITY,DUEDATE) VALUES
(1,'1review','review daily report on transactions','done','medium','2024-07-20'),
(2,'2review','review daily report on transactions','in progress','high','2024-07-20'),
(3,'3review','review daily report on transactions','todo','low','2024-07-20'),
(4,'4review','review daily report on transactions','in progress','high','2024-07-20'),
(5,'5review','review daily report on transactions','done','medium','2024-07-20'),
(6,'6review','review daily report on transactions','todo','low','2024-07-20'),
(7,'7review','review daily report on transactions','in progress','high','2024-07-03'),
(8,'8review','review daily report on transactions','done','medium','2024-07-03'),
(9,'9review','review daily report on transactions','todo','low','2024-07-03'),
(10,'10review','review daily report on transactions','in progress','high','2024-07-04'),
(11,'1review','review daily report on transactions','done','medium','2024-07-04'),
(12,'2review','review daily report on transactions','todo','low','2024-07-04'),
(13,'3review','review daily report on transactions','in progress','high','2024-07-05'),
(14,'4review','review daily report on transactions','done','medium','2024-07-05'),
(15,'5review','review daily report on transactions','todo','low','2024-07-05'),
(16,'6review','review daily report on transactions','in progress','high','2024-07-06'),
(17,'7review','review daily report on transactions','done','medium','2024-07-06'),
(18,'8review','review daily report on transactions','todo','low','2024-07-06'),
(19,'9review','review daily report on transactions','in progress','high','2024-07-07'),
(20,'10review','review daily report on transactions','done','medium','2024-07-07'),
(21,'11review','review daily report on transactions','todo','low','2024-07-07'),
(22,'12review','review daily report on transactions','in progress','high','2024-07-08'),
(23,'13review','review daily report on transactions','done','medium','2024-07-08'),
(24,'14review','review daily report on transactions','todo','low','2024-07-08'),
(25,'15review','review daily report on transactions','in progress','high','2024-07-09'),
(26,'16review','review daily report on transactions','done','medium','2024-07-09'),
(27,'17review','review daily report on transactions','todo','low','2024-07-09'),
(28,'18review','review daily report on transactions','in progress','high','2024-07-10'),
(29,'19review','review daily report on transactions','done','medium','2024-07-10'),
(30,'20review','review daily report on transactions','todo','low','2024-07-10'),
(31,'21review','review daily report on transactions','in progress','high','2024-07-11'),
(32,'22review','review daily report on transactions','done','medium','2024-07-11'),
(33,'23review','review daily report on transactions','todo','low','2024-07-11'),
(34,'24review','review daily report on transactions','in progress','high','2024-07-12'),
(35,'25review','review daily report on transactions','done','medium','2024-07-12'),
(36,'26review','review daily report on transactions','todo','low','2024-07-12'),
(37,'27review','review daily report on transactions','in progress','high','2024-07-13'),
(38,'28review','review daily report on transactions','done','medium','2024-07-13'),
(39,'29review','review daily report on transactions','todo','low','2024-07-13'),
(40,'30review','review daily report on transactions','in progress','high','2024-07-14');
