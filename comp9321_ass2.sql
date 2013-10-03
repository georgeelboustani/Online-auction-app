--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.7
-- Dumped by pg_dump version 9.0.7
-- Started on 2013-10-03 13:21:31 SGT

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1821 (class 0 OID 0)
-- Dependencies: 146
-- Name: action_item_aid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('action_item_aid_seq', 1, false);


--
-- TOC entry 1822 (class 0 OID 0)
-- Dependencies: 142
-- Name: user_uid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_uid_seq', 19, true);


--
-- TOC entry 1815 (class 0 OID 45039)
-- Dependencies: 143
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "user" (uid, username, nickname, first_name, last_name, password, email, year_of_birth, avatar_img, activate, ban, credit_card_num, is_admin, activate_hashsum) FROM stdin;
19	admin	nick	first	last	5f4dcc3b5aa765d61d8327deb882cf99	ryantanjunming@gmail.com	1989-01-16	\N	t	f	446-667-651	f	5432735ff7445c61f64a6be0e792d568
\.


--
-- TOC entry 1817 (class 0 OID 45088)
-- Dependencies: 145 1815
-- Data for Name: auction_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auction_item (aid, auction_owner_uid, auction_title, auction_category, auction_image, auction_description, auction_postage_details, auction_reserve_price, bidding_start_price, bidding_increment, auction_start_time, auction_close_time, auction_halt) FROM stdin;
\.


--
-- TOC entry 1818 (class 0 OID 45136)
-- Dependencies: 147 1817 1815
-- Data for Name: auction_bid; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auction_bid (uid, aid, bid) FROM stdin;
\.


--
-- TOC entry 1816 (class 0 OID 45052)
-- Dependencies: 144 1815
-- Data for Name: user_admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_admin (uid) FROM stdin;
\.


-- Completed on 2013-10-03 13:21:31 SGT

--
-- PostgreSQL database dump complete
--

