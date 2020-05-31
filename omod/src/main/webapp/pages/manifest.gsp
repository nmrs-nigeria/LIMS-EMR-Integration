<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />

<link rel="stylesheet" type="text/css" href="/materials/docs/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="/materials/docs/DataTables-1.10.21//css/jquery.dataTables.min.css"/>
<link rel="stylesheet" typr="text/css" href="/materials/docs/state_lga_js/css/bootsrap.min.css"/>

* {
      box-sizing: border-box;
      }
      html, body {
      min-height: 100vh;
      padding: 0;
      margin: 0;
      font-family: Roboto, Arial, sans-serif;
      font-size: 14px; 
      color: #666;
      }
      input, textarea { 
      outline: none;
      }
      body {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 20px;
      background: #5a7233;
      }
      h1 {
      margin-top: 0;
      font-weight: 500;
      }
      form {
      position: relative;
      width: 80%;
      border-radius: 30px;
      background: #fff;
      }
      .form-left-decoration,
      .form-right-decoration {
      content: "";
      position: absolute;
      width: 50px;
      height: 20px;
      border-radius: 20px;
      background: #5a7233;
      }
      .form-left-decoration {
      bottom: 60px;
      left: -30px;
      }
      .form-right-decoration {
      top: 60px;
      right: -30px;
      }
      .form-left-decoration:before,
      .form-left-decoration:after,
      .form-right-decoration:before,
      .form-right-decoration:after {
      content: "";
      position: absolute;
      width: 50px;
      height: 20px;
      border-radius: 30px;
      background: #fff;
      }
      .form-left-decoration:before {
      top: -20px;
      }
      .form-left-decoration:after {
      top: 20px;
      left: 10px;
      }
      .form-right-decoration:before {
      top: -20px;
      right: 0;
      }
      .form-right-decoration:after {
      top: 20px;
      right: 10px;
      }
      .circle {
      position: absolute;
      bottom: 80px;
      left: -55px;
      width: 20px;
      height: 20px;
      border-radius: 50%;
      background: #fff;
      }
      .form-inner {
      padding: 40px;
      }
      .form-inner input,
      .form-inner textarea {
      display: block;
      width: 100%;
      padding: 15px;
      margin-bottom: 10px;
      border: none;
      border-radius: 20px;
      background: #d0dfe8;
      }
      .form-inner textarea {
      resize: none;
      }
      button {
      width: 100%;
      padding: 10px;
      margin-top: 20px;
      border-radius: 20px;
      border: none;
      border-bottom: 4px solid #3e4f24;
      background: #5a7233; 
      font-size: 16px;
      font-weight: 400;
      color: #fff;
      }
      button:hover {
      background: #3e4f24;
      } 
      @media (min-width: 568px) {
      form {
      width: 60%;
      }
      }
    </style>

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
                    <td>Tiger Nixon</td>
                    <td>GHN98765123</td>
                    <td>Edinburgh</td>
                    <td>61</td>
                    <td>2011/04/25</td>
                    <td>$320,800</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Garrett Winters</td>
                    <td>GHN98765123</td>
                    <td>Tokyo</td>
                    <td>63</td>
                    <td>2011/07/25</td>
                    <td>$170,750</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Ashton Cox</td>
                    <td>GHN98765123</td>
                    <td>San Francisco</td>
                    <td>66</td>
                    <td>2009/01/12</td>
                    <td>$86,000</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Cedric Kelly</td>
                    <td>GHN98765123</td>
                    <td>Edinburgh</td>
                    <td>22</td>
                    <td>2012/03/29</td>
                    <td>$433,060</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Airi Satou</td>
                    <td>GHN98765123</td>
                    <td>Tokyo</td>
                    <td>33</td>
                    <td>2008/11/28</td>
                    <td>$162,700</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Brielle Williamson</td>
                    <td>GHN98765123</td>
                    <td>New York</td>
                    <td>61</td>
                    <td>2012/12/02</td>
                    <td>$372,000</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Herrod Chandler</td>
                    <td>GHN98765123</td>
                    <td>San Francisco</td>
                    <td>59</td>
                    <td>2012/08/06</td>
                    <td>$137,500</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Rhona Davidson</td>
                    <td>GHN98765123</td>
                    <td>Tokyo</td>
                    <td>55</td>
                    <td>2010/10/14</td>
                    <td>$327,900</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Colleen Hurst</td>
                    <td>GHN98765123</td>
                    <td>San Francisco</td>
                    <td>39</td>
                    <td>2009/09/15</td>
                    <td>$205,500</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Sonya Frost</td>
                    <td>GHN98765123</td>
                    <td>Edinburgh</td>
                    <td>23</td>
                    <td>2008/12/13</td>
                    <td>$103,600</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Jena Gaines</td>
                    <td>GHN98765123</td>
                    <td>London</td>
                    <td>30</td>
                    <td>2008/12/19</td>
                    <td>$90,560</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Quinn Flynn</td>
                    <td>GHN98765123</td>
                    <td>Edinburgh</td>
                    <td>22</td>
                    <td>2013/03/03</td>
                    <td>$342,000</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Charde Marshall</td>
                    <td>GHN98765123</td>
                    <td>San Francisco</td>
                    <td>36</td>
                    <td>2008/10/16</td>
                    <td>$470,600</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Haley Kennedy</td>
                    <td>GHN98765123</td>
                    <td>London</td>
                    <td>43</td>
                    <td>2012/12/18</td>
                    <td>$313,500</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Tatyana Fitzpatrick</td>
                    <td>GHN98765123</td>
                    <td>London</td>
                    <td>19</td>
                    <td>2010/03/17</td>
                    <td>$385,750</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Michael Silva</td>
                    <td>GHN98765123</td>
                    <td>London</td>
                    <td>66</td>
                    <td>2012/11/27</td>
                    <td>$198,500</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Paul Byrd</td>
                    <td>GHN98765123</td>
                    <td>New York</td>
                    <td>64</td>
                    <td>2010/06/09</td>
                    <td>$725,000</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Gloria Little</td>
                    <td>GHN98765123</td>
                    <td>New York</td>
                    <td>59</td>
                    <td>2009/04/10</td>
                    <td>$237,500</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Bradley Greer</td>
                    <td>GHN98765123</td>
                    <td>London</td>
                    <td>41</td>
                    <td>2012/10/13</td>
                    <td>$132,000</td>
                    <td>Fragile</td>
                </tr>
                <tr>
                    <td>Dai Rios</td>
                    <td>GHN98765123</td>
                    <td>Edinburgh</td>
                    <td>35</td>
                    <td>2012/09/26</td>
                    <td>$217,500</td>
                    <td>Fragile</td>
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
</form>>

<script type="text/javascript" src="/materials/docs/datatables.min.js"></script>
<script type="text/javascript" src="/materials/docs/jquery-3.5.1.js"></script>
<script type="text/javascript" src="docs/DataTables-1.10.21/js/jquery.dataTables.min.js"></script>
<script src='docs/pdfmake-0.1.36/pdfmake.min.js'></script>
<script src='docs/pdfmake-0.1.36/vfs_fonts.js'></script>

<script src="docs/state_lga_js/js/bootstrap.min.js"></script>
<script src="docs/state_lga_js/js/lga.min.js"></script>
<script src="docs/state_lga_js/js/PCRlabs.min.js"></script>
<script>
$(document).ready(function() {
    var table = $('#example').DataTable({"pagingType": "full", stateSave: true});
    //"pagingType": "full_numbers"

    $('#example tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

    $('#button').click( function () {
        alert( table.rows('.selected').data().length +' row(s) selected' );
    } );

    //$('#button').click( function () {
    //    table.row('.selected').remove().draw( false );
    //} );


} );
</script>