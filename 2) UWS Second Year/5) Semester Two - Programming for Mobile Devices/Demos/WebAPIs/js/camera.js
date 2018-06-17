/**
 * Created by alistair on 13/03/2014.
 */

var video, context, snapshot,
    userMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

/**
 * This cool bit of code comes from The Rock n Coder blog.
 * http://therockncoder.blogspot.co.uk/2012/08/jquery-mobile-dynamic-content-area.html
 * Respect and thanks. AMcM
 * @returns {{width: number, height: number}}
 */
function getDimensions() {
    // the iphone specific code is kind of kludgy, if you have a better way let me know
    var isIPhone = (/iphone/gi).test(navigator.appVersion),
        iPhoneHeight = (isIPhone ?  60 : 0),
        width = $(window).width(),
        height = $(window).height(),
    // if one of these doesn't exist, assign 0 rather a null or undefined
        hHeight = $('header').outerHeight() || 0,
        fHeight = $('footer').outerHeight() || 0;
    return {
        // w-28 and h-88 worked out by trial and error...
        width: width - 28,
        height: height - hHeight - fHeight - 88 + iPhoneHeight
    };
}

function resizePageElements() {
    // calculate available page width and height...
    var dims = getDimensions();
    dims.height -= $("#snap").height();
    $("#video").width(dims.width)
        .height(dims.height/2);
    $("#snapshot").width(dims.width)
        .height(dims.height/2);
}

function setUpVideo() {
    // Grab elements, create settings, etc.
    var vid, videoObj,
        errBack = function(error) {
            console.log("Video capture error: ", error.code);
        };
    snapshot = document.getElementById("snapshot"),
    context = snapshot.getContext("2d"),
    vid = document.getElementById("video");
    videoObj = { "video": true };

    // Put video listeners into place
    if(navigator.getUserMedia) { // Standard
        navigator.getUserMedia(videoObj, function(stream) {
            vid.src = stream;
            vid.play();
        }, errBack);
    } else if(navigator.webkitGetUserMedia) { // WebKit-prefixed
        navigator.webkitGetUserMedia(videoObj, function(stream){
            vid.src = window.webkitURL.createObjectURL(stream);
            vid.play();
        }, errBack);
    }
    else if(navigator.mozGetUserMedia) { // Firefox-prefixed
        navigator.mozGetUserMedia(videoObj, function(stream){
            vid.src = window.URL.createObjectURL(stream);
            vid.play();
        }, errBack);
    }
    return vid;
}

$(window).bind('resize orientationchange pageshow', function (event) {
    window.scrollTo(1, 0);
    resizePageElements();
});

$(document).bind('pageinit', function () {
    "use strict";
    if (userMedia) {
        video = setUpVideo();
        $("#snap").on('click', function () {
            context.drawImage(video, 0, 0, snapshot.width, snapshot.height);
        });
    } else {
        jqmAlert("User Media", "Your device's browser does not support UserMedia - i.e. the Camera interface");
    }
});
