/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 14/04/2013
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */

var appCache = null;

var symbols = {
    "sun": [0, 0],
    "fog": [],
    "cloud": [64, 0],
    "rain": [],
    "heavy-rain": [],
    "snow": [],
    "thunder": []
};

/**  Assign using CSS values, like

#home{left:0px;width:46px;}
#home{background:url('img_navsprites.gif') 0 0;}

#prev{left:63px;width:43px;}
#prev{background:url('img_navsprites.gif') -47px 0;}

#next{left:129px;width:43px;}
#next{background:url('img_navsprites.gif') -91px 0;}

 */

$(document).ready(function() {

    // The next 3 blocks of code wire up the buttons for the fixed feeds (weather,
    // Rail Info and Football.  The 'any' feed will need to be handled differently
    $("#weatherFeed").bind('click', function() {
        // Note - 2640677 is the ID code for Paisley...
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

    // In fact, just as easy because we've attached the 'any feed' option to a
    // button on that page...
    $("#getFeed").bind('click', function() {
        getFeed($("#feedUrl").val(), showAnyFeed);
    });

    $.getJSON('https://auws-counter.webscript.io', function (data) {
        $('#visitCount').text('Visit count: ' + data.count);
    });

    appCache = window.applicationCache;
    appCache.addEventListener('updateready', function(e) {
        if (appCache.status == appCache.UPDATEREADY) {
            // Browser downloaded a new app cache.
            // Swap it in and reload the page to get the new hotness.
            appCache.swapCache();
            if (confirm('A new version of this site is available. Load it?')) {
                window.location.reload();
            }
        } else {
            // Manifest didn't change. Nothing new to serve.
        }
    }, false);

    $("#update").bind('click', function() {
        appCache = window.applicationCache;
        //try {
        appCache.update(); // Attempt to update the user's cache.
        if (appCache.status == window.applicationCache.UPDATEREADY) {
            appCache.swapCache();  // The fetch was successful, swap in the new cache.
            alert("Cache Updated"); // Only on a desktop browser.
        }
        //} catch (ex) {
        //}
    });

    // VV-- Remove comment to....>>
    // debugAppCache();

});

// Now to handle the application cache...
function handleCacheEvent(e) {
    alert(e.type);
}

function handleCacheError(e) {
    alert('Error: Cache failed to update!');
};

function debugAppCache() {
    // Fired after the first cache of the manifest.
    appCache.addEventListener('cached', handleCacheEvent, false);

    // Checking for an update. Always the first event fired in the sequence.
    appCache.addEventListener('checking', handleCacheEvent, false);

    // An update was found. The browser is fetching resources.
    appCache.addEventListener('downloading', handleCacheEvent, false);

    // The manifest returns 404 or 410, the download failed,
    // or the manifest changed while the download was in progress.
    appCache.addEventListener('error', handleCacheError, false);

    // Fired after the first download of the manifest.
    appCache.addEventListener('noupdate', handleCacheEvent, false);

    // Fired if the manifest file returns a 404 or 410.
    // This results in the application cache being deleted.
    appCache.addEventListener('obsolete', handleCacheEvent, false);

    // Fired for each resource listed in the manifest as it is being fetched.
    appCache.addEventListener('progress', handleCacheEvent, false);

    // Fired when the manifest resources have been newly redownloaded.
    appCache.addEventListener('updateready', handleCacheEvent, false);
}


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
        list.append(formatWeatherReportItem(items[index]));
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

function formatWeatherReportItem(item){
    var listItem = document.createElement("li");
        anchor = document.createElement("a"),
        image = document.createEement("img");

    anchor.setAttribute("href", item.link);
    anchor.innerText = item.title;
    listItem.innerHTML = anchor.outerHTML;
    return listItem.outerHTML;
}

function formatItem(item) {
    var listItem = document.createElement("li"),
        anchor = document.createElement("a");
    anchor.setAttribute("href", item.link);
    anchor.innerText = item.title;
    listItem.innerHTML = anchor.outerHTML;
    return listItem.outerHTML;
}