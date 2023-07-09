INSERT INTO roles (name)
VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

INSERT INTO users (username,email,password,first_name,last_name,registration_time,update_time,phone,birth_date,is_blocked)
VALUES
    ('Jeremy','netus.et@aol.com','Uhj51iuE7sW','Jonah','Hale','2023-11-23 21:29:20','2023-06-13 17:01:11','+7 (947) 822-22-66','1993-05-31','false'),
    ('Whoopi','tellus.non@aol.com','Mqm95ruC0yS','Charles','Curtis','2024-06-21 00:40:18','2023-05-01 07:22:34','+7 (961) 233-72-37','1993-10-21','false'),
    ('Inga','aliquam.erat@aol.net','Tqy66xqS2yH','Caryn','Stanton','2024-06-09 07:17:47','2023-03-29 13:42:49','+7 (922) 329-31-65','1996-07-23','false'),
    ('Brock','montes.nascetur@google.ca','Ybq85gsL4jK','Lavinia','Waters','2023-09-17 01:30:23','2023-08-30 19:24:38','+7 (980) 405-61-38','1993-08-13','false'),
    ('Eleanor','ornare@hotmail.edu','Tuf44fuM5eS','Kamal','Anthony','2023-02-12 12:05:02','2022-11-24 10:35:41','+7 (918) 632-88-73','1984-11-16','false'),
    ('Yael','et.magna.praesent@outlook.couk','Eiu20neM6rE','Victor','Burton','2023-03-27 15:31:19','2024-02-22 12:37:55','+7 (961) 226-07-71','1988-01-28','false'),
    ('Melyssa','aliquet.magna@yahoo.edu','Ojz56pzL5xR','Quyn','Beck','2022-12-13 04:02:30','2024-05-28 13:02:01','+7 (983) 375-64-46','2002-10-30','false'),
    ('Marshall','dui.semper@protonmail.org','Lef30yyV4bY','Brody','French','2023-02-27 07:50:52','2023-09-14 18:50:38','+7 (952) 837-20-31','1997-07-20','false'),
    ('Benedict','donec.vitae@google.couk','Ikj17gxW8oI','Christian','Wooten','2023-05-12 20:54:01','2023-03-04 03:33:12','+7 (926) 209-73-18','2002-10-07','false'),
    ('Kevin','quis.accumsan@yahoo.couk','Wpc78sbU1hF','Jordan','Cruz','2024-01-14 09:15:09','2023-12-15 19:43:14','+7 (986) 851-92-26','2005-09-07','false'),
    ('Isadora','mollis.duis@hotmail.com','Nfl18upD3nM','Noelani','Robinson','2022-06-27 23:58:05','2023-06-20 16:09:33','+7 (937) 523-75-41','2001-11-13','false'),
    ('Eve','massa.suspendisse@hotmail.com','Jij94riX4bC','Yeo','Mercer','2022-12-17 21:46:42','2022-08-29 13:56:46','+7 (967) 960-38-45','1991-10-09','false'),
    ('Armando','massa.vestibulum@icloud.couk','Yny05xlR2mS','Brynn','Gross','2022-10-15 06:35:04','2023-07-01 01:40:40','+7 (918) 643-55-02','1998-10-03','false'),
    ('Charde','ut.sem.nulla@google.net','Wly57mlF6gG','Piper','Leach','2024-05-24 17:59:43','2023-10-09 22:51:21','+7 (957) 548-41-06','2000-01-22','false'),
    ('Flavia','in.molestie@icloud.com','Ghk87lvV1pD','Wendy','Mayer','2022-08-23 06:50:34','2024-04-09 14:40:47','+7 (940) 344-44-95','2005-11-29','false'),
    ('Noelani','ac.facilisis@icloud.ca','Ibg35syQ7eD','Ahmed','Rodriguez','2024-06-06 03:29:50','2022-08-23 20:30:05','+7 (964) 625-29-21','2005-06-16','false'),
    ('Alan','dui.nec.urna@outlook.com','Tdd38onW6oW','Patience','Collier','2023-06-27 16:54:32','2022-08-14 21:33:04','+7 (987) 336-07-73','1981-03-27','false'),
    ('Ishmael','per@google.net','Xpg05snB6sQ','Rashad','Morse','2023-06-17 08:57:39','2023-04-01 22:08:34','+7 (944) 647-07-14','2000-11-01','false'),
    ('Amal','vestibulum.accumsan@protonmail.com','Rqo53aqQ6cJ','Charissa','Garrison','2022-10-06 23:00:53','2023-10-26 23:39:20','+7 (960) 824-26-34','2002-02-07','false'),
    ('Carissa','tempor@google.org','Fqh01byS7zP','Natalie','Alvarado','2023-03-07 05:38:43','2023-10-01 10:42:38','+7 (905) 982-75-58','2007-08-02','false');

