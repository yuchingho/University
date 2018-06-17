var http = require("http");
var url = require("url");
var fs = require('fs');

http.createServer(function(request, response) {
	var url_parts = url.parse(request.url, true);
	console.log(url_parts.query);
	var stream = fs.createWriteStream("my_file.txt");
	stream.once('open', function(fd) {
		stream.write(url_parts.query+'\n');
  		stream.write("My first row\n");
  		stream.write("My second row\n");  
	});
	response.writeHead(200, {"Content-Type": "text/html"});
	response.write("ok");		
	response.end();
}).listen(8890);

console.log("Listening for requests on http://localhost:8890");