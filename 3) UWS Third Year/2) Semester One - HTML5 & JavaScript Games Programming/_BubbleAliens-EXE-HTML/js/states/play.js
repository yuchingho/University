var playState = {
    create: function() {
        createWorld();
        createPlayer();
    },
    
    update: function() {
        controlPlayer();
        createFireball();
    },
};