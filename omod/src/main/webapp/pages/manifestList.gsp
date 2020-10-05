<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
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
        content = "<table><thead><tr><th>S/N</th><th>Manifest ID</th><th>PCR Lab Name</th><th>Prepared By</th><th>Date Prepared</th></tr></thead><tbody>";
        for (i = 0; i < dataList.length; i++) {
            var manifestElement = dataList[i];
            content += "<tr>";
            content += "<td>" + count + "</td>";
            content += "<td>" + manifestElement.manifestID + "</td>";
            content += "<td>" + manifestElement.pcrLabName + "</td>";
            content += "<td>" + manifestElement.createdBy + "</td>";
            content += "<td>" + manifestElement.dateCreated + "</td>";
            content += "</tr>";
            count++;
        }
        content += "</tbody></table>";
        jq("#manifest-list-table").append(content);
    }

    getManifestList();
</script>

<div id="manifest-list-table">
</div>
<br>