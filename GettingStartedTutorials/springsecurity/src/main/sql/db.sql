drop table user_roles if exists;
drop table users if exists;


CREATE TABLE IF NOT EXISTS users (
  username varchar(45) NOT NULL,
  password varchar(450) NOT NULL,
  enabled int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  ROLE varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (ROLE,username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users (username, password, enabled) VALUES
 ('user1', 'password', 1),
 ('admin', 'admin', 1);

INSERT INTO user_roles (user_role_id, username, ROLE) VALUES
 (2, 'admin', 'ROLE_ADMIN'),
 (1, 'admin', 'ROLE_USER'),
 (3, 'user1', 'ROLE_USER');
