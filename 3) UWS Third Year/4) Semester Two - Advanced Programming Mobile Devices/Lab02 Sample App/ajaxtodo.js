/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 17/02/2013
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */

// The getList() function below assigns the web-service return to this...
var list;

// The standard ready() function for jQuery.  This binds a call to getToDoList()
// to the button whose id is 'getData'.  It also sets up 'busy' indicators for
// AJAX calls - since these are asynchronous, a call can take some time.  Best
// always to let the user know this.  You can cut all of the ajaxSetup() code if
// you don't need it.
$(document).ready(function () {
    $("#getData").bind('click', function() {
        getToDoList("AMcMonnies@gmail.com");
    });
    // Set up 'busy' indicators for AJAX calls...
    $.ajaxSetup({
        beforeSend:function(){
            // show a 'waiting' indicator (platform specific)..
            $("#loading").show();
        },
        complete:function(){
            // hide the waiting indicator...
            $("#loading").hide();
        }
        /*
        Note - if you do not want the waiting indicator to show, add this
         to an AJAX call...
             $.ajax({
             global: false,    // Just need to set thi attribute to false.
             // All the rest of the ajax call.
             });
         */
    });
});

// This is the function that is passed as a callback to the web-service.
// The $.ajax function defined in getToDoList() (below) will arrange to
// call this function.  However, it also arranges to get the results inside
// its own 'success' callback definition.  You can choose to use either.
function getList(itemList){
    list = itemList;
    var i, item, strList = "";
    for (i = 0; i < itemList.length; i += 1) {
        item = itemList[i];
        strList += "<li>" + item.item + ": " + item.due_date.toString() + "</li>";
    }
    $.mobile.changePage("#listPage", {transition: "flip"});
    $("#list").html(strList).listview('refresh');
}

// Here be dragons - or, at least, a jQuery organised AJAX call.
// $.ajax() sets up all the elements needed to make a call to a
// web-resource to collect data.  This involves creating an XHR
// (XMLHTTPRequest) object, assigning an HTTP method (GET, here),
// sending out the request and assigning callback functions to deal
// with the two possible forms of response (success or error).  In
// addition, it sets the contentType header for the HTTP call, indicates
// the expected data type from the response (json here), and organises
// the call-type as 'async', meaning, don't wait for a response (which
// is why we need success and error functions).
// In this case (for example purposes only), a jsonpCallBack function
// has also been assigned (although since 'success' already does this,
// it is redundant.  You should use one or other - not both.
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

