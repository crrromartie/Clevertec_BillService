--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-01-23 01:58:57

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
-- Name: discount_card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discount_card (
    card_id bigint NOT NULL,
    card_number smallint NOT NULL,
    discount_percent smallint DEFAULT 0 NOT NULL
);


ALTER TABLE public.discount_card OWNER TO postgres;

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
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 202
-- Name: discount_card_card_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discount_card_card_id_seq OWNED BY public.discount_card.card_id;


--
-- TOC entry 201 (class 1259 OID 16424)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    product_id bigint NOT NULL,
    name character varying(30) NOT NULL,
    price numeric NOT NULL,
    is_promo boolean DEFAULT false NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

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
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public.product.product_id;


--
-- TOC entry 2859 (class 2604 OID 16441)
-- Name: discount_card card_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_card ALTER COLUMN card_id SET DEFAULT nextval('public.discount_card_card_id_seq'::regclass);


--
-- TOC entry 2857 (class 2604 OID 16427)
-- Name: product product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- TOC entry 3002 (class 0 OID 16438)
-- Dependencies: 203
-- Data for Name: discount_card; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.discount_card (card_id, card_number, discount_percent) FROM stdin;
1	1111	10
2	2222	20
3	3333	30
4	4444	40
\.


--
-- TOC entry 3000 (class 0 OID 16424)
-- Dependencies: 201
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (product_id, name, price, is_promo) FROM stdin;
2	Snickers	10	t
3	Nuts	11	f
4	Spartak	7.5	t
5	Bounty	13.5	f
6	KitKat	9	f
7	MilkyWay	8	f
9	Vitba	6.25	f
1	Mars	13.5	f
10	Smack	3	t
\.


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 202
-- Name: discount_card_card_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.discount_card_card_id_seq', 4, true);


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_product_id_seq', 10, true);


--
-- TOC entry 2866 (class 2606 OID 16446)
-- Name: discount_card discount_card_card_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_card
    ADD CONSTRAINT discount_card_card_number_key UNIQUE (card_number);


--
-- TOC entry 2868 (class 2606 OID 16444)
-- Name: discount_card discount_card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_card
    ADD CONSTRAINT discount_card_pkey PRIMARY KEY (card_id);


--
-- TOC entry 2862 (class 2606 OID 16435)
-- Name: product product_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_name_key UNIQUE (name);


--
-- TOC entry 2864 (class 2606 OID 16433)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


-- Completed on 2021-01-23 01:58:58

--
-- PostgreSQL database dump complete
--

