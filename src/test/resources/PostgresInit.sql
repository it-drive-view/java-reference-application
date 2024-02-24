CREATE TABLE public.student (
	dob date NULL,
	id int8 NOT NULL,
	email varchar(255) NULL,
	name varchar(255) NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.student_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;







