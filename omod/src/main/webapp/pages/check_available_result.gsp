<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
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
                displayManaifestData(response);
            }
        });
    }

    function displayManaifestData(response) {
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
           url: "${ ui.actionLink("limsemrops", "EMRExchange", "getAllSavedManifest") }",
           dataType: "json",
           success: function (response) {
              displayManaifestData(response);
           }
        });
        alert(ManfID);
    }

    function viewResult(ManfID) {
        alert(ManfID);
    }

    getPendingManifestList();
</script>

<div id="pending-manifest-list-table">
</div>
<br>