INSERT INTO users (username, password, enabled) VALUES ('test', '123456', true) ON DUPLICATE KEY UPDATE username='test';
