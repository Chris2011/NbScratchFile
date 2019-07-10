<template>
    <ul id="languageTypes">
        <li
            :tabindex="++index"
            @click="chooseExt($event, languageType)"
            v-for="(languageType, index) in filteredLanguageTypes"
            :key="languageType.LanguageName"
            :class="{ selected: languageType.IsSelected }"
            v-focus="languageType.HasFocus"
        >
            <div v-once class="icon" :class="'svg-' + languageType.Icon"></div>
            <div v-once>{{ languageType.LanguageName }}</div>
            <div v-once class="small ext">
                {{ languageType.FileExt && '(.' + languageType.FileExt + ')' }}
            </div>
            <div v-once class="small" v-if="languageType.IsPluginRequired">
                - plugin is required
            </div>
        </li>
    </ul>
</template>

<script lang="ts">
import Vue from 'vue';
import { Component, Watch } from 'vue-property-decorator';
import { getModule } from 'vuex-module-decorators';

import ILanguageType, { LanguageType } from '../shared/model/LanguageType';
import { KeyCode } from '../shared/model/KeyCode';
import SearchStore from '../shared/store/SearchStore';
import { DirectiveOptions } from 'vue/types/options';

@Component({
    directives: {
        focus: {
            update(el: HTMLElement, node): void {
                if (node.value) {
                    Vue.nextTick(function() {
                        el.focus();
                    });
                }
            },
        } as DirectiveOptions,
    },
})
export default class LanguageTypeListComponent extends Vue {
    private firstListElem: ILanguageType | null;
    private lastListElem: ILanguageType | null;

    private prevElem: ILanguageType | null;
    private nextElem: ILanguageType | null;

    private languageTypes: ILanguageType[];
    public filteredLanguageTypes: ILanguageType[];
    private searchStore: SearchStore;

    constructor() {
        super();

        this.searchStore = getModule(SearchStore);

        this.prevElem = null;
        this.nextElem = null;

        this.firstListElem = null;
        this.lastListElem = null;

        this.languageTypes = [
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
            new LanguageType('YAML', 'yaml', false),
        ];

        this.filteredLanguageTypes = this.languageTypes;
    }

    public chooseExt($event: Event, selectedLanguageType: LanguageType): void {
        this.searchStore.updateSelectedElement(selectedLanguageType);

        selectedLanguageType.setExt(selectedLanguageType);
    }

    @Watch('searchStore.SearchState.SearchTerm')
    public filterList(): void {
        this.filteredLanguageTypes = this.languageTypes.filter(
            (language: ILanguageType) => {
                language.IsSelected = false;

                return (
                    language.LanguageName.toLowerCase().indexOf(
                        this.searchStore.SearchState.SearchTerm.toLowerCase(),
                    ) > -1
                );
            },
        );

        if (
            this.filteredLanguageTypes.length > 0 &&
            this.searchStore.SearchState.SearchTerm
        ) {
            this.searchStore.updateSelectedElement(
                this.filteredLanguageTypes[0],
            );
        } else {
            this.searchStore.updateSelectedElement(null);
        }
    }

    public mounted(): void {
        this.prepareElements();

        this.handleItemSelectionWithArrowKeys();
    }

    private prepareElements(): void {
        this.firstListElem = this.filteredLanguageTypes[0];
        this.lastListElem = this.filteredLanguageTypes[
            this.filteredLanguageTypes.length - 1
        ];
    }

    private moveUp(): void {
        if (this.searchStore.SearchState.SelectedElem) {
            if (!this.prevElem) {
                this.searchStore.updateSelectedElement(this.lastListElem);
            } else {
                this.searchStore.updateSelectedElement(this.prevElem);
            }
        } else {
            this.searchStore.updateSelectedElement(this.lastListElem);
        }
    }

    private moveDown(): void {
        this.prepareElements();

        if (this.searchStore.SearchState.SelectedElem) {
            if (!this.nextElem) {
                this.searchStore.updateSelectedElement(this.firstListElem);
            } else {
                this.searchStore.updateSelectedElement(this.nextElem);
            }
        } else {
            this.searchStore.updateSelectedElement(this.firstListElem);
        }
    }

    private getElement(index: number): ILanguageType {
        return this.filteredLanguageTypes[
            this.filteredLanguageTypes.findIndex(
                (language: ILanguageType) => language.IsSelected,
            ) + index
        ];
    }

    private handleItemSelectionWithArrowKeys(): void {
        const body = document.querySelector('body');

        if (body) {
            body.addEventListener('keyup', (evt: KeyboardEvent) => {
                if (this.searchStore.SearchState.SelectedElem) {
                    this.prevElem = this.getElement(-1);
                    this.nextElem = this.getElement(+1);
                }

                if (evt.keyCode === KeyCode.Up) {
                    evt.preventDefault();

                    this.moveUp();
                } else if (evt.keyCode === KeyCode.Down) {
                    evt.preventDefault();

                    this.moveDown();
                }

                if (evt.keyCode === KeyCode.Enter) {
                    if (this.searchStore.SearchState.SelectedElem) {
                        this.getDataFromSelectedElem(evt);
                    }
                }
            });
        }
    }

    private getDataFromSelectedElem(evt: KeyboardEvent): void {
        const selectedElem: LanguageType | null = this.searchStore.SearchState
            .SelectedElem as LanguageType;

        if (selectedElem) {
            const ext: string | null = selectedElem.FileExt;

            if (ext) {
                const languageExt: string = ext
                        .replace('(.', '')
                        .replace(')', ''),
                    languageType: LanguageType = new LanguageType(
                        '',
                        languageExt,
                    );

                selectedElem.setExt(languageType);
                this.searchStore.updateSearchTerm('');
            }
        }
    }
}
</script>

<style lang="scss">
#languageTypes {
    list-style: none;
    margin: 0;
    color: darkslategrey;
    padding: 0;
    overflow-y: scroll;
    max-height: 385px;

    li {
        padding: 2px 0 2px 12px;
        transition: background-color 100ms ease, color 100ms ease;
        display: flex;
        align-items: center;

        div {
            pointer-events: none;
        }

        .icon {
            width: 20px;
            height: 20px;
            margin-right: 8px;
        }

        .small {
            color: grey;
            font-size: 80%;
            margin-left: 4px;
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