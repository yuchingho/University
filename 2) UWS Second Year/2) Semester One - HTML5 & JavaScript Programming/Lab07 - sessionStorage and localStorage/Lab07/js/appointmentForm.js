/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/09/2012
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

/*global Appointment, appointments, event, loadList, saveList $*/
/*jslint browser:true */
// These are for references to the user-interface (form) controls.
var subjectField, descriptionField, dateField, timeField, postcodeField;

/***************************************************************************************
 * Appointment User-Interface methods.  These methods specifically deal with events that
 * are generated within the user-interface.
 ***************************************************************************************/

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
    var hours, minutes, hh, time;
    for (hours = 0; hours < 24; hours += 1) {
        hh = hours.toString();
        if (hh.length < 2) {
            hh = "0" + hh;
        }
        time = hh + ':00';
        tf.options[tf.options.length] = new Option(time, time);
        time = hh + ':30';
        tf.options[tf.options.length] = new Option(time, time);
    }
    selectNearestTime(tf);
};

/**
 * Sets the date input field to today's date.
 */
var setToday = function () {
    "use strict";
    var d = new Date(),
        month = d.getMonth() + 1,
        day = d.getDate();
    dateField.value = d.getFullYear() + '-' + month + '-' + day;
};

var setToDate = function (dateString) {
    "use strict";
    var d = new Date(dateString),
        month = d.getMonth() + 1,
        day = d.getDate();
    dateField.value = d.getFullYear() + '-' + month + '-' + day;
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
 * This function takes the data from HTML input fields and either updates an existing appointment or adds a new one.
 * Whether to add or edit is determined by the index passed to the function: -1 means new appointment.  Any other
 * value is assumed to the the index of that appointment in the list.
 */
var addNew = function () {
    "use strict";
    addAppointment(subjectField, descriptionField, dateField, timeField, postcodeField);
    clearUI();
};

var updateExisting = function (index) {
    "use strict";
    appointments[index].subject = subjectField.value;
    appointments[index].description = descriptionField.value;
    appointments[index].datetime = new Date(dateField.value + " " + timeField.value);
    appointments[index].postcode = postcodeField.value;
    clearUI();
};

/**
 * Cancels an edit/add operation and returns the user to the appointments list.
 */
var cancel = function () {
    "use strict";
};

/**
 * This displays a specified appointment (by index number) on the form page.
 * @param index - the position of the appointment in the list.
 */
var showAppointment = function (index) {
    "use strict";
    // First, pick up the appointment (from the index value)...
    if (index > -1) {
        // Now pass all of the appointment data on to the form fields...
        subjectField.value = appointments[index].subject;
        descriptionField.value = appointments[index].description;
        dateField.value = appointments[index].getDateValue();
        timeField.selectedIndex = appointments[index].getTimeIndex();
        postcodeField.value = appointments[index].postcode;
    }
    // Note - if index is -1, it is a new appointment and
    // the OK button will need to create a new appointment.
};

function addOrUpdate() {
    "use strict";
    // This function will either add a new appointment based on the values of the
    // input fields, or will update an existing appointment, using the input fields.
    // Code to go here.  For now we'll add adding a new one and saving it...
    addNew();
    saveList();
    alert("If you now browser to AppointmentsForm.html, you should see that the new appointment data has been added.  We can do better than this...");
}

/**
 * This is the standard jQuery initialization routine.
 */
$(document).ready(function () {
    "use strict";
    loadList();
    // These are the Form UI elements...
    subjectField = $("#subject")[0];
    descriptionField = $("#description")[0];
    dateField = $("#duedate")[0];
    timeField = $("#duetime")[0];
    postcodeField = $("#postcode")[0];
    fillTimeList(timeField);
    // Now just to add event handlers to the buttons on the form...
    $("#ok").click(addOrUpdate);
    $("#cancel").click(cancel);
});