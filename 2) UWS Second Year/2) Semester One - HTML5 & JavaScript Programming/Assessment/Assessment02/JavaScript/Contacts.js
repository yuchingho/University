// Submitted by B00255970 & B00264983
var contacts = [];

jQuery(document).ready(function()
{
    jQuery("#saveButton").bind('click',saveContact);
    jQuery("#addContactButton").bind('click', showForm);
    jQuery("#cancelButton").bind('click',showList);
    getContacts();
    showList();
});