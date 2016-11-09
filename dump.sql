-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  age integer NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;

-- Index: public.user_email_uindex

-- DROP INDEX public.user_email_uindex;

CREATE UNIQUE INDEX user_email_uindex
  ON public."user"
  USING btree
  (email COLLATE pg_catalog."default");

