/*
 * ---------------------------------------------
 * Author: Jay Christopher A Anacio
 * Date:   Tuesday August 15th 2023
 * Last Modified by: Jay Christopher A Anacio - <jcaanacio@gmail.com>
 * Last Modified time: August 15th 2023, 10:57:47 pm
 * ---------------------------------------------
 */

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_role VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE profiles (
    profile_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE notes (
    note_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    content VARCHAR(255),
    subject VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);