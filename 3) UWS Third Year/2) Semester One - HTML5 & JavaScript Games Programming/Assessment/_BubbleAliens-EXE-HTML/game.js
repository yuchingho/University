var game = new Phaser.Game(800, 608, Phaser.AUTO, "BubbleAliens", { preload: preload, create: create, update: update, render: render });
// https://www.joshmorony.com/how-to-scale-a-game-for-all-device-sizes-in-phaser/

function preload() {
    // Game world
    game.load.image("loadingScreen", "tiled/loadingScreen.png");
    game.load.image("background", "tiled/background.png");
    game.load.image("tileCollide", "tiled/tileCollide.png");
    game.load.tilemap("map", "tiled/bubblealiens.json", null, Phaser.Tilemap.TILED_JSON);
    
    // Audio (make audio same level)
    game.load.audio("bgMusic", "audio/phaser_bodenstaendig.mp3");
    game.load.audio("321", "audio/3-2-1-Go.mp3");
    game.load.audio("armyPowerUp", "audio/armyPowerUp.mp3");
    game.load.audio("bombCharge", "audio/bombCharge.mp3");
    game.load.audio("bombReady", "audio/bombReady.mp3");
    game.load.audio("bubblePop", "audio/bubblePop.mp3");
    game.load.audio("enemyFiring", "audio/enemyFiring.mp3");
    game.load.audio("enemyHit", "audio/enemyHit.mp3");
    game.load.audio("explode", "audio/explode.mp3");
    game.load.audio("plane", "audio/plane.mp3");
    game.load.audio("planeFiring", "audio/planeFiring.mp3");
    game.load.audio("playerDrench", "audio/playerDrench.mp3");
    game.load.audio("ship", "audio/ship.mp3");
    game.load.audio("shipFiring", "audio/shipFiring.mp3");
    game.load.audio("tank", "audio/tank.mp3");
    game.load.audio("tankFiring", "audio/tankFiring.mp3");
    game.load.audio("towerFiring", "audio/towerFiring.mp3");
    game.load.audio("towerHeal", "audio/towerHeal.mp3");
    game.load.audio("towerHit", "audio/towerHit.mp3");
    game.load.audio("truck", "audio/truck.mp3");
    game.load.audio("truckFiring", "audio/truckFiring.mp3");
    //game.load.audio("YouLose", "audio/YouLose.mp3");
    //game.load.audio("YouWin", "audio/YouWin.mp3");
    //game.load.audio("YouWinFirework", "audio/YouWinFirework.mp3");
    
    // Bubbles
    game.load.image("CrudeOil", "images/bubbles/01CrudeOil.png");
    game.load.image("Fuel", "images/bubbles/02Fuel.png");
    game.load.image("Diesel", "images/bubbles/03Diesel.png");
    game.load.image("Kerosene", "images/bubbles/04Kerosene.png");
    game.load.image("Naphtha", "images/bubbles/05Naphtha.png");
    game.load.image("Gasoline", "images/bubbles/06Gasoline_Petrol.png");
    game.load.image("RefineryGases", "images/bubbles/07RefineryGases.png");
    game.load.image("Residue", "images/bubbles/08Residue_Bitumen.png");
    
    // Player
    game.load.image("player", "images/player/player.png");
    game.load.image("playerDrenched", "images/player/playerDrenched.png");
    game.load.image("fireball", "images/player/weaponFireball.png");
    game.load.image("bomb", "images/player/weaponBomb.png");
    game.load.spritesheet("explode", "images/player/explode.png", 128, 128);
    
    // Sprites that attack each other
    game.load.image("enemyShip", "images/spawn/enemyShip.png");
    game.load.image("enemyBullet", "images/spawn/enemyBullet.png");
    game.load.image("tower", "images/spawn/tower.png");
    game.load.image("ship", "images/spawn/ship.png");
    game.load.image("tank", "images/spawn/tank.png");
    game.load.image("plane", "images/spawn/plane.png");
    game.load.image("truck", "images/spawn/truck.png");
    game.load.image("bulletTower", "images/spawn/bulletTower.png");
    game.load.image("bulletShip", "images/spawn/bulletShip.png");
    game.load.image("bulletTank", "images/spawn/bulletTank.png");
    game.load.image("bulletPlane", "images/spawn/bulletPlane.png");
    game.load.image("bulletTruck", "images/spawn/bulletTruck.png");
}

var Phaser;
var cursors;
var map;
var layer;
var loopInstance = 0;
var bgMusic;
var secondTimer, minuteTimer;
var second = 0, minute = 10;
//var secondCounter = 0, minuteCounter = 0;

// Player and Weapon
var player, weapon, fireball, nextFire = 0, fireRate = 238, fireButton;
var bomb, bombCharge = 0, bombReady = false, bombChargeText, bombDamageText, bombDamage = 500;
var playerExplode = false, deathTimer, explode;

// Bubbles
var bubbleGroup;
var bubbleIndexArray = 0;
var FuelText, DieselText, KeroseneText, NaphthaText, GasolineText, RefineryGasesText, ArmyChargedText;
var bubbleArray = ["CrudeOil", "Fuel", "Diesel", "Kerosene", "Naphtha", "Gasoline", "RefineryGases"];
var bubble07; // Refinery_Gases
var bubble08; // Residue_Bitumen
var bubbleGroupX = [];
var currentStatusBubble = [];
/*
CrudeOil in seperate group/array?
Look at bubbleInherit for reference to push to seperate group?
Seperate group because want CrudeOil to collide with Tiled Layer while when the other bubbles touch the Tiled Layer, they pop.

If CrudeOil in seperate array, CrudeOil bubbles will collide with tiles and as overlap with fireball, will be pushed into
var bubbleArray = ["Fuel", "Diesel", "Kerosene", "Naphtha", "Gasoline", "RefineryGases"];
new collision,overlap with CrudeOil seperate from bubbleGroup?

Fuel spawn createShip
Diesel spawn createTank
Kerosene spawn createPlane

^ all correctly, individually. var getKey is global variable so doesn't work now
*/
var getKey;

