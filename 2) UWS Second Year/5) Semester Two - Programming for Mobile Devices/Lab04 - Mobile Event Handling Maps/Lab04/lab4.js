/**
 * Created by B00274179 on 03/03/2015.
 */


$(document).on("pageinit", "#homepage", function(){

    //Sets The Pop-up box up
    $("#about").on("click", function(){

        //Our Alert to the user
        popupAlert("About Demos", "This App Demonstrates A variety of\ " +
                   "jQuery Mobile features & techniques");

    });

    //How we set up the clock in the panel
    setInterval(function(){

        //Set the time..
        $("#time").text(getTime());

        //Every 1000ms (1s)
    }, 1000);

    //Code to the close the panel, not needed
    $("#close").on('click',	function()	{

        $("#panel").panel("close");

    });

}).on("pageinit", "#listpage", function(){

    $("#add").on("click", function(evt){

        var item;
        popupPrompt("Add Item", "Enter list-item text:", function() {

            item = getPromptValue();
            $("#list").append("<li>" + item + "</li>").listview("refresh");

        });
    });

    $("#list").on("swiperight", "li", function(){
        var	li = $(this);
        li.animate({"margin-left": '+=' + $(window).width( )},
                    400, function() {
            li.remove();
        });
    });

}).on("pageshow", "#mappage", function() {
    // Can't draw the map until	the	page is	visible, since g-maps is a
    // bit incapable of	a re-size...

    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (pos) {

            drawMap(new	google.maps.LatLng(pos.coords.latitude, pos.coords.longitude), "map-canvas");

        });
    }

}).ready(function()	{
    // Re-size the map <div> as	soon as	the	document is	loaded…

    google.maps.event.addDomListener(window, 'load', function()	{

        initializeMap("map-canvas");

    });
});