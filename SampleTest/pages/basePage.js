var fs = require('fs');

export default class BasePage {
    constructor() {
        this.timeout = 5000;
        this.testData = this.getData();
    }

    loaded() {
        return browser.wait(() => {
            return this.pageLoaded();
        }, this.timeout, 'timeout: waiting for page to load. The url is: ' + this.url);
    }

    goto() {
        browser.get(this.url, this.timeout.xl);
        return this.loaded();
    }

    /**
     * Wrappers for useful protractor methods
     */

    isVisible(locator) {
        return protractor.ExpectedConditions.visibilityOf(locator);
    }

    isNotVisible(locator) {
        return protractor.ExpectedConditions.invisibilityOf(locator);
    }

    getData() {
        let data;
        if(!process.env.DATA_FILE) {
            data = JSON.parse(fs.readFileSync("data/data.json", 'utf8'));
        }
        else {
            data = JSON.parse(fs.readFileSync(process.env.DATA_FILE, 'utf8'));
        }

        return data;
    }



}