<% ui.decorateWith("appui", "standardEmrPage") %>
<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<%= ui.resourceLinks() %>

<h2 style="text-decoration: underline; text-align: center;">Manifest Result</h2>
   </br>














    <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>visit Date</th>
                    <th>Test Result</th>
                    <th>Result Date</th>
                    <th>Approval Date</th>
                    <th>Sample Testable</th>
                    <th>Sendig PCR Lab</th>

                </tr>
            </thead>
            <tbody>
            <tr>
                <td>Mujidah</td>
                <td>Hafiz</td>
                <td>2020-07-20</td>
                <td>87432</td>
                <td>2020-02-01</td>
                <td>2020-07-21</td>
                <td>Y</td>
                <td>PLASVIREC TESTING LAB</td>
                </tr>
            </tbody>

        </table>
</br>










<button id="button" type="submit" onclick="getResult_return()">Update Result</button>
<button type="button" onclick="loadDoc()">Check Result</button>

<script>

        function loadDoc() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("demo").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", "https://run.mocky.io/v3/77c64347-3ae4-40fa-afec-648b076edafe", true);
            xhttp.send(/**{
                "manifestID": "99FE9D9-BF7D-4",
                "facilityID": "Ro8QYYh2EVH",
                "testType": "VL"
            }**/);
        }

</script>