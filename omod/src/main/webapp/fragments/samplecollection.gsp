<%
def id = config.id
%>
<%= ui.resourceLinks() %>

<script>
    jq = jQuery;
    jq('#wait').hide();
  

    jq(function() {
    jq('#${ id }_button_manifest').click(function() {
    jq('#gen-wait').show();

    jq.getJSON('${ ui.actionLink("limsemrops", "EMRExchange", "fetchSampleResult") }')
    .success(function(data) {
    jq('#gen-wait').hide();
    alert("Done working");
    console.log(data);
    })
    .error(function(xhr, status, err) {
    jq('#gen-wait').hide();
    alert(data);

    })
    });
    });
</script>

<!-- <a id="${ id }_button_manifest" class="button app big" style="font-size:12px;min-height: 10px;">
    <i class="icon-refresh"></i>
    <br/>
    <p>Generate</p>
</a> -->

<a href="schedule_manifest.page" class="button app big" style="font-size:12px;min-height: 10px;">
    <i class="icon-refresh"></i>
    <br/>
    <p>Generate Manifest</p>
</a>

<a href="manifestList.page"  class="button app big" style="font-size:12px;min-height: 10px;">
    <i class="icon-list"></i>
    <br/>
    <p>Manifests</p>
</a>

<a href="check_available_result.page"  class="button app big" style="font-size:12px;min-height: 10px;">
    <i class="icon-refresh"></i>
    <br/>
    <p>Request Result</p>
</a>

 <a id="${ id }_button_manifest" class="button app big" style="font-size:12px;min-height: 10px;">
    <i class="icon-refresh"></i>
    <br/>
    <p>Trigger Result Cron</p>
</a>

