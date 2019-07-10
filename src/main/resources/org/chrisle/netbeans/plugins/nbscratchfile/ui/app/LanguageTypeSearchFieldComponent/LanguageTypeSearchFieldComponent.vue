<template>
    <div class="wrapper">
        <input
            id="languageSearch"
            v-model="searchStore.SearchState.SearchTerm"
            type="text"
            placeholder="Search available languages..."
        />
    </div>
</template>

<script lang="ts">
import Vue from 'vue';
import { Component } from 'vue-property-decorator';
import { getModule } from 'vuex-module-decorators';

import SearchStore from '../shared/store/SearchStore';

@Component
export default class LanguageTypeSerchFieldComponent extends Vue {
    private searchField: HTMLInputElement | null;

    private readonly searchStore = getModule(SearchStore);

    constructor() {
        super();

        this.searchField = null;
    }

    public mounted(): void {
        this.searchField = this.$el.querySelector(
            '#languageSearch',
        ) as HTMLInputElement;

        this.searchField.focus();
        this.alwaysSetFocusOnClick();
        this.alwaysSetFocusOnArrowNavigation();
    }

    private alwaysSetFocusOnClick(): void {
        window.addEventListener('click', (evt: MouseEvent) => {
            this.searchField!.focus();

            if (this.searchStore.SearchState.SelectedElem) {
                this.searchField!.value = '';
            }
        });
    }

    public deleteValue(): void {
        if (this.searchStore.SearchState.SelectedElem) {
            this.searchStore.updateSearchTerm('');
        }
    }

    private alwaysSetFocusOnArrowNavigation(): void {
        window.addEventListener('keydown', (evt: KeyboardEvent) => {
            this.searchField!.focus();
        });
    }
}
</script>

<style lang="scss" scoped>
.wrapper {
    padding: 8px;

    input {
        padding-left: 8px;
        border: 1px solid #c8ccd0;
        border-radius: 2px;
        box-sizing: border-box;
        box-shadow: inset 0 0 1px rgba(145, 153, 161, 0.2),
            0 0 0 rgba(255, 255, 255, 0);
        width: 100%;
        color: #6a737c;
        font-size: 14px;
        outline: none;
        height: 28px;
    }
}
</style>