import ILanguageType from "../model/LanguageType";

export default interface SearchState {
    SearchTerm: string;
    SelectedElem: ILanguageType | null
}