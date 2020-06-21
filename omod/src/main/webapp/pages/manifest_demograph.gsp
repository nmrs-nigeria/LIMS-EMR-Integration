<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/lga.min.js?cache=1525344062488"></script>
<script type="text/javascript"
    src="/openmrs/ms/uiframework/resource/uicommons/scripts/popper.min.js?cache=1525344062488"></script>

<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488"
    type="text/css" />
<% ui.includeJavascript("limsemrops", "lga.min.js") %>
<% ui.includeCss("limsemrops", "bootstrap.css") %>
<% ui.includeJavascript("limsemrops", "jquery-3.5.1.js") %>
<div class="container col" id="ui">
<form>
        <div class="form-row">
            <div class="col-lg-2">
                <label class="control-label">State of Origin</label>
            </div>
            <div class="col-lg-4">
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
            <div class="col-lg-2">
                <label class="control-label">LGA of Origin</label>
            </div>
            <div class="col-lg-4">
                <select name="lga" id="lga" class="form-control select-lga" required>
            </div>
            </select>
        </div>
    </form>
    <br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Facility Name</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Enter Full Faciltity Name">
            </div>
            <div class="col-lg-2">
                <label>Facility Code</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Provide Facility Code">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Sender's Name</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Full Name">
            </div>
            <div class="col-lg-2">
                <label>Sender's Mobile</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Phone Number">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Date of Pick up</label>
            </div>
            <div class="col-lg-4 md-form md-outline input-with-post-icon datepicker">
                <input placeholder="Select date" type="date" id="DateofPickUp" class="form-control">
            </div>
            <div class="col-lg-2">
                <label>Pick up Time</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Time format in hh:mm:ss">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Test Type">
            </div>
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Last name">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Test Type">
            </div>
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Last name">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Test Type">
            </div>
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Last name">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Test Type">
            </div>
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Last name">
            </div>
        </div>
    </form>
    </br>
    <form>
        <div class="form-row">
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Test Type">
            </div>
            <div class="col-lg-2">
                <label>Test Type</label>
            </div>
            <div class="col-lg-4">
                <input type="text" class="form-control" placeholder="Last name">
            </div>
        </div>
    </form>
    <br>
<br>
    <button type="submit" onclick="fetchItems">Save Manifest</button>
    </div>


    <script>

    </script>