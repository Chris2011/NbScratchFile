import {LanguageType} from "../app/model/LanguageType";

// TODO: Create better tests. Up to the user of the template.
// https://journal.artfuldev.com/write-tests-for-typescript-projects-with-mocha-and-chai-in-typescript-86e053bdb2b6
// http://thejsguy.com/2015/01/12/jasmine-vs-mocha-chai-and-sinon.html
// if you used the '@types/mocha' method to install mocha type definitions, uncomment the following line
// import 'mocha';
import {expect} from 'chai';
import {} from 'mocha';

describe('LanguagType LanguagName getter', () => {
    it('should return JavaScript', () => {
        let languageTypeModel: LanguageType = new LanguageType('JavaScript', 'js', false);

        expect(languageTypeModel.LanguageName).to.equal('JavaScript');
    });
});