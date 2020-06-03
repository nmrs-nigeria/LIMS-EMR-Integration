<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>

<script>
    jq = jQuery;
    jq.ajax({
        url: "${ ui.actionLink("limsemrops", "EMRExchangeFragmentController", "searchVLSamples") }",
        dataType: "json",
        data: {
            'Date_Range': manifest_schedule

            document.write(Date_Range);
        }
    }).success(function (data) {

    })
        .error(function (xhr, status, err) {
            j
        });

</script>


<div class="container">
                    <p class="lead">Generate Manifest</p>
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="md-form md-outline input-with-post-icon datepicker">
                                  <input placeholder="Select date" type="date" id="Date_Rage_1" class="form-control">
                                  <label for="example">Start Date</label>
                                </div>

                                <div class="md-form md-outline input-with-post-icon datepicker">
                                                                  <input placeholder="Select date" type="date" id="Date_Rage_2" class="form-control">
                                                                  <label for="example">End Date</label>
                                                                </div>
                                <div>
                                </br>
                                <form action="manifest.page" method="post" target="_blank">
                                <button type="submit" onclick="window.location.href = 'manifest.page';">Create Manifest</button>
                                <hr />
                                </form>
                                </div>
                                <div class="col-md-7" id="gist-6"></div>
                            </div>
                        </div>
                    </div>
</div>
</br>
</br>

<form action="manifest_list.page" method="post" target="_blank">
                                <button type="submit" onclick="window.location.href = 'manifest_list.page';">Show Manifest</button>
                                <hr />
                                </form>

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "moment.js") %>
<!--<% ui.includeJavascript("limsemrops", "lightpick.js")%>-->

<script>
    //var picker = new Lightpick({ field: document.getElementById('datepicker') });

var picker = new Lightpick({
    field: document.getElementById('manifest_schedule'),
    singleDate: false,
    minDate: moment().startOf('month').subtract(30, 'day'),
    maxDate: moment().endOf('month'),
    onSelect: function(start, end){
        var str = '';
        str += start ? start.format('DD MMMM YYYY') + ' to ' : '';
        str += end ? end.format('DD MMMM YYYY') : '...';
        document.getElementById('schedule_range').innerHTML = str;
    }
});
</script>
        