INSERT INTO users_roles (user_id, role_id)
VALUES
    (1,1),
    (1,2),
    (2,2),
    (3,2),
    (4,2),
    (5,2),
    (6,2),
    (7,2),
    (8,2),
    (9,2),
    (10,2),
    (11,2),
    (12,2),
    (13,2),
    (14,2),
    (15,2),
    (16,2),
    (17,2),
    (18,2),
    (19,2),
    (20,2);

INSERT INTO wallets (currency,value,user_id)
VALUES
    ('RUB',2319,1),
    ('BYN',9313,2),
    ('EUR',1807,3),
    ('USD',1080,4),
    ('GEL',4860,5),
    ('KZT',8540,6),
    ('KGS',3707,7),
    ('TJS',4853,8),
    ('UZS',3750,9),
    ('RUB',7929,10),
    ('BYN',4226,11),
    ('EUR',2193,12),
    ('USD',4325,13),
    ('GEL',8477,14),
    ('KZT',6379,15),
    ('KGS',1537,16),
    ('TJS',9307,17),
    ('UZS',2452,18),
    ('RUB',4784,19),
    ('RUB',497,20);

INSERT INTO platforms (name)
VALUES
    ('Playstation 4'),
    ('Playstation 5'),
    ('X-Box One');

INSERT INTO accounts (login,password,creation_time,update_time,expiration_time,platform_id)
VALUES
    ('Pvc57qdj8SS','YjE6j1Z1y5iH','2024-06-15 13:30:52','2023-07-28 18:44:45','2022-08-25 02:13:43',2),
    ('Egj32drv5XP','NbS7x0E4j7rG','2024-05-18 14:25:27','2023-05-24 03:28:39','2023-07-31 23:32:48',2),
    ('Qtz22kmf2QK','LjO5r3C9x5tP','2022-09-19 18:28:26','2023-01-05 13:05:16','2023-05-17 23:27:20',2),
    ('Jel87moq2BH','XpI2g4L6v3cI','2024-05-19 08:38:10','2023-03-13 18:14:41','2023-09-27 18:00:35',1),
    ('Rgd75vav5AX','FuU2b2F5x7iR','2022-06-10 02:00:20','2022-12-06 20:49:05','2023-03-11 07:30:13',1),
    ('Bhd57wtb5DK','YsU2f5U8q7yF','2024-06-15 00:33:33','2022-07-04 17:24:28','2023-06-19 23:57:07',2),
    ('Xxe94iue1RZ','SdP5p8B1s8hV','2024-06-20 19:13:59','2023-03-25 03:11:06','2023-07-07 18:49:25',2),
    ('Orl57ytg7PL','CoG7t5G5v8eP','2023-12-07 12:16:35','2023-05-03 20:01:24','2023-09-05 07:03:29',1),
    ('Bup38jfu5BD','TcJ4y4X4h7wY','2022-06-12 10:35:53','2023-09-19 18:18:47','2023-04-14 14:44:09',3),
    ('Rnu60ple3WA','WqH2w7E8h2yO','2022-12-15 21:26:26','2024-05-22 19:11:47','2024-03-08 06:53:13',2),
    ('Lgm74jwn0SV','SwS4g4S7y8tI','2022-06-22 17:51:50','2023-02-17 16:46:02','2023-07-26 09:40:28',2),
    ('Qki38rac0IC','CsG0f1V9m5mX','2023-10-18 00:41:42','2024-05-23 18:47:10','2022-10-14 05:12:44',1),
    ('Kbw83vwc1TP','JnL3b8W5d4nT','2022-07-25 01:14:25','2022-11-26 18:21:36','2024-01-01 20:53:43',1),
    ('Mww98aht6MN','BwD4b7J9m7yV','2022-07-16 06:29:23','2023-10-22 12:14:31','2023-04-17 15:08:17',2),
    ('Rsg35xgf2PV','DdS1i4J1e1fY','2024-01-07 16:14:58','2023-07-20 08:41:59','2022-12-19 17:34:57',3),
    ('Jkb55gkb5KP','EsE7r1V8v5uR','2024-04-25 12:25:32','2023-04-06 01:27:51','2023-12-09 21:53:28',3),
    ('Mev14gxv4BM','BvF0f3B3o8zM','2023-03-08 03:49:45','2023-06-20 08:17:05','2024-01-25 20:33:24',2),
    ('Ogi81rdk3IM','QpZ1s4K9f7wV','2022-06-16 11:27:19','2023-07-10 06:07:02','2023-06-01 21:18:43',3),
    ('Rtj06vhb6BO','BoJ9m1K4d2zT','2023-09-06 07:58:47','2024-01-07 18:54:08','2023-06-12 16:16:51',2),
    ('Wzz13izx4UH','UbL6u2W7c2iO','2024-04-07 13:01:47','2024-04-07 11:26:12','2022-09-05 09:35:33',2);

