var player;
var playerExplode = false;
var playerDeathTimer;
var playerAmmo;
var playerShoot;
var fireNext = 0;
var fireRate = 238;
var fireButton;

var bomb;
var bombReady = false;
var bombCharge = 0;
var bombChargeText;
var bombDamage = 500;
var bombDamageText;

var explode;

function preloadPlayer() {
    game.load.image("player", "images/player.png");
    game.load.image("playerDrenched", "images/playerDrenched.png");
    game.load.image("playerShoot", "images/playerShoot.png");
    game.load.image("playerBomb", "images/playerBomb.png");
    game.load.image("playerBombReady", "images/playerBombReady.png");
    game.load.spritesheet("playerExplode", "images/playerExplode.png", 64, 64);
}

function createPlayer() {
    game.physics.startSystem(Phaser.Physics.ARCADE); // Adding game physics
    cursors = game.input.keyboard.createCursorKeys();
    fireButton = game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    
    player = game.add.sprite(288, 608, "player");
    game.physics.enable(player);
    player.body.collideWorldBounds = true;
    playerDeathTimer = game.time.create(false);
}

function createFireball() {
    playerAmmo = game.add.group();
    playerAmmo.enableBody = true;
    playerAmmo.physicsBodyType = Phaser.Physics.ARCADE;
    playerAmmo.createMultiple(10000, "playerShoot");
    playerAmmo.setAll("anchor.x", -0.5); // Anchor of where it fires from x
    playerAmmo.setAll("anchor.y", 1.2);  // Anchor of where it fires from y
    playerAmmo.setAll("outOfBoundsKill", true);
    playerAmmo.setAll("checkWorldBounds", true);
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
        playerShoot();
    }
    if (bombReady == true && cursors.up.isDown) {
        fireBomb();
        bomb.visible = false;
        bombCharge = 0;
        bombReady = false;
    }
}
    
 function playerShoot() {
     // If player shoots while drenched, will lose a life
     if (playerExplode == true) {
        //var explodePlayerSound = game.add.audio("explode"); explodePlayerSound.play();
        //playerShoot.kill();
        player.kill();
        game.time.events.add(Phaser.Timer.SECOND * 3, playerReset, this);
        //player.events.onKilled.add(playerMinusLife, this);
        explode = game.add.sprite(player.body.x-32, player.body.y-64, "explode");
        explode.animations.add("boom");
        explode.animations.play("boom", 30, false, true); //fps, loop, killonComplete
    }
    
    if (game.time.now > fireNext) {
        fireNext = game.time.now + fireRate; // Firing speed
        //playerShoot = playerAmmo.getFirstExists(false);

        if (playerShoot) {
            //playerShoot.reset(player.x, player.y + 8);
            playerShoot.body.velocity.y = -400;
        }
    }
}