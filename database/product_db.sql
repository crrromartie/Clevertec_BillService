--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-03-17 22:11:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16438)
-- Name: discount_cards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discount_cards (
    card_id bigint NOT NULL,
    card_number smallint NOT NULL,
    discount_percent smallint NOT NULL
);


ALTER TABLE public.discount_cards OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16436)
-- Name: discount_card_card_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discount_card_card_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discount_card_card_id_seq OWNER TO postgres;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 202
-- Name: discount_card_card_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discount_card_card_id_seq OWNED BY public.discount_cards.card_id;


--
-- TOC entry 201 (class 1259 OID 16424)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id bigint NOT NULL,
    name character varying(30) NOT NULL,
    price numeric NOT NULL,
    promo boolean DEFAULT false NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16422)
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_product_id_seq OWNER TO postgres;

--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public.products.product_id;


--
-- TOC entry 205 (class 1259 OID 16634)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    login character varying(15) NOT NULL,
    password character varying NOT NULL,
    email character varying(128) NOT NULL,
    CONSTRAINT users_email_check CHECK (((email)::text <> ''::text)),
    CONSTRAINT users_login_check CHECK (((login)::text <> ''::text)),
    CONSTRAINT users_password_check CHECK (((password)::text <> ''::text))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16632)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 2866 (class 2604 OID 16441)
-- Name: discount_cards card_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_cards ALTER COLUMN card_id SET DEFAULT nextval('public.discount_card_card_id_seq'::regclass);


--
-- TOC entry 2864 (class 2604 OID 16427)
-- Name: products product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- TOC entry 2867 (class 2604 OID 16637)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 3018 (class 0 OID 16438)
-- Dependencies: 203
-- Data for Name: discount_cards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.discount_cards (card_id, card_number, discount_percent) FROM stdin;
44	6666	19
49	2222	10
50	3333	15
48	1111	11
\.


--
-- TOC entry 3016 (class 0 OID 16424)
-- Dependencies: 201
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (product_id, name, price, promo) FROM stdin;
6	KitKat	9.6	t
4	Spartak	8.7	f
55	Smack	3.5	t
2	Snickers	10.55	f
3	Nuts	11.5	f
44	Corny	4.0	t
1	Mars	13.17	f
7	MilkyWay	8.5	f
\.


--
-- TOC entry 3020 (class 0 OID 16634)
-- Dependencies: 205
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, login, password, email) FROM stdin;
2	cammy	$2a$10$foozfXbv2GRhBNyFrii/u.g6BvptvkOy6ePTr6DzbQXWveGWyza/G	LPKondrat90210@yandex.ru
4	dominator	$2a$10$F6b6qHDOiLyEUNIWN.P7OuFnwvR4TUVQHd2MbJfHnpJnzyAOUcpzG	dominator@tut.by
1	cromartie	$2a$10$lQyBAdXry5K./bDrPv5zfeEiLDcwsAhdhr/Yzbra8iA0p1cAApjl6	gaponenko1990igor@gmail.com
\.


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 202
-- Name: discount_card_card_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.discount_card_card_id_seq', 51, true);


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_product_id_seq', 55, true);


--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 4, true);


--
-- TOC entry 2876 (class 2606 OID 16446)
-- Name: discount_cards discount_card_card_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_cards
    ADD CONSTRAINT discount_card_card_number_key UNIQUE (card_number);


--
-- TOC entry 2878 (class 2606 OID 16444)
-- Name: discount_cards discount_card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_cards
    ADD CONSTRAINT discount_card_pkey PRIMARY KEY (card_id);


--
-- TOC entry 2872 (class 2606 OID 16435)
-- Name: products product_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT product_name_key UNIQUE (name);


--
-- TOC entry 2874 (class 2606 OID 16433)
-- Name: products product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- TOC entry 2880 (class 2606 OID 16649)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 2882 (class 2606 OID 16647)
-- Name: users users_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);


--
-- TOC entry 2884 (class 2606 OID 16645)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


-- Completed on 2021-03-17 22:11:17

--
-- PostgreSQL database dump complete
--

