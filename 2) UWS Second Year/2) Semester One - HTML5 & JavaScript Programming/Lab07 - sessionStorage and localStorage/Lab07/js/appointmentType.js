/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/09/2012
 * Time: 18:15
 */

/*global localStorage, window */

/**
 * This utility function returns a two0digit string for a number of 1 or two digits.
 * i.e. adds a leading 0 if needed.
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
 * value is needed to make it easy to set up a date input control to the date of the appointment.
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

function buttonField(text, index, clickhandler) {
    "use strict";
    var btn = "<button id=" + index +
              " onclick=" + clickhandler + "(" + index + ")" +
              ">" + text + "</button>";
    return btn;
}

function tableData(content) {
    "use strict";
    return "<td>" + content + "</td>";
}

function tableRow(clickhandler, index, tdElements) {
    "use strict";
    var element, tr = "<tr onclick='" + clickhandler + "(" + index + ")'>";
    for (element in tdElements) {
        tr += tdElements[element];
    }
    tr += "</tr>";
    return tr;
}

function checkBox(index, clickhandler) {
    "use strict";
    return "<input type='checkbox' onclick='" + clickhandler + "(" + index + ")'>";
}

/**
 * Generate a html <tr> element containing some of the appointment details.
 *
 * The format of the <tr> includes user-controls and event-handler assignments, so
 * this function uses a couple of 'helper' functions to simplify the code.
 *
 * @param index  - the position of this appointment object in the list of appointments.
 * @return {String} - a <tr> element.  Contains event handler references.
 */
Appointment.prototype.tableRow = function (index) {
    "use strict";
    var tr, me, btnText;
    me = this;
    btnText = buttonField(this.postcode, index, "showPos"); //<button id='" + index + "' onclick='showPos(" + index + ")'>Map</button>";

    tr = tableRow("selectAppointment", index, [tableData(this.getDate()),   // Appointment date
                                               tableData(this.getTime()),   // Appointment time
                                               tableData(this.subject),     // subject
                                               tableData(btnText),          // The button field for the map
                                               tableData(checkBox(index, "updateStatus"))]); // A checkbox showing completed status.

    //tr = "<tr onclick='selectAppointment(" + index + ")'><td>" + btnText + "</td><td>" + this.getDate() + "</td><td>" +
    //    this.getTime() + "</td><td>" + this.subject +
    //    "</td><td>" + this.postcode + "</td><td><input type='checkbox' onclick='updateStatus(" + index + ")'>" + this.completed + "</td></tr>";
    return tr;
};


/***********************************************************************
 * Appointment List declaration and methods
 ***********************************************************************/
var appointments = [];  // An array of appointments.

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
 * This function retrieves the list from localStorage and re-constitutes it as a collection of Appointment objects.
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

