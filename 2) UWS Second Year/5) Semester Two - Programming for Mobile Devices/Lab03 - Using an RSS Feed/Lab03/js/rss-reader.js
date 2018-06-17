/**
 * Created by alistairmcmonnies on 21/03/2016.
 */

var feedURL = "https://itunes.apple.com/gb/rss/topmovies/limit=~~/xml",
    numItems = 12, url = feedURL.replace("~~", numItems.toString());

function getFeed(url, success){
    if(window.navigator.onLine) {
        url = url.replace("~~", numItems.toString());
        $.jGFeed(url, function(feeds) { // Check for errors
            if(!feeds){
                // there was an error
                return;
            } else {
                // No error – we’ve got the feed data...
                localStorage.setItem(url, JSON.stringify(feeds));
                success(feeds.title, feeds.entries);
            }
        }, numItems);
    } else {
        // Get the fall-back...
        var feed = JSON.parse(localStorage.getItem(url));
        if(feed && feed.length > 0) {
            success(feed.title, feed.entries);
        }
    }
}

function formatItem(item) {
    var li = document.createElement('li'), // The main element returned
        a = document.createElement('a'),   // An <a> tag for the link
        p = document.createElement('p');   // The main text content
    a.setAttribute("href", item.link);
    a.setAttribute("style","white-space:normal;");
    a.innerText = item.title;
    p.innerText = item.contentSnippet;
    p.setAttribute("style", "white-space:normal;");
    a.appendChild(p);
    li.appendChild(a);
    return li;
}

$(document).on('pagebeforeshow', function() {
    getFeed(feedURL, function(title, items){
        $("#title").text(title);
        for(var index=0; index<items.length; index+=1){
            $("#list").append(formatItem(items[index])); }
        $("#list").listview('refresh');

    });
});