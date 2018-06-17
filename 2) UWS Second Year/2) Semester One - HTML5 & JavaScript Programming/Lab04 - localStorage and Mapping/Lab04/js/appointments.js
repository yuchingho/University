/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/09/2012
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

var subjectField, descriptionField, dateField, timeField, postcodeField;
var API_KEY = "AIzaSyD3Cw-Aj3UzIEvsLeQ_1FXXUPb-4Y544mQ";

var Appointment = function(subject, description, date, time, postcode){
    this.subject = subject;
    this.description = description;
    this.datetime = new Date(date + " " + time);
    this.postcode = postcode;
    this.completed = false;
};

var appointments = [];  // An array of appointments.


Appointment.prototype.isDue = function(){
    var now = new Date();
    if(this.datetime > now){
        return false;
    } else {
        return true;
    }
};

Appointment.prototype.whenDue = function(){
    return this.datetime - new Date(); /// milliSeconds
};

Appointment.prototype.toString = function(){
    var s = this.subject + '\n' + this.description + '\n' +
        this.datetime.toString() + '\n';
    if(this.completed){
        s += "Completed\n\n";
    } else {
        s += "Not Completed\n\n";
    }
    return s;
};

Appointment.prototype.getDate = function(){
    return this.datetime.toDateString();
};

Appointment.prototype.getTime = function(){
    return this.datetime.getHours() + ":" + this.datetime.getMinutes();
};

Appointment.prototype.tableRow = function(index){
    var me = this;
    var btnText = "<button id='"+index+"' onclick='showPos("+index+")'>Map</button>";
    var tr = "<tr><td>"+btnText+"</td><td>" + this.getDate() + "</td><td>" +
             this.getTime() + "</td><td>" + this.subject +
             "</td><td>" + this.postcode + "</td><td><input type='checkbox'>" + this.completed + "</td></tr>";
    return tr;
};

function showPos(index){
    var loc = appointments[index].postcode;
    //alert(loc);
    showPostCode(loc);
}

var addAppointment = function(subjField, descField, dateField, timeField, postcodeField){
    a = new Appointment(subjField.value, descField.value, dateField.value, timeField.value, postcodeField.value);
    appointments.push(a);
};

var clearUI = function(){
    var white = "#fff";
    subjectField.value = "";
    subjectField.style.backgroundColor = white;
    descriptionField.value = "";
    descriptionField.style.backgroundColor = white;
    dateField.value = "";
    dateField.style.backgroundColor = white;
    timeField.value = "";
    timeField.style.backgroundColor = white;
    selectNearestTime(timeField);
    setToday();
};

var updateList = function(){
    var tableDiv = document.getElementById("table"),
        table = "<table border='1'><thead><th>Sel</th></th><th>Date</th><th>Time</th><th>Subject</th><th>Location</th><th>Completed</th></thead>";

    for(var i=0, j=appointments.length; i<j; i++){
        var appt = appointments[i];
        table += appt.tableRow(i);
    }
    saveList();
    // Now insert the rows into the table...
    table+="</table>";
    tableDiv.innerHTML = table;
};

var add = function(){
    addAppointment(subjectField, descriptionField, dateField, timeField, postcodeField);
    clearUI();
    updateList();
};

var cancel = function(){
    clearUI();
    updateList();
};

var selectNearestTime = function(timeField){
    // Now select the nearest time.
    var t = new Date(),
        n = t.getHours()*4 + Math.floor(t.getMinutes() / 15);
    timeField.options[n].selected = true;
};

var setToday = function(){
    dateField.value = new Date();
};

var fillTimeList = function(tf){
    var hours, minutes;
    for(hours=0; hours<24; hours+=1){
        var hh = hours.toString();
        if(hh.length < 2){
            hh = "0"+hh;
        }
        for(minutes=0; minutes<60; minutes+=15){
            var mm = minutes.toString();
            if(mm.length < 2){
                mm = "0"+mm;
            }
            var time = hh+':'+mm;
            tf.options[tf.options.length] = new Option(time, time);
        }
    }
    selectNearestTime(tf);
};

var saveList = function(){
    var appts = JSON.stringify(appointments);
    if(appts !== ""){
        localStorage.appointments = appts;
    } else {
        alert("Could not save appointments at this time");
    }
};

var loadList = function(){
    var appts = "";
    if(localStorage.appointments !== undefined){
        appts = localStorage.appointments;
        appointments = JSON.parse(appts);
        var proto = new Appointment();
        for(var i=0; i<appointments.length; i++){
            var appt = appointments[i]
            // Attach the 'class' prototype to this object
            appt.__proto__ = proto;
            // And make sure the datetime is actually a datetime
            appt.datetime = new Date(appt.datetime);
        }
    }
}

var loadList2 = function(){
    var appts = "";
    if(localStorage.appointments !== null){
        appts = localStorage.appointments;
        appointments = JSON.parse(appts, function (key, value) {
            var type;
            if (value && typeof value === 'object') {
                type = value.type;
                if (typeof type === 'string' && typeof window[type] === 'function') {
                    return new (window[type])(value);
                }
            }
            return value;
        });
        var proto = new Appointment();
        for(var i=0; i<appointments.length; i++){
            for(var func in proto){
                var appt = appointments[i]
                if(!appt.hasOwnProperty(func)){
                    appt[func] = proto[func];
                }
            }
        }
    }
}

/*
var showMap = function(pcode){
    var map_options = {
        center: new google.maps.LatLng(55.797, -4.852),
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map_div"), map_options);
    //geocoder = new google.maps.Geocoder();
    //addPostCode(pcode);
};
*/

function showPostCode(zip) {
    var geocoder, map;
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': zip }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK){
            var map_options = {
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map_div"), map_options);
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location,
                name: zip
            });
        } else {
            alert("Geocode was not successful for the following reason: " + status);
        }
    });
}

window.onload = function(){
    subjectField = document.getElementById("subject");
    descriptionField = document.getElementById("description");
    dateField = document.getElementById("duedate");
    timeField = document.getElementById("duetime");
    postcodeField = document.getElementById("postcode");
    fillTimeList(timeField);
    okButton = document.getElementById("ok");
    okButton.onclick = add;
    cancelButton = document.getElementById("cancel");
    cancelButton.onclick = cancel;
    loadList();
    updateList();
    clearUI();
    //initializeMaps();
};

var showTable = function(){
    var tableDiv = document.getElementById("table"),
        table = "<table border='1'><thead><th>Date</th><th>Time</th><th>Subject</th><th>Completed</th></thead>";

    for(var i=0, j=appointments.length; i<j; i++){
        var appt = appointments[i];
        table += appt.tableRow();
    }
    table+="</table>";
    // Now add the table to the page...
    tableDiv.innerHTML = table;
};
