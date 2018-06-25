<template>
    <ul id="languageTypes">
        <li @click="chooseExt($event, languageType)" v-bind:key="languageType.LanguageName" v-for="languageType in filteredLanguageTypes">
            <div class="icon" :class="'svg-' + languageType.Icon"></div>
            <div>{{languageType.LanguageName}}</div>
            <div class="small">{{languageType.FileExt && '(.' + languageType.FileExt + ')'}}</div>
            <div class="small" v-if="languageType.IsPluginRequired"> - plugin is required</div>
        </li>
    </ul>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Watch, Prop} from 'vue-property-decorator';

    import {LanguageType} from './model/LanguageType';

    @Component
    export default class LanguageTypeListComponent extends Vue {
//        private languageTypeList: HTMLUListElement;
//        private languageTypeListItems: NodeListOf<HTMLLIElement>;
//        private firstListElem: HTMLLIElement;
//        private lastListElem: HTMLLIElement = null;
        private selectedElem: HTMLLIElement = null;
//        private textOfSelectedLiElem: Node = null;
//        private inputField: HTMLInputElement = null;
            
        @Prop()
        public searchTerm: string;

        public LanguageTypes: Array<LanguageType>;
        public filteredLanguageTypes: Array<LanguageType>;

        constructor() {
            super();

            this.LanguageTypes = [
                new LanguageType('ANTLRv3', 'g', true),
                new LanguageType('ANTLRv4', 'g4', true),
                new LanguageType('Assembler', 'asm', false),
                new LanguageType('Batch', 'bat', false),
                new LanguageType('C', 'c', false),
                new LanguageType('C#', 'cs', true),
                new LanguageType('C++', 'cpp', false),
                new LanguageType('CSS', 'css', false),
                new LanguageType('Clojure', 'clj', true),
                new LanguageType('CoffeeScript', 'coffee', true),
                new LanguageType('Dockerfile', '', false),
                new LanguageType('Freemarker', 'ftl', true),
                new LanguageType('Galen', 'gspec', true),
                new LanguageType('GLSL', 'glsl', true),
                new LanguageType('Go', 'go', true),
                new LanguageType('Groovy', 'groovy', true),
                new LanguageType('HAML', 'haml', true),
                new LanguageType('Handlebars', 'hbs', true),
                new LanguageType('HTML', 'html', false),
                new LanguageType('Ini', 'ini', false),
                new LanguageType('Jade/Pug', 'pug', false),
                new LanguageType('Java', 'java', false),
                new LanguageType('JavaScript', 'js', false),
                new LanguageType('JavaScript React', 'jsx', false),
                new LanguageType('JSP', 'jsp', false),
                new LanguageType('JSON', 'json', false),
                new LanguageType('Kotlin', 'kt', true),
                new LanguageType('Less', 'less', false),
                new LanguageType('LISP', 'lisp', true),
                new LanguageType('Lua', 'lua', true),
                new LanguageType('Makefile', '', false),
                new LanguageType('Markdown', 'md', true),
                new LanguageType('Perl', 'pl', true),
                new LanguageType('PHP', 'php', false),
                new LanguageType('Plain Text', 'txt', false),
                new LanguageType('PLSQL', 'plsql', true),
                new LanguageType('Puppet', 'pp', true),
                new LanguageType('Python', 'py', true),
                new LanguageType('R', 'r', true),
                new LanguageType('Ruby', 'rb', true),
                new LanguageType('Rust', 'rs', true),
                new LanguageType('Sass', 'sass', false),
                new LanguageType('Scala', 'scala', true),
                new LanguageType('Scss', 'scss', false),
                new LanguageType('Smarty', 'tpl', false),
                new LanguageType('SQL', 'sql', false),
                new LanguageType('Shell Script', 'sh', true),
                new LanguageType('Tex', 'tex', true),
                new LanguageType('Twig', 'twig', false),
                new LanguageType('TypeScript', 'ts', true),
                new LanguageType('TypeScript React', 'tsx', true),
                new LanguageType('Vue', 'vue', true),
                new LanguageType('XML', 'xml', false),
                new LanguageType('XSL', 'xsl', false),
                new LanguageType('YAML', 'yaml', false)
            ];

            this.filteredLanguageTypes = this.LanguageTypes;
        }
        
        public chooseExt($event: MouseEvent, selectedLanguageType: LanguageType): void {
            this.setSelectedClass($event);
            
            selectedLanguageType.setExt(selectedLanguageType);
        }

        @Watch('searchTerm')
        public filterList(): void {
            this.filteredLanguageTypes = this.LanguageTypes
                .filter((language: LanguageType) => language.LanguageName.toLowerCase().indexOf(this.searchTerm.toLowerCase()) > -1);
                
            // TODO: Select the first element of the filtered list.
        }
        
        private setSelectedClass($event: MouseEvent): void {
            this.selectedElem = this.$el.querySelector('.selected') as HTMLLIElement;

            if(this.selectedElem) {
                this.selectedElem.classList.toggle('selected');
            }

            ($event.target as HTMLLIElement).classList.toggle('selected');

            // TODO: Trigger set focus to input again
//            this.triggerFocusUpdate();
        }
        
//        public selectFirstElem(): void {
//            this.inputField.addEventListener('keyup', e => {
//                if(e.key !== KeyCode.Down && e.key !== KeyCode.Up) {
//                    this.selectedElem = document.querySelector('.selected') as HTMLLIElement;
//
//                    if(this.selectedElem) {
//                        this.selectedElem.classList.remove('selected');
//                    }
//
//                    if(!!this.inputField.value) {
//                        this.firstListElem && this.firstListElem.classList.add('selected');
//                    } else {
//                        this.firstListElem && this.firstListElem.classList.remove('selected');
//                    }
//                }
//            });
//        }
    }
</script>

<style lang="scss">
    ul {
        list-style: none;
        margin: 0;
        color: darkslategrey;
        padding: 0;
        padding-bottom: 5px;
        overflow-y: scroll;
        max-height: 385px;

        li {
            padding: .25em 0 0 .75em;
            transition: background-color 100ms ease, color 100ms ease;
            display: flex;
            align-items: center;

            .icon {
                width: 16px;
                height: 16px;
                margin-right: .5em;
            }

            .small {
                color: grey;
                font-size: 80%;
                margin-left: .3em;
            }

            &:hover {
                background-color: #f5f5f5;
                cursor: pointer;
            }

            &.selected {
                background-color: #2665e5;
                color: #fff;

                .file-ext {
                    color: #ccc;
                }
            }
        }
    }
</style>