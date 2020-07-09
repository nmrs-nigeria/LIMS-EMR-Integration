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
                    <th>Sample Number</th>
                    <th>visit Date</th>
                    <th>Test Result</th>
                    <th>Result Date</th>
                    <th>Approval Date</th>
                    <th>Sample Testable</th>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>NRL/UATH/20/15341</td>
                    <td>2020-05-01</td>
                    <td> <20</td>
                    <td>2020-06-17</td>
                    <td>2020-06-25</td>
                    <td>Y</td>
                  </tr>

                  <tr>
                     <td>NRL/UATH/20/15347</td>
                     <td>2020-05-03</td>
                     <td>40000</td>
                     <td>2020-06-25</td>
                     <td>2020-06-25</td>
                     <td>Y</td>
                  </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th>Sample Number</th>
                    <th>visit Date</th>
                    <th>Test Result</th>
                    <th>Result Date</th>
                    <th>Approval Date</th>
                    <th>Sample Testable</th>
                </tr>
            </tfoot>
        </table>
</br>
<button id="button" type="submit" onclick="getResult_return()">Check Result</button>
<button type="button" onclick="loadDoc()">Request data</button>

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "doc/jquery-3.5.1.js") %>

<script>
        //https://run.mocky.io/v3/77c64347-3ae4-40fa-afec-648b076edafe
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