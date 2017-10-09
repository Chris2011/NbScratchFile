import * as ko from 'knockout';
import {LanguageType} from "./LanguageType";

/**
 *
 * @author Chris
 */
declare const NbScratchFileViewModel: any;

export class FileType {
    private language: KnockoutObservable<string>;
    private languageTypes: KnockoutComputed<Array<LanguageType>>;

    private menuBgColor: KnockoutObservable<string>;
    private textFieldBgColor: KnockoutObservable<string>;
    private textFieldFgColor: KnockoutObservable<string>;

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

        this.menuBgColor = ko.observable('');
        this.textFieldBgColor = ko.observable('');
        this.textFieldFgColor = ko.observable('');
    }

    //    private manipulateString(languageName: string, term: string): string {
    //        return term ? languageName.replace(term, `<strong>${term}</strong>`) : languageName
    //    }

    public get Language(): KnockoutObservable<string> {
        return this.language;
    }

    public get MenuBgColor(): KnockoutObservable<string> {
        let self = this;

        setTimeout(() => {
            self.menuBgColor(NbScratchFileViewModel.getColor('Menu.background'));
        }, 200);

        return self.menuBgColor;
    }

    public get TextFieldBgColor(): KnockoutObservable<string> {
        let self = this;

        setTimeout(() => {
            self.textFieldBgColor(NbScratchFileViewModel.getColor('TextField.background'));
        }, 200);

        return self.textFieldBgColor;
    }

    public get TextFieldFgColor(): KnockoutObservable<string> {
        let self = this;

        setTimeout(() => {
            self.textFieldFgColor(NbScratchFileViewModel.getColor('TextField.foreground'));
        }, 200);

        return self.textFieldFgColor;
    }

    public get LanguageTypes(): LanguageType[] {
        return this.languageTypes();
    }
}