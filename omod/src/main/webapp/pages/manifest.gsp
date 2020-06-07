<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />

<% ui.includeCss("limsemrops", "bootstrap.min.css") %>


<form>
   </br>


    <div>
        <div>

            <div class="col-md-3">
                <div class="form-group">
                    <label>State</label>
                    <select onchange="togglePCR(this);" name="_state" id="_state" class="form-control">
                        <option value="" selected="selected">- Select -</option>
                        <option value="Abia">Abia</option>
                        <option value="Adamawa">Adamawa</option>
                        <option value="AkwaIbom">AkwaIbom</option>
                        <option value="Anambra">Anambra</option>
                        <option value="Bauchi">Bauchi</option>
                        <option value="Bayelsa">Bayelsa</option>
                        <option value="Benue">Benue</option>
                        <option value="Borno">Borno</option>
                        <option value="Cross River">Cross River</option>
                        <option value="Delta">Delta</option>
                        <option value="Ebonyi">Ebonyi</option>
                        <option value="Edo">Edo</option>
                        <option value="Ekiti">Ekiti</option>
                        <option value="Enugu">Enugu</option>
                        <option value="FCT">FCT</option>
                        <option value="Gombe">Gombe</option>
                        <option value="Imo">Imo</option>
                        <option value="Jigawa">Jigawa</option>
                        <option value="Kaduna">Kaduna</option>
                        <option value="Kano">Kano</option>
                        <option value="Katsina">Katsina</option>
                        <option value="Kebbi">Kebbi</option>
                        <option value="Kogi">Kogi</option>
                        <option value="Kwara">Kwara</option>
                        <option value="Lagos">Lagos</option>
                        <option value="Nasarawa">Nasarawa</option>
                        <option value="Niger">Niger</option>
                        <option value="Ogun">Ogun</option>
                        <option value="Ondo">Ondo</option>
                        <option value="Osun">Osun</option>
                        <option value="Oyo">Oyo</option>
                        <option value="Plateau">Plateau</option>
                        <option value="Rivers">Rivers</option>
                        <option value="Sokoto">Sokoto</option>
                        <option value="Taraba">Taraba</option>
                        <option value="Yobe">Yobe</option>
                        <option value="Zamfara">Zamafara</option>

                    </select>
                    <div class="form-group">
                        <label class="control-label">PCR Lab</label>
                        <select name="pcr" id="pcr" class="form-control select-pcr" required>
                        </select>
                    </div>
                    <!-- <label class="control-label">State of Origin</label>
                        <select onchange="toggleLGA(this);" name="state" id="state" class="form-control">
                            <option value="" selected="selected">- Select -</option>
                            <option value="Abia">Abia</option>
                            <option value="Adamawa">Adamawa</option>
                            <option value="AkwaIbom">AkwaIbom</option>
                            <option value="Anambra">Anambra</option>
                            <option value="Bauchi">Bauchi</option>
                            <option value="Bayelsa">Bayelsa</option>
                            <option value="Benue">Benue</option>
                            <option value="Borno">Borno</option>
                            <option value="Cross River">Cross River</option>
                            <option value="Delta">Delta</option>
                            <option value="Ebonyi">Ebonyi</option>
                            <option value="Edo">Edo</option>
                            <option value="Ekiti">Ekiti</option>
                            <option value="Enugu">Enugu</option>
                            <option value="FCT">FCT</option>
                            <option value="Gombe">Gombe</option>
                            <option value="Imo">Imo</option>
                            <option value="Jigawa">Jigawa</option>
                            <option value="Kaduna">Kaduna</option>
                            <option value="Kano">Kano</option>
                            <option value="Katsina">Katsina</option>
                            <option value="Kebbi">Kebbi</option>
                            <option value="Kogi">Kogi</option>
                            <option value="Kwara">Kwara</option>
                            <option value="Lagos">Lagos</option>
                            <option value="Nasarawa">Nasarawa</option>
                            <option value="Niger">Niger</option>
                            <option value="Ogun">Ogun</option>
                            <option value="Ondo">Ondo</option>
                            <option value="Osun">Osun</option>
                            <option value="Oyo">Oyo</option>
                            <option value="Plateau">Plateau</option>
                            <option value="Rivers">Rivers</option>
                            <option value="Sokoto">Sokoto</option>
                            <option value="Taraba">Taraba</option>
                            <option value="Yobe">Yobe</option>
                            <option value="Zamfara">Zamafara</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label">LGA of Origin</label>
                        <select name="lga" id="lga" class="form-control select-lga" required>
                        </select>
                    </div> -->

                    <label class="control-label">Timely Pickup</label>
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio">Yes
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio">No
                        </label>
                    </div>

                </div>

            </div>

            <div class="col-md-3"></div>
        </div>
        </br>



        <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>S/N</th>
                    <th>Sample ID</th>
                    <th>Viral Load</th>
                    <th>CD4</th>
                    <th>Hepatitis</th>
                    <th>Others</th>
                    <th>Comments</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

            </tbody>
            <tfoot>
                <tr>
                    <th>S/N</th>
                    <th>Sample ID</th>
                    <th>Viral Load</th>
                    <th>CD4</th>
                    <th>Hepatitis</th>
                    <th>Others</th>
                    <th>Comments</th>
                </tr>
            </tfoot>
        </table>
        </br>
</br>
<button id="button" type="submit">Save Manifest</button>
</form>

<% ui.includeJavascript("limsemrops", "bootstrap.min.js") %>

<script>
var sample_data_user = JSON.parse(localStorage.getItem('sample_data'));
console.log(sample_data_user)
</script>


<!-- \$320,800 --!>