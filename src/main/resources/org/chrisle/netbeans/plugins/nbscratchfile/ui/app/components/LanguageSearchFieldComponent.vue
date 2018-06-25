<template>
    <div class="wrapper">
        <input id="languageSearch" v-model="searchTerm" type="text" @keyup="processEvent" placeholder="Search available languages..." />
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';

    // The @Component decorator indicates the class is a Vue component
    @Component
    export default class LanguageSerchFieldComponent extends Vue {
        private searchTerm: string;

        constructor() {
            super();

            this.searchTerm = '';
        }

        public processEvent(): void {
            this.$emit('searchForLang', this.searchTerm);
        }
    }
</script>

<style lang="scss">
    .wrapper {
        padding: 10px;

        input {
            padding-left: 8px;
            border: 1px solid #c8ccd0;
            border-radius: 2px;
            box-sizing: border-box;
            box-shadow: inset 0 0 1px rgba(145, 153, 161, 0.2), 0 0 0 rgba(255, 255, 255, 0);
            width: 100%;
            color: #6a737c;
            font-size: 16px;
            outline: none;
            height: 32px;
        }
    }
</style>