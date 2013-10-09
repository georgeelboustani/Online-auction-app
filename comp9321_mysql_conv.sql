CREATE TABLE auction_item (
    aid int(11) NOT NULL,
    auction_owner_uid int(11) NOT NULL,
    auction_title varchar(255) NOT NULL,
    auction_category varchar(255) NOT NULL,
    auction_image text,
    auction_description text,
    auction_postage_details text,
    auction_reserve_price double precision NOT NULL,
    bidding_start_price double precision NOT NULL,
    bidding_increment double precision DEFAULT 0.10 NOT NULL,
    auction_start_time timestamp DEFAULT NOW(),
    auction_close_time timestamp NOT NULL,
    auction_halt bool DEFAULT 0 NOT NULL
) TYPE=MyISAM;

CREATE TABLE auction_bid (
    uid int(11) NOT NULL,
    aid int(11) NOT NULL,
    bid double precision NOT NULL
) TYPE=MyISAM;

CREATE TABLE `user` (
    uid int(11) NOT NULL,
    username varchar(32) NOT NULL,
    nickname varchar(32),
    first_name varchar(32),
    last_name varchar(32),
    password varchar(255) NOT NULL,
    email varchar(128) NOT NULL,
    year_of_birth date,
    activate bool DEFAULT 0 NOT NULL,
    ban bool DEFAULT 0 NOT NULL,
    credit_card_num varchar(32),
    is_admin bool DEFAULT 0 NOT NULL,
    activate_hashsum varchar(128),
    address varchar(255)
) TYPE=MyISAM;

CREATE TABLE user_admin (
    uid int(11) DEFAULT 0 NOT NULL
) TYPE=MyISAM;

ALTER TABLE auction_item
    ADD CONSTRAINT action_item_pkey PRIMARY KEY (aid);
ALTER TABLE `user`
    ADD CONSTRAINT pk PRIMARY KEY (uid);