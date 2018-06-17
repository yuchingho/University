/**
 * Created by alistair on 18/11/2013.
 */

var appCache = null;

function debugAppCache(){
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

    // Now for cache event handlers...
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

// Now to handle the application cache...
function handleCacheEvent(e) {
    alert(e.type);
}

function handleCacheError(e) {
    alert('Error: Cache failed to update!');
}


