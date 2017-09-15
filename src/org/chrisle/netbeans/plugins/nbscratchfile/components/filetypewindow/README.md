# Knockout TypeScript Live Search Sample
This project sample uses TypeScript 2.*, Knockout, Webpack 3.*, Mocha (+Chai assertion lib), Protractor (with Mocha) and Scss.
There is no need to install stuff globally, everything works right inside the sample folder.


## First step
Open the command line of your choice, navigate to the project sample folder and do the following
```
npm i
```

## Usage
The sample uses npm scripts, right inside the package.json

* Start the webserver which builds the app, watches for file changes and does a livereload.
The URL is http://localhost:9000/generated/, The server creates a temp folder for the built app
to deliver it to the client (Maybe in memory, I don't know).
```
npm run serve
```

* Create a build for development
```
npm run build-dev
```

* Create a build for production
```
npm run build-prod
```

* Run unit tests
```
npm run test
```

* Run end-to-end tests
```
npm run e2e
```

* Generate SVG sprite from SVG icons folder (app/icons)
```
npm run gen-sprite
```


So basically thats all you have to do to start coding!

## Known problems
While using the liveserver for livecoding, atm there is no script to generate the css file from the svg sprite
with all the classes inside of it to use. Atm it is only possible to see the result with icons in the UI after call
`npm run build-dev` and call the index.html file from 'dist' in your browser.