// solves `SyntaxError: Unexpected token import`
require("babel-register")({
    presets: [ 'es2015' ]
});
const Jasmine2HtmlReporter = require('protractor-jasmine2-html-reporter');

exports.config = {
    directConnect: true,
    specs: ['specs/*Spec.js'],
    baseUrl: 'http://google.com',
    framework: 'jasmine',

    onPrepare: () => {
        // set browser size...
        browser.manage().window().setSize(1024, 800);

        // better jasmine 2 reports...
        jasmine.getEnv().addReporter(
            new Jasmine2HtmlReporter({
                takeScreenshots: true,
                takeScreenshotsOnlyOnFailures: true,
                savePath: 'target/screenshots'
            })
        );
    },

    capabilities: {
        browserName: 'chrome',
        shardTestFiles: true,
        maxInstances: 2,
        chromeOptions: {
            args: [
                // disable chrome's wakiness
                '--disable-infobars',
                '--disable-extensions',
                'verbose',
                'log-path=/tmp/chromedriver.log'
            ],
            prefs: {
                // disable chrome's annoying password manager
                'profile.password_manager_enabled': false,
                'credentials_enable_service': false,
                'password_manager_enabled': false
            }
        }
    },

    jasmineNodeOpts: {
        showColors: true,
        displaySpecDuration: true,
        // overrides jasmine's print method to report dot syntax for custom reports
        print: () => {},
        defaultTimeoutInterval: 50000
    }
};