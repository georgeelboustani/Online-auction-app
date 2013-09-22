--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auction_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE auction_item (
    aid integer NOT NULL,
    auction_owner_uid integer NOT NULL,
    auction_title character varying(255) NOT NULL,
    auction_category character varying(255) NOT NULL,
    auction_image character varying(512),
    auction_description text,
    auction_postage_details text,
    auction_reserve_price double precision NOT NULL,
    bidding_start_price double precision NOT NULL,
    bidding_increment double precision DEFAULT 0.10 NOT NULL,
    auction_start_time timestamp with time zone DEFAULT ('now'::text)::date NOT NULL,
    auction_close_time timestamp with time zone NOT NULL,
    auction_halt boolean DEFAULT false NOT NULL
);


ALTER TABLE public.auction_item OWNER TO postgres;

--
-- Name: COLUMN auction_item.auction_close_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN auction_item.auction_close_time IS 'serverside [default(10min), min(3min), max(60min)]';


--
-- Name: action_item_aid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE action_item_aid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.action_item_aid_seq OWNER TO postgres;

--
-- Name: action_item_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE action_item_aid_seq OWNED BY auction_item.aid;


--
-- Name: auction_bid; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE auction_bid (
    uid integer NOT NULL,
    aid integer NOT NULL,
    bid double precision NOT NULL
);


ALTER TABLE public.auction_bid OWNER TO postgres;

--
-- Name: creditcard; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE creditcard (
    ccid integer NOT NULL,
    card_owner_uid integer NOT NULL,
    card_num integer NOT NULL,
    card_name character varying(128) NOT NULL,
    card_expiry date NOT NULL,
    card_csc_num integer NOT NULL
);


ALTER TABLE public.creditcard OWNER TO postgres;

--
-- Name: TABLE creditcard; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE creditcard IS 'store creditcard details';


--
-- Name: COLUMN creditcard.ccid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN creditcard.ccid IS 'pk';


--
-- Name: COLUMN creditcard.card_owner_uid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN creditcard.card_owner_uid IS 'fk to "user:uid"';


--
-- Name: COLUMN creditcard.card_csc_num; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN creditcard.card_csc_num IS 'card security code';


--
-- Name: creditcard_ccid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE creditcard_ccid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.creditcard_ccid_seq OWNER TO postgres;

--
-- Name: creditcard_ccid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE creditcard_ccid_seq OWNED BY creditcard.ccid;


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    uid integer NOT NULL,
    username character varying(32) NOT NULL,
    nickname character varying(32),
    first_name character varying(32),
    last_name character varying(32),
    password character varying(255) NOT NULL,
    email character varying(128) NOT NULL,
    year_of_birth date,
    avatar_img character varying(255),
    activate boolean DEFAULT false NOT NULL,
    ban boolean DEFAULT false NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: TABLE "user"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "user" IS 'user accounts';


--
-- Name: COLUMN "user".uid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN "user".uid IS 'pk';


--
-- Name: COLUMN "user".avatar_img; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN "user".avatar_img IS 'pathname: file < 2mb size';


--
-- Name: user_admin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_admin (
    uid integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.user_admin OWNER TO postgres;

--
-- Name: user_uid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_uid_seq OWNER TO postgres;

--
-- Name: user_uid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_uid_seq OWNED BY "user".uid;


--
-- Name: aid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_item ALTER COLUMN aid SET DEFAULT nextval('action_item_aid_seq'::regclass);


--
-- Name: ccid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY creditcard ALTER COLUMN ccid SET DEFAULT nextval('creditcard_ccid_seq'::regclass);


--
-- Name: uid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN uid SET DEFAULT nextval('user_uid_seq'::regclass);


--
-- Name: action_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY auction_item
    ADD CONSTRAINT action_item_pkey PRIMARY KEY (aid);


--
-- Name: creditcard_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY creditcard
    ADD CONSTRAINT creditcard_pkey PRIMARY KEY (ccid);


--
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk PRIMARY KEY (uid);


--
-- Name: action_item_auction_owner_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_item
    ADD CONSTRAINT action_item_auction_owner_uid_fkey FOREIGN KEY (auction_owner_uid) REFERENCES "user"(uid);


--
-- Name: auction_bid_aid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_bid
    ADD CONSTRAINT auction_bid_aid_fkey FOREIGN KEY (aid) REFERENCES auction_item(aid);


--
-- Name: auction_bid_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_bid
    ADD CONSTRAINT auction_bid_uid_fkey FOREIGN KEY (uid) REFERENCES "user"(uid);


--
-- Name: creditcard_card_owner_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY creditcard
    ADD CONSTRAINT creditcard_card_owner_uid_fkey FOREIGN KEY (card_owner_uid) REFERENCES "user"(uid);


--
-- Name: fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_admin
    ADD CONSTRAINT fk FOREIGN KEY (uid) REFERENCES "user"(uid);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

