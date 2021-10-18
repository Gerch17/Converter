CREATE TABLE IF NOT EXISTS  users
(
    id BIGSERIAL PRIMARY KEY ,
    user_name VARCHAR NOT NULL ,
    password VARCHAR NOT NULL ,
    user_role VARCHAR not NULL
);

CREATE TABLE IF NOT EXISTS valutes
(
    id BIGSERIAL PRIMARY KEY ,
    valute_id INT NOT NULL ,
    char_code VARCHAR NOT NULL ,
    valute_name VARCHAR NOT NULL ,
    value INT NOT NULL ,
    valute_text VARCHAR NOT NULL ,
    date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS history
(
    id BIGSERIAL PRIMARY KEY ,
    user_name VARCHAR NOT NULL ,
    out_valute VARCHAR NOT NULL ,
    in_valute VARCHAR NOT NULL ,
    out_value FLOAT NOT NULL ,
    in_value FLOAT NOT NULL ,
    date DATE NOT NULL
);
