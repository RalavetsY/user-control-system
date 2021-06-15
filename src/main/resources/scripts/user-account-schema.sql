CREATE TABLE IF NOT EXISTS user_account
(
  id         BIGINT  NOT NULL PRIMARY KEY,
  username   VARCHAR(16) UNIQUE NOT NULL,
  password   VARCHAR(60) NOT NULL,
  first_name VARCHAR(16) NOT NULL,
  last_name  VARCHAR(16) NOT NULL,
  role       VARCHAR(12) NOT NULL,
  status     BOOLEAN NOT NULL,
  created_at TIMESTAMP NOT NULL
);