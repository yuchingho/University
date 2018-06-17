/**
 * Created by alistair on 13/03/2014.
 */

/* There is no need to test for the "touch" interface, since this is supported by jQuery Mobile.
   Otherwise (i.e. without jQM) you would need to include a test for it.
 */

var op; // This is the output textarea.  Declaring it here
        // saves lots of expensive look-ups.

function clear() {
    "use strict";
    op.text("");
}

function displayMessage(msg) {
    "use strict";
    op.append(msg + '\n');
    // This makes sure the message is cleared in a little while.
    window.setTimeout(clear, 2000);
}

function set_page_sizes() {
    "use strict";
    op.width($(window).width() - 50);
    op.height(100);
    var canvas = $("#canvas").get(0);
    canvas.width = $(window).width() - 50;
    canvas.height = $(window).height() - ($('header').outerHeight() || 0) - ($('footer').outerHeight() || 0) - 250;
}

/**
 * Note - 'pageinit' is the preferred initialization
 * event in jQuery Mobile.
 */
$(document).bind('pageinit', function () {
    "use strict";
    $("#date").text("Date: " + new Date().toDateString());
    /*
     These controls are on the Touch page...
     */
    op = $("#output");
    op.text("");
    set_page_sizes();
    $("#target").on("tap", function (ev) {
        displayMessage("TAP");
        ev.preventDefault(ev);
    }).on("taphold", function (ev) {
            displayMessage("TAPHOLD");
            ev.preventDefault();
        }).on("swipe", function (ev) {
            displayMessage("SWIPE");
            ev.preventDefault();
        }).on("swipeleft", function (ev) {
            displayMessage("SWIPELEFT");
            ev.preventDefault();
        }).on("swiperight", function (ev) {
            displayMessage("SWIPERIGHT");
            ev.preventDefault();
        });
});