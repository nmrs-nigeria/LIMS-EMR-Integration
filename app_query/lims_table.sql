create table lims_manifest(
`id` int(11) NOT NULL AUTO_INCREMENT,
`manifest_id` varchar(64) unique NOT NULL,
`sample_space` varchar(32) NOT NULL,
`test_type` varchar(32) NOT NULL,
`referring_lab_state` varchar(32) NOT NULL,
`referring_lab_lga` varchar(32) NOT NULL,
`date_schedule_for_pickup` datetime NULL,
`sample_pick_up_on_time` varchar(5) NULL,
`rider_total_samples_picked` int(11) NULL,
`rider_temp_at_pick_up` varchar(32) NULL,
`rider_name` varchar(64) NULL,
`rider_phone_number` varchar(64) NULL,
`pcr_lab_name` varchar(64) NOT NULL,
`pcr_lab_code` varchar(64) NOT NULL,
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
`sample_ordered_by` varchar(64) NULL,
`sample_ordered_date` datetime NULL,
`sample_collected_by` varchar(64) not NULL,
`sample_collected_date` datetime not NULL,
`sample_collection_time` datetime not NULL,
`date_sample_sent` datetime null,
`encounter_id` int(11) NOT NULL,
`sample_status` varchar(32) NULL,
`rejection_reason` varchar(32) NULL,
`date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
`created_by` varchar(32) NOT NULL,
`date_modified` datetime null ON UPDATE CURRENT_TIMESTAMP,
`modified_by` varchar(32) NULL,


PRIMARY KEY (`id`),
constraint  `sample_manifest_id_fk` foreign key (`manifest_id`) references lims_manifest(manifest_id),
constraint `sample_encounter_id` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`encounter_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table lims_manifest_result(
`id` int(11) NOT NULL AUTO_INCREMENT,
`laboratoryRegistrationNumber` varchar(15) NOT NULL
`pcrLAbSampleNumber` varchar(15) NOT NULL,
`dateSampleReceievedAtPCRLab` datetime NOT NULL,
`testResult` varchar(10) NOT NULL,
`resultDate` datetime NOT NULL,
`approvalDate` datetime NOT NULL,
`sampleStatus` int(2) NOT NULL,
`sampleTestable` varchar(2) NOT NULL


PRIMARY KEY (`id`),
constraint  `sample_manifest_id_fk` foreign key (`manifest_id`) references lims_manifest(manifest_id),
constraint `sample_encounter_id` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`encounter_id`)
)