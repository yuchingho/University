/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/04/2013
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function() {

    // The next 3 blocks of code wire up the buttons for the fixed feeds (weather,
    // Rail Info and Football.  The 'any' feed will need to be handled differently
    $("#weatherFeed").bind('click', function() {
        // Noe - 2640677 is the ID code for Paisley...
        getFeed("http://open.live.bbc.co.uk/weather/feeds/en/2640677/3dayforecast.rss",
                showWeatherFeed);
    });

    $("#railFeed").bind('click', function() {
        getFeed("http://rss.journeycheck.com/firstscotrail/route?action=search&from=GLC&to=PYG&period=today&formTubeUpdateLocation=&formTubeUpdatePeriod=&savedRoute=",
            showRailFeed);
    });

    $("#footyFeed").bind('click', function() {
        getFeed("http://www.football365.co.uk/premier-league/rss", showFootyFeed);
    });

    $("#getFeed").bind('click', function() {
        getFeed($("#feedUrl").val(), showAnyFeed);
    })

});

function getFeed(url, success){
    if(window.navigator.onLine) {
        $.jGFeed(url, function(feeds) {
            // Check for errors
            if(!feeds){
                // there was an error
                return false;
            } else {
                localStorage.setItem(url, JSON.stringify(feeds));
                success(feeds.title, feeds.entries);
            }
        });
    } else {
        // Get the fall-back...
        var feed = JSON.parse(localStorage.getItem(url));
        if(feed && feed.length > 0) {
            success(feed.title, feed.entries);
        }
    }
}

function showWeatherFeed(title, items) {
    $("#weatherTitle").text(title);
    var list = $("#weatherList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    $.mobile.changePage($("#weather"), "flip");
    list.listview("refresh");
}

function showRailFeed(title, items) {
    $("#railTitle").text(title);
    var list = $("#railList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    $.mobile.changePage($("#rail"), "flip");
    list.listview("refresh");
}

function showFootyFeed(title, items) {
    $("footyTitle").text(title);
    var list = $("#footyList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    $.mobile.changePage($("#footy"), "flip");
    list.listview("refresh");
}

function showAnyFeed(title, items) {
    $("#anyTitle").text(title);
    var list = $("#anyList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    $.mobile.changePage($("#any"), "flip");
    list.listview("refresh");
}

function formatItem(item) {
    var listItem = document.createElement("li"),
        anchor = document.createElement("a");
    anchor.setAttribute("href", item.link);
    anchor.innerText = item.title;
    listItem.innerHTML = anchor.outerHTML;
    return listItem.outerHTML;
}