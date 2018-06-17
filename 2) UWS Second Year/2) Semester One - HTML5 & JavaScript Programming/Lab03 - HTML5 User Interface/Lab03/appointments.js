/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/09/2012
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

var subjectField, descriptionField, dateField, timeField;

/**
 * This function is useful for padding a number out to be two digits long.
 * i.e. if input is 1, it will return "01"
 * @param v  - the input number
 * @return {string} - the number a sa string, padded out with a leading '0' if needed.
 */
function twoDigit(v){
    if(v.toString().length < 2){
        return "0"+v.toString();
    } else {
        return v.toString();
    }
}

/**
 * This is the Appointment constructor.  It will create a new appointment with the given
 * subject, description, date and time.
 * @param subject - a string
 * @param description - a string
 * @param date - a date string - e.g. "Mar 20, 2012", "2012-03-20"
 * @param time - a time string - e.g. "12:30", "17:00"
 * @constructor
 */
var Appointment = function(subject, description, date, time){
    this.subject = subject;
    this.description = description;
    this.datetime = new Date(date + " " + time);
    this.completed = false;
};

/**
 * This is our list of appointments.
 * @type {Array}
 */
var appointments = [];  // An array of appointments.

/**
 * Function will return true if an appointment's time has come or is overdue.
 * @return {Boolean}
 */
Appointment.prototype.isDue = function(){
    var now = new Date();
    if(this.datetime > now){
        return false;
    } else {
        return true;
    }
};

/**
 * Function will return the number of milliSeconds until the appointment is due.
 * @return {number}
 */
Appointment.prototype.whenDue = function(){
    return this.datetime - new Date(); /// milliSeconds
};

/**
 * This function returns the appointment as a text string.  It is quick and dirty,
 * so not suitable for a final app  useful for debugging purposes.
 * @return {String}
 */
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

/**
 * Return the date portion of the appointment date+time as a string.
 * @return {String}
 */
Appointment.prototype.getDate = function(){
    return this.datetime.toDateString();
};

/**
 * Return the time portion of the appointment date+time as a string.
 * @return {String}
 */
Appointment.prototype.getTime = function(){
    return twoDigit(this.datetime.getHours()) + ":" + twoDigit(this.datetime.getMinutes());
};

/**
 * This function returns a row of an HTML table containing the appointment
 * data.  A checkbox is substituted for the true/false value of the completed
 * member.  It would be useful to apply a function to this so that the user
 * checking on the checkbox would alter the completed flag.  I leave this as an
 * advanced exercise for the user.
 * @return {String}
 */
Appointment.prototype.tableRow = function(){
    var tr = "<tr><td>" + this.getDate() + "</td><td>" +
             this.getTime() + "</td><td>" + this.subject +
             "</td><td><input type='checkbox' value='" + this.completed + "'>" + "</td></tr>";
    return tr;
};

/**
 * This function adds a new appointment to the list, with values picked up
 * from the HTML input controls (text, textarea, date etc.)  Once an appointment
 * has been added, the HTML table showing the list of appointments should be
 * re-built.
 * @param subjField
 * @param descField
 * @param dateField
 * @param timeField
 */
var addAppointment = function(subjField, descField, dateField, timeField){
    a = new Appointment(subjField.value, descField.value, dateField.value, timeField.value);
    appointments.push(a);
};

/**
 * This function performs the useful task of clearing out the HTML
 * input controls, including re-setting the time select control to
 * show the nearest to the current time, and the date input to today's
 * date.
 */
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

/**
 * This function does the job of building a HTML table, using the list
 * of appointments.  Since the Appointment type has a tableRow() method,
 * this turns out to be pretty easy to do. Having built the necessary
 * HTML table, it then inserts this into the <div> in the document set
 * aside for that purpose.
 */
var updateList = function(){
    var tableDiv = document.getElementById("table"),
        table = "<table border='1'><thead><th>Date</th><th>Time</th><th>Subject</th><th>Completed</th></thead>";

    for(var i=0, j=appointments.length; i<j; i++){
        var appt = appointments[i];
        table += appt.tableRow();
    }
    // Now insert the rows into the table...
    table+="</table>";
    tableDiv.innerHTML = table;
};

/**
 * This function performs the overall task of adding a new appointment,
 * clearing out the input controls of the new appointment data and
 * then updating the display so that the new appointment is visible.
 */
var add = function(){
    addAppointment(subjectField, descriptionField, dateField, timeField);
    clearUI();
    updateList();
};

/**
 * This causes the input controls to be cleared and the appointments
 * table to be synchronized with the list.
 */
var cancel = function(){
    clearUI();
    updateList();
};

/**
 * A simple utility function to update the time input control
 * to show the nearest time, given 1/2 hour increments.  It will
 * select the nearest option *after* the current time.
 * @param timeField
 */
var selectNearestTime = function(timeField){
    // Now select the nearest time.
    var t = new Date(),
        n = t.getHours()*2 + Math.floor(t.getMinutes() / 30);
    timeField.options[n].selected = true;
};

/**
 * This resets the date input control to today's date.
 */
var setToday = function(){
    if(dateField.valueAsDate){
        dateField.valueAsDate = new Date();
    } else {
        dateField.value = (new Date()).toDateString();
    }
};

/**
 * This updates an empty select control to show 24 hours worth of
 * time options, in 1/2 hour increments. - e.g. 12:00, 12:30 etc.
 * @param tf
 */
var fillTimeList = function(tf){
    var hours, minutes;
    for(hours=0; hours<24; hours+=1){
        var hh = hours.toString();
        if(hh.length < 2){
            hh = "0"+hh;
        }
        for(minutes=0; minutes<60; minutes+=30){
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

/**
 * This function can be called to show which appointments are now due.
 */
function checkDueAlerts(){
    for(var i=0; i<appointments.length; i++){
        if(appointments[i].completed && appointments[i].isDue()){
            // This is a fudge for now.  Should really set an advance alarm
            // e.g. for 15 minutes from now.  Appointments should only be
            // marked as completed if the user says so.
            appointments[i].completed = false;
            alert(appointments[i].subject + "\n" + appointments[i].description);
        }
    }
}

/**
 * When the page loads, we needs to set up various things.
 * e.g. assign HTML elements to appropriate JS variables so
 * we can manipulate them, assign functions to events etc.
 * The final act is to set up an interval timer to check
 * for appointment alarms.
 */
window.onload = function(){
    subjectField = document.getElementById("subject");
    descriptionField = document.getElementById("description");
    dateField = document.getElementById("duedate");
    timeField = document.getElementById("duetime");
    fillTimeList(timeField);
    okButton = document.getElementById("ok");
    okButton.onclick = add;
    cancelButton = document.getElementById("cancel");
    cancelButton.onclick = cancel;
    clearUI();
    setInterval(checkDueAlerts, 60000);
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
