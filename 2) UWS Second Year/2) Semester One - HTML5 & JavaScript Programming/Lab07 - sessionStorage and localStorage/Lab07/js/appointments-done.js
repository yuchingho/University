/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/09/2012
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

/*global google */

var subjectField, descriptionField, dateField, timeField, postcodeField, currentIndex,
    currentAppointment, editWindow, listWindow;

var API_KEY = "AIzaSyD3Cw-Aj3UzIEvsLeQ_1FXXUPb-4Y544mQ";

/**
 * function returns a two0digit string for a number of 1 or two digits.  i.e. adds a leading 0 if needed.
 * @param n - the number to be delivered as a 2-digit string.
 * @return {*} - the number with a leading zero if needed.
 */
var twoDigit = function (n) {
    "use strict";
    var str = n.toString();
    if (str.length < 2) {
        return '0' + str;
    }
    return str;
};

/**
 * This is the constructor for the main type in the application.  An appointment has...
 * @param subject       - a short heading - e.g. "Dentist"
 * @param description   - a longer description of the appointment.
 * @param date          - the due date of the appointment
 * @param time          - the time of day of the appointment in hh:mm format.
 * @param postcode      - a PostCode to act as location data if needed.
 * @constructor
 */
var Appointment = function (subject, description, date, time, postcode) {
    "use strict";
    this.subject = subject;
    this.description = description;
    this.datetime = new Date(date + " " + time);
    this.postcode = postcode;
    this.completed = false;     // This new appointment is not yet completed.
};

/**
 * Retuns true if the appointment is now due, false otherwise.
 * @return {Boolean}
 */
Appointment.prototype.isDue = function () {
    "use strict";
    var now = new Date();
    return this.datetime <= now;
};

/**
 * Returns when the appoitment is due - i.e. how many mS from now.
 * @return {*} - a number of mS.
 */
Appointment.prototype.whenDue = function () {
    "use strict";
    return this.datetime - new Date(); /// milliSeconds
};

/**
 * Returns a string describing the fields of the appointment.  Handy for debug.
 * @return {String}
 */
Appointment.prototype.toString = function () {
    "use strict";
    var s = this.subject + '\n' + this.description + '\n' +
        this.datetime.toString() + '\n';
    if (this.completed) {
        s += "Completed\n\n";
    } else {
        s += "Not Completed\n\n";
    }
    return s;
};

/**
 * Returns a string containing the date of the appointment.  Useful for UI.
 * @return {String}
 */
Appointment.prototype.getDate = function () {
    "use strict";
    return this.datetime.toDateString();
};

/**
 * Returns the date as a yyyy-mm-dd string.  This seems to be what the <input type="date"> expects, so this
 * value is needed to set up a date input control to the date of the appointment.
 * @return {String}
 */
Appointment.prototype.getDateValue = function () {
    "use strict";
    var d = this.datetime.getFullYear() + "-" + this.datetime.getMonth() + "-" + this.datetime.getDate();
    return d;
};

/**
 * Return the appointment tim ein hh:mm format.
 * @return {String}
 */
Appointment.prototype.getTime = function () {
    "use strict";
    return twoDigit(this.datetime.getHours()) + ":" + twoDigit(this.datetime.getMinutes());
};

/**
 * Returns a number that indicates the position of the appointment time in the <select> control of time values.
 * @return {Number}
 */
Appointment.prototype.getTimeIndex = function () {
    "use strict";
    // Function calculates the index of the appointment time in the <select> list.
    // This is needed so we can set the <select> control to show the time of an
    // Appointment.
    return this.datetime.getHours() * 2 + Math.floor(this.datetime.getMinutes() / 30);
};

/**
 * Generate a html <tr> element containing some of the appointment details.
 * @param index  - the position of this appointment object in the list of appointments.
 * @return {String} - a <tr> element.  Contains event handler references.
 */
Appointment.prototype.tableRow = function (index) {
    "use strict";
    var tr, me, btnText;
    me = this;
    btnText = "<button id='" + index + "' onclick='showPos(" + index + ")'>Map</button>";
    tr = "<tr onclick='selectAppointment(" + index + ")'><td>" + btnText + "</td><td>" + this.getDate() + "</td><td>" +
        this.getTime() + "</td><td>" + this.subject +
        "</td><td>" + this.postcode + "</td><td><input type='checkbox' onclick='updateStatus(" + index + ")'>" + this.completed + "</td></tr>";
    return tr;
};