// Sprites that attack each other
var enemyShip, enemyShipTween, enemyWeapon, enemyFiringTimer = 0;
var enemyHealth = 10000, enemyHealthText;
var towerOne, towerTwo, towerThree;
var towerOneHealth = 1000, towerOneText, towerTwoHealth = 1000, towerTwoText, towerThreeHealth = 1000, towerThreeText;
var towerOneHeal, towerTwoHeal, towerThreeHeal;
var ship, tank, plane, truck, shipTween, tankTween, planeTween, truckTween;
var towerWeapon, towerFiringTimer = 0;
var shipWeapon, tankWeapon, planeWeapon, truckWeapon;
var powerUpCount = 1;
var shipDamage, tankDamage, planeDamage, truckDamage;
var towerDamageText, shipDamageText, tankDamageText, planeDamageText, truckDamageText;
/*var towerOneAlive = true, towerTwoAlive = true, towerThreeAlive = true, allTowersAlive = true;*/
var winText, loseText;

// 0 = Title Screen, 1 = Game
var gameMode = 0;
var loadingScreen;

function create() {
    bgMusic = game.add.audio("bgMusic", 1, true); bgMusic.play();
    game.physics.startSystem(Phaser.Physics.ARCADE); // Adding game physics
    cursors = game.input.keyboard.createCursorKeys();
    fireButton = game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    loadingScreen = game.add.sprite(0, 0, "loadingScreen");
}

function update() {
    if (gameMode == 0) {
        if (game.input.keyboard.addKey(Phaser.Keyboard.ENTER).isDown) {
            loadingScreen.destroy();
            gameMode = 1;
            createMap();
            createTowers();
            createEnemyShip();
            createEnemyBullets();
            createTowerBullets();
            createShipBullets();
            createTankBullets();
            createPlaneBullets();
            createTruckBullets();
            createPlayer();
            createFireball();
            //  Bomb
            bomb = game.add.sprite(768, 576, "bomb");
            bomb.visible = false;
            var countdown = game.add.audio('321'); countdown.play();
            game.time.events.add(Phaser.Timer.SECOND * 2, startGame, this);
            game.time.events.loop(Phaser.Timer.SECOND * 4, startGame, this);
            game.time.events.add(Phaser.Timer.MINUTE * 10, lose, this);
            
            winText = game.add.text(game.world.centerX,game.world.centerY,' ', { font: '84px Arial', fill: '#fff' });
            winText.anchor.setTo(0.5, 0.5);
            winText.fill = "#FFFFFF";
        	winText.stroke = "#000000";
        	winText.strokeThickness = 10;
            winText.visible = false;
            
            secondTimer = game.time.create(false);
            minuteTimer = game.time.create(false);
            secondTimer.loop(1000, minusSecond, this);
            //minuteTimer.loop(61000, minusMinute, this);
            secondTimer.start();
            minuteTimer.start();
            //minuteTimer.add(1000, minusMinute, this);
            
            secondTimer.visible = false;
            minuteTimer.visible = false;
            
            loseText = game.add.text(game.world.centerX,game.world.centerY,' ', { font: '84px Arial', fill: '#fff' });
            loseText.anchor.setTo(0.5, 0.5);
            loseText.fill = "#FFFFFF";
        	loseText.stroke = "#000000";
        	loseText.strokeThickness = 10;
            loseText.visible = false;
        }
    }
    // If the game is actually started, check for user input and collisions, in other words, play the game.
    else if (gameMode == 1) {
        secondTimer.visible = true;
        minuteTimer.visible = true;
        
        game.physics.arcade.overlap(towerOne, enemyWeapon, towerOneHit, null, this);
        game.physics.arcade.overlap(towerTwo, enemyWeapon, towerTwoHit, null, this);
        game.physics.arcade.overlap(towerThree, enemyWeapon, towerThreeHit, null, this);
        game.physics.arcade.overlap(enemyShip, towerWeapon, enemyHitByTower, null, this);
        game.physics.arcade.overlap(enemyShip, shipWeapon, enemyHitByShip, null, this);
        game.physics.arcade.overlap(enemyShip, tankWeapon, enemyHitByTank, null, this);
        game.physics.arcade.overlap(enemyShip, planeWeapon, enemyHitByPlane, null, this);
        game.physics.arcade.overlap(enemyShip, truckWeapon, enemyHitByTruck, null, this);
        
        controlPlayer();
        if (game.time.now > enemyFiringTimer) {enemyFires();}
        /*
        For one tower to reach health 0, takes 6 minutes.
        Add in tower explode if health == 0
        Tower alive = false, stop firing
        */
    	var randTower = game.rnd.between(1, 7);
    	if (game.time.now > towerFiringTimer) {
        	switch (randTower) {
        	    case 1:
        	        towerOneFires();
        	        break;
        	    case 2:
        	        towerTwoFires();
        	        break;
        	    case 3:
        	        towerThreeFires();
        	        break;
        	    case 4:
        	        towerOneFires();
        	        towerTwoFires();
        	        break;
        	    case 5:
        	        towerOneFires();
        	        towerThreeFires();
        	        break;
        	    case 6:
        	        towerTwoFires();
        	        towerThreeFires();
        	        break;
        	    case 7:
        	        towerOneFires();
        	        towerTwoFires();
        	        towerThreeFires();
        	        break;
                default:
                	break;
        	}
    	}
        game.physics.arcade.collide(bubbleGroup, layer);
        game.physics.arcade.overlap(player, bubble08, playerHit, null, this);
    	for (var i = 0; i < bubbleGroupX.length; i++) {
    		game.physics.arcade.collide(bubbleGroupX[i], layer);
    		game.physics.arcade.overlap(fireball, bubbleGroupX[i], bubbleSplit, null, this);
    		game.physics.arcade.collide(bubble07, layer);
    	}
    	if (enemyHealth <= 0) {
    	    //enemyShipAlive = false;
    	    enemyShip.kill();
    	}
    	enemyShip.events.onKilled.add(enemyShipBoom, this);
    }
}

