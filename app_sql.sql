INSERT INTO `appframework_user_app`
(`app_id`,
`json`)
VALUES
('EMR-LIMS-Exchange',
'{\r\n    \"id\": \"Surge\",\r\n    \"description\": \"OpenMRS System Administration\",\r\n    \"order\": 0,\r\n    \"extensions\": [\r\n        {\r\n            \"id\": \"demoapp.homepageLink\",\r\n            \"extensionPointId\": \"org.openmrs.referenceapplication.homepageLink\",\r\n            \"type\": \"link\",\r\n            \"label\": \"EMR-LIMS Exchange\",\r\n            \"url\": \"limsemrops/limsemr.page\",\r\n            \"icon\": \"icon-exchange\",\r\n            \"requiredPrivilege\": \"Replace with a privilege name, or else remove\"\r\n        }\r\n    ]\r\n}'
);