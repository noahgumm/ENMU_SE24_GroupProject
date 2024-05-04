-- Table for storing user information
drop database hotel_reservation_system;

CREATE DATABASE hotel_reservation_system;
use hotel_reservation_system;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for storing hotel rooms information
CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    floor_number int,
    price_per_night DECIMAL(10, 2) NOT NULL,
    room_description TEXT,
    number_of_beds INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for storing user reservations
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    check_in_date DATE,
    check_out_date DATE,
    total_price DECIMAL(10, 2),
    num_guests INT,
    pets BOOLEAN,
    reservation_status ENUM('pending', 'confirmed', 'cancelled') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Table for storing the relationship between reservations and rooms
CREATE TABLE reservation_rooms (
    reservation_id INT,
    room_id INT,
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id),
    PRIMARY KEY (reservation_id, room_id) -- Composite primary key to ensure uniqueness of room reservations
);

-- Table for storing user bookings
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    room_id INT,
    check_in_date DATE,
    check_out_date DATE,
    total_price DECIMAL(10, 2),
    booking_status ENUM('pending', 'confirmed', 'cancelled') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Table for storing admin information
CREATE TABLE admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    reservation_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);

CREATE TABLE user_payment_methods (
    card_id int PRIMARY KEY AUTO_INCREMENT,
    card_number VARCHAR(16) NOT NULL,
    card_holder_name VARCHAR(50) NOT NULL,
    card_type VARCHAR(50) NOT NULL,
    expiry_date DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) 
);

-- Add starting rooms for floors 1-4
INSERT INTO Rooms (room_number, room_type, floor_number, price_per_night, room_description, number_of_beds)
VALUES
('101', 'Standard', 1, 300, 'Basic room with two beds', 2),
('102', 'Standard', 1, 300, 'Basic room with two beds', 2),
('103', 'Standard', 1, 300, 'Basic room with two beds', 2),
('104', 'Standard', 1, 300, 'Basic room with two beds', 2),
('105', 'Deluxe', 1, 400, 'Spacious room with three bed', 3),
('106', 'Deluxe', 1, 400, 'Spacious room with three beds', 3),
('107', 'Deluxe', 1, 400, 'Spacious room with three beds', 3),
('108', 'Deluxe', 1, 400, 'Spacious room with three beds', 3),
('109', 'Suite', 1, 500, 'Luxurious suite with king-sized bed', 4),
('110', 'Suite', 1, 500, 'Luxurious suite with king-sized bed', 4),
('201', 'Standard', 2, 300, 'Basic room with two beds', 2),
('202', 'Standard', 2, 300, 'Basic room with two beds', 2),
('203', 'Standard', 2, 300, 'Basic room with two beds',2),
('204', 'Standard', 2, 300, 'Basic room with two beds', 2),
('205', 'Deluxe', 2, 400, 'Spacious room with three bed', 3),
('206', 'Deluxe', 2, 400, 'Spacious room with three beds', 3),
('207', 'Deluxe', 2, 400, 'Spacious room with three beds', 3),
('208', 'Deluxe', 2, 400, 'Spacious room with three beds', 3),
('209', 'Suite', 2, 500, 'Luxurious suite with king-sized bed', 4),
('210', 'Suite', 2, 500, 'Luxurious suite with king-sized bed', 4),
('301', 'Standard', 3, 300, 'Basic room with two beds', 2),
('302', 'Standard', 3, 300, 'Basic room with two beds', 2),
('303', 'Standard', 3, 300, 'Basic room with two beds', 2),
('304', 'Standard', 3, 300, 'Basic room with two beds', 2),
('305', 'Deluxe', 3, 400, 'Spacious room with three bed', 3),
('306', 'Deluxe', 3, 400, 'Spacious room with three beds', 3),
('307', 'Deluxe', 3, 400, 'Spacious room with three beds', 3),
('308', 'Deluxe', 3, 400, 'Spacious room with three beds', 3),
('309', 'Suite', 3, 500, 'Luxurious suite with king-sized bed', 4),
('310', 'Suite', 3, 500, 'Luxurious suite with king-sized bed', 4),
('401', 'Standard', 4, 300, 'Basic room with two beds', 2),
('402', 'Standard', 4, 300, 'Basic room with two beds', 2),
('403', 'Standard', 4, 300, 'Basic room with two beds', 2),
('404', 'Standard', 4, 300, 'Basic room with two beds', 2),
('405', 'Deluxe', 4, 400, 'Spacious room with three bed', 3),
('406', 'Deluxe', 4, 400, 'Spacious room with three beds', 3),
('407', 'Deluxe', 4, 400, 'Spacious room with three beds', 3),
('408', 'Deluxe', 4, 400, 'Spacious room with three beds', 3),
('409', 'Suite', 4, 500, 'Luxurious suite with king-sized bed', 4),
('410', 'Suite', 4, 500, 'Luxurious suite with king-sized bed', 4);

-- Generate bookings for 8 random rooms, at least one on each floor, for all of next month
INSERT INTO bookings (user_id, room_id, check_in_date, check_out_date, total_price, booking_status)
SELECT
    1 AS user_id, 
    room_id,
    DATE_ADD(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL 1 MONTH) AS check_in_date,
    DATE_ADD(DATE_ADD(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL 1 MONTH), INTERVAL 1 MONTH) AS check_out_date,
    -- Generate random total price
    ROUND(50 + RAND() * 150, 2) AS total_price,
    'pending' AS booking_status
FROM (
    SELECT room_id
    FROM (
        SELECT 
            FLOOR(101 + RAND() * 10) AS room_id
        FROM 
            (SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) AS floors
        CROSS JOIN 
            (SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
            UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10) AS rooms
    ) AS random_rooms
    GROUP BY room_id
    LIMIT 8 
) AS selected_rooms;