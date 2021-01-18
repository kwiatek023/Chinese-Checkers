DROP DATABASE IF EXISTS chinese_checkers;
CREATE DATABASE chinese_checkers;
USE chinese_checkers;

CREATE TABLE games
(
    id INT          NOT NULL AUTO_INCREMENT,
    date        TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE moves
(
    move_id INT NOT NULL AUTO_INCREMENT,
    game_id INT NOT NULL,
    player_color VARCHAR(10) NOT NULL,
    old_vertical_id INT NOT NULL,
    old_horizontal_id INT NOT NULL,
    new_vertical_id INT NOT NULL,
    new_horizontal_id INT NOT NULL,

    PRIMARY KEY (move_id),
    CONSTRAINT games_moves_fk FOREIGN KEY (game_id) REFERENCES games (id)
);

DROP USER IF EXISTS 'chinese_checkers_connector'@'localhost';
CREATE USER 'chinese_checkers_connector'@'localhost' IDENTIFIED BY 'connector';
GRANT ALL PRIVILEGES ON chinese_checkers.* TO 'chinese_checkers_connector'@'localhost';