function render() {
    game.debug.text(minute+":"+second, 700, 580);
}

function minusSecond() {
    second--;
    if(second <= -1) {
      second = 59;/*secondCounter++;*/
    minusMinute();
    }
}

function minusMinute() {
    minute--;
}

function createMap() {
    game.add.tileSprite(0, 0, 800, 608, "background");
    map = game.add.tilemap("map");
    map.addTilesetImage("world", "tileCollide");
    map.setCollisionBetween(1, 50);
    
    // Adding map layers
    layer = map.createLayer("layer");
    layer.resizeWorld();
    // game.world.setBounds(0, 0, 640, 608); // Setting new world bounds
    /*Don't set new world bounds as bullets will disappear when in contact*/
}

// Player section --------------------------------------------------------------
function createPlayer() {
    deathTimer = game.time.create(false);
    player = game.add.sprite(288, 608, "player");
    game.physics.enable(player);
    player.body.collideWorldBounds = true;
}

function controlPlayer() {
    game.physics.arcade.collide(player, layer);
    player.body.velocity.setTo(0, 0);
    if (cursors.left.isDown) {
        player.body.velocity.x -= 300;
    }
    else if (cursors.right.isDown) {
        player.body.velocity.x += 300;
    }
    if (fireButton.isDown) {
        shootFireball();
    }
    if (bombReady == true && cursors.up.isDown) {
        fireBomb();
        bomb.visible = false;
        bombCharge = 0;
        bombReady = false;
    }
}

function createFireball() {
    weapon = game.add.group();
    weapon.enableBody = true;
    weapon.physicsBodyType = Phaser.Physics.ARCADE;
    weapon.createMultiple(10000, "fireball");
    weapon.setAll("anchor.x", 0); // Anchor of where it fires from x
    weapon.setAll("anchor.y", 1.2);  // Anchor of where it fires from y
    weapon.setAll("outOfBoundsKill", true);
    weapon.setAll("checkWorldBounds", true);
}

// Player is hit by bubble08. Becomes "drenched" and if shoots weapon, will explode
function playerHit(player, residue) {
    var playerDrenchSound = game.add.audio("playerDrench"); playerDrenchSound.play();
    player.loadTexture("playerDrenched");
    playerExplode = true;
    deathTimer.loop(2000, residueOff, this);
    deathTimer.start();
}

// The residue wears off and player can start firing again
function residueOff() {
    playerExplode = false;
    deathTimer.destroy();
    player.loadTexture("player");
}

function playerReset() {
    player.reset(288, 608);
}

function shootFireball() {
    // If player shoots while drenched, will lose a life
    if (playerExplode == true) {
        var explodePlayerSound = game.add.audio("explode"); explodePlayerSound.play();
        fireball.kill();
        player.kill();
        game.time.events.add(Phaser.Timer.SECOND * 3, playerReset, this);
        //player.events.onKilled.add(playerMinusLife, this);
        explode = game.add.sprite(player.body.x-32, player.body.y-64, "explode");
        explode.animations.add("boom");
        explode.animations.play("boom", 30, false, true); //fps, loop, killonComplete
    }
    
    if (game.time.now > nextFire) {
        nextFire = game.time.now + fireRate; // Firing speed
        fireball = weapon.getFirstExists(false);
        
        if (fireball) {
            fireball.reset(player.x, player.y + 8);
            fireball.body.velocity.y = -400;
        }
    }
}

function createBomb() {
    bombCharge++;
    var bombChargeSound = game.add.audio("bombCharge"); bombChargeSound.play();
    if (bombCharge >= 5) {
        var bombReadySound = game.add.audio("bombReady"); bombReadySound.play();
        bombCharge = 5;
        bombReady = true;
        bomb.visible = true;
        
        bombDamageText = game.add.text(700, 552, "Bomb Damage: " + (bombDamage * powerUpCount), {font: "10px Arial"});
    	bombDamageText.align = "center";
    	bombDamageText.fill = "#FFFFFF";
    	bombDamageText.stroke = "#000000";
    	bombDamageText.strokeThickness = 5;
    }
    bombChargeText = game.add.text(700, 540, "Bomb Charge: " + bombCharge, {font: "10px Arial"});
	bombChargeText.align = "center";
	bombChargeText.fill = "#FFFFFF";
	bombChargeText.stroke = "#000000";
	bombChargeText.strokeThickness = 5;
}

function fireBomb() {
    var explodeBombSound = game.add.audio("explode"); explodeBombSound.play();
    enemyHealth = enemyHealth - (bombDamage * powerUpCount);
    enemyHealthText.text = "Enemy Health: " + enemyHealth;
    game.time.events.add(Phaser.Timer.SECOND, removebombDamageText, this);
    
    explode = game.add.sprite(enemyShip.body.x-30, enemyShip.body.y-25, "explode");
    explode.animations.add("boom");
    explode.animations.play("boom", 30, false, true); //fps, loop, killonComplete
}
// Player section --------------------------------------------------------------

function startGame() {
    bubbleInherit(bubbleArray[0], 0 , 415, 200, 0, 800, 1, "beforeGetShot");
    
    /*
    bubble01 = game.add.sprite(0, 415, "CrudeOil");
    game.physics.enable(bubble01, Phaser.Physics.ARCADE);
	bubble01.body.velocity.x = 200;
	bubble01.body.gravity.y = 800;
	bubble01.body.bounce.setTo(1);
	bubble01.checkWorldBounds = true;
	bubble01.body.collideWorldBounds = true;
	*/
	
    loopInstance++;
}

