/**
 * Created by alistairmcmonnies on 19/03/2014.
 */

var countOfRecords = 0;

function showData() {
    // Always start by opening the database...
    var db = openDatabase('people', '1.0', 'People Database', 2 * 1024);
    // Then create a transaction that will do the work...
    db.transaction( function(tx) {
        tx.executeSql('SELECT * FROM people', [], function (tx, results) {
            // This should return a number of rows, so, clear the listview...
            $("#results").html("");
            // Find out how many rows...
            var len = results.rows.length, i;
            // Iterate over them...
            for (i = 0; i < len; i += 1 ) {
                var item = results.rows.item(i);
                // Make a <li> for each...
                $("#results").append("<li>" + "<p><b>" + item.id + ":" + item.name + "</b></p><p>" + item.email + "</p>" + "</li>");
            }
            $("#message").text("");
            $("#count").text("Number of Records: " + len);
            countOfRecords = len;
        }, null);
    });
}

function createDB() {
    // Parameters here are..
    // database_name, database_version, descriptive_name, initial_size (in bytes)
    var db = openDatabase('people', '1.0', 'People Database', 2 * 1024);
    db.transaction( function (tx) {
        // This is pretty much standard SQL...
        tx.executeSql('CREATE TABLE IF NOT EXISTS people (id unique, name, email)');
        tx.executeSql('INSERT INTO people (id, name, email) VALUES (1, "Fred Flintstone", "fred@bedrock.com")');
        tx.executeSql('INSERT INTO people (id, name, email) VALUES (2, "Wilma Flintstone", "wilma@bedrock.com")');
        tx.executeSql('INSERT INTO people (id, name, email) VALUES (3, "Barney Rubble", "barney@bedrock.com")');
        tx.executeSql('INSERT INTO people (id, name, email) VALUES (4, "Betty Rubble", "betty@bedrock.com")');

        $("#message").text("people table created and 4 rows inserted");
        setTimeout(showData, 1000);
    });
}

function addRecord(id, name, email) {
    var db = openDatabase('people', '1.0', 'People Database', 2 * 1024);
    db.transaction( function (tx) {
        // Note the use of ?, ?, ? to define values to be inserted.  The [] array in the next parameter
        // gives the actual values to be used (the parameters to this function)...
        tx.executeSql('INSERT INTO people (id, name, email) VALUES (?, ?, ?)', [id, name, email], function (tx, res) {
            console.dir(res);
        });
    });
    return "OK";
}

function doAddRecord() {
    // Really just pick the info off the dialog/form, check them and
    // send them to the addRecord(0 function, whose job is to update the database...
    var n = $("#name").val(),
        e = $("#email").val(),
        id = countOfRecords + 1,
        res = "Ouch - it never worked!";
    if (n !== "" && e !== "") {
        res = addRecord(id, n, e);
    }
}

$(document).on('pageshow', function () {
    if (openDatabase in window) {
        $("#message").text("WebDB is NOT supported in your device's browser.");
    } else {
        $("#message").text("WebDB is supported in your device's browser.");
        // This program will create a new database every time it is run.
        // If you want to work with a database over time (which is normal),only do this if
        // the database cannot be opened (i.e. does not exist)...
        setTimeout(createDB, 1000);
    }
    $("#add").on('click', function () {
        $("#rid").text("New ID: " + (countOfRecords + 1));
    });
    $("#doadd").on('click', function() {
        // Add the data from the form...
        doAddRecord();
        // Display the whole set of data...
        showData();
        // And go back to the main page...
        $.mobile.changePage("#main");
    });
});