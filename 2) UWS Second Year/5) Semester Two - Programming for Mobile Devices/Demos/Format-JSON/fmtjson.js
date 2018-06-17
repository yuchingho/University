/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 04/03/2013
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function(){
    $("#loadlist").bind('click', function() {
        var user = $("#email").val();
        if(user.length > 0) {
            getToDoList(user);
        } else {
            confirm("Email Address", "You must enter your email address", null);
        }
    });
});

/**
 *
 * @param dialogHeading - text to go into the page heading
 * @param dialogText    - the message of the dialog
 * @param callbackFunction - If a function is to be called on "OK", this is it
 * @param button1Text   - text for the first button - OK by default
 * @param button2Text   - text for the second button - none by default
 */
function confirm(dialogHeading, dialogText, callbackFunction, button1Text, button2Text) {
    $("#confirm .confirmHead").text(dialogHeading);
    $("#confirm .confirmMessage").text(dialogText);
    if(button1Text) {   // button1 is the text for the first button...
        $("#confirmOK").text(button1Text)
            .bind('click', function() {
                // Only call the callback function is one is provided.
                if(callbackFunction) {
                    callbackFunction();
                }
                $(this).off("click.confirm");
        });
    } else {    // If no text is provided for button 1, use the default (see HTML)
        $("#confirmOK").bind('click', function() {
                if(callbackFunction) {
                    callbackFunction();
                }
                $(this).off("click.confirm");
         });
    }
    if(button2Text){  // If there is text for the second button, use it
        $("#confirmCancel").text(button2Text);
    } else { // otherwise hide the button...
        $("#confirmCancel").hide();
    }
    $.mobile.changePage("#confirm");
}

function formatItem(tdi){
    // This function breaks down the tdi (to-do-item) and creates HTML
    // elements that reflect their values.  These then get put together
    // to form the returned value.
    // Start by declaring some variables to hold (string) HTML elements.
    // Note each item will be a <li> element containing a small <table>...
    var html = "<li><table>", check, datetime, desc, item, priority;
    // We can style the table to make cells widths appropriate to their content.
    // Note that this involved a lot of trial and error...
    html += "<col width='20px' /><col width='100px' />"

    // This will create a checkbox element to reflect the 'completed' property...
    if(tdi.completed === "True") {
        // A checkbox is checked if it has a 'checked' property
        check = "<td style='width:16px'><input type='checkbox' checked='checked'/></td>";
    } else {
        // So this one is unchecked...
        check = "<td><input type='checkbox' /></td>";
    }

    // The description item is easy - just a paragraph inside a table0data (td) element...
    desc = "<td><p>" + tdi.description + "</p></td>";

    // The default format for the due-date is fine.  However, giving it a style
    // of 'aside' makes it appear more neatly...
    datetime = "<td><p class='ui-li-aside'><strong>" + tdi.due_date + "</strong></p></td>";

    // Priority will be a red (top), yellow (middle) or green (low) blob.  The
    // three src files are in the same folder as the HTML...
    switch(tdi.priority){
        // Note the three priority elements have styles defined for them (in styles.css)...
        case '1':
            priority = "<td><img src='pr1.png'/></td>";
            break;
        case '2':
            priority = "<td><img src='pr2.png'/></td>";
            break;
        case '3':
            priority = "<td><img src='pr3.png'/></td>";
            break;
    }

    // The item component is a subject heading - use a minor heading style...
    item = "<td><h1>" + tdi.item + "</h1></td>";

    // Now put all these together inside table row elements.  This was the most trial-and-error
    // part of the development...
    html += "<tr>" + check + item + "</tr><tr>" + priority + desc + "</tr></table>" + datetime + "</li>";
    return html;
}

/*
function formatItem(item){
    var html = "<li>";
    if(item.checked) {
        html += "<input type='checkbox' value='on'/>";
    } else {
        html += "<input type='checkbox' value='off'/>";
    }
    html += "<b>" + item.item + "</b>";
    html += "<br/><p>" + item.description + "</p>";
    html += "<br/><p>" + item.due_date + "</p>";
    html += "</li>";
    return html;
}
*/

function getList(itemList){
    list = itemList;
    var i, item, strList = "";
    for (i = 0; i < itemList.length; i += 1) {
        item = itemList[i];
        strList += formatItem(item); //"<li>" + item.item + ": " + item.due_date.toString() + "</li>";
    }
    $.mobile.changePage("#listPage", {transition: "flip"});
    $("#list").html(strList).listview('refresh');
}

function getToDoList(listID) {
    $.ajax({
        type: 'GET',    // This is the HTTP method that will be used.
        url: "http://mcm-to-do-json.appspot.com/json?user=" + listID,   // The email of the list's user.
        async: true,
        jsonpCallback: 'getList',   // This is the callback function we want the response to call.
        contentType: "application/json", // Header for format of data expected
        dataType: 'jsonp',      // Expected return type
        success: function(json) {
            /* remove the comment lines to use this function.
             * it is not needed here because a jsonpCallback was defined.
             * It shows how to access the returned data...
             for(var i = 0; i < json.length; i += 1) {
             alert(json[i].item);
             }
             alert(list);        // Just to indicate
             */
        },
        error: function(e) {
            alert(e.message);
        }
    });
}