INSERT INTO developers (title,description)
VALUES
    ('Nival Interactive','ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed pede. Cum sociis natoque penatibus et magnis'),
    ('Blizzard Entertainment','Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque'),
    ('Capcom','Curabitur egestas nunc sed libero. Proin sed turpis nec mauris blandit mattis. Cras eget nisi dictum augue malesuada malesuada. Integer id magna et'),
    ('Electronic Arts','Proin eget odio. Aliquam vulputate ullamcorper magna. Sed eu eros. Nam consequat dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet ante. Vivamus'),
    ('Rockstar Games','Morbi quis urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac nulla. In tincidunt congue');

INSERT INTO games (title,developer_id,release_date,description,price,image,trailer_url,create_time,update_time)
VALUES
    ('Proin',1,'2016-09-02','mus. Proin vel nisl. Quisque fringilla euismod enim. Etiam gravida molestie arcu. Sed eu nibh vulputate mauris sagittis placerat. Cras dictum ultricies ligula. Nullam enim.',166,'http://cnn.com/one','http://youtube.com/sub','2023-01-04 22:06:01','Nov 1, 2023'),
    ('dui lectus',2,'2020-03-03','Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper',299,'https://google.com/settings','https://naver.com/sub','2023-09-27 15:43:42','Dec 16, 2023'),
    ('vitae velit egestas lacinia.',4,'2022-05-20','placerat. Cras dictum ultricies ligula. Nullam enim. Sed nulla ante, iaculis',171,'http://cnn.com/en-ca','http://bbc.co.uk/sub','2024-05-05 21:43:02','Jan 26, 2023'),
    ('laoreet',5,'2023-10-05','egestas lacinia. Sed congue, elit sed consequat auctor, nunc nulla vulputate dui, nec tempus mauris erat eget ipsum. Suspendisse sagittis. Nullam vitae diam. Proin dolor. Nulla',133,'http://bbc.co.uk/group/9','https://instagram.com/one','2023-06-03 10:42:58','Nov 11, 2023'),
    ('dapibus quam',1,'2020-07-08','Quisque varius. Nam porttitor scelerisque neque. Nullam nisl. Maecenas malesuada fringilla est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada augue ut',300,'https://ebay.com/en-ca','http://netflix.com/fr','2023-05-05 21:21:54','Jul 5, 2023'),
    ('urna et arcu',5,'2021-10-24','bibendum fermentum metus. Aenean sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat, orci lacus vestibulum',287,'http://pinterest.com/fr','http://nytimes.com/sub/cars','2023-07-05 04:45:58','Dec 26, 2022'),
    ('dui nec urna',2,'2016-01-28','In mi pede, nonummy ut, molestie in, tempus eu, ligula. Aenean euismod',136,'https://nytimes.com/sub/cars','http://naver.com/en-ca','2022-11-29 17:50:30','Aug 16, 2023'),
    ('risus. Duis',4,'2010-08-21','tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat, orci lacus vestibulum',202,'https://bbc.co.uk/user/110','https://nytimes.com/sub','2024-04-18 14:49:57','Nov 27, 2022'),
    ('ad litora torquent per',2,'2013-04-15','faucibus orci luctus et ultrices posuere cubilia Curae Phasellus ornare. Fusce mollis. Duis',143,'http://google.com/sub','https://baidu.com/site','2023-06-19 05:53:44','Feb 23, 2024'),
    ('scelerisque neque',3,'2020-02-10','augue ut lacus. Nulla tincidunt, neque vitae semper egestas, urna justo faucibus lectus, a sollicitudin orci sem eget massa. Suspendisse eleifend. Cras sed leo. Cras vehicula aliquet',143,'https://google.com/en-ca','https://facebook.com/fr','2022-10-29 09:56:15','May 6, 2023'),
    ('Duis sit',5,'2017-08-09','eu tempor erat neque non quam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus',194,'http://wikipedia.org/sub/cars','http://zoom.us/fr','2023-10-14 06:34:00','Jul 1, 2023'),
    ('semper auctor. Mauris vel',2,'2021-11-12','Integer eu lacus. Quisque imperdiet, erat nonummy ultricies ornare, elit elit fermentum risus, at fringilla purus mauris a nunc. In at pede. Cras vulputate velit',263,'http://facebook.com/en-ca','https://bbc.co.uk/fr','2023-11-05 00:15:47','May 24, 2024'),
    ('tincidunt orci quis',5,'2017-07-25','velit egestas lacinia. Sed congue, elit sed consequat auctor, nunc nulla vulputate dui, nec tempus mauris erat eget ipsum. Suspendisse sagittis. Nullam vitae diam. Proin dolor. Nulla',244,'http://bbc.co.uk/en-us','https://bbc.co.uk/settings','2023-03-02 10:47:23','Jun 30, 2024'),
    ('semper',4,'2017-11-02','nec tellus. Nunc lectus pede, ultrices a, auctor non, feugiat nec, diam. Duis mi enim, condimentum eget, volutpat ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing',135,'http://netflix.com/en-us','http://cnn.com/one','2023-04-03 23:46:24','Dec 11, 2023'),
    ('Quisque porttitor eros nec',1,'2017-02-14','Proin mi. Aliquam gravida mauris ut mi. Duis risus odio, auctor vitae, aliquet nec, imperdiet',124,'http://ebay.com/one','https://pinterest.com/en-us','2022-09-09 00:37:10','Jan 8, 2023'),
    ('mollis vitae,',4,'2019-06-04','Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis',253,'http://baidu.com/fr','https://zoom.us/group/9','2023-12-19 07:59:38','Jun 27, 2023'),
    ('erat. Vivamus',3,'2017-10-06','diam dictum sapien. Aenean massa. Integer vitae nibh. Donec est mauris, rhoncus id,',226,'http://guardian.co.uk/en-us','http://ebay.com/settings','2023-11-30 05:22:55','Mar 2, 2023'),
    ('semper',4,'2023-06-01','rutrum, justo. Praesent luctus. Curabitur egestas nunc sed libero. Proin sed turpis',166,'https://nytimes.com/en-us','http://nytimes.com/one','2023-05-14 04:59:37','Jun 23, 2023'),
    ('in',4,'2012-10-03','fermentum arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor',110,'http://bbc.co.uk/en-us','http://reddit.com/user/110','2023-04-10 17:55:49','Feb 27, 2023'),
    ('Aenean eget',4,'2012-08-19','orci, consectetuer euismod est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu',210,'https://yahoo.com/one','http://netflix.com/en-ca','2022-08-29 11:11:07','Mar 14, 2024'),
    ('Nam',2,'2023-10-09','erat. Sed nunc est, mollis non, cursus non, egestas a, dui. Cras pellentesque. Sed dictum. Proin eget odio. Aliquam vulputate ullamcorper magna.',207,'http://ebay.com/sub/cars','https://youtube.com/settings','2024-01-20 11:05:24','Jan 12, 2024'),
    ('egestas hendrerit neque. In',4,'2021-10-11','nec mauris blandit mattis. Cras eget nisi dictum augue malesuada malesuada. Integer id magna et ipsum cursus',240,'http://whatsapp.com/site','https://reddit.com/user/110','2023-12-01 04:52:41','Oct 19, 2023'),
    ('porttitor',3,'2018-10-01','lectus ante dictum mi, ac mattis velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor vulputate, posuere vulputate, lacus. Cras',195,'http://facebook.com/en-ca','http://google.com/en-ca','2024-06-23 23:01:41','Apr 8, 2023'),
    ('Sed neque.',5,'2016-04-10','porttitor tellus non magna. Nam ligula elit, pretium et, rutrum non, hendrerit id,',122,'https://facebook.com/site','http://guardian.co.uk/sub','2024-05-09 02:31:18','Sep 30, 2023'),
    ('Vivamus sit',4,'2014-08-29','Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis',112,'https://baidu.com/site','http://walmart.com/settings','2023-01-10 11:21:17','Jun 1, 2023'),
    ('massa. Integer vitae nibh.',3,'2013-02-20','magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean',106,'http://baidu.com/settings','http://netflix.com/settings','2022-08-03 22:48:14','Oct 16, 2023'),
    ('at, velit. Cras',1,'2021-03-23','eget, volutpat ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed pede. Cum',107,'http://twitter.com/en-us','https://nytimes.com/en-us','2023-01-03 14:43:30','Jun 7, 2023'),
    ('felis. Nulla tempor augue',2,'2012-09-26','hendrerit a, arcu. Sed et libero. Proin mi. Aliquam gravida mauris ut mi. Duis risus odio, auctor',261,'https://whatsapp.com/en-us','http://nytimes.com/en-ca','2023-02-13 11:05:39','Aug 24, 2023'),
    ('fringilla mi lacinia mattis.',5,'2021-06-05','Aenean euismod mauris eu elit. Nulla facilisi. Sed neque. Sed eget lacus. Mauris non dui nec urna suscipit nonummy. Fusce fermentum fermentum arcu. Vestibulum ante ipsum primis in',288,'https://naver.com/sub/cars','https://instagram.com/user/110','2024-06-22 18:39:42','Dec 31, 2023'),
    ('neque sed sem',4,'2013-01-22','blandit mattis. Cras eget nisi dictum augue malesuada malesuada. Integer id magna et ipsum cursus vestibulum.',284,'https://netflix.com/site','https://yahoo.com/en-ca','2022-12-26 20:32:58','Aug 31, 2022');

