/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 16/11/2012
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */

var http = require('http');
var postHTML =
    '<html><head><title>Post Example</title></head>' +
        '<body>' +
        '<form method="post">' +
        'Input 1: <input name="number1"><br>' +
        'Input 2: <input name="input2"><br>' +
        '<input type="submit">' +
        '</form>' +
        '</body></html>';

http.createServer(function (req, res) {
    var body = "";
    req.on('data', function (chunk) {
        body += chunk;
    });
    req.on('end', function () {
        console.log('POSTed: ' + body);
        res.writeHead(200);
        // res.write(parseInt(req.number1)+parseInt(req.number2));
        res.end(postHTML);
    });
}).listen(8080);

/*
    see: http://stackoverflow.com/questions/4295782/node-js-extracting-post-data
*/