<template>
    <ul id="languageTypes">
        <li :tabindex="++index" @click="chooseExt($event, languageType)" v-bind:key="languageType.LanguageName" v-for="(languageType, index) in filteredLanguageTypes">
            <div v-once class="icon" :class="'svg-' + languageType.Icon"></div>
            <div v-once>{{languageType.LanguageName}}</div>
            <div v-once class="small ext">{{languageType.FileExt && '(.' + languageType.FileExt + ')'}}</div>
            <div v-once class="small" v-if="languageType.IsPluginRequired"> - plugin is required</div>
        </li>
    </ul>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Watch, Prop} from 'vue-property-decorator';

    import {LanguageType} from './model/LanguageType';
    import {KeyCode} from './model/KeyCode';
    
    @Component
    export default class LanguageTypeListComponent extends Vue {
        private firstListElem: HTMLLIElement | null;
        private lastListElem: HTMLLIElement | null;
        private previousListElem: HTMLLIElement | null;
        private nextListElem: HTMLLIElement | null;
        private selectedElem: HTMLLIElement | null;
            
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
                new LanguageType('NetBeans Mind Map', 'mmd', true),
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
        
        public chooseExt($event: Event, selectedLanguageType: LanguageType): void {
            this.setSelectedClass($event);
            
            selectedLanguageType.setExt(selectedLanguageType);
        }

        @Watch('searchTerm')
        public filterList(): void {
            this.filteredLanguageTypes = this.LanguageTypes
                .filter((language: LanguageType) => language.LanguageName.toLowerCase().indexOf(this.searchTerm.toLowerCase()) > -1);
                
            const findFirstMatchedElement = this.$el.querySelector(`.svg-${this.filteredLanguageTypes[0] && this.filteredLanguageTypes[0].FileExt}`);
            this.firstListElem = (findFirstMatchedElement && findFirstMatchedElement.parentElement) as HTMLLIElement;
            
            this.selectedElem = this.$el.querySelector('.selected');
            
            if(!!this.searchTerm) {
                this.firstListElem && this.firstListElem.classList.add('selected');
            } else {
                this.selectedElem && this.selectedElem.classList.remove('selected');
            }
        }

        public mounted(): void {
            this.prepareElements();

            this.handleItemSelectionWithArrowKeys();
        }
        
        private prepareElements(): void {
            this.firstListElem = this.$el.firstChild as HTMLLIElement;
            this.lastListElem = this.$el.lastChild as HTMLLIElement;
        }
        
        private moveUp(): void {
            if(this.selectedElem) {
                if(!this.previousListElem) {
                    this.selectedElem.classList.remove('selected');

                    this.selectedElem = this.lastListElem;

                    this.selectedElem.focus();
                    this.selectedElem.classList.add('selected');
                } else {
                    this.selectedElem.classList.remove('selected');

                    this.selectedElem = this.previousListElem;

                    this.selectedElem.focus();
                    this.selectedElem.classList.add('selected');
                }
            } else {
                this.selectedElem = this.lastListElem;
                
                if (this.selectedElem) {
                    this.selectedElem.focus();
                    this.selectedElem.classList.add('selected');
                }
            }
        }

        private moveDown(): void {
            this.prepareElements();
            
            if(this.selectedElem) {
                if(!this.nextListElem) {
                    this.selectedElem.classList.remove('selected');

                    this.selectedElem = this.firstListElem;

                    this.selectedElem.focus();
                    this.selectedElem.classList.add('selected');
                } else {
                    this.selectedElem.classList.remove('selected');
                    
                    this.selectedElem = this.nextListElem;

                    this.selectedElem.focus();
                    this.selectedElem.classList.add('selected');
                }
            } else {
                this.selectedElem = this.firstListElem;
                
                if (this.selectedElem) {
                    this.selectedElem.focus();
                    this.selectedElem.classList.add('selected');    
                }

            }
        }
        
        private setSelectedClass($event: Event): void {
            this.selectedElem = this.$el.querySelector('.selected') as HTMLLIElement;

            if(this.selectedElem) {
                this.selectedElem.classList.toggle('selected');
            }

            this.selectedElem = $event.target as HTMLLIElement;
            this.selectedElem.classList.toggle('selected');
        }
        
        private handleItemSelectionWithArrowKeys(): void {
            document.querySelector('body').addEventListener('keydown', (evt: KeyboardEvent) => {
                this.selectedElem = this.$el.querySelector('.selected') as HTMLLIElement;
                
                if (this.selectedElem) {
                    this.previousListElem = this.selectedElem.previousElementSibling as HTMLLIElement;
                    this.nextListElem = this.selectedElem.nextElementSibling as HTMLLIElement;
                }
                
                if(evt.keyCode === KeyCode.Up) {
                    evt.preventDefault();

                    this.moveUp();
                } else if(evt.keyCode === KeyCode.Down) {
                    evt.preventDefault();

                    this.moveDown();
                }
                
                if(evt.keyCode === KeyCode.Enter) {
                    this.getDataFromSelectedElem(evt);
                }
            });
        }
        
        private getDataFromSelectedElem(evt: KeyboardEvent): void {
            if(this.selectedElem) {
                this.searchTerm = '';
            }
            
            let languageExt: string = this.selectedElem && this.selectedElem.querySelector('.ext').textContent.replace('(.', '').replace(')', ''),
                languageType: LanguageType = new LanguageType('', languageExt);

            this.chooseExt(evt, languageType);
        }
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
            
            div {
                pointer-events: none;
            }

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