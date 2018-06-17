/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 12/11/2012
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
/*globals $ */

$().ready(function () {
    $('table').hide();
    $('#tbl').click(function () {
        $('table').fadeToggle();
        $('#tbl').text("A Table for Styling");
        $('#name').click(function () {
            $('#name').val("Fred Bloggs");
        });
    }).text();
    $('li').click(function (event) {
        //alert(event.target.nodeName);
        $(this).css('font-family', 'sans-serif');
        $(this).css('font-size', '14px');
    });
});