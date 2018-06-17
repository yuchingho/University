/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 30/09/2012
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

var text, saveButton, loadButton;

function doSave(){
    var txt = text.value;
    localStorage.storedText = txt;
}

function doLoad(){
    text.value = localStorage.storedText;
}


window.onload = function(){
    saveButton = document.getElementById("save");
    saveButton.onclick = doSave;
    loadButton = document.getElementById("load");
    loadButton.onclick = doLoad;
    text = document.getElementById("text");
};