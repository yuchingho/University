/**
 * Created by alistair on 29/12/2014.
 */

var ratesURL = "http://api.fixer.io/latest?base=",
    rateList = {};

function getRates(symbol) {
    // Get the exchange rates from the service.
    $.ajax({
        dataType: 'jsonp',
        url: ratesURL + symbol,
        success: function(data) {
            // Get a list of exchange rates from the service...
            rateList = data;
            // Stash these rates for offline use...
            localStorage.setItem("rateList", JSON.stringify(data));
            localStorage.setItem("baseRate", rateList.base);
            // and update the U-I...
            $("#baseList").val(rateList.base).selectmenu('refresh');
            $("#basesymbol").text(symbol);
        },
        error: function(err) {
            // Retrieve the most recently saved list of exchange rates...
            rateList = JSON.parse(localStorage.getItem("rateList", null));
            setBaseRate(rateList.base);
            $("#updated").text("Last updated: " + rateList.date);
            if(rateList) {
                alert("Unable to get current rates.\nUsing rates from " +
                rateList.date);
            } else {
                alert("Unable to get exchange rates.\n" +
                "Please check network connection.");
            }
        }
    });
}

function getSymbolList() {
    var options = "";
    for(symbol in symbolList) {
        options += "<option value='" + symbol + "'>" + symbolList[symbol] + "</option>"
    }
    $('select').html(options).selectmenu('refresh');
}

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

$(document).ready(function() {
    getSymbolList();
    getRates("GBP");
    $("#convert").on('click', baseToSelected);
    $("#convert-back").on('click', selectedToBase);
    $("#currency").on('change', baseToSelected);
});