// Bubble section --------------------------------------------------------------
function bubbleInherit(bubbleSprite, newX, newY, vX, vY, gY, bounce, theProcess, theBubble) {
	bubbleGroup = game.add.group();
	var newB;
	var arrLength;
	var arrIndex;
	console.log(theProcess + ":"); //afterGetShot
	for (var i = 0; i < 1; i++) {
		newB = bubbleGroup.create(newX, newY, bubbleSprite);
		game.physics.enable(newB, Phaser.Physics.ARCADE);
		newB.body.velocity.x = vX;
		newB.body.velocity.y = vY;
		newB.body.gravity.y = gY;
		newB.body.bounce.setTo(bounce);
		newB.checkWorldBounds = true;
		newB.body.collideWorldBounds = true;
		
		if (loopInstance >= 0) {
			arrLength = bubbleGroupX.length;
			arrIndex = arrLength + i;
			if (theProcess == "beforeGetShot") {
			    console.log("'theProcess' is inside 'beforeGetShot'");
			    bubbleGroupX[arrIndex] = newB;
			    currentStatusBubble[arrIndex] = 0;
			}
			else {
			    var theIndexOfBubbleGotShot = bubbleGroupX.indexOf(theBubble);
			    // Get the index of the bubble which got shot
			    // Putting theIndexOfBubbleGotShot into the new array currentStatusBubble
			    console.log("     theIndexOfBubbleGotShot = " + theIndexOfBubbleGotShot); //not working?
			    console.log("       Status of shot Bubble = " + currentStatusBubble[theIndexOfBubbleGotShot]); //not working?
			    console.log("          Key of shot Bubble = " + theBubble.key);
			    
			    // Get the property of the shot bubble
			    getKey = bubbleGroupX[theIndexOfBubbleGotShot].key;
			    console.log("            bubbleGroupX key = " + getKey);
			    
			    // Changing bubbleGroupX's object so it is now a different one
			    bubbleGroupX.splice(theIndexOfBubbleGotShot, 1, newB);
			    theBubble.kill();
			}
		} 
	}
    console.log(bubbleSprite + ", bubbleIndexArray = " + bubbleIndexArray);
    console.log("           bubbleGroup total = " + bubbleGroup.total);
    console.log("arrIndex.key of bubbleGroupX = " + bubbleGroupX[arrIndex.key]);
    console.log("         bubbleGroupX length = " + bubbleGroupX.length);
    console.log("  currentStatusBubble length = " + currentStatusBubble.length);    
    console.log("-------------");
}

function bubbleSplit(fireball, spawnBubbles) {
    game.physics.arcade.collide(fireball, bubbleGroup); // When fireball collides with a bubble, will "bounce"
    fireball.destroy();
    bubble08 = game.add.sprite(spawnBubbles.world.x+8, spawnBubbles.world.y, "Residue");
    game.physics.enable(bubble08, Phaser.Physics.ARCADE);
    bubble08.checkWorldBounds = true;
    bubble08.events.onOutOfBounds.add(residueDrop, this);
    
    var velArray = [-200, 200];
    var randVel = game.rnd.pick(velArray);

// nY, vX, xY, gY, bounce, no.bubbles
// vX increase == increase angle, vY increase == decrease bounce height, gY increase == bounce faster, Bounce == "1" for 100% energy return
    switch (spawnBubbles.key) {
        case "CrudeOil":
            var bubblePopSound2 = game.add.audio("bubblePop"); bubblePopSound2.play();
            FuelText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y, "340 C", {font: "20px Arial"});
	        FuelText.align = "center";
	        FuelText.fill = "#FFFFFF";
	        FuelText.stroke = "#000000";
	        FuelText.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.HALF, removeFuelText, this);
	        bubble08.body.velocity.y = 200;
	        bubbleInherit(bubbleArray[1], spawnBubbles.world.x, spawnBubbles.world.y, randVel, -250, 800, 1, "afterGetShot", spawnBubbles);
	        tileCollisionOff();
            break;
        case "Fuel":
            var bubblePopSound3 = game.add.audio("bubblePop"); bubblePopSound3.play();
            DieselText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y, "260 C", {font: "20px Arial"});
	        DieselText.align = "center";
	        DieselText.fill = "#FFFFFF";
	        DieselText.stroke = "#000000";
	        DieselText.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.HALF, removeDieselText, this);
	        bubble08.body.velocity.y = 200;
	        bubbleInherit(bubbleArray[2], spawnBubbles.world.x, spawnBubbles.world.y, randVel, -400, 800, 1, "afterGetShot", spawnBubbles);
	        tileCollisionOff(); 
            break;
        case "Diesel":
            var bubblePopSound4 = game.add.audio("bubblePop"); bubblePopSound4.play();
            KeroseneText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y, "180 C", {font: "20px Arial"});
	        KeroseneText.align = "center";
	        KeroseneText.fill = "#FFFFFF";
	        KeroseneText.stroke = "#000000";
	        KeroseneText.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.HALF, removeKeroseneText, this);
	        bubble08.body.velocity.y = 250;
	        bubbleInherit(bubbleArray[3], spawnBubbles.world.x, spawnBubbles.world.y, randVel, -550, 800, 1, "afterGetShot", spawnBubbles);
	        tileCollisionOff(); 
            break;
        case "Kerosene":
            var bubblePopSound5 = game.add.audio("bubblePop"); bubblePopSound5.play();
            NaphthaText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y, "110 C", {font: "20px Arial"});
	        NaphthaText.align = "center";
	        NaphthaText.fill = "#FFFFFF";
	        NaphthaText.stroke = "#000000";
	        NaphthaText.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.HALF, removeNaphthaText, this);
	        bubble08.body.velocity.y = 250;
	        bubbleInherit(bubbleArray[4], spawnBubbles.world.x, spawnBubbles.world.y, randVel, -650, 800, 1, "afterGetShot", spawnBubbles);
	        tileCollisionOff(); 
            break;
        case "Naphtha":
            var bubblePopSound6 = game.add.audio("bubblePop"); bubblePopSound6.play();
            bubbleIndexArray = 4;
            GasolineText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y, "40 C", {font: "20px Arial"});
	        GasolineText.align = "center";
	        GasolineText.fill = "#FFFFFF";
	        GasolineText.stroke = "#000000";
	        GasolineText.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.HALF, removeGasolineText, this);
	        bubble08.body.velocity.y = 300;
	        bubbleInherit(bubbleArray[5], spawnBubbles.world.x, spawnBubbles.world.y, randVel, -700, 800, 1, "afterGetShot", spawnBubbles);
	        tileCollisionOff();
            break;
        case "Gasoline":
            var bubblePopSound7 = game.add.audio("bubblePop"); bubblePopSound7.play();
            RefineryGasesText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y, "20 C", {font: "20px Arial"});
	        RefineryGasesText.align = "center";
	        RefineryGasesText.fill = "#FFFFFF";
	        RefineryGasesText.stroke = "#000000";
	        RefineryGasesText.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.HALF, removeRefineryGasesText, this);
	        ArmyChargedText = game.add.text(spawnBubbles.world.x, spawnBubbles.world.y-64, "Army power up!!!", {font: "20px Arial"});
        	ArmyChargedText.align = "center";
        	ArmyChargedText.fill = "#00FF40"; //Light Green
        	ArmyChargedText.stroke = "#000000";
        	ArmyChargedText.strokeThickness = 5;
        	game.time.events.add(Phaser.Timer.HALF, removeArmyChargedText, this);
	        
	        bubble08.body.velocity.y = 400;
	        bubble07 = game.add.sprite(spawnBubbles.world.x, spawnBubbles.world.y, "RefineryGases");
	        game.physics.enable(bubble07, Phaser.Physics.ARCADE);
	        bubble07.body.velocity.y = -200;
	        bubble07.body.gravity.y = -1;
	        bubble07.body.bounce.setTo(0);
	        bubble07.checkWorldBounds = true;
	        bubble07.events.onOutOfBounds.add(gasPop, this);
	        tileCollisionOff();
			spawnBubbles.kill();
            var theIndexOfBubbleGotShot = bubbleGroupX.indexOf(spawnBubbles);
			bubbleGroupX.splice(theIndexOfBubbleGotShot, 1);
			powerUpCount++;
			var armyPowerUpSound = game.add.audio("armyPowerUp"); armyPowerUpSound.play();
            break;
        default:
            bubble08.body.velocity.y = 500;
            break;
    }
}

