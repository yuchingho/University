/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 30/09/2012
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */

/**
 * The following API_KEY is registered to the html5-module-code ProjectID for the
 * HTML5 Demos app.  This will be used for HTML5+JS labs.
 * @type {String}
 */
var API_KEY = "AIzaSyD3Cw-Aj3UzIEvsLeQ_1FXXUPb-4Y544mQ";
var geocoder, map;

function showPosition(position){
    var map_options = {
        center: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map_div"), map_options);
	geocoder = new google.maps.Geocoder();
	addPostCode("PA12BE");
	addPostCode("KA309ES");
}

function showLatLng(position){
    var location = "Lat: " + position.coords.latitude +
                   "Long: " + position.coords.longitude;
    document.getElementById("location").textContent = location;
}

window.onload = function getLocation(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(show_map);
    } else {
        alert("Location data is unavailable!");
    }
}

function show_map() {
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(showPosition);
    }else{
        alert("Can not display map data!");
    }
}

function addPostCode(zip) {
    geocoder.geocode({'address': zip }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK){
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
            								map: map,
            								position: results[0].geometry.location,
            								name: zip
        								});
        } else {
            alert("Geocode was not successful for the following reason: " + status);
        }
    }); 
}
