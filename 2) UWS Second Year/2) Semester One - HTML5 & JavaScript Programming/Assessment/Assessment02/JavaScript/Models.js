// Submitted by B00255970 & B00264983
var Contact = function(firstname,lastname,sex,birthday,mstatus,personalEmail,workEmail,mobileNum,workNum,postcode,organisation,group,notes)
{
    this.firstname = firstname;
    this.lastname = lastname;
    this.sex = sex;
    this.birthday = birthday;
    this.mstatus = mstatus;
    this.personalEmail = personalEmail;
    this.workEmail = workEmail;
    this.mobileNum = mobileNum;
    this.workNum = workNum;
    this.postcode = postcode;
    this.organisation = organisation;
    this.group = group;
    this.notes = notes;
}

function saveContact()
{
    contacts.push
    (new Contact(
        jQuery("#firstname").val() + " ",
        jQuery("#lastname").val() + " ",
        jQuery('input[name=sex]:checked', '#contactForm').val() + " ",
        jQuery("#bday").val() + " ",
        jQuery("#mstatus").val() + " ",
        jQuery("#personalEmail").val() + " ",
        jQuery("#workEmail").val() + " ",
        jQuery("#mobileNum").val() + " ",
        jQuery("#workNum").val() + " ",
        jQuery("#postcode").val() + " ",
        jQuery("#organisation").val() + " ",
        jQuery('input[name=group]:checked', '#contactForm').val() + " ",
        jQuery("#notes").val() + " "
    ));
    saveContacts();
    showList();
}

function getContacts(){
    if(localStorage["contacts"] != undefined)
        {
            var storedContacts = JSON.parse(localStorage["contacts"]);
            // JSON.parse unpacks string to array
            for(var i=0; i < storedContacts.length; i++)
                {
                    contacts.push(storedContacts[i]);
                    // adds a new item to the array
                }
        }
}

function saveContacts()
{
    localStorage["contacts"] = JSON.stringify(contacts);
    // JSON.stringify converts the array into a string
}

function exportContacts(id)
{
    var storedContacts = JSON.parse(localStorage["contacts"]);
    uri = "data:application/octet-stream," + encodeURIComponent(localStorage["contacts"]);
    location.href = uri;
}

function deleteContact(id)
{
    contacts.splice(id,1);
    //splice() adds/removes items to/from an array, and returns the removed item(s).
    printContacts();
    localStorage["contacts"] = JSON.stringify(contacts);
}

function showForm()
{
    jQuery("#contactList").hide();
    jQuery("#addContact").show();
}

function showList()
{
    jQuery("#addContact").hide();
    printContacts();
    jQuery("#contactList").show();
}

function printContacts()
{

    var table= document.getElementById("contactTable");
    table.innerHTML="";
    for(var i=0;i<contacts.length;i++){
        var row = table.insertRow(0);
        row.insertCell(0).innerHTML=contacts[i].firstname ;
        row.insertCell(1).innerHTML=contacts[i].lastname;
        row.insertCell(2).innerHTML=contacts[i].sex;
        row.insertCell(3).innerHTML=contacts[i].birthday;
        row.insertCell(4).innerHTML=contacts[i].mstatus;
        row.insertCell(5).innerHTML=contacts[i].personalEmail;
        row.insertCell(6).innerHTML=contacts[i].workEmail;
        row.insertCell(7).innerHTML=contacts[i].mobileNum;
        row.insertCell(8).innerHTML=contacts[i].workNum;
        row.insertCell(9).innerHTML=contacts[i].postcode;
        row.insertCell(10).innerHTML=contacts[i].organisation;
        row.insertCell(11).innerHTML=contacts[i].group;
        row.insertCell(12).innerHTML=contacts[i].notes;
        row.insertCell(13).innerHTML='<a href="#" onclick="deleteContact('+ i + ');">delete</a>'
        row.insertCell(14).innerHTML='<a href="#" onclick="exportContacts('+ i + ');">export</a>'
    }
}
// Code taken from Simple GeoTag & Map application in Moodle Demo's
// -----------------------------------------------------------------
var postCodePattern = "^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$";
var form, postCodeBox;

function showPostCode(zip) {
    var geocoder, map, map_options, marker;
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': zip }, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            map_options = {
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map_div"), map_options);
            map.setCenter(results[0].geometry.location);
            marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location,
                name: zip
            });
        } else {
            window.alert("Geocode was not successful for the following reason: " + status);
        }
    });
}
function showLocation() {
    if (form.checkValidity()) {
        showPostCode(postCodeBox.value);
    } else {
        window.alert("Please supply a valid UK post code");
    }
}
window.onload = function () {
    form = document.getElementById("form");
    postCodeBox = document.getElementById("postcode");
    postCodeBox.setAttribute("pattern", postCodePattern);
    document.getElementById("showmap").onclick = showLocation;
};
// -----------------------------------------------------------------

