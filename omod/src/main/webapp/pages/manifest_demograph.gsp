<!DOCTYPE html>
<% ui.decorateWith("appui", "standardEmrPage" ) %>
<% ui.includeJavascript("limsemrops", "lga.min.js" ) %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js" ) %>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js" ) %>
<% ui.includeCss("limsemrops", "bootstrap.min.css" ) %>


<%= ui.resourceLinks() %>

<script type="text/javascript"
src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<script type="text/javascript"
src="/openmrs/ms/uiframework/resource/uicommons/scripts/popper.min.js?cache=1525344062488"></script>

<link rel="stylesheet"
href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
type="text/css" />

<style>
    table {
    border: 1px solid #CCC;
    border-collapse: collapse;
    }

    td {
    border: none;
    }
</style>
<div class="container card" id="ui">

    <div id="gen-wait" class="modal hide fade" role="dialog" aria-hidden="true">
        <div class="row">
            <div class="align-items-center col-md-3 col-xs-3 offset-6">
                <img src="../moduleResources/nigeriaemr/images/Sa7X.gif" alt="Loading Gif"
                style="width:100px">
            </div>
        </div>

    </div>

    <h2>Manifest Details</h2>
    <table>
        <tr>
            <td><label class="control-label">Facility State</label></td>
            <td><select onchange="toggleLGA(this);" name="state" id="state"
                class="form-control">
        <option value="" selected="selected"></option>
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
        </select></td>
        <td><label class="control-label">Facility LGA</label></td>
        <td><select name="lga" id="lga" class="form-control select-lga" required></select>
        </td>
        </tr>
        <tr>
            <td><label>Sender's Name</label></td>
            <td><input type="text" class="form-control" id="sender_full_name"
                placeholder="Full Name">
            </td>
            <td><label>Sender's Mobile</label></td>
            <td><input type="text" class="form-control" id="sender_phone"
                placeholder="Phone Number">
            </td>
        </tr>
        <tr>
            <td><label>Date of Pickup</label></td>
            <td>
                <div class="input-with-post-icon datepicker">
                    <input placeholder="Select date" type="date" id="DateofPickUp"
                    class="form-control">
                </div>
            </td>
            <td><label>Time of Pickup</label></td>
            <td><input type="time" class="form-control" id="pick_time"
                placeholder="Time format in hh:mm:ss"></td>
        </tr>
        <tr>
            <td><label>Schedule Pickup</label></td>
            <td>
                <div class="input-with-post-icon datepicker">
                    <input placeholder="Select date" type="date" id="schedule_DateofPickUp"
                    class="form-control">
                </div>
            </td>
            <td><label class="form-check-label">Timely Pickup</label></td>
            <td><input type="checkbox" id="timely_pickup"></td>
        </tr>
        <tr>
            <td><label>Total Number of Samples</label></td>
            <td><input type="text" class="form-control" id="number_samples"
                placeholder="Enter Total Samples to be Shipped" readonly></td>
            <td><label>Temp at Pickup</label></td>
            <td><input type="text" class="form-control" id="temperature"
                placeholder="Temperature at Pickup"></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <h3>Rider Information</h3>
    <table>
        <tr>
            <td><label>Rider Name</label></td>
            <td><input type="text" class="form-control" id="pl_name"
                placeholder="Rider's Full Name">
            </td>
            <td><label>Rider Phone Number</label></td>
            <td><input type="text" class="form-control" id="phone_3pl" size="11"
                placeholder="Rider's Phone Number"></td>
        </tr>
        <tr>
            <td><label>PCR Laboratory Name</label></td>
            <td><select class="form-control" id="pcr_lab_name">
                    <option value="" selected="selected">- Select -</option>

                </select></td>

        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><button type="submit" onclick="getFormvalue_geo()">Save Manifest</button></td>
            <td></td>
            <td></td>
        </tr>
    </table>

    <script>

        jq = jQuery;

        jq.ajax({
                                    url: "${ ui.actionLink("limsemrops", "EMRExchange", "getDefaultPCRLabs") }",
        dataType: "json",
        type: "GET",
        success: function (data) {
        data = JSON.parse(data.body);
        if (data != "") {
        console.log(data);
        for (var a = 0; a < data.length; a++) {
                                                jq('#pcr_lab_name').append("<option value=" + data[a].pcrLabCode + ">" + data[a].pcrLab + "</option>");
        }
        }
        },

        error: function (xhr, status, err) {
        console.log(err);
        }
        });

        
     

    </script>

    <script>

        function checkconnection() 
        {
        var status = navigator.onLine;
        return status;
        }


        //localStorage.clear();
        var vlsamples = JSON.parse(localStorage.getItem("sample_data"));
        const sampleSpace = JSON.parse(localStorage.getItem("sampleSpace_data"));
        document.getElementById("number_samples").value = JSON.parse(localStorage.getItem("sample_data")).length;

        function getFormvalue_geo() {
        var referringLabState = document.getElementById("state").value;
        var referringLabLga = document.getElementById("lga").value;
        //  var facility_name = document.getElementById("facility_name").value;
        // var facility_code = document.getElementById("facility_code").value;
        var sender_full_name = document.getElementById("sender_full_name").value;
        var sender_phone = document.getElementById("sender_phone").value;
        var DateofPickUp = document.getElementById("DateofPickUp").value;
        var pick_time = document.getElementById("pick_time").value;
        var dateScheduleForPickup = document.getElementById("schedule_DateofPickUp").value;
        //    var signature = document.getElementById("signature").value;
        var riderTotalSamplesPicked = document.getElementById("number_samples").value;
        var riderTempAtPickUp = document.getElementById("temperature").value;
        var riderName = document.getElementById("pl_name").value;
        var riderPhoneNumber = document.getElementById("phone_3pl").value;
        var pcrlabSelect = document.getElementById("pcr_lab_name");
        var pcrLabName = pcrlabSelect.options[pcrlabSelect.selectedIndex].text;
        var pcrLabCode = pcrlabSelect.options[pcrlabSelect.selectedIndex].value;
        //var manifestID = document.getElementById("manifest_id").value;
        //var comment = document.getElementById("comment").value;
        //var  = document.getElementById("").value;
        //var resultStatus = document.getElementById("result_status").value;
        //var createdBy = document.getElementById("sender_full_name").value;
        var dateModified = document.getElementById("schedule_DateofPickUp").value;
        //var dateCreated = document.getElementById("date_created").value;

        var manifestObj = {
        'referringLabState': referringLabState,
        'referringLabLga': referringLabLga,
        'dateScheduleForPickup': dateScheduleForPickup,
        'riderTotalSamplesPicked': riderTotalSamplesPicked,
        'riderTempAtPickUp': riderTempAtPickUp,
        'riderName': riderName,
        'riderPhoneNumber': riderPhoneNumber,
        'pcrLabName': pcrLabName,
        'pcrLabCode': pcrLabCode,
        'samplePickUpOnTime': 'yes',
        'samplePackagedBy': sender_full_name
        }


        console.log('sender phone length'+sender_phone.length)

        if (referringLabState == "") {
        alert("Select State...")
        }
        else if (referringLabLga == "") {
        alert("Select LGA where facility is located")
        }
        else if (sender_full_name == "") {
        alert("Please enter the sender name...");
        }
        else if (sender_phone.length !== 11) {
        alert("Enter valid sender phone number...");
        }
        else if (DateofPickUp == "") {
        alert("Enter Date of Pickup...");
        }
        else if (riderName == "") {
        alert("Enter Rider Name...");
        }
        else if (riderPhoneNumber.length !== 11) {
        alert("Enter valid Rider Phone Number...");
        }
        else if (pcrLabName == "") {
        alert("Select PCR Lab...");
        }
        else {

        var check_internet = checkconnection();

        if(!check_internet){
        alert('Check your network connection and try again!');
        return false;
        }

        manifestObj = JSON.stringify(manifestObj);
        vlsamples = JSON.stringify(vlsamples);

        jq = jQuery;

        jq('#gen-wait').modal('show');

        jq.ajax({
                                            url: "${ ui.actionLink("limsemrops", "EMRExchange", "performVLRequisition") }",
        dataType: "json",
        type: "POST",
        data: {
        'manifest': manifestObj,
        'vlsamples': vlsamples,
        'sampleSpace': sampleSpace
        },

        success: function (data) {
        console.log(data);
        // data = JSON.parse(data);
        if(data.statusCode === 'BAD_REQUEST'){
        alert(data.body);
        jq('#gen-wait').modal('hide');
        }else{
        console.log(data.body);
        data = JSON.parse(data.body);
        localStorage.setItem("manifestid", data.manifestID);
        jq('#gen-wait').modal('hide');
        console.log(data.responseMessage);
        alert(data.responseMessage);
       // alert('Samples successfully registered in Lab');
        window.location.assign("print_manifest.page");
        }                                             

        },
        error: function (xhr, status, err) {
        console.log(err);
        jq('#gen-wait').modal('hide');
        alert('Error occurred.');
        }
        });

        }
        }
    </script>
</div>