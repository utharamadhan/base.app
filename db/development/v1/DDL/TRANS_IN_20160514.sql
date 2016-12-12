ALTER TABLE trans_in RENAME fk_c_lookup_tof TO fk_c_lookup_top;
ALTER TABLE trans_in RENAME total_fee TO main_fee;
ALTER TABLE trans_in RENAME total_other_fee TO total_in_fee;

ALTER TABLE trans_in DROP CONSTRAINT ti_fk_c_lookup_tof;
      
ALTER TABLE trans_in ADD CONSTRAINT ti_fk_c_lookup_top FOREIGN KEY (fk_c_lookup_top)
      REFERENCES company_lookup (pk_company_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;