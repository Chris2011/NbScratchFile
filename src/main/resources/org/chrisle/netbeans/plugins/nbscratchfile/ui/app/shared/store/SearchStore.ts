import { Module, VuexModule, Mutation, Action } from 'vuex-module-decorators';
import { Module as Mod } from 'vuex';

import ILanguageType from '../model/LanguageType';
import store from '.';
import SearchState from './SearchState';

@Module({
    dynamic: true,
    name: 'SearchStore',
    store,
})
export default class SearchStore extends VuexModule {
    private readonly searchState: SearchState;

    constructor(module: Mod<ThisType<{}>, any>) {
        super(module);

        this.searchState = {
            SearchTerm: '',
            SelectedElem: {},
        } as SearchState;
    }

    @Action
    public updateSearchTerm(searchTerm: string): void {
        this.updateSearchTermMutation({ searchTerm: searchTerm });
    }

    @Action
    public updateSelectedElement(selectedElem: ILanguageType | null): void {
        this.updateSelectedElementMutation({ selectedElem: selectedElem });
    }

    public get SearchState(): SearchState {
        return this.searchState;
    }

    @Mutation
    private updateSearchTermMutation(payload: { searchTerm: string }): void {
        this.searchState.SearchTerm = payload.searchTerm;
    }

    @Mutation
    private updateSelectedElementMutation(payload: {
        selectedElem: ILanguageType | null;
    }): void {
        if (payload.selectedElem) {
            if (this.searchState.SelectedElem) {
                this.searchState.SelectedElem.IsSelected = false;
                this.searchState.SelectedElem.HasFocus = false;
            }

            this.searchState.SelectedElem = payload.selectedElem;

            this.searchState.SelectedElem.IsSelected = true;
            this.searchState.SelectedElem.HasFocus = true;
        } else {
            this.searchState.SelectedElem = null;
        }
    }
}
