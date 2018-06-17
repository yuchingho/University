var sys = require("sys");

function ask(question, format, callback) {
 var stdin = process.stdin, stdout = process.stdout;
 
 stdin.resume();
 stdout.write(question + ": ");
 
 stdin.once('data', function(data) {
   data = data.toString().trim();
 
   if (format.test(data)) {
     callback(data);
   } else {
     stdout.write("It should match: "+ format +"\n");
     ask(question, format, callback);
   }
 });
}

function age(dob) {
	var diff = (new Date() - new Date(dob));
	var yrs = Math.floor(diff / (1000 * 60 * 60 * 24 * 365.25));
	return yrs;
}

ask("Enter your name", /.+/, function(name){
	ask("Enter your date of birth (e.g. Nov 15, 1990)", /.+/, function(dob){
		console.log("You are: " + name);
		console.log("You were born on: " + dob);
		console.log("You are: " + age(dob) + " years old.");
	});
});
//var dob = new Date(window.prompt("Enter your date of birth (yyyy-mm-dd): "));
//window.alert("Your are "+age(dob)+" years old.");