function gasPop() {bubble07.kill();}
function residueDrop() {
    bubble08.kill();
    var randTower = game.rnd.between(1, 3);
    switch(randTower) {
        case 1:
            var towerHealSound1 = game.add.audio("towerHeal"); towerHealSound1.play();
            towerOneHealth = towerOneHealth + 100;
            towerOneText.text = towerOneHealth;
            towerOneHeal = game.add.text(655, 455, "Healed!", {font: "10px Arial"});
	        towerOneHeal.align = "center";
	        towerOneHeal.fill = "#82FA58";
	        towerOneHeal.stroke = "#000000";
	        towerOneHeal.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.SECOND, removeTowerOneHeal, this);
            break;
        case 2:
            var towerHealSound2 = game.add.audio("towerHeal"); towerHealSound2.play();
            towerTwoHealth = towerTwoHealth + 100;
            towerTwoText.text = towerTwoHealth;
            towerTwoHeal = game.add.text(710, 455, "Healed!", {font: "10px Arial"});
	        towerTwoHeal.align = "center";
	        towerTwoHeal.fill = "#82FA58";
	        towerTwoHeal.stroke = "#000000";
	        towerTwoHeal.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.SECOND, removeTowerTwoHeal, this);
            break;
        case 3:
            var towerHealSound3 = game.add.audio("towerHeal"); towerHealSound3.play();
            towerThreeHealth = towerThreeHealth + 100;
            towerThreeText.text = towerThreeHealth;
            towerThreeHeal = game.add.text(760, 455, "Healed!", {font: "10px Arial"});
	        towerThreeHeal.align = "center";
	        towerThreeHeal.fill = "#82FA58";
	        towerThreeHeal.stroke = "#000000";
	        towerThreeHeal.strokeThickness = 5;
	        game.time.events.add(Phaser.Timer.SECOND, removeTowerThreeHeal, this);
            break;
        default: break;
    }
}

function tileCollisionOff() {
    // If bubble collides these tiles, will destroy
    if(bubble07) {
        map.setTileIndexCallback(1, correctBubble, this);
        map.setTileIndexCallback(2, correctBubble, this);
        map.setTileIndexCallback(3, correctBubble, this);
        map.setTileIndexCallback(4, correctBubble, this);
        map.setTileIndexCallback(5, correctBubble, this);
        map.setTileIndexCallback(6, correctBubble, this);
        map.setTileIndexCallback(7, correctBubble, this);
        map.setTileIndexCallback(8, correctBubble, this);
        map.setTileIndexCallback(9, correctBubble, this);
    }
    else {
        map.setTileIndexCallback(10, correctBubble, this);
        map.setTileIndexCallback(17, correctBubble, this);
        map.setTileIndexCallback(18, correctBubble, this);
        map.setTileIndexCallback(19, correctBubble, this);
        map.setTileIndexCallback(20, correctBubble, this);
        map.setTileIndexCallback(27, correctBubble, this);
        map.setTileIndexCallback(28, correctBubble, this);
        //map.setTileIndexCallback(29, correctBubble, this);
        map.setTileIndexCallback(30, correctBubble, this);
        map.setTileIndexCallback(37, correctBubble, this);
        //map.setTileIndexCallback(39, correctBubble, this);
        map.setTileIndexCallback(40, correctBubble, this);    
    }
}

