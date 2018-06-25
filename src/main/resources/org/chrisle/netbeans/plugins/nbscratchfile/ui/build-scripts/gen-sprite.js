const nsg = require('node-sprite-generator');

nsg({
    src: [
        '.tmp/icons/*.png'
    ],
    compositor: 'jimp',
    stylesheet: 'prefixed-css',
    spritePath: './.tmp/sprite.png',
    stylesheetPath: './.tmp/sprite.css',
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