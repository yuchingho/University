var http = require("http");

http.createServer(function(request, response) {
  response.writeHead(200, {"Content-Type": "text/html"});
  var resp = "<html><head><title>Hello World</title></head>"
      resp += "<body><h1>Hello World</h1></body>"
  response.write(resp);
  response.end();
}).listen(8889);

console.log("Listening for requests on http://localhost:8889");
