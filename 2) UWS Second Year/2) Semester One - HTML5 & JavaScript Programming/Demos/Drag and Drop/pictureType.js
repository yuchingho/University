/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 23/11/2012
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */

/**
 * This file defines a new type that includes a picture element - demonstrates the
 * use of the drag&drop.js code.
 */

var PicObject = function(name){
    this.name = name;
    this.imageFile = null;
};

PicObject.prototype.setImage = function(imageFile) {
    this.imageFile = imageFile;
};

PicObject.prototype.generateView = function (width, height){
    // This function returns the whole object in html format,
    // including a <h2> element and an <img> element...
    var view = "<h2>This is " + this.name + "</h2>" +
               "<img width='" + width + "' height='" + height + "' src='" + this.imageFile + "'/>";
    return view;
}

var pictureObject;

/**
 * This will be the callback function for when an image is dropped on the target.
 * It receives the whole image file as a parameter.
 * @param file: the entire content of an image file in a for that can be assigned as <img> src attribute.
 */
function newObject(file) {
    var name = prompt("Enter object's name");
    pictureObject = new PicObject(name);
    pictureObject.setImage(file);
    // objectDiv is the id of an element that will display the whole object.
    document.getElementById("objectDiv").innerHTML = pictureObject.generateView(100, 100);
}

/**
 * Sets up drag & drop for when an image is dropped on the "target" element..
 */
window.onload = function() {
    setUpDragDrop("target", newObject);
}

