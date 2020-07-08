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
            <tbody id="Printresult">

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

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "doc/jquery-3.5.1.js") %>

<script>
function getResult_return(){
window.location.assign("schedule_manifest.page");
}
</script>

<!-- \$320,800 --!>