<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
    type="text/css" />
<% ui.includeCss("limsemrops", "bootstrap.min.css") %>




<script>
var todays_Date = new Date().toLocaleDateString();

var doc = new jsPDF()
var manifest_form = document.querySelector('#manifest_hard')
doc.fromHTML(manifest_form, 15, 15)
doc.save(todays_Date+"_manifest_form.pdf")
</script>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "moment.js") %>