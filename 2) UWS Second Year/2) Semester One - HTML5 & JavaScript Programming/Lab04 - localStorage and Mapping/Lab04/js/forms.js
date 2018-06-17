/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 30/09/2012
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

var okButton, cancelButton;

var Person = function(name, email, dob){
	this.name = name;
	this.email = email;
	this.dob = new Date(dob);
};

function clearForm(){
	var name = document.getElementById("name"),
		email = document.getELementById("email"),
		dob = document.getElementById("dob");
	name.value = "";
	email.value = "";
	dob.value = "";
}

function doSave(){
	var name = document.getElementById("name"),
		email = document.getElementById("email"),
		dob = document.getElementById("dob"),
		p, s;
	p = new Person(name.value, email.value, dob.value);
	s = JSON.stringify(p);
	if(s !== null){
		localStorage.pdata = s;
	}
	clearForm();
}

function doLoad(){
	var s = localStorage.pdata;
	p = JSON.parse(s);
	var name = document.getElementById("name"),
		email = document.getElementById("email"),
		dob = document.getElementById("dob");
	name.value = p.name;
	email.value = p.email;
	dob.valueAsDate = new Date(p.dob);	
	// Prevents the button from doing a 'submit'
	return false;
}

window.onload = function(){
    okButton = document.getElementById("ok");
    okButton.onclick = doSave;
    cancelButton = document.getElementById("cancel");
    cancelButton.onclick = clearForm;
	loadButton = document.getElementById("load");
	loadButton.onclick = doLoad;
};