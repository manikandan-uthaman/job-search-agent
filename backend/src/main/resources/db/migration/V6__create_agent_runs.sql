CREATE TABLE agent_runs
(
    id UUID PRIMARY KEY,

    agent_name VARCHAR(255),

    user_query TEXT,

    prompt TEXT,

    response TEXT,

    created_at TIMESTAMP
);