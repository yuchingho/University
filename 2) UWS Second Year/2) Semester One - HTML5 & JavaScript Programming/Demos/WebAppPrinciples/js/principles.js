/**
 * Created by alistairmcmonnies on 30/10/2014.
 */

/*
Global variables.  We need these here to make it possible to access the values
from more than one function.  A general principle is that if we only use a variable
inside one function, it should be declared locally - i.e. using a var statement inside
the function.
 */
var nameField,      // This single variable will be used to collect names entered
                    // in the user-interface.  I call it a 'field' here because
                    // the name distinguishes it from other variables - i.e. this
                    // variable refers to an input field on the HTML U-I
    nameList = [];  // This will hold the whole list of names eventually.

/*
We need to make sure that any input fields are accessible from Javascript code,
by attaching them to JS variables.  The only sensible time to do this is after
the whole of the HTML document has been read into the browser.  The window.onload
event is called immediately after all of the HTML file has been read and parsed,
so that is a good place to make references to input fields and other user-interface
elements (e.g. action buttons, drop down lists etc.
 */
window.onload = function() {
    // Assign the HTML input element to our pre-declared field variable...
    nameField = document.getElementById("name");
    // we can now access the contents of that text box using 'nameField.value'
    // when we need it.  Because nameField is a global variable, it can be accessed
    // in ANY code in this application - inside functions, inside object methods etc.

    // The other thing we need to do is to attach our action elements (the two buttons
    // on the page in this case) to code that responds to events on them.  For buttons,
    // this is usually the click event.  We could use more global variables to access
    // the buttons, but it is not necessary...
    document.getElementById("ok").onclick = function() {
        doOkPress();
    };

    document.getElementById("cancel").onclick = function() {
        // Code in here executes when the Cancel button is clicked.
        // In this case, I'm simply clearing out the input field without
        // doing anything with the text the user typed into it.
        // In the more general case, I'd want to minimise the amount of code
        // needed to do that, so I would define a clearInputFields() function
        // that I could call from here and fro the ok-button's click event.
        nameField.value = "";
    };

    // As a final bit of polish, I can add another event handler to the input box so that
    // when the user presses Enter, the OK button is pressed.
    nameField.onkeyup = function(evt) {
        if (evt.keyCode === 13) {
            doOkPress();
        }
    };
};

/*
This function does the work of adding user entries to the database/data structure.
In this case, it is a single name - in other it could be a whole appointment or
something else.
Typically, once I've added a new object to a data structure (our nameList array),
I would want to update the screen to show the new entries, so a function call to
a function that does this is needed...
 */
function addNameToList(name){
    nameList.push(name);
    updateOnScreenNameList();   // A bit long for a name, but it's unambiguous.
};

function updateOnScreenNameList(){
    // As this is a sample collection of names, this code can generate a simple
    // list of <li> elements to insert as the content of a <ul> element.
    // With a more complex collection (e.g. appointments), it is good practice to
    // have the collected type (Appointment) able to return a table row for an element.
    // We could then iterate over the whole list, building up a string that contains
    // all of the mark-up for table rows, and insert all of the mark-up into a <table>
    // element.
    // START by defining the local variables we'll need...
    var html = "", index;
    // Now iterate over the whole list, adding mark-up for each list item...
    for (index = 0; index < nameList.length; index += 1){
        html += "<li>" + nameList[index] + "</li>";
    }
    // Finally, get a reference to the "<ul>" element on the page and insert the
    // HTML code generated above as its .innerHTML
    // .innerText is only used where we want to add plain text as the content of an element.
    document.getElementById("list").innerHTML = html;
    // Note - there is an HTML <ul> element at the bottom of the page...
}

// This is the function that actually does the stuff you want to happen
// when the Ok button is clicked OR the Enter key is pressed. (We've defined
// it as a separate function because there are two events we want it to be
// called from).
function doOkPress() {
    // Code in here executes when the ok button is clicked.
    // A good pattern that I find makes de-bugging easier is to call a
    // function, passing in any necessary values from field variables...
    addNameToList(nameField.value);
    // It is also good practice to return input fields to a good default
    // state once you've collected the values from them...
    nameField.value = "";
}

/*
Here's a short exercise.  This function will clear out the whole list.
   1. Figure out what it does to have that effect.
   2. Add a suitable button to the user-interface (e.g name it Clear,
      and give it the ID 'clear')
   3. Add code to window.onload so that the button calls this function.
 */
function clearList() {
    document.getElementById("list").innerHTML = "";
}

