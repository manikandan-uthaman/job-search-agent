CREATE TABLE companies
(
    id UUID PRIMARY KEY,

    name VARCHAR(255),

    website VARCHAR(1000),

    headquarters VARCHAR(255),

    country VARCHAR(255),

    hiring BOOLEAN,

    score INTEGER,

    last_researched_at TIMESTAMP
);