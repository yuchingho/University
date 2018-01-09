var gameOver = function(game){}
	var theScore;
	var button;
gameOver.prototype = {
	init: function(score){
		this.theScore = score;
//		alert("You scored: "+score)
	},
  	create: function(){
		this.game.add.text(5, 250, 'This is the end of game. Your score is '+this.theScore+'.', { fill: '#ffffff', font: '14pt Arial' });
		var Thebutton = this.game.add.button(400, 230, 'buttonSprite', this.actionOnClick, this, 2, 1, 0);
		Thebutton.onInputDown.add(this.onDown, this);
	},
	

	onDown: function(){
		console.log("in ondown");

		var person = prompt("Please enter your name", "Harry Potter");
		if (person != null) {
		


var obj = {
        'user': person,
        'thescore': this.theScore
    };
	
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'savescore.php');
		xhr.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
		jsonData = JSON.stringify(obj);
		
		xhr.onreadystatechange = function() {
			if (xhr.status === 200) {
				alert(xhr.responseText);
			}
		};
		xhr.send('json=' + jsonData);
		
				}
	}
}