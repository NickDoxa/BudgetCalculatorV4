CREATE TABLE budget_information(
    username VARCHAR(100) PRIMARY KEY,
    report_id BIGINT,
    remainder FLOAT,
    expenses FLOAT,
    taxes FLOAT,
    FOREIGN KEY (report_id) REFERENCES budget_report(id)
);

CREATE TABLE budget_report(
    id BIGINT PRIMARY KEY,
    username VARCHAR(100),
    report VARCHAR(255),
    date_created DATE
);

CREATE TABLE budget_user(
    username VARCHAR(100) PRIMARY KEY,
    age INT,
    salary FLOAT
);

CREATE TABLE friend_list(
    username VARCHAR(100) PRIMARY KEY
);

CREATE TABLE friend_list_friends(
    friend_list_username VARCHAR(100),
    friend_username VARCHAR(100),
    FOREIGN KEY (friend_list_username) REFERENCES friend_list(username),
    FOREIGN KEY (friend_username) REFERENCES budget_user(username)
);