enum KeyCode {
    Enter = 13,
    Up = 38,
    Down = 40
}

export class LanguageTypesDOMModel {
    private languageTypeList: HTMLUListElement = null;
    private languageTypeListItems: NodeListOf<HTMLLIElement>;
    private firstListElem: HTMLLIElement = null;
    private lastListElem: HTMLLIElement = null;
    private selectedElem: HTMLLIElement = null;
    private inputField: HTMLInputElement = null;

    private getIndexOfElem(selectedElem: HTMLLIElement): number {
        return [].findIndex.call(this.languageTypeListItems, (elem: HTMLLIElement) => {
            return elem === selectedElem;
        });
    }

    public init(): void {
        this.languageTypeList = document.querySelector('#languageTypes') as HTMLUListElement;
        this.languageTypeListItems = document.querySelectorAll('#languageTypes li') as NodeListOf<HTMLLIElement>;
        this.firstListElem = document.querySelector('#languageTypes > li') as HTMLLIElement;
        this.lastListElem = document.querySelector('#languageTypes > li:last-child') as HTMLLIElement;
        this.inputField = document.querySelector('input') as HTMLInputElement;

        // TODO: Remove handler of each elem, befor setting again.
        [].forEach.call(this.languageTypeListItems, (item: HTMLLIElement) => {
            item.addEventListener('click', () => {
                this.selectedElem = document.querySelector('.selected') as HTMLLIElement;

                if(this.selectedElem) {
                    this.selectedElem.classList.toggle('selected');
                }

                item.classList.toggle('selected');

                // TODO: Test the click handler event.
                console.log("clicked");

                this.inputField.focus();
            });
        });
    }

    public get List(): HTMLUListElement {
        return this.languageTypeList;
    }

    public get LanguageTypeListItems(): NodeListOf<HTMLLIElement> {
        return this.languageTypeListItems;
    }

    public get SelectedElem(): HTMLLIElement {
        return this.selectedElem;
    }

    public get PreviousElement(): HTMLLIElement {
        return this.selectedElem.previousElementSibling as HTMLLIElement;
    }

    public get NextElement(): HTMLLIElement {
        return this.selectedElem.nextElementSibling as HTMLLIElement;
    }

    public set SelectedElem(value: HTMLLIElement) {
        this.selectedElem = value;
    }

    public get FirstListElem(): HTMLLIElement {
        return this.firstListElem;
    }

    public get LastListElem(): HTMLLIElement {
        return this.lastListElem;
    }

    public moveUp(): void {
        if(this.selectedElem) {
            if(!this.PreviousElement) {
                this.selectedElem.classList.remove('selected');

                this.selectedElem = this.lastListElem;

                this.selectedElem.classList.toggle('selected');
                this.languageTypeList.scrollTop = 800;
            } else {
                if(this.selectedElem.offsetTop < 450) {
                    this.languageTypeList.scrollTop = (this.getIndexOfElem(this.selectedElem) / this.selectedElem.offsetHeight);
                }

                this.selectedElem.classList.remove('selected');

                this.selectedElem = this.PreviousElement;

                this.selectedElem.classList.toggle('selected');
            }
        } else {
            this.selectedElem = this.LastListElem;

            this.selectedElem.classList.toggle('selected');
            this.languageTypeList.scrollTop = 800;
        }
    }

    public moveDown(): void {
        if(this.selectedElem) {
            if(this.selectedElem.offsetTop > 400) {
                this.languageTypeList.scrollTop = (this.getIndexOfElem(this.selectedElem) * this.selectedElem.offsetHeight);
            }

            if(!this.NextElement) {
                this.selectedElem.classList.remove('selected');

                this.selectedElem = this.FirstListElem;

                this.selectedElem.classList.toggle('selected');
                this.languageTypeList.scrollTop = 0;
            } else {
                this.selectedElem.classList.remove('selected');

                this.selectedElem = this.NextElement;

                this.selectedElem.classList.toggle('selected');
            }
        } else {
            this.selectedElem = this.FirstListElem;

            this.selectedElem.classList.toggle('selected');
        }
    }

    public handleItemSelectionWithArrowKeys(): void {
        document.querySelector('body').addEventListener('keydown', (evt: KeyboardEvent) => {
            this.SelectedElem = document.querySelector('.selected') as HTMLLIElement;

            if(evt.keyCode === KeyCode.Up) {
                evt.preventDefault();

                this.moveUp();
            } else if(evt.keyCode === KeyCode.Down) {
                evt.preventDefault();

                this.moveDown();
            }
        });
    }

    public selectFirstElem(): void {
        this.inputField.addEventListener('keyup', e => {
            if(e.keyCode !== KeyCode.Down && e.keyCode !== KeyCode.Up) {
                this.selectedElem = document.querySelector('.selected') as HTMLLIElement;

                if(this.selectedElem) {
                    this.selectedElem.classList.remove('selected');
                }

                if(!!this.inputField.value) {
                    this.firstListElem && this.firstListElem.classList.add('selected');
                } else {
                    this.firstListElem && this.firstListElem.classList.remove('selected');
                }
            }
        });
    }
}