const nsg = require('node-sprite-generator');

nsg({
    src: [
        'dist/icons/*.png'
    ],
    compositor: 'jimp',
    stylesheet: 'prefixed-css',
    spritePath: 'dist/sprite.png',
    stylesheetPath: 'dist/sprite.css',
    stylesheetOptions: {
        prefix: 'svg-'
    }
}, (err) => {
    if(err) {
        console.log('Error occured: \n');
        console.log(err);
    } else {
        console.log('Sprite generated!');
    }
});