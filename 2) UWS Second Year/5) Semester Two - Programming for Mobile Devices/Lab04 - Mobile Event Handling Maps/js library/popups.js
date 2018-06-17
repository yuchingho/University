/**
 * Created by alistair on 29/09/2014.
 * Revised 05/01/2015 - fixed error that caused a new event handler
 * to be added to 'close' buttons on each call, resulting in multiple
 * close-function calls.
 * Revised 17/01/2015 - fixed problem where the screen overlay did not
 * fade out if the popup was closed by clicking on the background.
 */

$(document).ready(function() {
    /*
        Here we need to initialize the whole popups subsystem.
        This means we need to load the pop-up page definitions 
        into the current page (which should be the main page) of
        the app.
        Because the whole <body> element must be in place before this
        code is executed, it is placed in $(document).ready().  It should not
        conflict with other $(document).ready() functions.
        ***THIS CODE MUST BE LINKED BEFORE ANY CODE THAT USES IT***
     */
    var __overlay = "<style> html, body {width  : 100%;height : 100%;} #overlay-back {position:absolute;top:0;left:0;width:100%;" +
                   "height:100%;background:#000;opacity:0.6;filter:alpha(opacity=60);z-index:5;display:none;}</style>" +
                   "<div id='overlay-back'></div>" ,
        __popupalert = "<div data-role='popup' id='__jqm_popupAlert' data-theme='a' class='ui-content' data-position-to='window'>" +
                     "<h1 id='__jqm_popup_alertHead'>Alert</h1>" +
                     "<p id='__jqm_popup_alertText'>Sample Alert Text</p>" +
                     "<a href='#' data-role='button' data-rel='back' data-icon='delete' data-iconpos='notext' class='ui-btn-right btn_close'></a>" +
                     "</div>",
        __popupconfirm = "<div data-role='popup' id='__jqm_popupConfirm' data-theme='a' class='ui-content' data-position-to='window'>" +
                       "<h1 id='__jqm_popup_confirmHead'>Confirm</h1>" +
                       "<p id='__jqm_popup_confirmText'>Text text text text text</p>" +
                       "<div data-role='navbar'><form>" +
                       "<ul data-role='listview' data-inset='true'>" +
                       "<li><a href='#' id='__jqm_popup_confirmOk' data-rel='back' data-role='button' class='btn_close'>Ok</a></li>" +
                       "<li><a href='#' id='__jqm_popup_confirmCancel' data-rel='back' data-role='button' class='btn_close'>Cancel</a></li>" +
                       "</ul></form></div></div>",
        __popupprompt = "<div data-role='popup' id='__jqm_popupPrompt' data-theme='a' class='ui-content' data-position-to='window'>" +
                     "<form><h1 id='__jqm_popup_promptHead'>Prompt</h1><label id='__jqm_popup_promptLabel' for='__jqm_popup_promptText'>Label</label>" +
                     "<textarea rows='5' cols='30' id='__jqm_popup_promptText'></textarea><div data-role='navbar'>" +
                     "<ul data-role='listview' data-inset='true'><li><a href='#' id='__jqm_popup_promptOk' data-rel='back' data-role='button' class='btn_close'>Ok</a></li>" +
                     "<li><a href='#' id='__jqm_popup_promptCancel' data-rel='back' data-role='button' class='btn_close'>Cancel</a></li></ul></div></form></div>",
        __popuplogin = "<div data-role='popup' id='popupLogin' data-theme='a' class='ui-content' data-position-to='window'><h1>Log-In</h1>" +
                       "<form><label for='userid'>User:</label><input type='text' size='30' id='userid'/><label for='passwordid'>Password:</label>" +
                       "<input type='password' id='passwordid' size='20'/><div data-role='navbar'><ul data-role='listview' data-inset='true'>" +
                       "<li><a href='#' id='okid' data-rel='back' data-role='button' class='btn_close'>Ok</a></li><li>" +
                       "<a href='#' id='cancelid' data-rel='back' data-role='button' class='btn_close'>Cancel</a></li></ul></div></form></div>";

    // Append these to the current <body> element...
    $('body').append("<div>" + __overlay + __popupalert + __popupconfirm + __popupprompt + __popuplogin + "</div>")
             .trigger("create");
});

function popupAlert(head, text) {
    $("#__jqm_popup_alertHead").text(head);
    $("#__jqm_popup_alertText").text(text);
    $('#overlay-back').fadeIn(500);
    $("#__jqm_popupAlert").popup("open").on("popupafterclose", function(evt) {
        $('#overlay-back').fadeOut(500);
    });
}

function popupConfirm(head, text, yesFunction, noFunction) {
    $("#__jqm_popup_confirmHead").text(head);
    $("#__jqm_popup_confirmText").text(text);
    if(yesFunction) {
        $("#__jqm_popup_confirmOk").bind('click', function(evt) {
            yesFunction();
            evt.target.off('click');
        });
    }
    if(noFunction) {
        $("#__jqm_popup_confirmCancel").bind('click', function(evt) {
            noFunction();
            evt.target.off('click');
        });
    }
    $('#overlay-back').fadeIn(500);
    $("#__jqm_popupConfirm").popup("open").on("popupafterclose", function(evt) {
        $('#overlay-back').fadeOut(500);
    });
}

/**
 * Possible refactor - use an object to allow modification of parameters and access to last
 * response.  This will be done in the next major upgrade of this code.
 *
var confirm = {
    call: function (head, text, yesFunction, noFunction) {
        $("#__jqm_popup_confirmHead").text(head);
        $("#__jqm_popup_confirmText").text(text);
        if (yesFunction) {
            $("#__jqm_popup_confirmOk").bind('click', yesFunction);
        }
        if (noFunction) {
            $("#__jqm_popup_confirmCancel").bind('click', noFunction);
        }
        $("#__jqm_popupConfirm").popup("open");
    },
    response: undefined,
    get: function () {
        return this.response;
    }
};
 */

function popupPrompt(head, prompt, okFunction, cancelFunction) {
    $("#__jqm_popup_promptHead").text(head);
    $("#__jqm_popup_promptLabel").text(prompt);
    $("#__jqm_popup_promptText").val("");
    if(okFunction) {
        $("#__jqm_popup_promptOk").on('click', function (evt) {
            okFunction();
            evt.target.off('click');
        });
    }
    if(cancelFunction) {
        $("#__jqm_popup_promptCancel").on('click', function(evt) {
            cancelFunction();
            evt.target.off('click');
        });
    }
    $('#overlay-back').fadeIn(500);
    $("#__jqm_popupPrompt").popup("open").on("popupafterclose", function(evt) {
        $('#overlay-back').fadeOut(500);
    });
    $("#__jqm_popup_promptText").focus();
}

function getPromptValue() {
    return $("#__jqm_popup_promptText").val();
}


function popupLogin(okFunction, cancelFunction) {
    $("#userid").val("");
    $("#passwordid").val("");
    if(okFunction) {
        $("#okid").off('click').on('click', function(evt) {
            okFunction();
            evt.target.off('click');
        });
    }
    if(cancelFunction) {
        $("#cancelid").off('click').on('click', function(evt) {
            cancelFunction();
            evt.target.off('click');
        });
    }
    $('#overlay-back').fadeIn(500);
    $("#popupLogin").popup("open").on("popupafterclose", function(evt) {
        $('#overlay-back').fadeOut(500);
    });
    $("#userid").focus();
}

function getLoginValues() {
    return {
        user: $("#userid").val(),
        password: $("#passwordid").val()
    };
}