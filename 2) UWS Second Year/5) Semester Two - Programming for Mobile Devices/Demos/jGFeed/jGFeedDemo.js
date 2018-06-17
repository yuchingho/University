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
        getFeed2("http://open.live.bbc.co.uk/weather/feeds/en/2640677/3dayforecast.rss",
                "weatherTitle", "weatherList", showFeedItems);
    });

    $("#railFeed").bind('click', function() {
        getFeed2("http://rss.journeycheck.com/firstscotrail/route?action=search&from=GLC&to=PYG&period=today&formTubeUpdateLocation=&formTubeUpdatePeriod=&savedRoute=",
            "railTitle", "railList", showFeedItems);
    });

    $("#footyFeed").bind('click', function() {
        getFeed2("http://www.football365.co.uk/premier-league/rss",
            "footyTitle", "footyList", showFeedItems);
    });

    // In fact, just as easy because we've attached the 'any feed' option to a
    // button on that page...
    $("#getFeed").bind('click', function() {
        getFeed2($("#feedUrl").val(), "anyTitle", "anyList", showFeedItems);
    });

    debugAppCache();
});

/*
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
*/

function getFeed2(url, titleID, listID){
    if(window.navigator.onLine) {
        $.jGFeed(url, function(feeds) {
            // Check for errors
            if(!feeds){
                // there was an error
                return false;
            } else {
                localStorage.setItem(url, JSON.stringify(feeds));
                showFeedItems(titleID, listID, feeds.title, feeds.entries);
            }
        }, 10);
    } else {
        // Get the fall-back...
        var feed = JSON.parse(localStorage.getItem(url));
        if(feed && feed.length > 0) {
            showFeedItems(titleID, listID, feeds.title, feeds.entries);
        }
    }
}

function showFeedItems(titleID, listID, title, items){
    $("#"+titleID).text(title);
    var list = $("#"+listID);
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    list.listview("refresh");
}

/*
function showWeatherFeed(title, items) {
    $("#weatherTitle").text(title);
    var list = $("#weatherList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    list.listview("refresh");
    //$.mobile.changePage($("#weather"), "flip");
}

function showRailFeed(title, items) {
    $("#railTitle").text(title);
    var list = $("#railList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        if(index === items.length) break;
        list.append(formatItem(items[index]));
    }
    list.listview("refresh");
    //$.mobile.changePage($("#rail"), "flip");
}

function showFootyFeed(title, items) {
    $("footyTitle").text(title);
    var list = $("#footyList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    list.listview("refresh");
    //$.mobile.changePage($("#footy"), "flip");
}

function showAnyFeed(title, items) {
    $("#anyTitle").text(title);
    var list = $("#anyList");
    list.empty();
    for(var index = 0; index < items.length; index += 1) {
        list.append(formatItem(items[index]));
    }
    list.listview("refresh");
    //$.mobile.changePage($("#any"), "flip");
}


function formatWeatherItem(item){
    var listItem = document.createElement("li"),
        anchor = document.createElement("a"),
        image = document.createEement("img");
    anchor.setAttribute("href", item.link);
    anchor.innerText = item.title;
    image.src = ""
    listItem.innerHTML = anchor.outerHTML;
    return listItem.outerHTML;

}
*/
function formatItem(item) {
    var listItem = document.createElement("li"),
        anchor = document.createElement("a");
    anchor.setAttribute("href", item.link);
    anchor.innerText = item.title;
    listItem.innerHTML = anchor.outerHTML;
    return listItem.outerHTML;
}