INSERT INTO accounts_games (assigning_time,account_id,game_id)
VALUES
    ('2022-11-04 11:38:26',3,1),
    ('2024-04-07 07:38:40',6,2),
    ('2023-07-04 09:50:37',5,3),
    ('2024-04-30 11:10:22',11,4),
    ('2023-03-16 03:03:15',9,5),
    ('2024-04-11 05:23:15',12,6),
    ('2024-01-19 11:49:27',13,7),
    ('2023-12-27 12:01:16',12,8),
    ('2023-07-31 13:22:38',16,9),
    ('2023-12-14 08:35:11',13,10),
    ('2022-11-15 07:58:03',13,11),
    ('2022-08-10 20:52:19',5,12),
    ('2022-12-24 04:48:08',14,13),
    ('2023-09-14 01:24:45',6,14),
--     ('2023-10-08 11:27:26',14,15),
    ('2023-07-28 21:56:24',3,16),
    ('2023-05-30 14:24:37',2,17),
    ('2022-09-30 11:36:15',14,18),
    ('2022-11-30 08:06:53',5,19),
    ('2024-03-23 07:27:56',18,20),
    ('2022-09-05 23:30:39',19,21),
    ('2023-01-14 10:07:18',14,22),
    ('2022-10-26 03:16:41',16,23),
    ('2024-06-07 18:14:39',3,24),
    ('2023-08-23 19:00:06',13,25),
    ('2024-03-22 14:25:57',14,26),
    ('2023-07-29 11:04:12',2,27),
    ('2022-12-16 16:21:37',2,28),
    ('2023-07-30 08:15:33',2,29),
    ('2022-09-15 12:38:19',19,30);
