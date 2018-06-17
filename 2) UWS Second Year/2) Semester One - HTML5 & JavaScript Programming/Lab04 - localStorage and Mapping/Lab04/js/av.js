/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 28/09/2012
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */

/*

var tracks = [
    "assets/Active_Child-Playing_House.mp3",
    "assets/Badly_Drawn_Boy-This_Electric.mp3",
    "assets/Grizzly_Bear-Deep_Sea_Diver.mp3"
];

var select, source, audio;

function replaceAll(str, find, repl){

    str = str.replace(finc, repl);
}

var trackList = function(){
    var track, list=[];

    for(var i=0; i<tracks.length; i++){
        track = tracks[i];
        var parts = track.split("/");
        var name = parts[1];
        parts = name.split(".");
        name = parts[0];
        name = name.split("_").join(" ");
        name = name.split("-").join(":");
        console.log(name);
        list.push(name);
    }
    return list;
};

*/

var init = function(){
    select = document.getElementById("track");
    source = document.getElementById("src");
    audio = document.getElementById("audio");

    /*
    var list = trackList();
    for(var i=0; i<tracks.length; i++){
        select.options.add(new Option(list[i], list[i]));
    }


    select.onchange = function(){
        var index = select.selectedIndex;
        audio.src = tracks[index];
        audio.load();
    };
    */

    select.onchange = function(){
        var track = select.value;
        audio.src = track;
        audio.load();
    }
};

window.onload = init;