// as go up the chain, fire less but more powerful
function correctBubble(correctBubble) {
    correctBubble.kill();
    var bubblePopSound = game.add.audio("bubblePop"); bubblePopSound.play();
    switch(getKey) {
	    case "CrudeOil": createShip(); game.time.events.repeat(Phaser.Timer.SECOND, 7, shipFires, this); break; // Fuel
	    case "Fuel": createTank(); game.time.events.repeat(Phaser.Timer.SECOND, 4, tankFires, this); break; // Diesel
	    case "Diesel": createPlane(); game.time.events.repeat(Phaser.Timer.SECOND, 2, planeFires, this); break; // Kerosene
	    case "Kerosene": createBomb(); break; // Naphtha
	    case "Naphtha": createTruck(); game.time.events.repeat(Phaser.Timer.SECOND*2, 2, truckFires, this); break; // Gasoline
	    case "Gasoline": break;
	    default: break;
    }
}

// Bubble section --------------------------------------------------------------
// -----------------------------------------------------------------------------

// Sprites section -------------------------------------------------------------
function createEnemyShip() {
    enemyShip = game.add.sprite(688, 0, "enemyShip");
    game.physics.enable(enemyShip, Phaser.Physics.ARCADE);
    enemyShipTween = game.add.tween(enemyShip).to({x: game.rnd.between(640, 740), y: game.rnd.between(0, 112)}, 1000, "Linear", true);
    enemyShipTween.onComplete.add(enemyShipRandomMovement, this);
    enemyHealthText = game.add.text(640, 0, "Enemy Health: " + enemyHealth, {font: "16px Arial"});
	enemyHealthText.align = "center";
	enemyHealthText.fill = "#FA5882";
	enemyHealthText.stroke = "#000000";
	enemyHealthText.strokeThickness = 5;
}

function enemyShipRandomMovement() {
    var rX = game.rnd.between(-100, 100);
    var rY = game.rnd.between(-112, 112);
    var rSwitch = game.rnd.between(1, 3);
    switch(rSwitch) {
        case 1:
            var rValueX = enemyShip.world.x + rX;
            if (rValueX <= 640) {rValueX = 640}
            if (rValueX >= 740) {rValueX = 740}
            enemyShipTween.to({x: rValueX            }, 500, "Linear", true);
            enemyShipTween.onComplete.add(enemyShipRandomMovement, this);
            break;
        case 2:
            var rValueY = enemyShip.world.y + rY;
            if (rValueY <=   0) {rValueY =   0}
            if (rValueY >= 112) {rValueY = 112}
            enemyShipTween.to({            y: rValueY}, 750, "Linear", true);
            enemyShipTween.onComplete.add(enemyShipRandomMovement, this);
            break;
        case 3:
            var rValueX2 = enemyShip.world.x + rX;
            var rValueY2 = enemyShip.world.y + rY;
            if (rValueX2 <= 640) {rValueX2 = 640}
            if (rValueX2 >= 740) {rValueX2 = 740}
            if (rValueY2 <=   0) {rValueY2 =   0}
            if (rValueY2 >= 112) {rValueY2 = 112}
            enemyShipTween.to({x: rValueX2, y: rValueY2}, 1000, "Linear", true);
            enemyShipTween.onComplete.add(enemyShipRandomMovement, this);
            break;
        default: break;
    }
}

function createEnemyBullets() {
    enemyWeapon = game.add.group();
    enemyWeapon.enableBody = true;
    enemyWeapon.physicsBodyType = Phaser.Physics.ARCADE;
    enemyWeapon.createMultiple(500, "enemyBullet");
    enemyWeapon.setAll("anchor.x", -2.35);
    enemyWeapon.setAll("anchor.y", -4.5);
    enemyWeapon.setAll("outOfBoundsKill", true);
    enemyWeapon.setAll("checkWorldBounds", true);
}

function enemyFires() {
    var enemyFiringSound = game.add.audio("enemyFiring"); enemyFiringSound.play();
    var randTimer = game.rnd.between(1, 9) * 100;
    var enemyBullet = enemyWeapon.getFirstExists(false);
    enemyBullet.reset(enemyShip.body.x, enemyShip.body.y, 1);
    var randTower = game.rnd.between(1, 3);
    switch (randTower) {
        case 1: game.physics.arcade.moveToObject(enemyBullet, towerOne, 200); break;
        case 2: game.physics.arcade.moveToObject(enemyBullet, towerTwo, 200); break;
        case 3: game.physics.arcade.moveToObject(enemyBullet, towerThree, 200); break;
        default: break;
    }
    enemyFiringTimer = game.time.now + randTimer;
}

function enemyShipBoom() {
    explode = game.add.sprite(enemyShip.body.x-32, enemyShip.body.y-64, "explode");
    explode.animations.add("boom");
    explode.animations.play("boom", 30, false, true); //fps, loop, killonComplete
    
    game.paused = true;
    winText.text = " You Win!";
    winText.visible = true;
}

function lose() {
    game.paused = true;
    loseText.text = " You Lost...";
    loseText.visible = true;
}

function enemyHitByTower(enemyShip, friendlyBulletTower) {
    var enemyHitSound = game.add.audio("enemyHit"); enemyHitSound.play();
    friendlyBulletTower.kill();
    enemyHealth = enemyHealth - powerUpCount;
    enemyHealthText.text = "Enemy Health: " + enemyHealth;
    
    towerDamageText = game.add.text(600, 540, "Tower Damage: " + powerUpCount, {font: "10px Arial"});
	towerDamageText.align = "center";
	towerDamageText.fill = "#FFFFFF";
	towerDamageText.stroke = "#000000";
	towerDamageText.strokeThickness = 5;
}

function enemyHitByShip(enemyShip, friendlyBulletShip) {
    var enemyHitSound = game.add.audio("enemyHit"); enemyHitSound.play();
    friendlyBulletShip.kill();
    shipDamage = 10 * powerUpCount;
    enemyHealth = enemyHealth - shipDamage;
    enemyHealthText.text = "Enemy Health: " + enemyHealth;

	shipDamageText = game.add.text(600, 552, "  Ship Damage: " + shipDamage, {font: "10px Arial"});
	shipDamageText.align = "center";
	shipDamageText.fill = "#FFFFFF";
	shipDamageText.stroke = "#000000";
	shipDamageText.strokeThickness = 5;
}

