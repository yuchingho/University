/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 11/12/2012
 * Time: 19:59
 */

/*jslint browser:true*/
/*global google*/

var postCodePattern = "^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$";
var form, postCodeBox;

function showPostCode(zip) {
    "use strict";
    var geocoder, map, map_options, marker;
    /**
     * A geocoder converts location data in postcode form to a geographical location (lat & long).
     */
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({'address': zip }, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            map_options = {
                zoom: 16,                                   // Map scale
                mapTypeId: google.maps.MapTypeId.ROADMAP    // Type of map to show
            };
            // Create a map, naming the <div> element to place the map in...
            map = new google.maps.Map(document.getElementById("map_div"), map_options);
            // Show our specified location at the centre of the map...
            map.setCenter(results[0].geometry.location);
            // and place an orange marker there...
            marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location,
                name: zip
            });
        } else {
            window.alert("Geocode was not successful for the following reason: " + status);
        }
    });
}

function showLocation() {
    "use strict";
    if (form.checkValidity()) {
        showPostCode(postCodeBox.value);
    } else {
        window.alert("Please supply a valid UK post code");
    }
}

/**
 * This function acts as an initializer - assigns functions to buttons and
 * inserts the postcode element with a parrern
 */
window.onload = function () {
    "use strict";
    form = document.getElementById("form");
    // We need to add the above regex pattern (for a UK post code) to the input field...
    postCodeBox = document.getElementById("postcode");
    postCodeBox.setAttribute("pattern", postCodePattern);
    document.getElementById("showmap").onclick = showLocation;
};