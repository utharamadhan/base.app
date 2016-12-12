UPDATE LOOKUP SET name = 'Pengering/Oven', descr = 'Pengering/Oven' WHERE LOOKUP_GROUP = 'DRYING_METHOD' AND CODE = 'DR';
UPDATE LOOKUP SET name = 'Lantai Jemur', descr = 'Lantai Jemur' WHERE LOOKUP_GROUP = 'DRYING_METHOD' AND CODE = 'MT';
UPDATE LOOKUP SET name = 'Pihak Lain', descr = 'Pihak Lain' WHERE LOOKUP_GROUP = 'ACTOR_PRODUCTION' AND CODE = 'L';

CREATE OR REPLACE VIEW vw_party_seller AS 
SELECT b.pk_party,
    b.name,
    b.contact
   FROM (select distinct(p.pk_party), p.name, pc.contact from party p
   inner join party_role pr join lookup lo on lo.pk_lookup = pr.fk_lookup_party_role and code = 'SL' on pr.fk_party = p.pk_party
   left outer join party_contact pc on pc.fk_party = p.pk_party) b;
   
CREATE OR REPLACE VIEW vw_party_buyer AS 
SELECT b.pk_party,
    b.name,
    b.contact
   FROM (select distinct(p.pk_party), p.name, pc.contact from party p
   inner join party_role pr join lookup lo on lo.pk_lookup = pr.fk_lookup_party_role and code = 'BY' on pr.fk_party = p.pk_party
   left outer join party_contact pc on pc.fk_party = p.pk_party) b;

CREATE OR REPLACE VIEW vw_party_producer AS 
SELECT b.pk_party,
    b.name,
    b.contact
   FROM (select distinct(p.pk_party), p.name, pc.contact from party p
   inner join party_role pr join lookup lo on lo.pk_lookup = pr.fk_lookup_party_role and code = 'PD' on pr.fk_party = p.pk_party
   left outer join party_contact pc on pc.fk_party = p.pk_party) b;
   
