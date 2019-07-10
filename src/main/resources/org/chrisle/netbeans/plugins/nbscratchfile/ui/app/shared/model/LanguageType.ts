/**
 *
 * @author Chrl
 */
declare const NbScratchFileViewModel: any;

export default interface ILanguageType {
    Icon: string;
    LanguageName: string;
    FileExt: string;
    IsPluginRequired: boolean;
    IsSelected: boolean;
    HasFocus: boolean;
}

export class LanguageType implements ILanguageType {
    private icon: string;
    private languageName: string;
    private fileExt: string;
    private isPluginRequired: boolean;
    private isSelected: boolean;
    private hasFocus: boolean;

    constructor(
        languageName: string,
        fileExt: string,
        isPluginRequired: boolean = false,
        isSelected = false,
        hasFocus = false,
    ) {
        this.icon = fileExt || languageName;
        this.languageName = languageName;
        this.fileExt = fileExt || languageName;
        this.isPluginRequired = isPluginRequired;
        this.isSelected = isSelected;
        this.hasFocus = hasFocus;
    }

    public get Icon() {
        return this.icon.toLowerCase();
    }

    public get LanguageName(): string {
        return this.languageName;
    }

    public set LanguageName(value: string) {
        this.languageName = value;
    }

    public get FileExt(): string {
        return this.fileExt.toLowerCase();
    }

    public get IsPluginRequired(): boolean {
        return this.isPluginRequired;
    }

    public get IsSelected(): boolean {
        return this.isSelected;
    }

    public set IsSelected(value: boolean) {
        this.isSelected = value;
    }

    public get HasFocus(): boolean {
        return this.hasFocus;
    }

    public set HasFocus(value: boolean) {
        this.hasFocus = value;
    }

    public setExt(languageType: LanguageType): void {
        NbScratchFileViewModel.setExt(languageType.FileExt, languageType.LanguageName);
    }
}
