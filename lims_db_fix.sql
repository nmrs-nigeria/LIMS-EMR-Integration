drop table if exists lims_auth_module;
drop table if exists lims_manifest;
drop table if exists lims_manifest_result;
drop table if exists lims_manifest_samples;
delete from appframework_user_app where app_id = 'EMR-LIMS-Exchange';
delete from liquibasechangelog where id like '%lims%';