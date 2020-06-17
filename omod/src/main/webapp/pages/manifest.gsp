<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
    type="text/css" />

<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>

<% ui.includeCss("limsemrops", "bootstrap.css") %>


<form id="manifest_details">
    </br>
    <div class="row col-sm-6">
        <div class="form-group">
            <div class="form-inline">
                <label>State</label>
                <select onchange="togglePCR(this);" name="_state" id="_state" class="form-control form-group col-sm-3">
                    <option value="" selected="selected">- Select -</option>
                    <option value="Abia">Abia</option>
                    <option value="Adamawa">Adamawa</option>
                    <option value="AkwaIbom">AkwaIbom</option>
                    <option value="Anambra">Anambra</option>
                    <option value="Bauchi">Bauchi</option>
                    <option value="Bayelsa">Bayelsa</option>
                    <option value="Benue">Benue</option>
                    <option value="Borno">Borno</option>
                    <option value="Cross River">Cross River</option>
                    <option value="Delta">Delta</option>
                    <option value="Ebonyi">Ebonyi</option>
                    <option value="Edo">Edo</option>
                    <option value="Ekiti">Ekiti</option>
                    <option value="Enugu">Enugu</option>
                    <option value="FCT">FCT</option>
                    <option value="Gombe">Gombe</option>
                    <option value="Imo">Imo</option>
                    <option value="Jigawa">Jigawa</option>
                    <option value="Kaduna">Kaduna</option>
                    <option value="Kano">Kano</option>
                    <option value="Katsina">Katsina</option>
                    <option value="Kebbi">Kebbi</option>
                    <option value="Kogi">Kogi</option>
                    <option value="Kwara">Kwara</option>
                    <option value="Lagos">Lagos</option>
                    <option value="Nasarawa">Nasarawa</option>
                    <option value="Niger">Niger</option>
                    <option value="Ogun">Ogun</option>
                    <option value="Ondo">Ondo</option>
                    <option value="Osun">Osun</option>
                    <option value="Oyo">Oyo</option>
                    <option value="Plateau">Plateau</option>
                    <option value="Rivers">Rivers</option>
                    <option value="Sokoto">Sokoto</option>
                    <option value="Taraba">Taraba</option>
                    <option value="Yobe">Yobe</option>
                    <option value="Zamfara">Zamafara</option>

                </select>
                <div class="form-group col-sm-3">
                    <label class="control-label">PCR Lab</label>
                    <select name="pcr" id="pcr" class="form-control select-pcr">
                    </select>
                </div>
</form>
<!-- <label class="control-label">State of Origin</label>
                        <select onchange="toggleLGA(this);" name="state" id="state" class="form-control">
                            <option value="" selected="selected">- Select -</option>
                            <option value="Abia">Abia</option>
                            <option value="Adamawa">Adamawa</option>
                            <option value="AkwaIbom">AkwaIbom</option>
                            <option value="Anambra">Anambra</option>
                            <option value="Bauchi">Bauchi</option>
                            <option value="Bayelsa">Bayelsa</option>
                            <option value="Benue">Benue</option>
                            <option value="Borno">Borno</option>
                            <option value="Cross River">Cross River</option>
                            <option value="Delta">Delta</option>
                            <option value="Ebonyi">Ebonyi</option>
                            <option value="Edo">Edo</option>
                            <option value="Ekiti">Ekiti</option>
                            <option value="Enugu">Enugu</option>
                            <option value="FCT">FCT</option>
                            <option value="Gombe">Gombe</option>
                            <option value="Imo">Imo</option>
                            <option value="Jigawa">Jigawa</option>
                            <option value="Kaduna">Kaduna</option>
                            <option value="Kano">Kano</option>
                            <option value="Katsina">Katsina</option>
                            <option value="Kebbi">Kebbi</option>
                            <option value="Kogi">Kogi</option>
                            <option value="Kwara">Kwara</option>
                            <option value="Lagos">Lagos</option>
                            <option value="Nasarawa">Nasarawa</option>
                            <option value="Niger">Niger</option>
                            <option value="Ogun">Ogun</option>
                            <option value="Ondo">Ondo</option>
                            <option value="Osun">Osun</option>
                            <option value="Oyo">Oyo</option>
                            <option value="Plateau">Plateau</option>
                            <option value="Rivers">Rivers</option>
                            <option value="Sokoto">Sokoto</option>
                            <option value="Taraba">Taraba</option>
                            <option value="Yobe">Yobe</option>
                            <option value="Zamfara">Zamafara</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label">LGA of Origin</label>
                        <select name="lga" id="lga" class="form-control select-lga">
                        </select>
                    </div> -->
<form class="form-inline">
    <label class="control-label">Timely Pickup</label>
    <div class="from-group col-sm-3">
        <label class="form-check-label">
            <input type="radio" class="form-check-input" name="optradio">Yes
        </label>
    </div>
    <div class="form-group col-sm-3">
        <label class="form-check-label">
            <input type="radio" class="form-check-input" name="optradio">No
        </label>
    </div>
</form>
<form class="form-inline">
    <div>
        <label class="form-group">Sender's Name</label>
        <input type="text" id="senderName" placeholder="Full Name">
    </div>
    <div>
        <label class="form-group">Sender Phone No.</label>
        <input type="text" id="senderName" placeholder="Phone Number">
    </div>
</form>
<form class="form-inline">
    <div>
        <label class="form-group">3PL's Name</label>
        <input type="text" id="senderName" placeholder="Full Name">
    </div>
    <div>
        <label class="form-group">3PL Phone No.</label>
        <input type="text" id="senderPhone" placeholder="Phone Number">
    </div>
</form>
<form class="form-inline">
    <div class="md-form md-outline input-with-post-icon datepicker form-group">
        <label for="example">Scheduled Pickup Date</label>
        <input placeholder="Select date" type="date" id="Date_Rage_2" class="form-control">
    </div>
</form>

</div>
</br>
</form>
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