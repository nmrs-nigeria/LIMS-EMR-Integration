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
	var manifestId = localStorage.getItem("manifestid");
    
    function printManifestResult(manifestId) {
         jq.ajax({
             url: "${ ui.actionLink("limsemrops", "EMRExchange", "getResultByManifestId") }",
             dataType: "json",
             data: {manifestId: manifestId},
             success: function (response) {
                 printResultDetail(response, manifestId);
             }
         });
    }

        function printResultDetail(response, ManfID) {
                    var conts = "";
                    var counter = 1;
                    conts += "<h3 style=\"text-decoration: underline; text-align: center;\">Manifest ID: " + ManfID + "</h3>";
                    conts += "<table class='table'>";
                    conts += "<tr><th>S/N</th><th>Sample ID</th><th>PCR Lab Sample No.</th><th>Date Sample Recieved at PCR Lab</th><th>Test Result</th><th>Sample Status</th><th>Sample Testable</th><th>Result Date</th><th>Assay Date</th><th>Approval Date</th></tr>";
                    if(response != "EMPTY"){
                      var resps = JSON.parse(response);
                      for (i = 0; i < resps.length; i++) {
                        var resultElement = resps[i];
                        conts += "<tr>";
                        conts += "<td>" + counter + "</td>";
                        conts += "<td>" + resultElement.sampleID + "</td>";
                        conts += "<td>" + resultElement.pcrLabSampleNumber + "</td>";
                        conts += "<td>" + resultElement.dateSampleReceivedAtPCRLab + "</td>";
                        conts += "<td>" + resultElement.testResult + "</td>";
                        conts += "<td>" + resultElement.sampleStatus + "</td>";
                        conts += "<td>" + resultElement.sampleTestable + "</td>";
                        conts += "<td>" + resultElement.resultDate + "</td>";
                        conts += "<td>" + resultElement.assayDate + "</td>";
                        conts += "<td>" + resultElement.approvalDate + "</td>";
                        conts += "<tr>";
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