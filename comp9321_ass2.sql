--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.7
-- Dumped by pg_dump version 9.0.7
-- Started on 2013-10-03 13:33:53 SGT

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1817 (class 1262 OID 45036)
-- Name: 9321db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "9321db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE "9321db" OWNER TO postgres;

\connect "9321db"

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 462 (class 2612 OID 11574)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 145 (class 1259 OID 45088)
-- Dependencies: 1801 1802 1803 6
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
-- TOC entry 1820 (class 0 OID 0)
-- Dependencies: 145
-- Name: COLUMN auction_item.auction_close_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN auction_item.auction_close_time IS 'serverside [default(10min), min(3min), max(60min)]';


--
-- TOC entry 146 (class 1259 OID 45094)
-- Dependencies: 6 145
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
-- TOC entry 1821 (class 0 OID 0)
-- Dependencies: 146
-- Name: action_item_aid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE action_item_aid_seq OWNED BY auction_item.aid;


--
-- TOC entry 147 (class 1259 OID 45136)
-- Dependencies: 6
-- Name: auction_bid; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE auction_bid (
    uid integer NOT NULL,
    aid integer NOT NULL,
    bid double precision NOT NULL
);


ALTER TABLE public.auction_bid OWNER TO postgres;

--
-- TOC entry 143 (class 1259 OID 45039)
-- Dependencies: 1796 1797 1799 6
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
    activate boolean DEFAULT false NOT NULL,
    ban boolean DEFAULT false NOT NULL,
    credit_card_num character varying(32),
    is_admin boolean DEFAULT false NOT NULL,
    activate_hashsum character varying(128)
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 1822 (class 0 OID 0)
-- Dependencies: 143
-- Name: TABLE "user"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "user" IS 'user accounts';


--
-- TOC entry 1823 (class 0 OID 0)
-- Dependencies: 143
-- Name: COLUMN "user".uid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN "user".uid IS 'pk';


--
-- TOC entry 144 (class 1259 OID 45052)
-- Dependencies: 1800 6
-- Name: user_admin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_admin (
    uid integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.user_admin OWNER TO postgres;

--
-- TOC entry 142 (class 1259 OID 45037)
-- Dependencies: 143 6
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
-- TOC entry 1824 (class 0 OID 0)
-- Dependencies: 142
-- Name: user_uid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_uid_seq OWNED BY "user".uid;


--
-- TOC entry 1804 (class 2604 OID 45155)
-- Dependencies: 146 145
-- Name: aid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_item ALTER COLUMN aid SET DEFAULT nextval('action_item_aid_seq'::regclass);


--
-- TOC entry 1798 (class 2604 OID 45157)
-- Dependencies: 142 143 143
-- Name: uid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN uid SET DEFAULT nextval('user_uid_seq'::regclass);


--
-- TOC entry 1808 (class 2606 OID 45118)
-- Dependencies: 145 145
-- Name: action_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY auction_item
    ADD CONSTRAINT action_item_pkey PRIMARY KEY (aid);


--
-- TOC entry 1806 (class 2606 OID 45047)
-- Dependencies: 143 143
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk PRIMARY KEY (uid);


--
-- TOC entry 1810 (class 2606 OID 53345)
-- Dependencies: 147 147 147 147
-- Name: unique_bid_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY auction_bid
    ADD CONSTRAINT unique_bid_constraint UNIQUE (uid, aid, bid);


--
-- TOC entry 1812 (class 2606 OID 45119)
-- Dependencies: 145 143 1805
-- Name: action_item_auction_owner_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_item
    ADD CONSTRAINT action_item_auction_owner_uid_fkey FOREIGN KEY (auction_owner_uid) REFERENCES "user"(uid);


--
-- TOC entry 1813 (class 2606 OID 45139)
-- Dependencies: 147 1807 145
-- Name: auction_bid_aid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_bid
    ADD CONSTRAINT auction_bid_aid_fkey FOREIGN KEY (aid) REFERENCES auction_item(aid);


--
-- TOC entry 1814 (class 2606 OID 45144)
-- Dependencies: 147 143 1805
-- Name: auction_bid_uid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auction_bid
    ADD CONSTRAINT auction_bid_uid_fkey FOREIGN KEY (uid) REFERENCES "user"(uid);


--
-- TOC entry 1811 (class 2606 OID 45056)
-- Dependencies: 144 1805 143
-- Name: fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_admin
    ADD CONSTRAINT fk FOREIGN KEY (uid) REFERENCES "user"(uid);


--
-- TOC entry 1819 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-10-03 13:33:53 SGT

--
-- PostgreSQL database dump complete
--

