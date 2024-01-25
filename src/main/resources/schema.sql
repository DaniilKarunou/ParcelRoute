drop table if exists users;
drop table if exists parcels;
drop table if exists lockers;
drop table if exists locker_cells;
drop table if exists user_group;
drop table if exists category;

-- Create the Users table
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL
);

-- Create the Lockers table
CREATE TABLE lockers (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         address VARCHAR(255) NOT NULL
);

-- Create the LockerCells table
CREATE TABLE locker_cells (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              locker_id BIGINT,
                              cell_size VARCHAR(255),
                              is_available BOOLEAN NOT NULL,
                              FOREIGN KEY (locker_id) REFERENCES lockers(id)
);

-- Create the Parcels table
CREATE TABLE parcels (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         locker_id BIGINT,
                         cell_id BIGINT,
                         size VARCHAR(255) NOT NULL,
                         parcel_type VARCHAR(255) NOT NULL,
                         sender_id BIGINT,
                         recipient_id BIGINT,
                         pickup_code VARCHAR(255) UNIQUE,
                         is_collected BOOLEAN NOT NULL,
                         FOREIGN KEY (locker_id) REFERENCES lockers(id),
                         FOREIGN KEY (cell_id) REFERENCES locker_cells(id),
                         FOREIGN KEY (sender_id) REFERENCES users(id),
                         FOREIGN KEY (recipient_id) REFERENCES users(id)
);