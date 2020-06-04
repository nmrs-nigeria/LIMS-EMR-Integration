<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>

<style>
body {margin: 30px;}
</style>

<div class="container">
                    <p class="lead">Generate Manifest</p>

                    <div class="card">
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
                                </br>
                                </br>
                                </div>
                                   <input type="button" value="Submit" onclick="getFormvalue()">
                            </div>
                        </div>
                    </div>

</div>
</br>
</br>
                  <button type="submit" id="pass_message" onclick="document.write("HTML DOM is working")">Show Manifest</button>
                                <hr />
<script>
function getFormvalue()
{
  var x=document.getElementById("form1");

  var startDate = document.getElementById("Date_Rage_1").value;
  var endDate = document.getElementById("Date_Rage_2").value;

  jq = jQuery;

 jq.ajax({
        url: "${ ui.actionLink("limsemrops", "EMRExchange", "searchVLSamples") }",
        dataType: "json",
        data: {
            'startDate':startDate,
            'endDate': endDate
        }
    }).success(function (data) {
console.log(data);
    })
            .error(function (xhr, status, err) {
console.log('error occurred');
            });


   }
</script>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "moment.js") %>

        