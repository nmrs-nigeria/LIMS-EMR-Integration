<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />

<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<% ui.includeCss("limsemrops", "datatables.min.css") %>
<% ui.includeCss("limsemrops", "docs/DataTables-1.10.21/css/jquery.bootstrap.min.css") %>

   </br>
    <h3>Results not available at this time</h3>
    <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>visit Date</th>
                    <th>Test Result</th>
                    <th>Result Date</th>
                    <th>Approval Date</th>
                    <th>Sample Testable</th>
                    <th>Sendig PCR Lab</th>

                </tr>
            </thead>
            <tbody>
            <tr>
                <td>Mujidah</td>
                <td>Hafiz</td>
                <td>2020-07-20</td>
                <td>87432</td>
                <td>2020-02-01</td>
                <td>2020-07-21</td>
                <td>Y</td>
                <td>PLASVIREC TESTING LAB</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>visit Date</th>
                    <th>Test Result</th>
                    <th>Result Date</th>
                    <th>Approval Date</th>
                    <th>Sample Testable</th>
                    <th>Sendig PCR Lab</th>
                </tr>
            </tfoot>
        </table>
</br>
<button id="button" type="submit" onclick="getResult_return()">Update Result</button>
<button type="button" onclick="loadDoc()">Check Result</button>

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "doc/jquery-3.5.1.js") %>

<script>

        function loadDoc() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("demo").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", "https://run.mocky.io/v3/77c64347-3ae4-40fa-afec-648b076edafe", true);
            xhttp.send(/**{
                "manifestID": "99FE9D9-BF7D-4",
                "facilityID": "Ro8QYYh2EVH",
                "testType": "VL"
            }**/);
        }

function getResult_return(){
window.location.assign("schedule_manifest.page");
}
</script>

<!-- \$320,800 --!>