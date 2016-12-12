create or replace view VW_COMPANY_THIRD_PARTY
	as
select 
   p.pk_party as pk_party,
   c.pk_company as FK_COMPANY,
   p.code as CODE,
   p.name as NAME,
   pa.alamat as ALAMAT,
   kella.address_name as KELURAHAN,
   kecla.address_name as KECAMATAN,
   kabla.address_name as KABUPATEN_KOTA,
   prola.address_name as PROVINSI,
   pa.kodepos as KODEPOS,
   p.npwp as NPWP,
   pr.pk_party_role as PK_PARTY_ROLE,
   l.code as ROLE_CODE,
   p.status as STATUS
from party p
inner join party_role pr 
      join lookup l on pr.fk_lookup_party_role = l.pk_lookup and l.code in (select code from lookup where lookup_group = 'PARTY_ROLE_THIRD_PARTY')
	on p.pk_party = pr.fk_party
left join party_address pa on pa.fk_party = p.pk_party
left join lookup_address kella on kella.pk_lookup_address = pa.fk_lookup_addr_kelurahan
left join lookup_address kecla on kecla.pk_lookup_address = pa.fk_lookup_addr_kecamatan
left join lookup_address kabla on kabla.pk_lookup_address = pa.fk_lookup_addr_kabupaten_kota
left join lookup_address prola on prola.pk_lookup_address = pa.fk_lookup_addr_provinsi
inner join party_company pc on pc.fk_party = p.pk_party
inner join company c on c.pk_company = pc.fk_company;