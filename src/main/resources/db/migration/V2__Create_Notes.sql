CREATE TABLE notes (
    note_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    content VARCHAR(255),
    subject VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);