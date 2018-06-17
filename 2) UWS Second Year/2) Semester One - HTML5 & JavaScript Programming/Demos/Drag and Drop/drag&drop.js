/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 22/11/2012
 * Time: 20:28
 */

function setUpDragDrop(dropTargetId, callback) {
    var dropElement = document.getElementById(dropTargetId);
    dropElement.addEventListener('dragenter', function (e) {
        e.stopPropagation();
        e.preventDefault();
    }, false);
    dropElement.addEventListener('dragover', function (e) {
        e.stopPropagation();
        e.preventDefault();
    }, false);
    dropElement.addEventListener('dragleave', function (e) {
        e.stopPropagation();
        e.preventDefault();
    }, false);
    dropElement.addEventListener('drop', function onDrop(e) {
        var cb = callback;
        e.stopPropagation();
        e.preventDefault();
        var readFileSize = 0;
        var files = e.dataTransfer.files;
        file = files[0];
        readFileSize += file.fileSize;
        // Only process image files.
        var imageType = /image.*/;
        if (!file.type.match(imageType)) {
            return;
        }
        var reader = new FileReader();
        reader.onerror = function(e) {
            alert('Error code: ' + e.target.error);
        };
        // Create a closure to capture the file information.
        reader.onload = (function(aFile) {
            return function(evt) {
                if(cb)cb(evt.target.result);
                dropElement.src = evt.target.result;
            }
        })(file);

        // Read in the image file as a data url.
        reader.readAsDataURL(file);
    }, false);
}
