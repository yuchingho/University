/**
 * Created by alistair on 07/01/2015.
 */

/**
 * Calculate the display dimensions and
 * return an object with these values.
 * @returns {{height: *, width: (*|jQuery)}}
 */
function getScreenDims() {
    return {
        height: $.mobile.getScreenHeight(),
        width: $(window).width()
    };
}

/**
 * Turn a number (of up to two digits) into a two-digit number,,,
 * @param n - the number
 * @returns {string} - the number with a leading zero if needed.
 */
function twoDigit(n) {
    var nstr = n.toString();
    return nstr.length < 2 ? "0" + nstr : nstr;
}

/**
 * Return the current time in hh:mm:ss format.
 * @returns {string}
 */
function getTime() {
    var t = new Date();
    return twoDigit(t.getHours()) + ":" +
        twoDigit(t.getMinutes()) + ":" +
        twoDigit(t.getSeconds());
}


/**
 * Paul Irish's requestAnimationFrame() polyfill.
 * See http://www.paulirish.com/2011/requestanimationframe-for-smart-animating/
 */
window.requestAnimFrame = (function(){
    return  window.requestAnimationFrame       ||
        window.webkitRequestAnimationFrame ||
        window.mozRequestAnimationFrame    ||
        function( callback ){
            window.setTimeout(callback, 1000 / 60);
        };
})();