function enemyHitByTank(enemyShip, friendlyBulletTank) {
    var enemyHitSound = game.add.audio("enemyHit"); enemyHitSound.play();
    friendlyBulletTank.kill();
    tankDamage = 20 * powerUpCount;
    enemyHealth = enemyHealth - tankDamage;
    enemyHealthText.text = "Enemy Health: " + enemyHealth;
	
	tankDamageText = game.add.text(600, 564, "  Tank Damage: " + tankDamage, {font: "10px Arial"});
	tankDamageText.align = "center";
	tankDamageText.fill = "#FFFFFF";
	tankDamageText.stroke = "#000000";
	tankDamageText.strokeThickness = 5;
}

function enemyHitByPlane(enemyShip, friendlyBulletPlane) {
    var enemyHitSound = game.add.audio("enemyHit"); enemyHitSound.play();
    friendlyBulletPlane.kill();
    planeDamage = 30 * powerUpCount;
    enemyHealth = enemyHealth - planeDamage;
    enemyHealthText.text = "Enemy Health: " + enemyHealth;
	
	planeDamageText = game.add.text(600, 576, "Plane Damage: " + planeDamage, {font: "10px Arial"});
	planeDamageText.align = "center";
	planeDamageText.fill = "#FFFFFF";
	planeDamageText.stroke = "#000000";
	planeDamageText.strokeThickness = 5;
}

function enemyHitByTruck(enemyShip, friendlyBulletTruck) {
    var enemyHitSound = game.add.audio("enemyHit"); enemyHitSound.play();
    friendlyBulletTruck.kill();
    truckDamage = 100 * powerUpCount;
    enemyHealth = enemyHealth - truckDamage;
    enemyHealthText.text = "Enemy Health: " + enemyHealth;
	
	truckDamageText = game.add.text(600, 588, "Truck Damage: " + truckDamage, {font: "10px Arial"});
	truckDamageText.align = "center";
	truckDamageText.fill = "#FFFFFF";
	truckDamageText.stroke = "#000000";
	truckDamageText.strokeThickness = 5;
}
// Enemy sprite -- -------------------------------------------------------------

// Towers ----------------------------------------------------------------------
function createTowers() {
    towerOne = game.add.sprite(660, 500, "tower");
    game.physics.enable(towerOne, Phaser.Physics.ARCADE);
    towerOne.enableBody = true;
    towerOneText = game.add.text(660, 475, towerOneHealth, {font: "10px Arial"});
	towerOneText.align = "center";
	towerOneText.fill = "#01DF01";
	towerOneText.stroke = "#000000";
	towerOneText.strokeThickness = 5;
    
    towerTwo = game.add.sprite(715, 500, "tower");
    game.physics.enable(towerTwo, Phaser.Physics.ARCADE);
    towerTwo.enableBody = true;
    towerTwoText = game.add.text(715, 475, towerTwoHealth, {font: "10px Arial"});
	towerTwoText.align = "center";
	towerTwoText.fill = "#01DF01";
	towerTwoText.stroke = "#000000";
	towerTwoText.strokeThickness = 5;
    
    towerThree = game.add.sprite(770, 500, "tower");
    game.physics.enable(towerThree, Phaser.Physics.ARCADE);
    towerThree.enableBody = true;
    towerThreeText = game.add.text(770, 475, towerThreeHealth, {font: "10px Arial"});
	towerThreeText.align = "center";
	towerThreeText.fill = "#01DF01";
	towerThreeText.stroke = "#000000";
	towerThreeText.strokeThickness = 5;
}

function createTowerBullets() {
    towerWeapon = game.add.group();
    towerWeapon.enableBody = true;
    towerWeapon.physicsBodyType = Phaser.Physics.ARCADE;
    towerWeapon.createMultiple(500, "bulletTower");
    towerWeapon.setAll("anchor.x", 0);
    towerWeapon.setAll("anchor.y", 0);
    towerWeapon.setAll("outOfBoundsKill", true);
    towerWeapon.setAll("checkWorldBounds", true);
}

function towerOneFires() {
    var towerFiringSound = game.add.audio("towerFiring"); towerFiringSound.play();
    var randTimer = game.rnd.between(1, 4) * 1000;
    var friendlyBullet = towerWeapon.getFirstExists(false);
    friendlyBullet.reset(670, 500);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
    towerFiringTimer = game.time.now + randTimer;
}

function towerTwoFires() {
    var towerFiringSound = game.add.audio("towerFiring"); towerFiringSound.play();
    var randTimer = game.rnd.between(1, 4) * 1000;
    var friendlyBullet = towerWeapon.getFirstExists(false);
    friendlyBullet.reset(725, 500);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
    towerFiringTimer = game.time.now + randTimer;
}

function towerThreeFires() {
    var towerFiringSound = game.add.audio("towerFiring"); towerFiringSound.play();
    var randTimer = game.rnd.between(1, 4) * 1000;
    var friendlyBullet = towerWeapon.getFirstExists(false);
    friendlyBullet.reset(780, 500);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
    towerFiringTimer = game.time.now + randTimer;
}

function towerOneHit(towerOne, enemyWeapon) {
    var towerHitSound = game.add.audio("towerHit"); towerHitSound.play();
    enemyWeapon.kill();
    towerOneHealth = towerOneHealth - 4;
    towerOneText.text = towerOneHealth;
}

function towerTwoHit(towerTwo, enemyWeapon) {
    var towerHitSound = game.add.audio("towerHit"); towerHitSound.play();
    enemyWeapon.kill();
    towerTwoHealth = towerTwoHealth - 4;
    towerTwoText.text = towerTwoHealth;
}

