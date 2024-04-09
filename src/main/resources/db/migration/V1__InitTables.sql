--
-- PostgreSQL database dump
--

-- Dumped from database version 12.17 (Debian 12.17-1.pgdg120+1)
-- Dumped by pg_dump version 12.17 (Debian 12.17-1.pgdg120+1)

-- Started on 2024-04-09 10:33:45 UTC

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

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: reference-user
--

ALTER SCHEMA public OWNER TO "reference-user";

--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: reference-user
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16385)
-- Name: job_position; Type: TABLE; Schema: public; Owner: reference-user
--

CREATE TABLE public.job_position (
    position_uuid uuid NOT NULL,
    job_platform_id character varying(255),
    posted_at timestamp(6) without time zone,
    source character varying(255),
    fetched_at timestamp(6) without time zone
);


ALTER TABLE public.job_position OWNER TO "reference-user";

--
-- TOC entry 203 (class 1259 OID 16393)
-- Name: scheduler; Type: TABLE; Schema: public; Owner: reference-user
--

CREATE TABLE public.scheduler (
    uuid uuid NOT NULL,
    fetched_at date,
    implementation character varying(255),
    last_fetched_at date
);


ALTER TABLE public.scheduler OWNER TO "reference-user";

--
-- TOC entry 204 (class 1259 OID 16398)
-- Name: skill; Type: TABLE; Schema: public; Owner: reference-user
--

CREATE TABLE public.skill (
    skill_uuid uuid NOT NULL,
    skill character varying(255),
    position_uuid uuid
);


ALTER TABLE public.skill OWNER TO "reference-user";

--
-- TOC entry 205 (class 1259 OID 16403)
-- Name: student; Type: TABLE; Schema: public; Owner: reference-user
--

CREATE TABLE public.student (
    id bigint NOT NULL,
    dob date,
    email character varying(255),
    name character varying(255)
);


ALTER TABLE public.student OWNER TO "reference-user";

--
-- TOC entry 206 (class 1259 OID 16415)
-- Name: student_seq; Type: SEQUENCE; Schema: public; Owner: reference-user
--

CREATE SEQUENCE public.student_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_seq OWNER TO "reference-user";

--
-- TOC entry 2865 (class 2606 OID 16392)
-- Name: job_position job_position_pkey; Type: CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.job_position
    ADD CONSTRAINT job_position_pkey PRIMARY KEY (position_uuid);


--
-- TOC entry 2869 (class 2606 OID 16397)
-- Name: scheduler scheduler_pkey; Type: CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.scheduler
    ADD CONSTRAINT scheduler_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2873 (class 2606 OID 16402)
-- Name: skill skill_pkey; Type: CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (skill_uuid);


--
-- TOC entry 2875 (class 2606 OID 16410)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- TOC entry 2871 (class 2606 OID 16414)
-- Name: scheduler ukgdoptloihp9trp8ptfui5c10c; Type: CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.scheduler
    ADD CONSTRAINT ukgdoptloihp9trp8ptfui5c10c UNIQUE (implementation);


--
-- TOC entry 2867 (class 2606 OID 16412)
-- Name: job_position ukhrdwi51tcel4od1fvuad4s23g; Type: CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.job_position
    ADD CONSTRAINT ukhrdwi51tcel4od1fvuad4s23g UNIQUE (source, job_platform_id);


--
-- TOC entry 2876 (class 2606 OID 16417)
-- Name: skill fkjjlypjsmvjb6rbbug272scqe1; Type: FK CONSTRAINT; Schema: public; Owner: reference-user
--

ALTER TABLE ONLY public.skill
    ADD CONSTRAINT fkjjlypjsmvjb6rbbug272scqe1 FOREIGN KEY (position_uuid) REFERENCES public.job_position(position_uuid);


-- Completed on 2024-04-09 10:33:45 UTC

--
-- PostgreSQL database dump complete
--

