INSERT INTO units (_id, name) VALUES (9910, "unit 1");
INSERT INTO units (_id, name) VALUES (9920, "unit 2");
INSERT INTO device_types (_id, name, unit_id) VALUES (9910, "type 1a", 9910);
INSERT INTO device_types (_id, name, unit_id) VALUES (9911, "type 1b", 9910);
INSERT INTO device_types (_id, name, unit_id) VALUES (9920, "type 2a", 9920);
INSERT INTO device_types (_id, name, unit_id) VALUES (9921, "type 2b", 9920);
INSERT INTO device_types (_id, name, unit_id) VALUES (9922, "type 2c", 9920);
INSERT INTO devices (_id, name, device_type_id) VALUES (9910, "device 1 1a", 9910);
INSERT INTO devices (_id, name, device_type_id) VALUES (9911, "device 2 1a", 9910);
INSERT INTO devices (_id, name, device_type_id) VALUES (9920, "device 3 2a", 9920);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9910, "2016-01-20 12:00:45.123", 50, 9910);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9911, "2016-02-19 13:10:45.123", 57, 9910);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9912, "2016-03-25 17:10:45.123", 60, 9910);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9920, "2016-01-20 12:00:45.123", 40, 9911);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9921, "2016-02-10 13:10:45.123", 37, 9911);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9922, "2016-03-20 14:10:45.123", 64, 9911);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9930, "2016-01-10 10:11:12.1", 100, 9920);
INSERT INTO meterings (_id, created, measure, device_id) VALUES (9931, "2016-05-21 14:10:45.345", 218, 9920);