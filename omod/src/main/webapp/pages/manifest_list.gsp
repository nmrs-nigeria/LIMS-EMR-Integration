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
                    <th>Number of Samples</th>
                    <th>Date Created</th>
                    <th>Prepared By</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th>S/N</th>
                    <th>Manifest ID</th>
                    <th>Number of Samples</th>
                    <th>Date Created</th>
                    <th>Prepared By</th>
                </tr>
            </tfoot>
        </table>
        </br>
        </div>
</br>
<button id="button" type="submit">Save Manifest</button>
</form>

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "doc/datatables.min.js") %>
<% ui.includeJavascript("limsemrops", "doc/jquery-3.5.1.js") %>
<% ui.includeJavascript("limsemrops", "DataTables-1.10.21/js/jquery.dataTables.min.js") %>
<% ui.includeJavascript("limsemrops", "pdfmake-0.1.36/pdfmake.min.js") %>
<% ui.includeJavascript("limsemrops", "vfs_fonts.js") %>
<% ui.includeJavascript("limsemrops", "lga.min.js") %>
<% ui.includeJavascript("limsemrops", "PCRlabs.min.js") %>

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