/***********************************************************************
 * Appointment List declaration and methods
 ***********************************************************************/
var appointments = [];  // An array of appointments.

/**
 * This function saves the entire appointments collection into localStorage.
 */
var saveList = function () {
    "use strict";
    var appts = JSON.stringify(appointments);
    if (appts !== "") {
        localStorage.appointments = appts;
    } else {
        window.alert("Could not save appointments at this time");
    }
};

/**
 * This function tetrieves the list from localStorage and re-constitutes it as a collection of Appointment objects.
 */
var loadList = function () {
    /*jshint proto: false */
    "use strict";
    var appts = "", i, appt, proto;
    if (localStorage.appointments !== undefined) {
        appts = localStorage.appointments;
        appointments = JSON.parse(appts);
        proto = new Appointment();
        for (i = 0; i < appointments.length; i += 1) {
            appt = appointments[i];
            // Attach the 'class' prototype to this object
            appt.__proto__ = proto;
            // And make sure the datetime is actually a datetime
            appt.datetime = new Date(appt.datetime);
        }
    }
};

/**
 * This method will toggle the completed status of a given appointment in the collection.
 * @param index
 */
function updateStatus (index) {
    "use strict";
    appointments[index].completed = !appointments[index].completed;
    event.stopPropagation();
}

/**
 * A Google-Maps interaction.  This function uses Google geocoder to retrieve the latitude and longitude
 * from a UK PostCode, then displays the location on a Google Map.  The map will be generated in the
 * "map_div" HTML <div> element.
 * @param zip - a valid postcode.
 */
