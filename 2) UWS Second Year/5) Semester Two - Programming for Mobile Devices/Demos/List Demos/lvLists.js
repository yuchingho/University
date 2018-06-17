/*
 * First thing to do:
 * Bind event handlers to items on the page
 */
$(document).ready(function(){
	// Add a keypress event handler to the #newitem text box...
	$("#newitem").bind('keypress', function(evt){
		// Only act on an Enter key
		if(evt.keyCode == 13){
			doAddItem();
		}
	});
});

/*
 * This function adds the item in the #newitem text box to the listview.
 * It also asks for an associated URL, and uses options to incorporate
 * a link (to the URL) and a delete split-button.
 */
function doAddItem(){
	var text = $("#newitem").val();
	if (text.length > 0){
		var url = prompt("Enter a URL (or cancel)", "http://");
		if(url==null || url=="htp://"){
			url="";
		}
		insertNewListItem($("list"), text, url, true);
		$("#newitem").val("");
	}
}

/*
 * This will remove the identified item from the listview.
 */
function removeItem(id){
	$(id).remove();
	$("#list").refresh();
}

/*
 * Insert a new list item into a list.
 * list: an UL or OL
 * content: text that will appear in the list
 * url: a link that the list item leads to (or an empty string)
 * deleteNode: true if a delete split-button is wanted.
 */
function insertNewListItem(list, content, url, deleteNode){
	var li = null;
	li = newListItem("", replaceAll(content, ' ', '_'));
	var anchorNode = newElement('a', content);
	anchorNode.setAttributeNode(newAttribute("href", url));
	li.appendChild(anchorNode);
	if(deleteNode){
		var dn = document.createElement('a');
		dn.setAttributeNode(newAttribute("href", "#"));
		// Create and assign the 'x' icon...
		dn.setAttributeNode(newAttribute('data-icon', 'delete'));
		// Create the 'onclick' attribute...
		dn.setAttributeNode(newAttribute("onclick", "removeItem('#"+replaceAll(content, ' ', '_')+"');"));
		// Insert into the listitem...
		li.appendChild(dn);
	}
	alert(li.outerHTML);
	$("#list").append(li);
	$("#list").listview("refresh");
}

/*
 * Create a new listItem with specified content.
 */
function newListItem(content, id){
	var li = newElement('li', content);
	li.setAttributeNode(newAttribute('id', id));
	return li;
}

/*
 * Create a new DOM element node.  This will be a simple
 * node of the form...
 * 
 * <type>content</type>
 * 
 * ..with no attributes present. e.g. a call to...
 * 
 * var e = newElement("li", "Some Data Item");
 * 
 * .. will result in...
 * 
 * <li>Some Data Item</li>
 */
function newElement(type, content){
	var elem = document.createElement(type);
	elem.innerText = content;
	return elem;
}


/*
 * Create a new DOM attribute for insertion into a node.
 * e.g. to add an href attribute to an element...
 * 
 * elem.setAttributeNode(newAttribute('href', 'http://www.google.com'));
 * 
 */
function newAttribute(name, value){
	var attr = document.createAttribute(name);
	attr.nodeValue = value;
	return attr;
}

/*
 * A very useful utility function for replacing characters
 * or substrings in a string.
 * For example, to replace the spaces in "This is text" with
 * suitable characters for an ID, use
 * 
 * var s = replaceAll("This is text", " ", "_");
 * 
 */
function replaceAll(txt, replace, with_this) {
	return txt.replace(new RegExp(replace, 'g'),with_this);
}