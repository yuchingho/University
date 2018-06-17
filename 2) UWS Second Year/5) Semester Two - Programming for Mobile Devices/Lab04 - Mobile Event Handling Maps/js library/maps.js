/**
 * Created by alistair on 07/01/2015.
 */

/*********************************************************
 * Definitions and functions for the maps demo page.     *
 *********************************************************/
var map;

function drawMap(latlng, screenElement) {
    var mapOptions = {
        zoom: 14,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById(screenElement), mapOptions);
    // Add an overlay to the map of current lat/lng
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: "You are here!"
    });
}

function initializeMap(maparea) {
    // First find window width and height...
    var header = $(".ui-header").hasClass("ui-header-fixed") ? $(".ui-header").outerHeight()  - 1 : $(".ui-header").outerHeight(),
        footer = $(".ui-footer").hasClass("ui-footer-fixed") ? $(".ui-footer").outerHeight() - 1 : $(".ui-footer").outerHeight(),
        w = window.innerWidth * 0.9,
        h = window.innerHeight - header - footer - 20;
    $("#" + maparea).width(w).height(h);
}
