<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
type="text/css" />
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>



<body id="target">
    <div id="manifest_content">
        <div class="main">

            <div>
                <div class="header-text col-md-8 offset-4">
                    <h1>NISRN TRANSPORTATION MANIFEST</h1>
                </div>

                <div class="col-md-4">
                    <img src="../moduleResources/limsemrops/images/coat.jpg" alt="Loading Gif"  style="width:100px">
                </div>
            </div>


            <!--div class="header-image">
                <img src="nigeria-coat-of-arm.jpg">
            </div-->

            <div class="header-sub-text">
                Manifest Created By: JOHN DOE <br>
                Facility Contact: 08011111111 <br>
                Date Created: 21 - 05 - 2020
            </div>
            <br>
            <div class="manifest-details">
                <div class="row">
                    <div class="col-md-4" id="pcr_details" style="background-color:#fff;">

                    </div>
                    <div class="col-md-4" id="rider_profile" style="background-color:#fff;">

                    </div>
                    <div class="col-md-4" id="manifest_detail" style="background-color:#fff;">

                    </div>
                </div>
            </div>

            <div class="manifest-details">
                <table border=1 cellspacing=0>
                    <thead>
                        <tr class="manifest-details-header manifest-details-header-color">
                            <th>S/No</th>
                            <th>Sample ID</th>
                            <th>Patient ID</th>
                            <th>Date Collected</th>
                            <th>Sample Type</th>
                        </tr>
                    </thead>
                    <tbody id="sample_body">
          
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <br>
    <button id="cmd" onclick="printDoc()">Print</button>
</body>


<script>
    jq = jQuery;
   // localStorage.setItem("manifestid", "01D96B3-9B2F-4");
    var manifestID =    localStorage.getItem("manifestid");
    console.log(manifestID);

    jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getSavedManifestById") }",
    dataType: "json",
    data: {
    'manifestId':manifestID
    }

    }).success(function (data) {
    data = JSON.parse(data.body);
    console.log(data);    
    if(data != ""){
    //pcr lab details
        jq('#pcr_details').append("<h2>PCR Details</h2>");
        jq('#pcr_details').append("<p>Destination</p>");
         jq('#pcr_details').append("<span>"+data.pcrLabName+"</span>");
          jq('#pcr_details').append("<p>PCR Lab Code</p>");
         jq('#pcr_details').append("<span>"+data.pcrLabCode+"</span>");

    // rider profile
         jq('#rider_profile').append("<h2>Courier Details</h2>");
         jq('#rider_profile').append("<p>Courier Name</p>");
         jq('#rider_profile').append("<span>"+data.riderName+"</span>");
         jq('#rider_profile').append("<p>Courier Contact</p>");
        jq('#rider_profile').append("<span>"+data.riderPhoneNumber+"</span>");
        jq('#rider_profile').append("<p>Pickup Date</p>");
       jq('#rider_profile').append("<span>"+data.dateScheduleForPickup+"</span>");

    // manifest details     
 jq('#manifest_detail').append("<h2>Courier Details</h2>");
 jq('#manifest_detail').append("<p>Manifest ID</p>");
 jq('#manifest_detail').append("<span>"+data.manifestID+"</span>");
  jq('#manifest_detail').append("<p>Total Sample</p>");
 jq('#manifest_detail').append("<span>"+data.riderTotalSamplesPicked+"</span>");
 jq('#manifest_detail').append("<p>Test type</p>");
 jq('#manifest_detail').append("<span>"+data.testType+"</span>");
  jq('#manifest_detail').append("<p>Result Status</p>");
  jq('#manifest_detail').append("<span>"+data.resultStatus+"</span>");

    }
    })
    .error(function (xhr, status, err) {
    console.log(err);
    });


</script>

<script>
    jq = jQuery;
    //  localStorage.setItem("manifestid", "01D96B3-9B2F-4");
    var manifestID =    localStorage.getItem("manifestid");
    console.log(manifestID);

    jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "getManifestSamples") }",
    dataType: "json",
    data: {
    'manifestId':manifestID
    }

    }).success(function (data) {
    data = JSON.parse(data.body);
    console.log(data);    
    for(var i=0;i<data.length;i++) {

        var sn = i+1;
        jq('#sample_body').append('<tr>');
      jq('#sample_body').append("<td>"+sn+"</td>");
     jq('#sample_body').append("<td>"+data[i].sampleID+"</td>");
      jq('#sample_body').append("<td>"+data[i].patientID[0].idNumber+"</td>");
      jq('#sample_body').append("<td>"+data[i].sampleCollectionDate+"</td>");
      jq('#sample_body').append("<td>"+data[i].sampleType+"</td>");
 jq('#sample_body').append('</tr>');
 
    }
    })
    .error(function (xhr, status, err) {
    console.log(err);
    });



</script>

<script>
    function printDoc(){
    window.print();
    }
</script>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "moment.js") %>