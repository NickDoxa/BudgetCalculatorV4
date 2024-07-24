CREATE TABLE budget_information(
    username VARCHAR(100) PRIMARY KEY,
    report_id BIGINT,
    remainder FLOAT,
    expenses FLOAT,
    taxes FLOAT
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