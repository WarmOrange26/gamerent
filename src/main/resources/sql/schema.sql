CREATE TABLE settings_profile
(
    id bigserial NOT NULL PRIMARY KEY
);

CREATE TABLE settings
(
    id bigserial PRIMARY KEY,
    setting_name character varying(30) NOT NULL,
    setting_value boolean NOT NULL
);

CREATE TABLE users_settings
(
    id bigserial PRIMARY KEY,
    setting_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT fk_usr_stg2stg FOREIGN KEY (setting_id)
        REFERENCES settings (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_usr_stg2usr FOREIGN KEY (user_id)
        REFERENCES settings_profile (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    username character varying(30),
    email character varying(60) NOT NULL UNIQUE,
    password character varying(45) NOT NULL,
    first_name character varying(30),
    last_name character varying(30),
    registration_time timestamp NOT NULL,
    update_time timestamp NOT NULL,
    phone character varying(30),
    birth_date date,
    is_blocked boolean NOT NULL,
    user_settings_id bigint NOT NULL,
    CONSTRAINT fk_usr2stg FOREIGN KEY (user_settings_id)
        REFERENCES settings_profile (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE wallets
(
    id bigserial PRIMARY KEY,
    currency character varying(10) NOT NULL,
    value numeric(12, 2) NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT fk_wlt2usr FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE action_types
(
    id bigserial PRIMARY KEY,
    action character varying(30) NOT NULL
);

CREATE TABLE wallet_actions
(
    id bigserial PRIMARY KEY,
    wallet_id bigint NOT NULL,
    transaction_value numeric(12, 2) NOT NULL,
    transaction_time timestamp NOT NULL,
    type_id bigint NOT NULL,
    CONSTRAINT fk_wlt2atn FOREIGN KEY (wallet_id)
        REFERENCES wallets (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_typ2atn FOREIGN KEY (type_id)
        REFERENCES action_types (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE roles
(
    id bigserial PRIMARY KEY,
    role character varying(30) NOT NULL UNIQUE
);

CREATE TABLE users_roles
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    assigning_time timestamp NOT NULL,
    CONSTRAINT fk_role_usr2usr FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_role_usr2role FOREIGN KEY (role_id)
        REFERENCES roles (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE developers
(
    id bigserial PRIMARY KEY,
    title character varying(30) NOT NULL UNIQUE ,
    description text
);

CREATE TABLE genres
(
    id bigserial PRIMARY KEY,
    title character varying(30) NOT NULL UNIQUE
);

CREATE TABLE games
(
    id bigserial PRIMARY KEY,
    title character varying(50) NOT NULL,
    developer_id bigint NOT NULL,
    release_date date NOT NULL,
    description text NOT NULL,
    price numeric(12, 2) NOT NULL,
    image character varying(100) NOT NULL,
    trailer_url character varying(250),
    create_time timestamp NOT NULL,
    update_time timestamp NOT NULL,
    UNIQUE (title, release_date),
    CONSTRAINT fk_gms2dvp FOREIGN KEY (developer_id)
        REFERENCES developers (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE screenshots
(
    id bigserial PRIMARY KEY,
    url character varying(250) NOT NULL UNIQUE,
    game_id bigint NOT NULL,
    CONSTRAINT fk_scr2gms FOREIGN KEY (game_id)
        REFERENCES games (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE games_genres
(
    id bigserial PRIMARY KEY,
    game_id bigint NOT NULL,
    genre_id bigint NOT NULL,
    CONSTRAINT fk_gms_gms2gnr FOREIGN KEY (game_id)
        REFERENCES games (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_gnr_gms2gnr FOREIGN KEY (genre_id)
        REFERENCES genres (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE platforms
(
    id bigserial PRIMARY KEY,
    name character varying(30) NOT NULL UNIQUE
);

CREATE TABLE accounts
(
    id bigserial PRIMARY KEY,
    login character varying(30) NOT NULL UNIQUE,
    password character varying(45) NOT NULL,
    creation_time timestamp NOT NULL,
    update_time timestamp NOT NULL,
    expiration_time timestamp NOT NULL,
    platform_id bigint NOT NULL,
    CONSTRAINT fk_acc2pfm FOREIGN KEY (platform_id)
        REFERENCES platforms (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE accounts_games
(
    id bigserial PRIMARY KEY,
    account_id bigint NOT NULL,
    game_id bigint NOT NULL,
    assigning_time timestamp NOT NULL,
    CONSTRAINT fk_acc_acc2gms FOREIGN KEY (account_id)
        REFERENCES accounts (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_gms_acc2gms FOREIGN KEY (game_id)
        REFERENCES games (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE orders
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    payment_time timestamp NOT NULL,
    CONSTRAINT fk_trn2usr FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE orders_accounts
(
    id bigserial PRIMARY KEY,
    order_id bigint NOT NULL,
    account_id bigint NOT NULL,
    periods integer NOT NULL,
    CONSTRAINT fk_ord2trn FOREIGN KEY (order_id)
        REFERENCES orders (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_ord2acc FOREIGN KEY (account_id)
        REFERENCES accounts (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE postponed_games
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    game_id bigint NOT NULL,
    postpone_time timestamp NOT NULL,
    CONSTRAINT fk_ptd2gms FOREIGN KEY (game_id)
        REFERENCES games (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_ptd2usr FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE warm_company_bank_account
(
    id bigserial PRIMARY KEY,
    wallet_action_id bigint NOT NULL,
    action_id bigint NOT NULL,
    CONSTRAINT fk_acc2act FOREIGN KEY (wallet_action_id)
        REFERENCES wallet_actions (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_acc2typ FOREIGN KEY (action_id)
        REFERENCES action_types (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)