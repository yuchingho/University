/**
 * Created with WebStorm.
 * User: alistair
 * Date: 16/03/2014
 * Time: 19:43
 */

// First we need some global variables...
var db = null,          // the database object...
    dbName = "MyDB",    // the name we use for it...
    dbVersion = 5,      // the current version number (good for upgrades)...
    countOfRecords;     // and the number of records in or data-store.

/**
 * Function returns a dataStoreItem (i.e. a record of data) organised as
 * a <li> element, for display purposes.
 * @param dataStoreItem     - a database record
 * @returns {string}        - the formatted record
 */
function getListItem(dataStoreItem) {
    return "<li><h3>id: " + dataStoreItem.key + "</h3><p>" + dataStoreItem.value.name + "</p></li>";
}

/**
 * This (beast of a) function does much of the hard work.
 * There needs to be three event handlers associated with opening
 * a database:
 *      * success       - a function that opens the database for read and/or write
 *      * error         - something went wrong - better deal with it
 *      * upgradeneeded - the database schema has changed (new code) and so we need
 *                        to copy all of the existing data into the new schema.  In
 *                        this version, we just re-write the database from scratch.
 *                        Obviously in a real app, we would need to preserve existing data.
 * @param idb           - this is the indexedDB object (like the localStorage object)
 */
function initDb(idb) {

    // Set up some simple data objects to store...
    var people = [
        { id: 1, name: "Fred Flintstone", email: "fred@bedrock.com" },
        { id: 2, name: "Barney Rubble", email: "barney@bedrock.com"},
        { id: 3, name: "Wilma Flintstone", email: "wilma@bedrock.com"},
        { id: 4, name: "Betty Rubble", email: "betty@bedrock.com"}
    ];

    // If this were being run in Firefox, we could generate the 'id'
    // values using autoIncrement.  It isn't
    /*
    var people = [
        { name: "Fred Flintstone", email: "fred@bedrock.com" },
        { name: "Barney Rubble", email: "barney@bedrock.com"},
        { name: "Wilma Flintstone", email: "wilma@bedrock.com"},
        { name: "Betty Rubble", email: "betty@bedrock.com"}
    ];
    */

    // Try to open a database...
    var openDbRequest = idb.open(dbName, dbVersion);

    // If it succeeded, there should be data to access...
    openDbRequest.onsuccess = function (evt) {
        console.log("IndexedDB opened");
        // The database object is passed in here as part of the event.  Stash it
        // in a global variable for use in other contexts...
        db = evt.target.result;
        // We need a transaction to get data from the database.  Note the first parameter
        // which identifies which objectStore (["people'] is an array of names with 1 element...)
        var trans = db.transaction(["people"], "readwrite"),
            // A database can contain one or more objectStore instances
            objectStore = trans.objectStore("people"),
            // A cursor is the bit of logic than moves from record to record...
            request = objectStore.openCursor(),
            // We're going to generate a <ul> table of items...
            tbl = "", count = 0;
        // If a cursor was opened...
        request.onsuccess = function(evt) {
            console.log("Cursor opened");
            // Pick up the cursor...
            var cursor = evt.target.result;
            if(cursor) {
                console.log("Cursor updated");
                // format the current data item (see getListItem(), above)...
                tbl += getListItem(cursor);
                // Count records..
                count += 1;
                // Update the list and refresh..
                $("#list").html(tbl).listview('refresh');
                /// And the count
                $("#count").text("Number of items: " + count);
                countOfRecords = count;
                // This will generate another request.onsuccess event.
                cursor.continue();
            }
        };
    };

    // If not, need to know why...
    openDbRequest.onerror = function (evt) {
        console.log("Unable to open.");
        // Make the whole object available in the console...
        console.dir(evt);
    };

    // For a new database, this is the normal entry point.  For an upgraded one,
    // we would normally, do something to copy existing data into the new schema.
    // In this case, it is just a demo, so just add records to a new database...
    openDbRequest.onupgradeneeded = function (evt) {
        console.log("Upgrading");
        db = evt.target.result;
        var person,
            // In an objectstore, keyPath means the property name that leads to
            // the primary key of the data store.  AutoIncrement doesn't work
            // in Google Chrome (so far)...
            objStore = db.createObjectStore("people", { keyPath: "id", autoIncrement: false});
        objStore.createIndex("name", "name", { unique: false});
        objStore.createIndex("email", "email", { unique: true});

        // Now get our list of objects and add them to the objectStore...
        for (person in people) {
            objStore.add(people[person])
        }
    };
}

function doAddRecord() {
    // Create a new object by picking the data off the form...
    var record = {id: (countOfRecords + 1), name: $("#name").val(), email: $("#email").val()}, trans, store, request;
    // A transaction is needed to manage the update...
    trans = db.transaction(["people"], "readwrite");
    // This is the data store we're adding to...
    store = trans.objectStore("people");
    // Try to add a record...
    request = store.add(record);
    // And provide two handlers - it worked and it didn't work...
    request.onerror = function (e) {
        console.log("Error", e.target.error.name);
    };
    request.onsuccess = function (e) {
        console.log("New object added!");
    };
}

$(document).ready(function () {
    "use strict";
    // First, we need to access this browser's version of indexedDB (if one exists)...
    var indexedDB = window.indexedDB || window.webkitIndexedDB || window.mozIndexedDB || window.msIndexedDB;
    // Now test for it, and if it exists, start up a database...
    if (indexedDB) {
        initDb(indexedDB);
        $("#add").on('click', function () {
            // Generate a record ID to show what a new records ID will be...
            $("#rid").text("New ID: " + (countOfRecords + 1));
        });
        $("#doadd").on('click', function() {
            // Add the data from the form...
            doAddRecord();
            // Re-query the database to show the new data...
            initDb(indexedDB);
            // And go back to the main page...
            $.mobile.changePage("#main");
        });
    } else {
        jqmAlert("IndexedDB", "Your device's browser does not support the IndexedDB API - try a better device.");
    }
});