var createState = {
    preload: function() {
        console.log("preload state inside the create state");
        //  37x45 is the size of each frame

        //  There are 18 frames in the PNG - you can leave this value blank if the frames fill up the entire PNG, but in this case there are some blank frames at the end, so we tell the loader how many to load
        game.load.spritesheet('mummy', 'assets/metalslug_mummy37x45.png', 37, 45, 18);
    },
    create: function() {
        console.log("create state inside create state");
        var mummy = game.add.sprite(300, 200, 'mummy');

        //  Here we add a new animation called 'walk'
        //  Because we didn't give any other parameters it's going to make an animation from all available frames in the 'mummy' sprite sheet
        var walk = mummy.animations.add('walk');

        //  And this starts the animation playing by using its key ("walk")
        //  30 is the frame rate (30fps)
        //  true means it will loop when it finishes
        mummy.animations.play('walk', 30, true);
    }
};