function towerThreeHit(towerThree, enemyWeapon) {
    var towerHitSound = game.add.audio("towerHit"); towerHitSound.play();
    enemyWeapon.kill();
    towerThreeHealth = towerThreeHealth - 4;
    towerThreeText.text = towerThreeHealth;
}

// Friendly sprites ------------------------------------------------------------
function createShip() {
    var shipSound = game.add.audio("ship"); shipSound.play();
    var y = game.rnd.between(480, 500);
    ship = game.add.sprite(640, y, "ship");
    game.world.sendToBack(ship);
    game.world.moveUp(ship);
    game.physics.enable(ship, Phaser.Physics.ARCADE);
    shipTween = game.add.tween(ship).to({x: 800, y: y}, 10000, "Linear", true);
    shipTween.onComplete.add(killShip, this);
}

function createShipBullets() {
    shipWeapon = game.add.group();
    shipWeapon.enableBody = true;
    shipWeapon.physicsBodyType = Phaser.Physics.ARCADE;
    shipWeapon.createMultiple(500, "bulletShip");
    shipWeapon.setAll("anchor.x", 0);
    shipWeapon.setAll("anchor.y", 0);
    shipWeapon.setAll("outOfBoundsKill", true);
    shipWeapon.setAll("checkWorldBounds", true);
}

function createTank() {
    var tankSound = game.add.audio("tank"); tankSound.play();
    tank = game.add.sprite(640, 512, "tank");
    game.physics.enable(tank, Phaser.Physics.ARCADE);
    tankTween = game.add.tween(tank).to({x: 800}, 5000, "Linear", true);
    tankTween.onComplete.add(killTank, this);
}

function createTankBullets() {
    tankWeapon = game.add.group();
    tankWeapon.enableBody = true;
    tankWeapon.physicsBodyType = Phaser.Physics.ARCADE;
    tankWeapon.createMultiple(500, "bulletTank");
    tankWeapon.setAll("anchor.x", 0);
    tankWeapon.setAll("anchor.y", 0);
    tankWeapon.setAll("outOfBoundsKill", true);
    tankWeapon.setAll("checkWorldBounds", true);
}

function createPlane() {
    var planeSound = game.add.audio("plane"); planeSound.play();
    var y = game.rnd.between(192, 416);
    plane = game.add.sprite(640, y, "plane");
    game.physics.enable(plane, Phaser.Physics.ARCADE);
    planeTween = game.add.tween(plane).to({x: 800, y: y}, 3000, "Linear", true);
    planeTween.onComplete.add(killPlane, this);
}

function createPlaneBullets() {
    planeWeapon = game.add.group();
    planeWeapon.enableBody = true;
    planeWeapon.physicsBodyType = Phaser.Physics.ARCADE;
    planeWeapon.createMultiple(500, "bulletPlane");
    planeWeapon.setAll("anchor.x", 0);
    planeWeapon.setAll("anchor.y", 0);
    planeWeapon.setAll("outOfBoundsKill", true);
    planeWeapon.setAll("checkWorldBounds", true);
}

function createTruck() {
    var truckSound = game.add.audio("truck"); truckSound.play();
    truck = game.add.sprite(640, 512, "truck");
    game.physics.enable(truck, Phaser.Physics.ARCADE);
    truckTween = game.add.tween(truck).to({x: 800}, 6000, "Linear", true);
    truckTween.onComplete.add(killTruck, this);
}

function createTruckBullets() {
    truckWeapon = game.add.group();
    truckWeapon.enableBody = true;
    truckWeapon.physicsBodyType = Phaser.Physics.ARCADE;
    truckWeapon.createMultiple(500, "bulletTruck");
    truckWeapon.setAll("anchor.x", 0);
    truckWeapon.setAll("anchor.y", 0);
    truckWeapon.setAll("outOfBoundsKill", true);
    truckWeapon.setAll("checkWorldBounds", true);
}

// Kill Friendlies -------------------------------------------------------------
function killShip() {ship.kill();}
function killTank() {tank.kill();}
function killPlane() {plane.kill();}
function killTruck() {truck.kill();}

// Fire Friendlies -------------------------------------------------------------
function shipFires() {
    var shipFiringSound = game.add.audio("shipFiring"); shipFiringSound.play();   
    var friendlyBullet = shipWeapon.getFirstExists(false);
    friendlyBullet.reset(ship.body.x, ship.body.y);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
}

function tankFires() {
    var tankFiringSound = game.add.audio("tankFiring"); tankFiringSound.play();  
    var friendlyBullet = tankWeapon.getFirstExists(false);
    friendlyBullet.reset(tank.body.x, tank.body.y);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
}

function planeFires() {
    var planeFiringSound = game.add.audio("planeFiring"); planeFiringSound.play();  
    var friendlyBullet = planeWeapon.getFirstExists(false);
    friendlyBullet.reset(plane.body.x, plane.body.y);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
}

function truckFires() {
    var truckFiringSound = game.add.audio("truckFiring"); truckFiringSound.play();  
    var friendlyBullet = truckWeapon.getFirstExists(false);
    friendlyBullet.reset(truck.body.x, truck.body.y);
    game.physics.arcade.moveToObject(friendlyBullet, enemyShip, 100);
}

function removeFuelText() {FuelText.destroy();}
function removeDieselText() {DieselText.destroy();}
function removeKeroseneText() {KeroseneText.destroy();}
function removeNaphthaText() {NaphthaText.destroy();}
function removeGasolineText() {GasolineText.destroy();}
function removeRefineryGasesText() {RefineryGasesText.destroy();}
function removeArmyChargedText() {ArmyChargedText.destroy();}
function removeTowerOneHeal() {towerOneHeal.destroy();}
function removeTowerTwoHeal() {towerTwoHeal.destroy();}
function removeTowerThreeHeal() {towerThreeHeal.destroy();}
function removebombDamageText() {bombDamageText.destroy();}