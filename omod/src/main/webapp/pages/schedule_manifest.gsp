<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />
<% ui.includeCss("limsemrops", "lightpick.css") %>

<div class="container">
                    <p class="lead">Generate Manifest</p>
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-5">
                                    <p id="schedule_range">&nbsp;</p>
                                    <input type="text" id="manifest_schedule" class="form-control form-control-sm"/>
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


<% ui.includeJavascript("limsemrops", "moment.js") %>
<% ui.includeJavascript("limsemrops", "lightpick.js") %>

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
        