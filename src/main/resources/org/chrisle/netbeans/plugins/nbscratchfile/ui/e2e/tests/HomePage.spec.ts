import {expect} from 'chai';

import {HomePagePO} from '../pageobjects/HomePage.po.spec';
import {By} from "protractor/built";

let homePagePO: HomePagePO;

before(() => {
    homePagePO = new HomePagePO();
});

describe('home view', () => {
    beforeEach(() => {
        homePagePO.Url;
    });

    it('title should be Vue TypeScript Live Search Sample', () => {
        homePagePO.Title.then((title: string) => {
            expect(title).to.equal('Vue TypeScript Live Search Sample');
        });
    });

    it('searchField should be set to JavaScript', () => {
        homePagePO.SearchField.then((value: string) => {
            expect(value).to.equal('');
        });

        homePagePO.setSearchField('JavaScript');

        homePagePO.SearchField.then((value: string) => {
            expect(value).to.equal('JavaScript');
        });
    });

    it('should show 2 elements in result list, after searching for JavaScript', () => {
        homePagePO.setSearchField('JavaScript');

        homePagePO.ResultList.findElements(By.css('li')).then(elem => {
            expect(elem.length).to.equal(2);
        });
    });
});