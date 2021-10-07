<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<%= ui.resourceLinks() %>
<script type="text/javascript"
src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
type="text/css" />

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
                <th>Age</th>
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
                <th>Age</th>
                <th>Order Type</th>
                </th>
            </tr>
        </tfoot>
    </table>
</form>
</br>
</br>
<button type="submit" id="btnFetchSamples" onclick="fetchItems()">Save Manifest</button>
<button type="button" id="btnFetchSamples" onclick="validateSample()">Validate Samples</button>


<!-- Validation Modal -->
<div class="modal fade" id="validationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
      <div class="modal-content">
        <div class="modal-header" style="background-color: #00463f;">
          <h5 class="modal-title" id="exampleModalLabel" style="color: white;"><i class="icon-folder-open"></i> Samples Validation Result</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" id="validation-details">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" style="color: black" data-dismiss="modal">Ok</button>
        </div>
      </div>
    </div>
  </div>

<script type="application/javascript">

    var jq = jQuery;

    function buildTable() {
    var table = document.getElementById('manifest_table');
    //TODO
    //Specify date and time of pickup in before printing...
    for (var i = 0; i < sample_data_user.length; i++) {
	    	var row = '<tr>' +
	        '<td><input id="sample_check" type="checkbox" name="select_test" value='+sample_data_user[i].sampleID+'></td>' +
	        '<td>' + sample_data_user[i].sampleID + '</td>' +
	        '<td>' + sample_data_user[i].firstName + '</td>' +
	        '<td>' + sample_data_user[i].surName + '</td>' +
	        '<td>' + sample_data_user[i].age + '</td>' +
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

    function validateSample() {
        var checkSamples = [];
        const sampleSpace = JSON.parse(localStorage.getItem("sampleSpace_data"));
        jq("input:checkbox[name='select_test']:checked").each(function(){
            checkSamples.push(jq(this).val());
        });
        var checkedSamplesString = JSON.stringify(checkSamples);
        localStorage.setItem("check_sample",checkedSamplesString);
        var local_sample_data = localStorage.getItem("sample_data");
        var sample_data_user = [];
        var final_checked_sample =[];
        if (local_sample_data !== undefined) {
            sample_data_user = JSON.parse(JSON.parse(local_sample_data));
            for(var i=0; i<sample_data_user.length; i++){
                if(checkSamples.includes(sample_data_user[i].sampleID)){
                    final_checked_sample.push(sample_data_user[i].encounterId);
                }
            }            
            final_checked_sample = JSON.stringify(final_checked_sample);
            jq = jQuery;
            jq.ajax({
                url: "${ ui.actionLink("limsemrops", "EMRExchange", "validateSampleInfo") }",
                dataType: "json",
                type: "POST",
                data: { 
                    'vlsamples': final_checked_sample,
                    'sampleSpace': sampleSpace
                },
                success: function (data) {
                    console.log(data);
                    data = JSON.parse(data.body);
                    console.log(data);
                    //call the modal function
                    displayValidationResponseData(data);
                }
            });
        }
    }

    function displayValidationResponseData(response) {
       
        var resps = response;
        var conts = "";
        var counter = 1;
        conts = "VALIDATION RESULT";
        conts += "<table>";
        conts += "<tr><th>S/N</th><th>Patient ID</th><th>Sample ID</th><th>Validation Error</th></tr>";
        for (i = 0; i < resps.length; i++) {
            var sampElement = resps[i];
            var res = sampElement.patientId.split(",");
            var id1 = res[0];
            var id2 = res[1];  
            var patId = id1 + id2 +"]";
            conts += "<tr>";
            conts += "<td>" + counter + "</td>";
            conts += "<td>" + patId + "</td>";
            conts += "<td>" + sampElement.sampleId + "</td>";
            conts += "<td>" + sampElement.validateError + "</td>";
            conts += "</tr>";
            counter++;
        }
        conts += "</table>";
        console.log(conts);
        var element = document.getElementById("validation-details");
        element.innerHTML = conts;
        jq('#validationModal').modal('show');
    }


    function fetchItems() {
        var checkSamples = [];
        jq("input:checkbox[name='select_test']:checked").each(function(){
            checkSamples.push(jq(this).val());
        });
        console.log(checkSamples[0]);
        //  alert(checkSamples[0]);
        var checkedSamplesString = JSON.stringify(checkSamples);
        localStorage.setItem("check_sample",checkedSamplesString);

        var local_sample_data = localStorage.getItem("sample_data");
        var sample_data_user = [];
        var final_checked_sample =[];

        if (local_sample_data !== undefined) {
            sample_data_user = JSON.parse(JSON.parse(local_sample_data));
            for(var i=0; i<sample_data_user.length; i++){
                if(checkSamples.includes(sample_data_user[i].sampleID)){
                    final_checked_sample.push(sample_data_user[i].encounterId);
                }
            }
            final_checked_sample = JSON.stringify(final_checked_sample);
            localStorage.setItem("sample_data",final_checked_sample);
            // remove unused stuff
            localStorage.removeItem("check_sample");
        }
        window.location.assign("manifest_demograph.page");
    }

    var local_sample_data = localStorage.getItem("sample_data");
    console.log(local_sample_data);
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
