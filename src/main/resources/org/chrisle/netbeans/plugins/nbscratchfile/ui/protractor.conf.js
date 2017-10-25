// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/docs/referenceConf.js

// Other Resource they could help
// http://www.webdriverjs.com/protractor-example-with-typescript/
// https://medium.com/@cnishina/protractor-with-typescript-2eae05016929

/*global jasmine */
//import SpecReporter from 'jasmine-spec-reporter';
//import HtmlScreenshotReporter from 'protractor-jasmine2-screenshot-reporter';

//var screenshotReporter = new HtmlScreenshotReporter({
//    dest: 'e2e/screenshots',
//    cleanDestination: true,
//    showSummary: false,
//    showConfiguration: false,
//    captureOnlyFailedSpecs: true,
//    reportOnlyFailedSpecs: true,
//    reportTitle: null
//});

exports.config = {
    allScriptsTimeout: 11000,
    specs: [
        './e2e/tests/*.spec.ts'
    ],
    capabilities: {
        browserName: 'chrome'
    },
    directConnect: false,
    baseUrl: 'http://localhost:9000',
    framework: 'mocha',
    useAllAngular2AppRoots: false,

    mochaOpts: {
        showColors: true,
        timeout: 30000, // ms
        print: function() {
        }
    },

    beforeLaunch: function() {
        require('ts-node').register({
            project: 'e2e'
        });
    },

    onPrepare: function() {
        //        jasmine.getEnv().addReporter(new SpecReporter());
        //        jasmine.getEnv().addReporter(screenshotReporter);
    }
};