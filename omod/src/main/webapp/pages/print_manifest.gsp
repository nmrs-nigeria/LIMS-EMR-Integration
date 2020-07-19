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
            <div class="header-text">
                <h1>FEDERAL MINISTRY OF HEALTH</h1>
            </div>


            <!--div class="header-image">
                <img src="nigeria-coat-of-arm.jpg">
            </div-->

            <div class="header-sub-text">
                Manifest Created By: MUBARAK DADA <br>
                Facility Contact: 08055533311 <br>
                Date Created: 07 - 05 - 2020
            </div>

            <div class="manifest-details">
                <div class="row">
                    <div class="column" style="background-color:#fff;">
                      <h2>PCI Details</h2>
                      <div class="col-right">Destination</div><div class="col-left">NRL</div>
                      <div class="col-right">PCR Lab Code</div><div class="col-left">NG 1004</div>
                      <div class="col-right">State</div><div class="col-left">Abuja</div>
                      <div class="col-right">LGA</div><div class="col-left">AMAC</div>
                    </div>
                    <div class="column" style="background-color:#fff;">
                      <h2>Courier Details</h2>
                      <div class="col-right">Courier Name</div><div class="col-left">Emeka Orji</div>
                      <div class="col-right">Courier Contact</div><div class="col-left">08165773746</div>
                      <div class="col-right">Pickup Date</div><div class="col-left">05/07/2020</div>
                      <div class="col-right">Sign</div><div class="col-left"></div>
                    </div>
                    <div class="column" style="background-color:#fff;">
                      <h2>Manifest Details</h2>
                      <div class="col-right">Manifest ID</div><div class="col-left">AB5678J</div>
                      <div class="col-right">Total Sample</div><div class="col-left">12</div>
                      <div class="col-right">Test Type</div><div class="col-left">VL</div>
                      <div class="col-right">Status</div><div class="col-left">Sent</div>
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
                            <th>Test Type</th>
                            <th>Encoutered ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>12478</td>
                            <td>KN35456</td>
                            <td>20-06-2020</td>
                            <td>VL</td>
                            <td>82373</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>12478</td>
                            <td>KN35456</td>
                            <td>20-06-2020</td>
                            <td>VL</td>
                            <td>82373</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>12478</td>
                            <td>KN35456</td>
                            <td>20-06-2020</td>
                            <td>VL</td>
                            <td>82373</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>12478</td>
                            <td>KN35456</td>
                            <td>20-06-2020</td>
                            <td>VL</td>
                            <td>82373</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>12478</td>
                            <td>KN35456</td>
                            <td>20-06-2020</td>
                            <td>VL</td>
                            <td>82373</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>12478</td>
                            <td>KN35456</td>
                            <td>20-06-2020</td>
                            <td>VL</td>
                            <td>82373</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <button id="cmd">Generate PDF</button>
    </body>


<script>
var todays_Date = new Date().toLocaleDateString();

var doc = new jsPDF()
var manifest_form = document.querySelector('#manifest_hard')
doc.fromHTML(manifest_form, 15, 15)
doc.save(todays_Date+"_manifest_form.pdf")
</script>
<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>
<% ui.includeJavascript("limsemrops", "moment.js") %>