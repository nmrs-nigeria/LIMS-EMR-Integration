<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
    type="text/css" />
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>

<style>
</style>

    <p class="lead">Generate Manifest</p>
    
    <div id="gen-wait" class="dialog" style="display: none; ">
    <div class="row">
        <div class="col-md-3 col-xs-3 offset-2" >
            <img src="../moduleResources/nigeriaemr/images/Sa7X.gif" alt="Loading Gif"  style="width:100px">
        </div>              
    </div>

</div>

        <div class="card-body">
            <div class="row">
                <div class="md-form md-outline input-with-post-icon datepicker">
                    <input placeholder="Select date" type="date" id="Date_Rage_1" class="form-control">
                    <label>Start Date</label>
                </div>

                <div class="md-form md-outline input-with-post-icon datepicker">
                    <input placeholder="Select date" type="date" id="Date_Rage_2" class="form-control">
                    <label for="example">End Date</label>
                </div>
                <div>
                    <select id="Test_Type" class="form-control">
                        <option value="" selected="selected">- Select -</option>
                        <option value="VL">Viral Load</option>
                        <option value="eid">EID</option>
                        <option value="recency">Recency</option>
                    </select>
                    <label>Test Type</label>

                </div>

            </div>
        </div>

                <input type="button" value="Submit" onclick="getFormvalue()">

</br>

<script>
    function getFormvalue() {
        //var x=document.getElementById("form1");
        localStorage.clear();
        var sampleSpace = document.getElementById("Test_Type").value;
        var startDate = document.getElementById("Date_Rage_1").value;
        var endDate = document.getElementById("Date_Rage_2").value;

        jq = jQuery;
        
        jq('#gen-wait').show();

        jq.ajax({
            url: "${ ui.actionLink("limsemrops", "EMRExchange", "searchVLSamples") }",
            dataType: "json",
            data: {
                'sampleSpace': sampleSpace,
                'startDate': startDate,
                'endDate': endDate
            }
        }).success(function (data) {

            //console.log(data);
            const sampleSpace_data = localStorage.setItem("sampleSpace_data", JSON.stringify(sampleSpace));
            const sample_data = localStorage.setItem("sample_data", JSON.stringify(data));
            const sampleSpace_set = JSON.parse(localStorage.getItem("sampleSpace_data"));
            const sample_data_set = JSON.parse(localStorage.getItem("sample_data"));
            console.log(sampleSpace_set)
            console.log(sample_data_set)
             jq('#gen-wait').hide();
            window.location.assign("manifest.page");
        })
            .error(function (xhr, status, err) {
                console.log('error occurred');
            });
            //window.location.assign("manifest.page");
    }
</script>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "moment.js") %>