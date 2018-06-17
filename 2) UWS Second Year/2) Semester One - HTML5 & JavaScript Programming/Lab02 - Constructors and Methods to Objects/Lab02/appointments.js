/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/09/2012
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

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
    // I've shown two different versions of this.  Both work
    // and you can decide which is the easiest to follow.
    // One is commented out since we can only have one return statement.
    //return twoDigit(this.datetime.getHours()) + ":" + twoDigit(this.datetime.getMinutes());
    return this.datetime.toTimeString().substring(0, 5);
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
 */
var addAppointment = function(subject, description, date, time){
    a = new Appointment(subject, description, date, time);
    appointments.push(a);
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
 * and then updating the display so that the new appointment is visible.
 */
var add = function(){
    var subject = prompt("Enter a subject heading: "),
        description = prompt("Enter a brief description: "),
        date = prompt("Enter the date (e.g. Oct 10, 2012"),
        time = prompt("Enter the time of the appointment (e.g. 12:30)");
    addAppointment(subject, description, date, time);
    updateList();
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
    var addButton = document.getElementById("add");
    addButton.onclick = add;
    setInterval(checkDueAlerts, 60000);
};