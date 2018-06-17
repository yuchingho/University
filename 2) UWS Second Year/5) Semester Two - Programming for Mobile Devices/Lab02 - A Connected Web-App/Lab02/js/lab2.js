/**
 * Created by alistair on 21/12/2014.
 */

    /**
     * ratesURL: string.
     * This is the service URL that - a base rate must be added.
     */
    ratesURL = "http://api.fixer.io/latest?base=",
    /**
     * rateList
     * @type {object}
     * An object that will contain the full set of data returned from the service.
     * Structure as follows:
     *    {
     *       base: (@type {string}: base symbol),
     *       date: (@type {string}: date last retrieved),
     *       rates: (@type {object}: a set of rate-symbols:values)
     *    }
     */
    rateList = {},
    /**
     * A cached version of the current base rate symbol.
     * @type {string}
     */
    defaultBase = "GBP",

    /**
     * A cached version of the most recent conversion target currency.
     * @type {string}
     */
    recentCurrency = "EUR";

/**
 * A cross-origin AJAX call (i.e. returns results in JSONP format).
 */
function getRates(symbol) {
    // Get the exchange rates from the service.
    $.ajax({
        dataType: 'jsonp',
        url: ratesURL + symbol,
        success: function(data) {
            // Get a list of exchange rates from the service...
            rateList = data;
            // Stash these for offline use...
            localStorage.setItem("rateList", JSON.stringify(data));
            localStorage.setItem("baseRate", rateList.base);
            // and update the U-I...
            $("#baseList").val(rateList.base).selectmenu('refresh');
            $("#basesymbol").text(symbol);
            $("#updated").text("Last updated: today");
        },
        error: function(err) {
            // Retrieve the most recently saved list of exchange rates...
            rateList = JSON.parse(localStorage.getItem("rateList", null));
            setBaseRate(rateList.base);
            $("#updated").text("Last updated: " + rateList.date);
            if(rateList) {
                alert("Unable to get current rates.\nUsing rates from " + rateList.date);
            } else {
                alert("Unable to get exchange rates.\nPlease check network connection.");
            }
        }
    });
}

/**
 * SOME ERROR MANAGEMENT STUFF.
 * 1. showError() adds an .error class to the input field
 *    (sets its background colour to highlight)
 * 2. clearError() removes the .error class
 * 3. checkSelected() checks whether an input has the .error class.  If it does
 *    it removes the class and clears out its content.
 */
function showError(inputField) {
    inputField.addClass("error");
}

function clearError(inputField) {
    inputField.removeClass("error");
}

function checkSelected() {
    if($(this).hasClass("error")) {
        clearError($(this));
        $(this).val("");
    }
}

/**
 * Convert from the base currency to the selected one.
 */
function baseToSelected() {
    var amount = parseFloat($("#amount").val(), 10), convertedAmount = 0.0;
    if(isNaN(amount)){
        showError($("#amount"));
    } else {
        $("#amount").val(amount.toFixed(2));
        convertedAmount = amount * rateList.rates[$("#currency").val()];
        $("#selected").val(convertedAmount.toFixed(2));
    }
}

/**
 * Convert from the selected currency to the base one.
 */
function selectedToBase() {
    var amount = parseFloat($("#selected").val(), 10), baseAmount = 0.0;
    if(isNaN(amount)){
        showError($("#selected"));
    } else {
        $("#selected").val(amount.toFixed(2));
        baseAmount = amount / rateList.rates[$("#currency").val()];
        $("#amount").val(baseAmount.toFixed(2));
    }
}

/**
 * Called when the currency <selection> is changed.
 */
function changeSelected() {
    setRecentCurrencySelection($("#currency").val());
    baseToSelected();
}

/**
 * Puts the list of symbols/countries/currencies into <select> controls.
 */
function getSymbolList() {
    var options = "";
    for(symbol in symbolList) {
        options += "<option value='" + symbol + "'>" + symbolList[symbol] + "</option>"
    }
    $('select').html(options).selectmenu('refresh');
}

/**
 * Called when a change of base-rate is made in the config panel.
 * @param symbol: @type {string}
 */
function changeBaseRate() {
    var symbol = $("#baseList").val();
    $("#basesymbol").text(symbolList[symbol]);
    getRates(symbol);
    $("#amount").val("");
    $("#selected").val("");
}

function getBaseRate() {
    if(localStorage.rateList) {
        return localStorage.baseRate
    } else {
        return defaultBase;
    }
}

function setRecentCurrencySelection(symbol) {
    localStorage.setItem("recent", symbol);
    $("#currency").val(symbol).selectmenu('refresh');
}

/**
 * Start-up function.
 *
 */
$(document).ready(function() {
    // Initialize values in app...
    getSymbolList();
    getRates(getBaseRate());
    setRecentCurrencySelection(localStorage.recent || recentCurrency);

    // Set up event handlers for user-interactions...
    $("#baseList").on('change', changeBaseRate);
    $("#amount").on("blur", baseToSelected);
    $("#amount").on("focus", checkSelected);
    $("#selected").on("blur", selectedToBase);
    $("#selected").on("focus", checkSelected);
    $("#currency").on("change", changeSelected);
    $("#convert").on("click", baseToSelected);
    $("#convertBack").on("click", selectedToBase);
});
