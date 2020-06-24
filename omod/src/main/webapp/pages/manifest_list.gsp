<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />

<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<% ui.includeCss("limsemrops", "datatables.min.css") %>
<% ui.includeCss("limsemrops", "docs/DataTables-1.10.21/css/jquery.bootstrap.min.css") %>

<form>
   </br>
    <div>
        </br>
        <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>S/N</th>
                    <th>Manifest ID</th>
                    <th>PCR Lab Name</th>
                    <th>Prepared By</th>
                    <th>Date Prepared</th>
                    <th>Total Sample</th>
                </tr>
            </thead>
            <tbody id="list_manifests">

            </tbody>
            <tfoot>
                <tr>
                    <th>S/N</th>
                    <th>Manifest ID</th>
                    <th>PCR Lab Name</th>
                    <th>Prepared By</th>
                    <th>Date Prepared</th>
                    <th>Total Sample</th>
                </tr>
            </tfoot>
        </table>
        </br>
        </div>
</br>
<button id="button" onclick="" type="submit">Save Manifest</button>
</form>

<script>
function buildTable() {
        var table = document.getElementById('list_manifests');
        //TODO
        //Specify date and time of pickup in before printing...
        for (var i = 0; i < sample_data_user.length; i++) {
            var row = '<tr>' +
                '<td></td>' +
                '<td>' + sample_data_user[i].manifestID + '</td>' +
                '<td>' + sample_data_user[i].pcrLabName + '</td>' +
                '<td>' + sample_data_user[i].createdBy + '</td>' +
                '<td>' + sample_data_user[i].dateCreated + '</td>' +
                '<td>' + sample_data_user[i].riderTotalSamplesPicked + '</td>' +
                '</tr>';
            table.innerHTML += row
        }
    }

    var local_sample_data = localStorage.getItem("sample_data");
    var local_sample_data = localStorage.getItem("samplespace_data");
    var local_sample_data = localStorage.getItem("sample_data");
        var sample_data_user = [];

        if (local_sample_data !== undefined) {
            console.log('sample is not undefined');
            sample_data_user = JSON.parse(JSON.parse(local_sample_data));
            if (sample_data_user !== undefined) {
                buildTable();
            } else {
                console.log('local storage is empty');
            }
            //window.location.assign("manifest_demograph.page");

        }
</script>

<!-- \$320,800 --!>