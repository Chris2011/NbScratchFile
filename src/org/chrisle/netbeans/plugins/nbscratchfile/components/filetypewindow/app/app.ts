import {FileType} from "./model/FileType";
import {LanguageType} from "./model/LanguageType";
import {LanguageTypesDOMModel} from "./model/LanguageTypesDOMModel";

export class App {
    private fileTypeWindowViewModel: FileType;
    private languageTypesListModel: LanguageTypesDOMModel;

    constructor() {
        this.fileTypeWindowViewModel = new FileType([
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
        ]);

        this.languageTypesListModel = new LanguageTypesDOMModel();
    }

    public main(): void {

        this.fileTypeWindowViewModel.Language.subscribe(() => {
            this.languageTypesListModel.init();
        });

        ko.applyBindings(this.fileTypeWindowViewModel);

        console.log(this.fileTypeWindowViewModel.LanguageTypes.length);
        console.log(this.fileTypeWindowViewModel.LanguageTypes);

        this.languageTypesListModel.init();
        this.languageTypesListModel.handleItemSelectionWithArrowKeys();
        this.languageTypesListModel.selectFirstElem();
        this.languageTypesListModel.getDataFromSelectedElem();
    }
}