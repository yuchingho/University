/**
 * Created by alistairmcmonnies on 19/10/15.
 */

var currentPage;

function show_page(pageID){
    currentPage = pageID;
    document.getElementById(pageID).style.display = 'block';
}

function hide_page(pageID){
    document.getElementById(pageID).style.display = 'none';
}

function hide_all_pages() {
    var elements = document.getElementsByClassName("page"), index;
    for(index = 0; index < elements.length; index += 1) {
        //elements[index].onclick = function() {
        elements[index].style.display = "none";
    }
}

function change_page(pageID) {
    hide_page(currentPage);
    currentPage = pageID;
    show_page(currentPage);
}

function go_home(){
    change_page("home");
}

window.onload = function() {
    /*
        Start by wiring-up all the "back" buttons in the app.
        Every page except the "home" page should have a "back" button,
        coded as <button name='back'>...</button>.  Every "back" button
        in the app is wired to the go_home() function.
     */
    var elements = document.getElementsByName("back"), index;
    for(index = 0; index < elements.length; index += 1) {
        //elements[index].onclick = function() {
        elements[index].addEventListener('click', go_home);
    }
    /*
        Now wire up the buttons one the home page that will transfer to
        other pages.
     */
    document.getElementById("to-list").onclick = function() {
        change_page("list");
    }
    document.getElementById("to-form").onclick = function() {
        change_page("form");
    }
    /*
        Finally, set up the pages so that only the "home" page is
        visible.
     */
    hide_all_pages();
    show_page("home");
};