<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/lga.min.js?cache=1525344062488"></script>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/popper.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
    type="text/css" />
<% ui.includeJavascript("limsemrops", "lga.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>

<div class="container card" id="ui">

        <div class="form-row">
            <div class="col-lg-2">
                <label class="control-label">State of Origin</label>
            </div>
            <div class="col-lg-4">
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
            <div class="col-lg-2">
                <label class="control-label">LGA of Origin</label>
            </div>
            <div class="col-lg-4">
                <select name="lga" id="lga" class="form-control select-lga" required>
            </div>
            </select>
        </div>

    </br>
    <br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>Facility Name</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="facility_name" placeholder="Enter Full Faciltity Name">
            </div>
            <div class="col-lg-2">
                <label>Facility Code</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="facility_code" placeholder="Provide Facility Code">
            </div>
        </div>

    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>Sender's Name</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="sender_full_name" placeholder="Full Name">
            </div>
            <div class="col-lg-2">
                <label>Sender's Mobile</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="sender_phone" placeholder="Phone Number">
            </div>
        </div>

    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>Date of Pick up</label>
            </div>
            <div class="col-lg-4 md-form md-outline input-with-post-icon datepicker">
                <input placeholder="Select date" type="date" id="DateofPickUp" class="form-control">
            </div>
            <div class="col-lg-2">
                <label>Pick up Time</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="pick_time" placeholder="Time format in hh:mm:ss">
            </div>
        </div>

    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>Pick Up Schedule</label>
            </div>
            <div class="col-lg-4 md-form md-outline input-with-post-icon datepicker">
               <input placeholder="Select date" type="date" id="schedule_DateofPickUp" class="form-control">
            </div>
            <div class="col-lg-2">
                <label>Sign</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="signature" placeholder="Signature">
            </div>
        </div>

    <br>
    <label><h3>3PL Details</h3></label>
    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>Total Number of Samples</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="number_samples" placeholder="Enter Total Samples to be Shipped">
            </div>
            <div class="col-lg-2">
                <label>Temp at Pickup</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="temperature" placeholder="Enter temperature in Celsius">
            </div>
        </div>

    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>3PL Name</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="pl_name" placeholder="Full Name of 3PL">
            </div>
            <div class="col-lg-2">
                <label>Phone Number</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="phone_3pl placeholder="Enter 3PL Phone Number">
            </div>
        </div>

    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>PCR Laboratory Name</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="pcr_lab_name" placeholder="Enter name of PCR Lab">
            </div>
            <div class="col-lg-2">
                <label>PCR Lab Code</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="pcr_lab_code" placeholder="Enter PCR Lab Code">
            </div>
        </div>

    </br>

        <div class="form-row">
            <div class="col-lg-2">
                <label>Manifest ID</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="manifest_id" placeholder="Enter MAnifest ID">
            </div>
            <div class="col-lg-2">
                <label>Comment</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" id="comment" placeholder="Enter Comment on Manifest">
            </div>
        </div>

        </br>
        <div class="form-row">
                    <div class="col-lg-2">
                        <label>Created By</label>
                    </div>
                    <div class="col-lg-4 md-form md-outline input-with-post-icon datepicker">
                       <input placeholder="Select date" type="date" id="createdBy" class="form-control">
                    </div>
                    <div class="col-lg-2">
                        <label>Date Created</label>
                    </div>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="dateCreated" placeholder="Signature">
                    </div>
                </div>
                        </br>
                        <div class="form-row">
                    <div class="col-lg-2">
                        <label>Pick Up Schedule</label>
                    </div>
                    <div class="col-lg-4 md-form md-outline input-with-post-icon datepicker">
                       <input placeholder="Select date" type="date" id="schedule_DateofPickUp" class="form-control">
                    </div>
                    <div class="col-lg-2">
                        <label>Sign</label>
                    </div>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="signature" placeholder="Signature">
                    </div>
                </div>

    <br>
<br>
</div>
    <button type="submit" onclick="fetchItems" onclick="getFormvalue_geo()">Save Manifest</button>


<script>
    function getFormvalue_geo() {
        var referringLabState = document.getElementById("state").value;
        var referringLabLga = document.getElementById("lga").value;
        var facility_name = document.getElementById("facility_name").value;
        var facility_code = document.getElementById("facility_code").value;
        var sender_full_name = document.getElementById("sender_full_name").value;
        var sender_phone = document.getElementById("sender_phone").value;
        var DateofPickUp = document.getElementById("DateofPickUp").value;
        var pick_time = document.getElementById("pick_time").value;
        var dateScheduleForPickup = document.getElementById("schedule_DateofPickUp").value;
        var signature = document.getElementById("signature").value;
        var riderTotalSamplesPicked = document.getElementById("number_samples").value;
        var riderTempAtPickUp = document.getElementById("temperature").value;
        var riderName = document.getElementById("pl_name").value;
        var riderPhoneNumber = document.getElementById("phone_3pl").value;
        var pcrLabName = document.getElementById("pcr_lab_name").value;
        var pcrLabCode = document.getElementById("pcr_lab_code").value;
        var manifestID = document.getElementById("manifest_id").value;
        //var comment = document.getElementById("comment").value;
        //var  = document.getElementById("").value;
        var resultStatus = document.getElementById("result_status").value;
        var createdBy = document.getElementById("sender_full_name").value;
        var dateModified = document.getElementById("schedule_DateofPickUp").value;
        var dateCreated = document.getElementById("date_created").value;
        jq = jQuery;

        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "performVLRequisition") }",
            dataType: "json",
            data: {
                'referringLabState': referringLabState,
                'referringLabLga': referringLabLga,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,
                'endDate': endDate,

            }
        }).success(function (data) {

            //console.log(data);
            const sampleSpace_data = localStorage.setItem("sampleSpace_data", JSON.stringify(sampleSpace));
            const sample_data = localStorage.setItem("sample_data", JSON.stringify(data));
            const sampleSpace_set = JSON.parse(localStorage.getItem("sampleSpace_data"));
            const sample_data_set = JSON.parse(localStorage.getItem("sample_data"));
            console.log(sampleSpace_set)
            console.log(sample_data_set)
        })
            .error(function (xhr, status, err) {
                console.log('error occurred');
            });
    }
</script>