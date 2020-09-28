<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<%= ui.resourceLinks() %>

<script type="application/javascript">
	
    var jq = jQuery;

    function getManifestList() {
        jq("#manifest-list-table").html("");
        console.log("Getting Manifest");
        console.log('${ ui.actionLink("limsemrops", "manifestList", "getAllSavedManifest") }');
        jq.get('${ ui.actionLink("limsemrops", "manifestList", "getAllSavedManifest") }',
        function (response) {
            if (response) {
            	//displayData(response)
            	//var responseData = JSON.parse(response.replace("manifestElementList=", "\"manifestElementList\":").trim());
                console.log(response);
            } else if (!response) {
            	console.log("error");
            }
        });
    }

    function displayData(response) {
            console.log(responseData);
            var content = "";
            var count = 1;
            content = "<table id="example" class="display" style="width:100%"><thead><tr><th>S/N</th><th>Manifest ID</th><th>PCR Lab Name</th><th>Prepared By</th><th>Date Prepared</th></tr></thead><tbody>";
            for (i = 0; i < response.length; i++) {
                var manifestElement = response[i];
               	console.log(manifestElement);
                content += "<tr>";
                content += "<td>" + count + "</td>";
                content += "<td>" + manifestElement.manifestID + "</td>";
                content += "<td>" + manifestElement.pcrLabName + "</td>";
                content += "<td>" + manifestElement.createdBy + "</td>";
                content += "<td>" + manifestElement.dateCreated + "</td>";
                content += "</tr>";
            }
            content += "</tbody></table>";
            jq("#manifest-list-table").append(content);

        }

    getManifestList();
</script>

 <div id="manifest-list-table">
 </div>