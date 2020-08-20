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


<h2>Manifest Details</h2>
<table>
    <tr>
        <td><label class="control-label">Facility State</label></td>
        <td><select onchange="toggleLGA(this);" name="state" id="state" class="form-control">
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
        </select></td>
        <td><label class="control-label">Facility LGA</label></td>
        <td><select name="lga" id="lga" class="form-control select-lga" required></select></td>
    </tr>
    <tr>
        <td><label>Sender's Name</label></td>
        <td><input type="text" class="form-control" id="sender_full_name" placeholder="Full Name"></td>
        <td><label>Sender's Mobile</label></td>
        <td><input type="text" class="form-control" id="sender_phone" placeholder="Phone Number"></td>
    </tr>
    <tr>
        <td><label>Date of Pickup</label></td>
        <td><div class="input-with-post-icon datepicker">
            <input placeholder="Select date" type="date" id="DateofPickUp" class="form-control">
        </div></td>
        <td><label>Time of Pickup</label></td>
        <td><input type="time" class="form-control" id="pick_time" placeholder="Time format in hh:mm:ss"></td>
    </tr>
    <tr>
        <td><label>Schedule Pickup</label></td>
        <td><div class="input-with-post-icon datepicker">
            <input placeholder="Select date" type="date" id="schedule_DateofPickUp" class="form-control">
         </div></td>
        <td><label class="form-check-label">Timely Pickup</label></td>
        <td><input type="checkbox" id="timely_pickup"></td>
    </tr>
    <tr>
        <td><label>Total Number of Samples</label></td>
        <td><input type="text" class="form-control" id="number_samples" placeholder="Enter Total Samples to be Shipped"></td>
        <td><label>Temp at Pickup</label></td>
        <td><input type="text" class="form-control" id="temperature" placeholder="Temperature at Pickup"></td>
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
        <td><input type="text" class="form-control" id="pl_name" placeholder="Rider's Full Name"></td>
        <td><label>Rider Phone Number</label></td>
        <td><input type="text" class="form-control" id="phone_3pl" placeholder="Rider's Phone Number"></td>
    </tr>
    <tr>
        <td><label>PCR Laboratory Name</label></td>
        <td><select class="form-control" id="pcr_lab_name" >
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
            dataType: "json"
            
        }).success(function (data) {
        data = JSON.parse(data.body);
        if(data != ""){
        console.log(data);
        for(var a=0; a<data.length; a++){
         jq('#pcr_lab_name').append("<option value="+data[a].pcrLabCode+">"+data[a].pcrLab+"</option>");
        }
       
        
        }
        
        })
            .error(function (xhr, status, err) {
                console.log(err);
            });  
    
</script>

<script>
//localStorage.clear();
const vlsamples = JSON.parse(localStorage.getItem("sample_data"));
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
        var pcrLabName = document.getElementById("pcr_lab_name").value;
      //  var pcrLabCode = document.getElementById("pcr_lab_code").value;
        //var manifestID = document.getElementById("manifest_id").value;
        //var comment = document.getElementById("comment").value;
        //var  = document.getElementById("").value;
        //var resultStatus = document.getElementById("result_status").value;
        //var createdBy = document.getElementById("sender_full_name").value;
        var dateModified = document.getElementById("schedule_DateofPickUp").value;
        //var dateCreated = document.getElementById("date_created").value;

        var  manifestObj = {
                'referringLabState': referringLabState,
                'referringLabLga': referringLabLga,
                'dateScheduleForPickup': dateScheduleForPickup,
                'riderTotalSamplesPicked': riderTotalSamplesPicked,
                'riderTempAtPickUp': riderTempAtPickUp,
                'riderName': riderName,
                'riderPhoneNumber': riderPhoneNumber,
                'pcrLabName': pcrLabName,
                'pcrLabCode': 'test',
                'samplePickUpOnTime':'yes'
                                        }
          manifestObj = JSON.stringify(manifestObj);

        jq = jQuery;

        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "performVLRequisition") }",
            dataType: "json",
            data: {
            'manifest':manifestObj,
            'vlsamples': vlsamples,
            'sampleSpace': sampleSpace
            }
           // console.log(sampleSpace)
        }).success(function (data) {
            console.log(data);
            data = JSON.parse(data.body);
            console.log(data);
            localStorage.setItem("manifestid", data.manifestID);
            alert('Samples successfully registered in Lab');
            window.location.assign("print_manifest.page");
        })
            .error(function (xhr, status, err) {
                console.log(err);
            });
    }
</script>
</div>
