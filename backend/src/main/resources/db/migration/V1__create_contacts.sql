CREATE TABLE contacts
(
    id UUID PRIMARY KEY,

    first_name VARCHAR(255),
    last_name VARCHAR(255),

    title VARCHAR(255),

    company VARCHAR(255),

    location VARCHAR(255),

    linkedin_url VARCHAR(1000),

    score INTEGER,

    created_at TIMESTAMP
);