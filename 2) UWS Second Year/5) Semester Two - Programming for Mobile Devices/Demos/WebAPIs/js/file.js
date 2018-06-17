/**
 * Created with WebStorm.
 * User: alistair
 * Date: 13/03/2014
 * Time: 20:05
 */

var fileList;

$(document).bind('pageinit', function () {
    // Check if the file APIs are supported...
    if (!window.File || !window.FileReader || !window.FileList || !window.Blob) {
        jqmAlert("File API Problem", "The File APIs are not fully supported in this browser.");
    } else {
        // #files is the <input> file element...
        $("#files").on('change', function (e) {
            var list = [], i, file, num = 0;
            // If this fires, the user has selected one or more files...
            fileList = e.target.files;    // e.target.files is an array
            for(i = 0; file = fileList[i]; i += 1) {
                // file is now one of the list of items in files[]...
                num += 1;
                var finfo = file.name + " (" + file.type + ") - " + file.size + "bytes";
                list.push("<li><a href='#' id='" + file.name + "'>" + finfo +  "</a></li>");
            }
            $("#list").html(list.join('')).listview('refresh');
            $("#fnum").text("Number of files chosen: " + num);
        });
    }
});

$(document).on('click', 'li', function (evt) {
    var index = $("#list li").index(this);
    // A FileReader is an object that can read a file (duh!)...
    var reader = new FileReader();
    // An event tells us when a file has been read...
    reader.onload = function (evt) {
        // And the even contains the file data (event.target.result)...
        $("#fileContent").text(evt.target.result);
    };
    // readAsText simply reads the whole file (and fires the event handler above)...
    reader.readAsText(fileList[index]);
});
