<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />

<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<% ui.includeCss("limsemrops", "datatables.min.css") %>
<% ui.includeCss("limsemrops", "docs/DataTables-1.10.21/css/jquery.bootstrap.min.css") %>


   </br>
    <h3>Results Available</h3>
    <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>Manifest ID</th>
                    <th>Facility ID</th>
                    <th>Test Type</th>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>r-FMCK162020-07-16</td>
                    <td>Ro8QYYh2EVH</td>
                    <td>Viral Load</td>
                  </tr>
            </tbody>
            <tfoot>
                <tr>
                                    <th>Manifest ID</th>
                                    <th>Facility ID</th>
                                    <th>Test Type</th>

                                </tr>
                </tr>
            </tfoot>
        </table>
</br>
<button type="submit" onclick="getResult_()">Check Result</button>



<script>
function getResult_(){
window.location.assign("returned_result.page");
}
</script>

<!-- \$320,800 --!>