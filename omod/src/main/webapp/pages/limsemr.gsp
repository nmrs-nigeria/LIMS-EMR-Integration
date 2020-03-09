<% ui.decorateWith("appui", "standardEmrPage") %>

<%= ui.resourceLinks() %>
<script type="text/javascript" src="/openmrs/ms/uiframework/resource/uicommons/scripts/datetimepicker/bootstrap-datetimepicker.min.js?cache=1525344062488"></script>
<link rel="stylesheet" href="/openmrs/ms/uiframework/resource/uicommons/styles/datetimepicker.css?cache=1525344062488" type="text/css" />
<style>
    .ndr_btn{
    border: none;
    display: inline-block;
    padding: 8px 16px;
    vertical-align: middle;
    overflow: hidden;
    text-decoration: none;
    color: #4e3302;
    background-color: #90c590;
    text-align: center;
    cursor: pointer;
    white-space: nowrap;
    border: 1px solid black;
    border-radius: 8px;
    }
</style>

${ ui.includeFragment("limsemrops", "samplecollection")}

<div id="gen-wait" class="dialog" style="display: none; ">
    <div class="row">
        <div class="col-md-3 col-xs-3 offset-2" >
            <img src="../moduleResources/limsemrops/images/Sa7X.gif" alt="Loading Gif"  style="width:100px">
        </div>              
    </div>

    <div>
        <div class="col-md-7 col-xs-7 " style="text-align:center;">
            <h1>Please wait, operation in progress...</h1>
        </div>
    </div>
</div>


