$(document).ready(function(){

    var d = new Date();
    var todayDate = d.toUTCString();
    var specialElementHandlers = {
        "#editor": function (element, renderer) {
            return true;
        }
    };

    $("#cmd").click(function(){
        var doc = new jsPDF();

        doc.fromHTML($("#target").html(), 10, 10, {
            "width":170,
            "elementHandlers":specialElementHandlers
        });
        doc.save(todayDate + "_Mubarak.pdf");

    });

});


