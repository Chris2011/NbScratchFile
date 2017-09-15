import {} from 'knockout';
import {LanguageType} from "./LanguageType";

/**
 *
 * @author Chris
 */
export class FileType {
    private language: KnockoutObservable<String>;
    private languageTypes: KnockoutComputed<Array<LanguageType>>;

    constructor(languageTypes: Array<LanguageType>) {
        this.language = ko.observable('');
        this.languageTypes = ko.computed(() => {
            var searchTerm = this.language().toLowerCase();

            return ko.utils.arrayFilter(languageTypes, (languageType: LanguageType) => {
                return languageType.LanguageName.toLowerCase().indexOf(searchTerm) >= 0;
            }).sort((elem1: LanguageType, elem2: LanguageType) => {
                if(elem1.LanguageName > elem2.LanguageName) {
                    return 1;
                } else if(elem1.LanguageName < elem2.LanguageName) {
                    return -1
                }

                return 0;
            });
            //            }).filter((languageType: LanguageType) => {
            //                languageType.LanguageName = this.manipulateString(languageType.LanguageName, searchTerm);
            //
            //                return languageType;
            //            });
        }, this);
    }

    //    private manipulateString(languageName: string, term: string): string {
    //        return term ? languageName.replace(term, `<strong>${term}</strong>`) : languageName
    //    }

    public get Language(): KnockoutObservable<String> {
        return this.language;
    }

    public get LanguageTypes(): KnockoutObservable<Array<LanguageType>> {
        return this.languageTypes;
    }
}