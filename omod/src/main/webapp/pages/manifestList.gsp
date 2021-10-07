<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>
<%= ui.resourceLinks() %>

<style>
    .active {
       position:relative;  
       background-color:#0E9EBB !important;  /* u can use background-color:#0E9EBB!important;*/
       top:1px;
    }
 </style>

<h2 style="text-decoration: underline; text-align: center;">List of Manifest</h2>

<div id="manifest-list-table"></div>
<br>

<div id="pagination-wrapper" style="border-color: #333;"></div>

<script type="application/javascript">
    var jq = jQuery;
    var state;
    var dataList; 
    getManifestList();
    console.log('Local storage response: ' + localStorage.getItem("datalist"));
    dataList = JSON.parse(localStorage.getItem("datalist"));
    console.log('Data List: '+dataList);
    state = {
            'querySet': dataList,
            'page':1,
            'rows':50,
            'window':5,
        };
    console.log('State: '+state.querySet);
    displayData();

    function getManifestList() {
        jq("#manifest-list-table").html("");
        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getAllSavedManifest") }",
            dataType: "json",
            success: function (response) {
                console.log(response);
                dataList = JSON.parse(response);
                console.log(dataList);
                localStorage.setItem("datalist", response);
            }
        });
    }

    function displayData() {
        var data = pagination(state.querySet, state.page, state.rows)
        console.log('Data:', data)
        myList = data.querySet;       
        var content = "";
        var count = state.page + 5;
        console.log(count);
        content = "<table><thead><tr><th>Manifest ID</th><th>PCR Lab Name</th><th>Prepared By</th><th>Date Prepared</th><th colspan='2'>Action</th></tr></thead><tbody>";
        for (i = 0; i < myList.length; i++) {
            var manifestElement = myList[i];
            content += "<tr>";
            // content += "<td>" + state.page + "</td>";
            content += "<td>" + manifestElement.manifestID + "</td>";
            content += "<td>" + manifestElement.pcrLabName + "</td>";
            content += "<td>" + manifestElement.createdBy + "</td>";
            content += "<td>" + manifestElement.dateCreated + "</td>";
            content += "<td>";
            content += "<i style=\"font-size: 25px;\" class=\"icon-eye-open\" title=\"View Samples\" onclick=\"viewSamples('" + manifestElement.manifestID + "')\"></i>";
            content += "</td>";
            content += "<td>";
            content += "<i style=\"font-size: 25px;\" class=\"icon-comments\" title=\"View Feedbacks\" onclick=\"viewFeedbacks('" + manifestElement.manifestID + "')\"></i>";
            content += "</td>";   
            content += "</tr>";
            count++;
        }
        content += "</tbody></table>";
        jq("#manifest-list-table").append(content);
        pageButtons(data.pages);
    }
    
    function viewSamples(manId) {
        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getManifestSamples") }",
            dataType: "json",
            data: {manifestId: manId},
            success: function (response) {
                displaySampleDialog(response, manId);
            }
        });
    }

  	function viewFeedbacks(manId) {
        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getSavedManifestById") }",
            dataType: "json",
            data: {manifestId: manId},
            success: function (response) {
                displayFeedbacksDialog(response);
            }
        });
    }

    function displaySampleDialog(response, ManfID) {
        var resps = JSON.parse(response);
        var conts = "";
        var counter = 1;
        conts = "MANIFEST ID: " + ManfID;
        conts += "<table>";
        conts += "<tr><th>S/N</th><th>Patient Name</th><th>Patient ID</th><th>Sample ID</th><th>Sample Type</th><th>Sample Order Date</th><th>Sample Collected By</th><th>Sample Collection Date</th><th>Date Sample Sent</th></tr>";
        for (i = 0; i < resps.length; i++) {
            var sampElement = resps[i];
            conts += "<tr>";
            conts += "<td>" + counter + "</td>";
            conts += "<td>" + sampElement.firstName + " " + sampElement.surName + "</td>";
            conts += "<td>" + sampElement.patientID[0].idNumber + "</td>";
            conts += "<td>" + sampElement.sampleID + "</td>";
            conts += "<td>" + sampElement.sampleType + "</td>";
            conts += "<td>" + sampElement.sampleOrderDate + "</td>";
            conts += "<td>" + sampElement.sampleCollectedBy + "</td>";
            conts += "<td>" + sampElement.sampleCollectionDate + "</td>";
            conts += "<td>" + sampElement.dateSampleSent + "</td>";
            conts += "</tr>";
            counter++;
        }
        conts += "</table>";
        var element = document.getElementById("sample-details");
        element.innerHTML = conts;
        jq('#mySampleModal').modal('show');
     }
    
    function displayFeedbacksDialog(response) {
        var resp = JSON.parse(response);
        var jsonFeedback = JSON.parse(resp.feedback);
        var conts = "";
        var incr = 1;
        conts += "<h2 style='text-decoration: underline; text-align: center;'>Manifest Feedbacks</h2>";
        conts += "<table>";
        conts += "<tr>";
        conts += "<td>Manifest ID: </td><td colspan='3'>" + jsonFeedback.manifestID + "</td>";
        conts += "</tr>";
        conts += "<tr>";
        conts += "<td>Facility ID: </td><td>" + jsonFeedback.facilityId + "</td>";
        conts += "<td>Facility Name: </td><td>" + jsonFeedback.facilityName + "</td>";
        conts += "</tr>";
        conts += "<tr>";
        conts += "<td>Receiving PCR LabId ID: </td><td>" + jsonFeedback.receivingPCRLabId + "</td>";
        conts += "<td>Receiving PCR Lab Name: </td><td>" + jsonFeedback.receivingPCRLab + "</td>";
        conts += "</tr>";
        conts += "<tr>";
        conts += "<td>Total Samples Processed: </td><td>" + jsonFeedback.totalSamplesProcessed + "</td>";
        conts += "<td>Total Samples Not Processed: </td><td>" + jsonFeedback.totalSamplesNotProcessed + "</td>";
        conts += "</tr>";       
        conts += "</table>";
        conts += "<h2 style='text-decoration: underline; text-align: center;'>Manifest Errors</h2>";       
        conts += "<table>";
        conts += "<tr><th>S/N</th><th>Sample ID</th><th>Reason</th></tr>";
        for (i = 0; i < jsonFeedback.errors.length; i++) {
            var feedbackErrors = jsonFeedback.errors[i];
            conts += "<tr>";
            conts += "<td>" + incr + "</td>";
            conts += "<td>" + feedbackErrors.sampleId + "</td>";
            conts += "<td>" + feedbackErrors.reasons + "</td>";
            conts += "</tr>";
            incr++;
        }
        conts += "</table>";
        var element = document.getElementById("feedback-details");
        element.innerHTML = conts;
        jq('#myFeedbackModal').modal('show');
    }

    function pagination(querySet, page, rows){
        var trimStart = (page - 1) * rows
        var trimEnd = trimStart + rows
        var trimmedData = querySet.slice(trimStart, trimEnd)
        var pages = Math.ceil(querySet.length / rows)
        return{
            'querySet': trimmedData,
            'pages': pages 
        }
    }
   
    function pageButtons(pages){
        var wrapper = document.getElementById('pagination-wrapper');
        wrapper.innerHTML = '';
        var maxLeft = (state.page - Math.floor(state.window /2))
        var maxRight = (state.page + Math.floor(state.window /2))
        if(maxLeft < 1){
            maxLeft = 1;
            maxRight = state.window;
        }
        if(maxRight > pages){
            maxLeft = pages - (state.window - 1);
            maxRight = pages;
            if(maxLeft < 1){
                maxLeft = 1;
            }
        }
        for(var page=maxLeft; page <= maxRight; page++){
            wrapper.innerHTML += "<button value='" + page + "' class='page btn btn-md btn-default' style='color: black'>" + page +"</button>";                             
        }
        if(state.page != 1){
            wrapper.innerHTML = "<button value='" + state + "' class='page btn btn-md btn-default' style='color: black'>&#171; First</button>" + wrapper.innerHTML;                             
        }
        if(state.page != pages){
            wrapper.innerHTML += "<button value='" + pages + "' class='page btn btn-md btn-default' style='color: black'>Last &#187;</button>";                             
        }
        jq('.page').on('click', function(){
            jq('#manifest-list-table').empty();
            state.page = Number(jq(this).val());
            jq(this).addClass('active');
            displayData();
        })
    }
   

</script>

<!-- View Manifest Samples Modal -->
<div class="modal fade" id="mySampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #00463f;">
        <h5 class="modal-title" id="exampleModalLabel" style="color: white;"><i class="icon-folder-open"></i> Manifest Sample Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="sample-details">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" style="color: black" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>

<!-- View Manifest Feedback Modal -->
<div class="modal fade" id="myFeedbackModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #00463f;">
        <h5 class="modal-title" id="exampleModalLabel" style="color: white;"><i class="icon-folder-open"></i> Manifest Feedback Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="feedback-details">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" style="color: black" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>