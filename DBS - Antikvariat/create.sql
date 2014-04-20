CREATE TABLE titul(
id serial NOT NULL PRIMARY KEY,
nazov VARCHAR(100) NOT NULL,
rok_vydania INT NULL,
stav VARCHAR(200) NULL,
cena FLOAT NOT NULL
);

CREATE TABLE autor(
id serial NOT NULL PRIMARY KEY,
meno VARCHAR(50),
priezvisko VARCHAR(30)
)

CREATE TABLE autorstvo(
id serial NOT NULL PRIMARY KEY,
id_titul BIGINT NOT NULL REFERENCES titul(id) ON UPDATE CASCADE ON DELETE RESTRICT,
id_autor BIGINT NOT NULL REFERENCES autor(id) ON UPDATE CASCADE ON DELETE RESTRICT
)

CREATE TABLE zaner(
id serial NOT NULL PRIMARY KEY,
nazov VARCHAR(20)
)

CREATE TABLE zanerstvo(
id_zaner BIGINT NOT NULL REFERENCES zaner(id) ON UPDATE CASCADE ON DELETE RESTRICT,
id_titul BIGINT NOT NULL REFERENCES titul(id) ON UPDATE CASCADE ON DELETE RESTRICT
)

CREATE TABLE typ(
id serial NOT NULL PRIMARY KEY,
nazov varchar(50)
)

CREATE TABLE sklad(
id serial NOT NULL PRIMARY KEY,
nazov varchar(100),
dostupnost BOOLEAN
)

CREATE TABLE objednavka(
id serial NOT NULL PRIMARY KEY
)

CREATE TABLE prod_v_obj(
id_titul BIGINT NOT NULL REFERENCES titul(id) ON UPDATE CASCADE ON DELETE RESTRICT,
id_obj BIGINT NOT NULL REFERENCES objednavka(id) ON UPDATE CASCADE ON DELETE RESTRICT
)

ALTER TABLE titul ADD COLUMN id_typ BIGINT NOT NULL REFERENCES typ(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE titul ADD COLUMN id_sklad BIGINT NOT NULL REFERENCES sklad(id) ON UPDATE CASCADE ON DELETE RESTRICT;

SELECT * FROM autor
select * from titul
select * from autorstvo
select * from zanerstvo

delete from zanerstvo where id = 7

SELECT t.id, t.nazov, t. cena, t.rok_vydania, 
t.stav, au.meno, s.nazov as nazovS, 
ty.nazov AS nazovT, za.nazov AS nazovZ FROM autorstvo a
JOIN titul t ON a.id_titul = t.id
JOIN autor au ON au.id = a.id_autor
JOIN sklad s ON s.id = t.id_sklad
JOIN typ ty ON ty.id = t.id_typ
JOIN zanerstvo z ON z.id_titul = t.id
JOIN zaner za ON za.id = z.id_zaner
ORDER BY t.id

ALTER TABLE autor ADD COLUMN meno character varying(50);


SELECT au.meno, count(t.id) FROM autorstvo a
JOIN autor au ON au.id = a.id_autor
JOIN titul t ON t.id = a.id_titul
JOIN sklad s ON s.id = t.id_sklad
GROUP BY au.meno