function showPostCode(zip) {
    "use strict";
    var geocoder, map, map_options, marker;
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': zip }, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            map_options = {
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map($("#map_div")[0], map_options);
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

/**
 * This handler is fired when the user clicks within a table row.
 * @param index
 */
function selectAppointment(index) {
    "use strict";
    // Stash the clicked--on appointment's index in sessionStorage.
    sessionStorage.current = index;
    // Open the edit window...
    editWindow = open("AppointmentsForm-done.html");
    // ...and close this one.
    window.close();
}

/**
 * This handler fires when the Map button on an appointment in the table is clicked.
 * @param index - the row number of the appointment in the table (from 0).
 */
function showPos(index) {
    "use strict";
    showPostCode(appointments[index].postcode);
    // Note - we need to stop the table-row onclick event (above), since this would move us to the form UI.
    event.stopPropagation();
}

/**
 * A function to create a new appointment and add it to the collection.
 * @param subjField - a html field containing the appointment subject
 * @param descField - a html field containing the appointment description
 * @param dateField - a html field containing the appointment date in short-date format.
 * @param timeField - a html field containing the appointment time in hh:mm format.
 * @param postcodeField - a html field containing the appointment postcode/zipcode.
 */
var addAppointment = function (subjField, descField, dateField, timeField, postcodeField) {
    "use strict";
    var a = new Appointment(subjField.value, descField.value, dateField.value, timeField.value, postcodeField.value);
    appointments.push(a);
};

/**
 * Function removes all data from a form of input fields.
 */
var clearUI = function () {
    "use strict";
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

/**
 * function re-generates the HTML <table> of appointments
 */
var updateList = function () {
    "use strict";
    var table = "<table id='tbl' border='1'><thead><th>Sel</th></th><th>Date</th><th>Time</th><th>Subject</th><th>Location</th><th>Completed</th></thead>",
        i, j, appt;

    for (i = 0, j = appointments.length; i < j; i += 1) {
        appt = appointments[i];
        table += appt.tableRow(i);
    }
    saveList();
    // Now insert the rows into the table...
    table+="</table>";
    $("#table").html(table);
};

/**
 * This function takes the data from HTML input fields and either updates an existing appointment or adds a new one.
 * Whether to add or edit is determined by the index passed to the function: -1 means new appointment.  Any other
 * value is assumed to the the index of that appointment in the list.
 */
var addEdit = function () {
    "use strict";
    currentIndex = parseInt(sessionStorage.current, 10);
    if(currentIndex === -1) { // a new appointment...
        addAppointment(subjectField, descriptionField, dateField, timeField, postcodeField);
    } else {
        // Pass the UI details back into the appointment...
        currentAppointment = appointments[currentIndex];
        appointments[currentIndex].subject = subjectField.value;
        appointments[currentIndex].description = descriptionField.value;
        appointments[currentIndex].datetime = new Date(dateField.value + " " + timeField.value);
        appointments[currentIndex].postcode = postcodeField.value;
    }
    clearUI();
    updateList();
    listWindow = open("AppointmentsList-done.html");
    window.close();
};

/**
 * Cancels an edit/add operation and returns the user to the appointments list.
 */
var cancel = function () {
    "use strict";
    clearUI();
    updateList();
    listWindow = open("AppointmentsList-done.html");
    window.close();
};

/**
 * Sets the date input field to today's date.
 */
var setToday = function () {
    "use strict";
    dateField.value = new Date();
};

/**
 * Sets the time <select> field to show the time nearest to now.
 * @param timeField
 */
var selectNearestTime = function (timeField) {
    "use strict";
    // Now select the nearest time.
    var t = new Date(),
        n = t.getHours() * 2 + Math.floor(t.getMinutes() / 30);
    timeField.options[n].selected = true;
};

/**
 * this initializes the list of times in a <select> control.
 */
var fillTimeList = function (tf) {
    "use strict";
    var hours, minutes;
    for (hours=0; hours<24; hours+=1) {
        var hh = hours.toString();
        if(hh.length < 2){
            hh = "0"+hh;
        }
        var time = hh+':00';
        tf.options[tf.options.length] = new Option(time, time);
        time = hh+':30';
        tf.options[tf.options.length] = new Option(time, time);
    }
    selectNearestTime(tf);
};

/**
 * This displays a specified appointment (by index number) on the form page.
 * @param index - the position of the appointment in the list.
 */
var showAppointment = function(index) {
    "use strict";
    // First, pick up the appointment (from the index value)...
    if(index > -1) {
        currentAppointment = appointments[index];
        // Now pass all of the appointment data on to the form fields...
        subjectField.value = currentAppointment.subject;
        descriptionField.value = currentAppointment.description;
        dateField.value = currentAppointment.getDateValue();
        timeField.selectedIndex = currentAppointment.getTimeIndex();
        postcodeField.value = currentAppointment.postcode;
    } else {
        currentAppointment = null;
    }
    // Note - if currentAppointment is -1, it is a new appointment and
    // the OK button will need to create a new appointment.
};

/**
 * Prepare to add a new appointment.
 */
var addNew = function() {
    "use strict";
    // Simply call selectAppointment with the index -1 (which is not an appointment).
    selectAppointment(-1);
};

/**
 * This goes through the list of appointments removing those marked as completed.
 * It does it in a slightly awkward way because it is not a good idea to modify a list in the middle of a for-loop.
 * Could do this slightly more elegantly with a while loop, but since it works - leave it.
 */
var removeCompletedAppointments = function() {
    "use strict";
    var i, killList = [];
    for(i=0; i<appointments.length; i++) {
        if(appointments[i].completed) {
            killList.push(i);
        }
    }
    for(i=killList.length-1; i>=0; i--){
        appointments.splice(killList[i], 1);
    }
    updateList();

    /*
    Note - to do the same thing without the awkward killList[] array, you could do...

       var index = appointments.length -1;  // Start from the last one.
       while(index >=0) {
            if(appointments[index].completed) {
                appointments.splice(index, 1);      // This removes the one at the index.
            }
            index -= 1; // Move to the next one in the list.
       }
       // Now just re-display...
       updateList();
     */

};

/**
 * This is the standard jQuery initialization routine.
 */
$(document).ready(function () {
    "use strict";
    // Load the list of appointments from localStorage.
    loadList();
    // Get the index of the 'current' appointment. -1 means there isn't one.
    var index = parseInt(sessionStorage.current, 10);
    /**
     * If there is an element with an "ok" id, this is the entry form...
     */
    if($("#ok")[0]) {
        // These are the Form UI elements...
        subjectField = $("#subject")[0];
        descriptionField = $("#description")[0];
        dateField = $("#duedate")[0];
        timeField = $("#duetime")[0];
        postcodeField = $("#postcode")[0];
        fillTimeList(timeField);
        // Now just to add event handlers to the buttons on the form...
        $("#ok").click(addEdit);
        $("#cancel").click(cancel);
        clearUI();
        showAppointment(index);
    } else {
        // This is the List UI...
        $("#addnew").click(addNew);
        $("#cleanUp").click(removeCompletedAppointments);
        updateList();
    }
});