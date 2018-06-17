/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 25/07/2012
 * Time: 09:27
 * To change this template use File | Settings | File Templates.
 */

/**
 * Collection definition.
 * @constructor Collection: sets up a Collection objects with no items and count == 0
 */
var Collection = function(type){
    "use strict";
    this.coll = {};      // Using a map.
    this.length = 0;
    if(type) {
        // This will be a typed collection - objects with a specific constructor.
        this.type = type;
    }
};

/**
 * Collection.add() function: adds an item to a collection with a key
 * @param key {Object} - typically a string
 * @param item {Object} - an item to add to the collection
 * @param replace {Boolean} - if true, any existing item with the given key will be replaced
 *                            if false, any existing item with the given key will not be replaced
 * @return {Boolean} - true if the item was added, or if it replaced an existing item
 */
Collection.prototype.add = function(key, item, replace){
    "use strict";
    // Are we adding an appropriate type of object...
    if(this.type && !(item instanceof this.type)) {
        return false;
    }
    // If the key exists and we've opted to replace the item, replace it..
    if(this.coll[key] && replace){
        this.coll[key] = item;
        return true;
    }
    // If the key exists, don't replace the item...
    if(this.coll[key]){
        return false;
    } else {    // Add the item and increment count...
        this.coll[key] = item;
        this.length += 1;
        return true;
    }
};

/**
 * Collection.item(key) - returns an item with the given key, or null
 * @param key {Object} - typically a string that uniquely identifies an item
 * @return {*}  - the object at the given key, or null
 */
Collection.prototype.item = function(key){
    "use strict";
    if(this.coll[key]){
        return this.coll[key];
    } else {
        return null;
    }
};

/**
 * Collection.exists(key) - test whether an item is stored uder the given key
 * @param key {Object} - key to test
 * @return {Boolean} - true if an item exists at that key, false otherwise
 */
Collection.prototype.exists = function(key){
    "use strict";
    if(this.coll[key]){
        return true;
    } else {
        return false;
    }
};

/**
 * Collection.count() - return the number of items in the collection
 * @return {number} - the count of items
 */
Collection.prototype.count = function(){
    "use strict";
    return this.length;
};

/**
 * Collection.remove(key) - remove the item with the given key
 * @param key - the key for the item to remove
 * @return {number} - the new length of the collection
 */
Collection.prototype.remove = function(key){
    "use strict";
    if(this.exists(key)){
        delete this.coll[key];
        this.length -= 1;
    }
    return this.length;
};

/**
 * Collection.removeAll() - deletes all collection contents and sets length to 0.
 */
Collection.prototype.removeAll = function(){
    "use strict";
    this.coll = {};
    this.length = 0;
};

/**
 * Collection.toArray() - return a standard array of the collection's items
 * @return {Array} - a new array of items
 */
Collection.prototype.toArray = function(){
    "use strict";
    var arr = [], o;
    for(o in this.coll){
        arr.push(this.coll[o]);
    }
    return arr;
};

/**
 * Collection.keys() - return an array of item keys from the collection.
 * @return {Array} - an array of key values.
 */
Collection.prototype.keys = function(){
    "use strict";
    var k = [], o;
    for(o in this.coll){
        k.push(o);
    }
    return k;
};

Collection.prototype.forEach = function(func){
    for(key in this.coll){
        func(this.coll[key]);
    }
};

/**
 * Test suite for this object definition.
 */
var collTest = function(){
    "use strict";

    // Run tests on all methods..
    var c = new Collection();

    console.log("*** add() tests***");
    if(c.add("one", "First item")){
        console.log("Pass: added 'one'");
    } else {
        console.log("Fail: 'one' not added");
    }
    if(c.add("one", "New First item", true)){
        console.log("Pass: updated 'one'");
    } else {
        console.log("Fail: 'one' not updated");
    }
    if(c.add("one", "First item")){
        console.log("Fail: updated 'one' when shouldn't have");
    } else {
        console.log("Pass: correctly didn't update 'one'");
    }
    if(c.add("two", "Second item")){
        console.log("Pass: added 'two'");
    } else {
        console.log("Fail: 'two' not added");
    }
    if(c.add("three", "Third item", true)){
        console.log("Pass: added 'three'");
    } else {
        console.log("Fail: 'three' not added");
    }
    if(c.add("four", "Fourth item", false)){
        console.log("Pass: added 'four'");
    } else {
        console.log("Fail: 'four' not added");
    }

    console.log("***item() tests***");
    var key = "one";
    var item = c.item(key);
    if(item){
        console.log("Pass: retrieved "+key+":"+item);
    } else {
        console.log("Fail: could not retrieve "+key);
    }
    key = "none";
    var item = c.item(key);
    if(item){
        console.log("Fail: wrongly retrieved "+key+":"+item);
    } else {
        console.log("Pass: did not retrieve "+key);
    }

    console.log("***exists() tests***");
    var key = "one";
    if(c.exists(key)){
        console.log("Pass: states " + key + " exists.");
    } else {
        console.log("Fail: states " + key + " does not exist.");
    }
    var key = "none";
    if(c.exists(key)){
        console.log("Fail: states " + key + " exists.");
    } else {
        console.log("Fail: states " + key + " does not exist.");
    }

    console.log("***toArray() tests***");
    var a = c.toArray();
    if(a){
        console.log("Pass: retrieved " + a.join(', '));
    } else {
        console.log("Fail: array not returned");
    }

    console.log("***keys() tests***");
    var k = c.keys();
    if(k){
        console.log("Pass: retrieved " + k.join(', '));
    } else {
        console.log("Fail: keys not returned");
    }

    console.log("***count() tests***");
    var n = c.count();
    if(n){
        console.log("Pass: count is "+n);
    } else {
        console.log("Fail: count not returned");
    }

    console.log("***remove() tests***");
    var n2 = c.remove("two");
    if((n - n2) === 1){
        console.log("Pass: item successfully removed");
    } else {
        console.log("Fail: item not removed");
    }
    console.log("Status - values: " + c.toArray().join(', '));
    console.log("Status - keys: " + c.keys().join(', '));
    console.log("Status - count: " + c.count() + " items in collection");

    console.log("***removeAll() tests***");
    c.removeAll();
    if(c.count() == 0){
        console.log("Pass: all items removed");
    } else {
        console.log("Fail: items still in collection");
    }
    console.log("Status - final collection state = [" + c.toArray().join(', ') + "]");
    console.log("Status - final keys state = [" + c.keys().join(', ') + "]");

    console.log("***typed collection tests***");
    c = new Collection(string);

};

window.onload = collTest;	// Run the tests when the page loads.
