/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 23/07/2012
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */

var walk_the_dom = function walk2(element, func){
    func(element);
    if(element.hasChildNodes()){
        for(var i= 0, j=element.childNodes.length; i<j; i++){
            walk2(element.childNodes[i], func);
        }
    }
};

function walk(node, func){
    func(node);
    node = node.firstChild;
    while(node) {
        walk(node, func);
        node = node.nextSibling;
    }
};

var display = function(element){
    if(element.nodeType === 3){
        console.log(element.nodeName + ":" + element.nodeValue);
    } else {
        console.log(element.nodeName);
    }
};

window.onload = walk(document.documentElement, display);