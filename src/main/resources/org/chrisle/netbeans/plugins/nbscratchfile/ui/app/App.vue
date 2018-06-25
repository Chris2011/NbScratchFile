<template>
    <div>
        <language-search @searchForLang="updateSearchTerm"></language-search>
        <language-list :searchTerm="searchTerm"></language-list>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';

    import LanguageSearchFieldComponent from './components/LanguageSearchFieldComponent.vue';
    import LanguageTypeListComponent from './components/LanguageTypeListComponent.vue';

    @Component({
        components: {
            'language-search': LanguageSearchFieldComponent,
            'language-list': LanguageTypeListComponent
        }
    })
    export default class App extends Vue {
        public searchTerm: string;

        constructor() {
            super();

            this.searchTerm = '';
        }

        public updateSearchTerm(value: string): void {
            this.searchTerm = value;
        }
    }
</script>