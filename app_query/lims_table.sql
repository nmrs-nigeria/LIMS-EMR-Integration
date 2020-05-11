create table lims_manifest(
`id` int(11) NOT NULL AUTO_INCREMENT,
`manifest_id` varchar(64) unique NOT NULL,
`sample_space` varchar(32) NOT NULL,
`result_status` varchar(32) NOT NULL,
`date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
`created_by` varchar(32) NOT NULL,
`date_modified` datetime null ON UPDATE CURRENT_TIMESTAMP,
`modified_by` varchar(32) NULL, 


PRIMARY KEY (`id`),
constraint unique_key unique (manifest_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table lims_manifest_samples(
`id` int(11) NOT NULL AUTO_INCREMENT,
`manifest_id` varchar(64) NOT NULL,
`patient_id` text NOT NULL,
`firstname` varchar(64) NOT NULL,
`surname` varchar(64) NOT NULL,
`sex` varchar(16) NOT NULL,
`pregnantBreastFeedingStatus` int(2) NULL,
`age` int(11) NOT NULL,
`dateOfBirth` varchar(16) NULL,
`sample_id` varchar(64) NOT NULL,
`sample_type` varchar(16) NOT NULL,
`indication_vl_test` int(3) NULL,
`art_commencement_date` datetime NULL,
`drugregimen` varchar(16) NULL,
`sample_ordered_by` varchar(32) NULL,
`sample_ordered_date` datetime NULL,
`sample_collected_by` varchar(16) not NULL,
`sample_collected_date` datetime not NULL,
`date_sample_sent` datetime null,
`encounter_id` int(11) NOT NULL,
`date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
`created_by` varchar(32) NOT NULL,
`date_modified` datetime null ON UPDATE CURRENT_TIMESTAMP,
`modified_by` varchar(32) NULL,  


PRIMARY KEY (`id`),
constraint  `sample_manifest_id_fk` foreign key (`manifest_id`) references lims_manifest(manifest_id),
constraint `sample_encounter_id` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`encounter_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;