<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<%= ui.resourceLinks() %>

<h2 style="text-decoration: underline; text-align: center;">List of Manifest Result</h2>
<br>
<script type="application/javascript">
    var jq = jQuery;

    function getPendingManifestList() {
        jq("#pending-manifest-list-table").html("");
        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getAllSavedManifest") }",
            dataType: "json",
            success: function (response) {
                displayManifestData(response);
            }
        });
    }

    function displayManifestData(response) {
        var myDataList = JSON.parse(response);
        var content = "";
        var count = 1;
        content = "<table><thead><tr><th>S/N</th><th>Manifest ID</th><th>PCR Lab Name</th><th>PCR Lab Code</th><th>Test Type</th><th>Total Sample</th><th>Result Status</th><th>Date Created</th><th colspan='2'>Action</th></tr></thead><tbody>";
        for (i = 0; i < myDataList.length; i++) {
            var myManifestElement = myDataList[i];
            content += "<tr>";
            content += "<td>" + count + "</td>";
            content += "<td>" + myManifestElement.manifestID + "</td>";
            content += "<td>" + myManifestElement.pcrLabName + "</td>";
            content += "<td>" + myManifestElement.pcrLabCode + "</td>";
            content += "<td>" + myManifestElement.testType + "</td>";
            content += "<td>" + myManifestElement.riderTotalSamplesPicked + "</td>";
            content += "<td>" + myManifestElement.resultStatus + "</td>";
            content += "<td>" + myManifestElement.dateCreated + "</td>";
            content += "<td>";
            content += "<i style=\"font-size: 20px;\" class=\"icon-edit edit-action\" title=\"Check & Update Result\" onclick=\"getResultFromLims('" + myManifestElement.manifestID + "')\"></i>";
            content += "</td>";
            content += "<td>";
            content += "<i style=\"font-size: 20px;\" class=\"icon-eye-open\" title=\"View Result\" onclick=\"viewResult('" + myManifestElement.manifestID + "')\"></i>";
            content += "</td>";
            content += "</tr>";
            count++;
        }
        content += "</tbody></table>";
        jq("#pending-manifest-list-table").append(content);
    }

    function getResultFromLims(ManfID) {
        jq.ajax({
           url: "${ ui.actionLink("limsemrops", "EMRExchange", "fetchSampleResult") }",
           dataType: "json",
           success: function (response) {
             displayDialogNotification();
           }
        });
    }

    function displayDialogNotification() {
        jq('#myModal').modal('show');
    }

    function viewResult(ManfID) {
        jq('#myResultModal').modal('show');
    }

    getPendingManifestList();

</script>

<div id="pending-manifest-list-table">
</div>
<br>

<!-- Modal -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #00463f;">
        <h5 class="modal-title" id="exampleModalLabel" style="color: white;"><i class="icon-folder-open"></i> Result Notification</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p><em> Result verification in progress. The <b>Result Status</b> column will be updated once result is available.</em></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" style="color: black" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>

<!-- View Result Modal -->
<div class="modal fade" id="myResultModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #00463f;">
        <h5 class="modal-title" id="exampleModalLabel" style="color: white;"><i class="icon-folder-open"></i> Result Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table>
            <tr>
                <th>Fullname: </th><td></td>
            </tr>
            <tr>
                <th>Manifest ID: </th><td></td>
            </tr>
            <tr>
                <th>PCR Lab Sample No.:</th><td></td>
            </tr>
            <tr>
                <th>Date Sample Recieved at PCR Lab: </th><td></td>
            </tr>
            <tr>
                <th>Test Result: </th><td></td>
            </tr>
            <tr>
                <th>Sample Status:</th><td></td>
            </tr>
            <tr>
                <th>Sample Testable: </th><td></td>
            </tr>
            <tr>
                <th>Result Date: </th><td></td>
            </tr>
            <tr>
                <th>Assay Date:</th><td></td>
            </tr>
            <tr>
                <th>Approval Date:</th><td></td>
            </tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" style="color: black" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>


</body>
</html>