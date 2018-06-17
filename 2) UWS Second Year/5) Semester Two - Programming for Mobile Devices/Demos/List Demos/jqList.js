
$(document).ready(function(){
    $("#addbtn").bind('click', function(){
    	addItem();
    });

    $(".col_pick").bind('click', function(){
    	alert(this.id);
    });
});

function addItem(){
	var text = prompt("Enter some text:", "Text");//get_text("Enter some text:");
	var url = prompt("Enter a url:", "http://");
	if (text!=null && text!=""){
		addNewItem(text, url, true);
	} else {
		alert("Not added");
	}
}

function addNewItem(itemText, url, addDeleteNode){
	/*
	 * Depending on the value in the url parameter, this
	 * will create a <li> tag which may (or may not) have
	 * a link to a url.
	 */
	var listitem=null;
	if(url!=null && url!=""){
		listitem = newListItemWithLink(itemText, url);
		if(addDeleteNode){
			listitem.appendChild(createDeleteNode(itemText));
		}
	} else {
		listitem = newListItemWithLink(itemText, "#");
		if(addDeleteNode){
			listitem.appendChild(createDeleteNode(itemText));
		}
	}
	alert(listitem.outerHTML);
	var list = $("#list");
	list.append(listitem);
	list.listview("refresh");	
}

function newListItemWithLink(itemText, linkUrl){
	/*
	 * This function will create a <li> tag with an inner <a>
	 * tag which defines the link to the URL.
	 * Note that there is no need to assign an innerText
	 * value to the <li>, since the link node will define
	 * this.
	 */
	var item = document.createElement('li');
	item.setAttributeNode(newAttribute("id", itemText));
	var anchorNode = createAnchorNode(linkUrl, itemText);
	item.appendChild(anchorNode);
	return item;	
}

function newListItem(itemText){
	/*
	 * This is a plain list item with no link; simply
	 * has an innerText value.
	 */
	var item = document.createElement('li');
	item.setAttributeNode(newAttribute("id", itemText));
	item.appendChild(createAnchorNode("#", itemText));
	return item;
}

function removeItem(id){
	$(id).remove();
	$("#list").refresh();
	// Also remove it from local storage...
	for(var index=0; index<localStorage.length; index++){
		var k = localStorage.key(index);
		var val = id.substring(1);	// skip the #
		if(val == localStorage[k]){
			localStorage.removeItem(k);
			break;
		}
	}
}

function newListItemWithDelete(itemText){
	// Create the basic item, with ID...
	var item = document.createElement('li');
	item.setAttributeNode(newAttribute("id", itemText));	
	// add the first anchor...
	item.appendChild(createAnchorNode("#", itemText));
	// add the second anchor (needed for a split-button item),
	// also contains the delete event link... 
	item.appendChild(createDeleteNode(itemText));
	return item;
}

function createAnchorNode(url, text){
	var node = document.createElement('a');
	if(text!=null && text!=""){
		node.innerText = text;
	}
	node.setAttributeNode(newAttribute("href", url));
	return node;
}

function createDeleteNode(id){
	// Create an anchor...
	var node = document.createElement('a');
	node.setAttributeNode(newAttribute("href", "#"));
	// Create and assign the 'x' icon...
	node.setAttributeNode(newAttribute('data-icon', 'delete'));
	// Create the 'onclick' attribute...
	node.setAttributeNode(newAttribute("onclick", "removeItem('#"+id+"');"));
	return node;	
}

function newAttribute(name, value){
	var attr = document.createAttribute(name);
	attr.nodeValue = value;
	return attr;
}
