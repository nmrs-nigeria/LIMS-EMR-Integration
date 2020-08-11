var http = require('http');
var fs = require('fs');

const PORT=8080;

fs.readFile('./returned_result.gsp', function (err, html) {

    if (err) throw err;

    http.createServer(function(request, response) {
        response.writeHeader(200, {"Content-Type": "text/html"});
        response.write(html);  
        response.end();  
    }).listen(PORT);
});