<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />

<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<% ui.includeCss("limsemrops", "datatables.min.css") %>
<% ui.includeCss("limsemrops", "docs/DataTables-1.10.21/css/jquery.bootstrap.min.css") %>

<form>
   </br>
    <h3>Results not available at this time</h3>
    <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th><input id="sample_check" type="checkbox" onchange="checkUncheck(this)" name="chk[]" />
                    <th>Sample ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date Sample Collected</th>
                    <th>Order Type</th>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr></tr>
            </tbody>
            <tfoot>
                <tr>
                    <th><input id="sample_check" type="checkbox" onchange="checkUncheck(this)" name="chk[]" />
                    <th>Sample ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date Sample Collected</th>
                    <th>Order Type</th>
                    </th>
                </tr>
            </tfoot>
        </table>
</br>
<button id="button" type="submit">Check Result</button>
</form>

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "doc/jquery-3.5.1.js") %>

<script>
jQuery(document).ready(function() {
    var table = jQuery('#example').DataTable({"pagingType": "full", stateSave: true});
    //"pagingType": "full_numbers"

    jQuery('#example tbody').on( 'click', 'tr', function () {
        jQuery(this).toggleClass('selected');
    } );
    jQuery('#button').click( function () {
        alert( table.rows('.selected').data().length +' row(s) selected' );
    } );
} );
</script>

<!-- \$320,800 --!>