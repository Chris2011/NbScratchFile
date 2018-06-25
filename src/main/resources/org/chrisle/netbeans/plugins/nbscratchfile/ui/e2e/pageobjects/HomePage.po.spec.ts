import {browser, element, by, promise, WebElementPromise} from 'protractor';

/**
 *
 * @author Chrl
 */
export class HomePagePO {
    private searchField = element(by.id('languageSearch'));
    private resultList = element(by.id('languageTypes'));

    constructor() {
        browser.waitForAngularEnabled(false);
    }

    public get Url(): promise.Promise<string> {
        return browser.get('/generated/');
    }

    public get Title(): promise.Promise<string> {
        return browser.getTitle();
    }

    public get SearchField(): promise.Promise<string> {
        return this.searchField.getAttribute('value');
    }

    public setSearchField(value: string) {
        this.searchField.sendKeys(value);
    }

    public get ResultList(): WebElementPromise {
        return this.resultList.getWebElement();
    }
}