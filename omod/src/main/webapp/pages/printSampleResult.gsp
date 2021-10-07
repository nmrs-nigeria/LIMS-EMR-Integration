<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<%= ui.resourceLinks() %>

<h2 style="text-decoration: underline; text-align: center;">Manifest Result</h2>
<br>
<script type="application/javascript">
    var jq = jQuery;
	var manifestId = localStorage.getItem("manifestId");
    var sampleId = localStorage.getItem("sampleId");
    var manifestSample;

    function printManifestResult(manifestId) {
         jq.ajax({
             url: "${ ui.actionLink("limsemrops", "EMRExchange", "getResultByManifestId") }",
             dataType: "json",
             data: {manifestId: manifestId},
             success: function (response) {
				 console.log(response);
				 console.log(manifestId);
				 console.log(sampleId);
                 printResultDetail(response, manifestId, sampleId);
             }
         });
    }

        function printResultDetail(response, ManfID, sampleId) {		

					jq.ajax({
						url: "${ ui.actionLink("limsemrops", "EMRExchange", "getManifestSamples") }",
						dataType: "json",
						data: {manifestId: ManfID},
						success: function (data) {		
							console.log(data);					
							manifestSample = JSON.parse(data);
							console.log(manifestSample);
							localStorage.setItem("mansample", data);
						}
					});
					console.log('Local storage' + localStorage.getItem("mansample"));
					manifestSample = JSON.parse(localStorage.getItem("mansample"));
					console.log('Manifest Sample' + manifestSample);
					console.log('Manifest Sample Size' + manifestSample.length);
                    var conts = "";
                    var counter = 1;
                    conts += "<table class='table'>";
                    if(response != "EMPTY"){
                        var resps = JSON.parse(response);
						
						for (j = 0; j < manifestSample.length; j++) {
							var sampElement = manifestSample[j];    
							if(sampElement.sampleID == sampleId){  											
								conts += "<tr>";
								conts += "<td>Patient ID: </td><td>" + sampElement.patientID[0].idNumber + "</td>";
								conts += "</tr>";
								conts += "<tr>";
								conts += "<td>Patient Name: </td><td>" + sampElement.firstName + " " + sampElement.surName + "</td>";
								conts += "</tr>";
								conts += "<tr>";
								conts += "<td>Sex: </td><td>" + sampElement.sex + "</td>";
								conts += "</tr>";
								conts += "<tr>";
								conts += "<td>Age: </td><td>" + sampElement.age + "</td>";						
								conts += "</tr>";
							}
					  }

                      for (i = 0; i < resps.length; i++) {
                        var resultElement = resps[i];                        
                        if(resultElement.sampleID == sampleId){                        
	                        conts += "<tr>";
	                        conts += "<td>Manifest ID: </td><td>" + resultElement.manifestID + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Sample ID: </td><td>" + resultElement.sampleID + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>PCR Lab Sample No.: </td><td>" + resultElement.pcrLabSampleNumber + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Date Sample Recieved at PCR Lab: </td><td>" + resultElement.dateSampleReceivedAtPCRLab + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Test Result: </td><td>" + resultElement.testResult + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Sample Status: </td><td>" + resultElement.sampleStatus + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Sample Testable: </td><td>" + resultElement.sampleTestable + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Result Date: </td><td>" + resultElement.resultDate + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Assay Date: </td><td>" + resultElement.assayDate + "</td>";
	                        conts += "</tr>";
	                        conts += "<tr>";
	                        conts += "<td>Approval Date: </td><td>" + resultElement.approvalDate + "</td>";
	                        conts += "</tr>";
                        }                        
                        counter++;
                      }
                      
                    }
                    conts += "</table>";             
                    jq("#manifest-result-table").append(conts);    
                    window.print();       
                    localStorage.clear();
                }

    printManifestResult(manifestId);

</script>

<div id="manifest-result-table">
</div>
<br>

</body>
</html>