<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
type="text/css" />

<% ui.includeJavascript("limsemrops", "bootstrap.js") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>

<% ui.includeCss("limsemrops", "bootstrap.css") %>

<h3>Select samples or pick all to initiate manifest</h3>
</br>
<form id="vlsamples_">
    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th><input id="sample_check" type="checkbox" onchange="checkUncheck(this)" name="chk[]" />
                <th>Sample ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Order Type</th>
                </th>
            </tr>
        </thead>
        <tbody id="manifest_table">

        </tbody>
        <tfoot>
            <tr>
                <th><input id="sample_check" type="checkbox" onchange="checkUncheck(this)" name="chk[]" />
                <th>Sample ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Order Type</th>
                </th>
            </tr>
        </tfoot>
    </table>
</form>
</br>
</br>
<button type="submit" id="btnFetchSamples" onclick="fetchItems()">Save Manifest</button>

<script type="application/javascript">

 jq = jQuery;



    

    function buildTable() {
    var table = document.getElementById('manifest_table');
    //TODO
    //Specify date and time of pickup in before printing...
    for (var i = 0; i < sample_data_user.length; i++) {
    var row = '<tr>' +
        '<td><input id="sample_check" type="checkbox" name="select_test" value='+sample_data_user[i].sampleID+'></td>' +
        '<td>' + sample_data_user[i].sampleID + '</td>' +
        '<td>' + sample_data_user[i].firstName + '</td>' +
        '<td>' + sample_data_user[i].surName + '</td>' +
        '<td>' + sample_data_user[i].age + '</td>' +
        '<td>' + sample_data_user[i].sampleType + '</td>' +
        '</tr>';
        table.innerHTML += row
        }
        }
        function checkUncheck(ele) {
        var checkboxes = document.getElementsByName('select_test');
        if (ele.checked) {
        for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type == 'checkbox') {
        checkboxes[i].checked = true;
        }
        }
        } else {
        for (var i = 0; i < checkboxes.length; i++) {
        console.log(i)
        if (checkboxes[i].type == 'checkbox') {
        checkboxes[i].checked = false;
        }
        }
        }
        }

        function fetchItems() {

            var checkSamples = [];
           jq("input:checkbox[name='select_test']:checked").each(function(){
                        checkSamples.push(jq(this).val());
                    });

                console.log(checkSamples[0]);
              //  alert(checkSamples[0]);
                var checkedSamplesString = JSON.stringify(checkSamples);
                localStorage.setItem("check_sample",checkedSamplesString);

            var local_sample_data = localStorage.getItem("sample_data");
            var sample_data_user = [];
            var final_checked_sample =[];

            if (local_sample_data !== undefined) {
                sample_data_user = JSON.parse(JSON.parse(local_sample_data));
                for(var i=0; i<sample_data_user.length; i++){
                    if(checkSamples.includes(sample_data_user[i].sampleID)){
                    final_checked_sample.push(sample_data_user[i]);
                    }
                }
                
                final_checked_sample = JSON.stringify(final_checked_sample);
                
               localStorage.setItem("sample_data",final_checked_sample);  

            }

        window.location.assign("manifest_demograph.page");
        }
        
        
          var local_sample_data = localStorage.getItem("sample_data");
        console.log(local_sample_data)
        var sample_data_user = [];
        //console.log(local_sample_data)

        if (local_sample_data !== undefined) {
        console.log('sample is not undefined');
        sample_data_user = JSON.parse(JSON.parse(local_sample_data));
        if (sample_data_user !== undefined) {
        buildTable();
        } else {
        console.log('local storage is empty');
        }
        //window.location.assign("manifest_demograph.page");

        }


        
</script>