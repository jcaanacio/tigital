-- V2__Drop_Notes_Table.sql

-- Remove foreign key reference to avoid conflicts
ALTER TABLE notes
DROP CONSTRAINT IF EXISTS fk_notes_user_id;

-- Drop the notes table
DROP TABLE IF EXISTS notes;




CREATE TABLE notes (
    note_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    content VARCHAR(255),
    subject VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);