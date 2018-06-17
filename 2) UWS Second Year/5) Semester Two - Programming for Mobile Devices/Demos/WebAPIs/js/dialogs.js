/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 14/10/2013
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
// Below is the list of global variables that jsLint will tolerate.  $ is jQuery,
// jqmXXX are functions defined at the bottom of the page (jsLint prefers to see them before use.
/*globals $, jqmConfirm, jqmPrompt, jqmAlert, getPromptValue*/

/**
 * When the document is ready, bind calls to the functions to be tested to <a> buttons on the home page.
 */
$(document).ready(function () {
    "use strict";

    // HTML definitions of dialog box elements...
    var alertDialog =       '<div data-role="dialog" id="__jqm_alert_dialog"><div data-role="header">' +
                            '<h1 id="__jqm_alert_dialogHeader"></h1>' +
                            '</div><div data-role="content"><p id="__jqm_alert_dialogContent"></p></div></div>',

        confirmDialog =     '<div data-role="dialog" data-close-btn="none" id="__jqm_confirm_dialog">' +
                            '<div data-role="header"><h1 id="__jqm_confirm_dialogHeader"></h1></div>' +
                            '<div data-role="content"><p id="__jqm_confirm_dialogContent"></p></div>' +
                            '<div data-role="footer" data-position="fixed">' +
                            '<a href="#" id="__jqm_confirm_dialogOk" data-rel="back" data-icon="check">Yes</a>' +
                            '<a href="#" id="__jqm_confirm_dialogCancel" data-rel="back" data-icon="delete">No</a>' +
                            '</div></div>',

        inputBox =          '<div data-role="dialog" id="__jqm_prompt_dialog"><div data-role="header">' +
                            '<h1 id="__jqm_prompt_dialogHeader">Header</h1></div><div data-role="content">' +
                            '<!--<p id="__jqm_prompt_dialogContent">Question</p>--><label for="__jqm_prompt_dialogInput" id="__jqm_prompt_dialogContent">' +
                            '</label><input type="text" size="20" id="__jqm_prompt_dialogInput" /></div>' +
                            '<div data-role="footer" data-position="fixed">' +
                            '<a href="#" id="__jqm_prompt_dialogOk" data-rel="back" data-icon="check">OK</a>' +
                            '<a href="#" id="__jqm_prompt_dialogCancel" data-rel="back" data-icon="delete">Cancel</a></div></div>';

    // Append these to the current <body> element...
    $('body').append(alertDialog + confirmDialog + inputBox);

    // Make sure the input box in the 'prompt' dialog has the focus...
    $("#__jqm_prompt_dialog").bind('pageshow', function () {
        $("#__jqm_prompt_dialogInput").val("").select().focus();
    });
});

/**
 * To display an alert message, simply call the function passing a header and the message.
 * @param header - a short text string that will appear in the dialog header
 * @param message - the full message you wish to display.
 */
function jqmAlert(header, message) {
    "use strict";
    $("#__jqm_alert_dialogHeader").text(header);
    $("#__jqm_alert_dialogContent").text(message);
    $.mobile.changePage("#__jqm_alert_dialog");
}

/**
 * To ask the user to confirm something (OK or Cancel), call the function with these parameters
 * @param header - the text in the heading
 * @param question - the question to confirm - shud have an OK/Cancel Yes/No answer,
 * @param okFunction - the function to execute if the answer is OK
 * @param cancelFunction - the function to execute if the answer is Cancel (can be omitted).
 */
function jqmConfirm(header, question, okFunction, cancelFunction) {
    "use strict";
    $("#__jqm_confirm_dialogHeader").text(header);
    $("#__jqm_confirm_dialogContent").text(question);
    if (okFunction) {
        $("#__jqm_confirm_dialogOk").bind('click', okFunction);
    }
    if (cancelFunction) {
        $("#__jqm_confirm_dialogCancel").bind('click', cancelFunction);
    }
    $.mobile.changePage("#__jqm_confirm_dialog");
}

/**
 * To ask the user to enter some data, call this function with...
 * @param header - a short title
 * @param question - the request (e.g. what is your name)
 * @param okFunction - the function to call if the user presses OK
 * @param cancelFunction - the function to call if the user presses cancel (can be omitted)
 */
function jqmPrompt(header, question, okFunction, cancelFunction) {
    "use strict";
    $("#__jqm_prompt_dialogHeader").text(header);
    $("#__jqm_prompt_dialogContent").text(question);

    if (okFunction) {
        $("#__jqm_prompt_dialogOk").bind('click', okFunction);
    }
    if (cancelFunction) {
        $("#__jqm_prompt_dialogCancel").bind('click', cancelFunction);
    }
    $.mobile.changePage("#__jqm_prompt_dialog");
}

function getPromptValue() {
    "use strict";
    return $("#__jqm_prompt_dialogInput").val();
}

