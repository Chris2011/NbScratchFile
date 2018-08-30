# Vue TypeScript Live Search Sample
This project sample uses TypeScript 2.*, Vue 2.5, Webpack 4.*, Mocha (+Chai assertion lib), Protractor (with Mocha) and Scss.
There is no need to install stuff globally, everything works right inside the sample folder.


## First step
Open the command line of your choice, navigate to the project sample folder and do the following
```
npm i
```

## Usage
The sample uses npm scripts, right inside the package.json

* Start the webserver which builds the app, watches for file changes and does a livereload.
The URL is http://localhost:9000/, The server creates a temp folder (in memory) for the built app
to deliver it to the client.
```
npm run serve
```

* Show local webpack version
```
npm run ver
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

* Fast build, to test new stuff fast and simple (Will run only webpack)
```
npm run fast-build-dev
```

* Analyze package sizes
```
npm run analyze-package
```

* Sourcemap explorer
```
npm run sourcemap-explorer
```

* Generate SVG to PNG
```
npm run svg2png
```

So basically thats all you have to do to start coding!