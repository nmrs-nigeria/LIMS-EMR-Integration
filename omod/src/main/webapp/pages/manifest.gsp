<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
    type="text/css" />

<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>

<% ui.includeCss("limsemrops", "bootstrap.css") %>

<h3>Select samples or pick all to initiate manifest</h3>
</br>
<form id="vlsamples_">
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
        <tbody id="manifest_table">

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
</form>
</br>
</br>
<button type="submit" onclick="fetchItems()">Save Manifest</button>

<script type="application/javascript">

    function buildTable() {
        var table = document.getElementById('manifest_table');
        //TODO
        //Specify date and time of pickup in before printing...
        for (var i = 0; i < sample_data_user.length; i++) {
            var row = '<tr>' +
                '<td><input id="sample_check" type="checkbox" name="select_test" value="picked"></td>' +
                '<td>' + sample_data_user[i].sampleID + '</td>' +
                '<td>' + sample_data_user[i].firstName + '</td>' +
                '<td>' + sample_data_user[i].surName + '</td>' +
                '<td>' + sample_data_user[i].sampleCollectionDate + '</td>' +
                '<td>' + sample_data_user[i].sampleType + '</td>' +
                '</tr>';
            table.innerHTML += row
        }
    }
    function checkUncheck(ele) {
        var checkboxes = document.getElementsByName('select_test');
        if (ele.checked) {
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].type == 'checkbox') {
                    checkboxes[i].checked = true;
                }
            }
        } else {
            for (var i = 0; i < checkboxes.length; i++) {
                console.log(i)
                if (checkboxes[i].type == 'checkbox') {
                    checkboxes[i].checked = false;
                }
            }
        }
    }

    function fetchItems() {
            //Reference the Table.
            var grid = document.getElementById("example");

            //Reference the CheckBoxes in Table.
            var checkBoxes = grid.getElementsByTagName("input");
            var message = "Id Name                  Country";

            //Loop through the CheckBoxes.
            for (var i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].checked) {
                    var row = checkBoxes[i].parentNode.parentNode;
                    message += row.cells[1].innerHTML;
                    message += "   " + row.cells[2].innerHTML;
                    message += "   " + row.cells[3].innerHTML;
                    message += "   " + row.cells[4].innerHTML;
                    message += "   " + row.cells[5].innerHTML;
                    message += " ";
                }
            }

            //Display selected Row data in Alert Box.
            alert(message);
        }

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
    }
</script>