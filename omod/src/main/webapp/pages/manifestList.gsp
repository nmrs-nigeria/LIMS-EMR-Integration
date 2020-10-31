<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<%= ui.resourceLinks() %>

<h2 style="text-decoration: underline; text-align: center;">List of Manifest</h2>

<script type="application/javascript">
    var jq = jQuery;

    function getManifestList() {
        jq("#manifest-list-table").html("");
        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getAllSavedManifest") }",
            dataType: "json",
            success: function (response) {
                displayData(response);
            }
        });
    }

    function displayData(response) {
        var dataList = JSON.parse(response);
        var content = "";
        var count = 1;
        content = "<table><thead><tr><th>S/N</th><th>Manifest ID</th><th>PCR Lab Name</th><th>Prepared By</th><th>Date Prepared</th><th>Action</th></tr></thead><tbody>";
        for (i = 0; i < dataList.length; i++) {
            var manifestElement = dataList[i];
            content += "<tr>";
            content += "<td>" + count + "</td>";
            content += "<td>" + manifestElement.manifestID + "</td>";
            content += "<td>" + manifestElement.pcrLabName + "</td>";
            content += "<td>" + manifestElement.createdBy + "</td>";
            content += "<td>" + manifestElement.dateCreated + "</td>";
            content += "<td>";
            content += "<i style=\"font-size: 25px;\" class=\"icon-eye-open\" title=\"View Samples\" onclick=\"viewSamples('" + manifestElement.manifestID + "')\"></i>";
            content += "</td>";
            content += "</tr>";
            count++;
        }
        content += "</tbody></table>";
        jq("#manifest-list-table").append(content);
    }

    function viewSamples(manId) {
        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getManifestSamples") }",
            dataType: "json",
            data: {manifestId: manId},
            success: function (response) {
                displaySampleDialog(response, manId);
            }
        });
    }

    function displaySampleDialog(response, ManfID) {
        var resps = JSON.parse(response);
        var conts = "";
        var counter = 1;
        conts = "MANIFEST ID: " + ManfID;
        conts += "<table>";
        conts += "<tr><th>S/N</th><th>Patient Name</th><th>Patient ID</th><th>Sample ID</th><th>Sample Type</th><th>Sample Order Date</th><th>Sample Collected By</th><th>Sample Collection Date</th><th>Date Sample Sent</th></tr>";
        for (i = 0; i < resps.length; i++) {
            var sampElement = resps[i];
            conts += "<tr>";
            conts += "<td>" + counter + "</td>";
            conts += "<td>" + sampElement.firstName + " " + sampElement.surName + "</td>";
            conts += "<td>" + sampElement.patientID[0].idNumber + "</td>";
            conts += "<td>" + sampElement.sampleID + "</td>";
            conts += "<td>" + sampElement.sampleType + "</td>";
            conts += "<td>" + sampElement.sampleOrderDate + "</td>";
            conts += "<td>" + sampElement.sampleCollectedBy + "</td>";
            conts += "<td>" + sampElement.sampleCollectionDate + "</td>";
            conts += "<td>" + sampElement.dateSampleSent + "</td>";
            conts += "</tr>";
            counter++;
        }
        conts += "</table>";
        var element = document.getElementById("sample-details");
        element.innerHTML = conts;
        jq('#mySampleModal').modal('show');
    }

    getManifestList();
</script>

<div id="manifest-list-table">
</div>
<br>

<!-- View Manifest Samples Modal -->
<div class="modal fade" id="mySampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #00463f;">
        <h5 class="modal-title" id="exampleModalLabel" style="color: white;"><i class="icon-folder-open"></i> Manifest Sample Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="sample-details">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" style="color: black" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>