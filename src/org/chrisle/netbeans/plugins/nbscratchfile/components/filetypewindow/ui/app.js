/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/generated/";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(1);
module.exports = __webpack_require__(6);


/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", { value: true });
var app_1 = __webpack_require__(2);
//import {WebpackRequire} from 'webpack-env';
//
//declare var require: WebpackRequire;
//
//var files = require.context('./icons', false, /\.svg$/);
//files.keys().forEach(files);
var starter = new app_1.App();
starter.main();

/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });
var FileType_1 = __webpack_require__(3);
var LanguageType_1 = __webpack_require__(4);
var LanguageTypesDOMModel_1 = __webpack_require__(5);

var App = function () {
    function App() {
        _classCallCheck(this, App);

        this.fileTypeWindowViewModel = new FileType_1.FileType([new LanguageType_1.LanguageType('Assembler', 'asm', false), new LanguageType_1.LanguageType('Batch', 'bat', false), new LanguageType_1.LanguageType('C', 'c', false), new LanguageType_1.LanguageType('C#', 'cs', true), new LanguageType_1.LanguageType('C++', 'cpp', false), new LanguageType_1.LanguageType('CSS', 'css', false), new LanguageType_1.LanguageType('Clojure', 'clj', true), new LanguageType_1.LanguageType('CoffeeScript', 'coffee', true), new LanguageType_1.LanguageType('Dockerfile', '', false), new LanguageType_1.LanguageType('Freemarker', 'ftl', true), new LanguageType_1.LanguageType('GLSL', 'glsl', true), new LanguageType_1.LanguageType('Go', 'go', true), new LanguageType_1.LanguageType('Groovy', 'groovy', true), new LanguageType_1.LanguageType('HAML', 'haml', true), new LanguageType_1.LanguageType('Handlebars', 'hbs', true), new LanguageType_1.LanguageType('HTML', 'html', false), new LanguageType_1.LanguageType('Ini', 'ini', false), new LanguageType_1.LanguageType('Jade/Pug', 'pug', false), new LanguageType_1.LanguageType('Java', 'java', false), new LanguageType_1.LanguageType('JavaScript', 'js', false), new LanguageType_1.LanguageType('JavaScript React', 'jsx', false), new LanguageType_1.LanguageType('JSP', 'jsp', false), new LanguageType_1.LanguageType('JSON', 'json', false), new LanguageType_1.LanguageType('Kotlin', 'kt', true), new LanguageType_1.LanguageType('Less', 'less', false), new LanguageType_1.LanguageType('LISP', 'lisp', true), new LanguageType_1.LanguageType('Lua', 'lua', true), new LanguageType_1.LanguageType('Makefile', '', false), new LanguageType_1.LanguageType('Markdown', 'md', true), new LanguageType_1.LanguageType('Perl', 'pl', true), new LanguageType_1.LanguageType('PHP', 'php', false), new LanguageType_1.LanguageType('PLSQL', 'plsql', true), new LanguageType_1.LanguageType('Puppet', 'pp', true), new LanguageType_1.LanguageType('Python', 'py', true), new LanguageType_1.LanguageType('R', 'r', true), new LanguageType_1.LanguageType('Ruby', 'rb', true), new LanguageType_1.LanguageType('Rust', 'rs', true), new LanguageType_1.LanguageType('Sass', 'sass', false), new LanguageType_1.LanguageType('Scala', 'scala', true), new LanguageType_1.LanguageType('Scss', 'scss', false), new LanguageType_1.LanguageType('Smarty', 'tpl', false), new LanguageType_1.LanguageType('SQL', 'sql', false), new LanguageType_1.LanguageType('Shell Script', 'sh', true), new LanguageType_1.LanguageType('Tex', 'tex', true), new LanguageType_1.LanguageType('Twig', 'twig', false), new LanguageType_1.LanguageType('TypeScript', 'ts', true), new LanguageType_1.LanguageType('TypeScript React', 'tsx', true), new LanguageType_1.LanguageType('Vue', 'vue', true), new LanguageType_1.LanguageType('XML', 'xml', false), new LanguageType_1.LanguageType('XSL', 'xsl', false), new LanguageType_1.LanguageType('YAML', 'yaml', false)]);
        this.languageTypesListModel = new LanguageTypesDOMModel_1.LanguageTypesDOMModel();
    }

    _createClass(App, [{
        key: "main",
        value: function main() {
            var _this = this;

            this.fileTypeWindowViewModel.Language.subscribe(function () {
                _this.languageTypesListModel.init();
            });
            ko.applyBindings(this.fileTypeWindowViewModel);
            this.languageTypesListModel.init();
            this.languageTypesListModel.handleItemSelectionWithArrowKeys();
            this.languageTypesListModel.selectFirstElem();
            this.languageTypesListModel.getDataFromSelectedElem();
        }
    }]);

    return App;
}();

exports.App = App;

/***/ }),
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });
/**
 *
 * @author Chris
 */

var FileType = function () {
    function FileType(languageTypes) {
        var _this = this;

        _classCallCheck(this, FileType);

        this.language = ko.observable('');
        this.languageTypes = ko.computed(function () {
            var searchTerm = _this.language().toLowerCase();
            return ko.utils.arrayFilter(languageTypes, function (languageType) {
                return languageType.LanguageName.toLowerCase().indexOf(searchTerm) >= 0;
            }).sort(function (elem1, elem2) {
                if (elem1.LanguageName > elem2.LanguageName) {
                    return 1;
                } else if (elem1.LanguageName < elem2.LanguageName) {
                    return -1;
                }
                return 0;
            });
            //            }).filter((languageType: LanguageType) => {
            //                languageType.LanguageName = this.manipulateString(languageType.LanguageName, searchTerm);
            //
            //                return languageType;
            //            });
        }, this);
    }
    //    private manipulateString(languageName: string, term: string): string {
    //        return term ? languageName.replace(term, `<strong>${term}</strong>`) : languageName
    //    }


    _createClass(FileType, [{
        key: "Language",
        get: function get() {
            return this.language;
        }
    }, {
        key: "LanguageTypes",
        get: function get() {
            return this.languageTypes;
        }
    }]);

    return FileType;
}();

exports.FileType = FileType;

/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";

/**
 *
 * @author Chrl
 */

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });

var LanguageType = function () {
    function LanguageType(languageName, fileExt, isPluginRequired) {
        _classCallCheck(this, LanguageType);

        this.icon = fileExt || languageName;
        this.languageName = languageName;
        this.fileExt = fileExt || languageName;
        this.isPluginRequired = isPluginRequired;
    }

    _createClass(LanguageType, [{
        key: "setExt",
        value: function setExt(languageType) {
            NbScratchFileViewModel.setExt(languageType.FileExt, languageType.LanguageName);
        }
    }, {
        key: "Icon",
        get: function get() {
            return this.icon.toLowerCase();
        }
    }, {
        key: "LanguageName",
        get: function get() {
            return this.languageName;
        },
        set: function set(value) {
            this.languageName = value;
        }
    }, {
        key: "FileExt",
        get: function get() {
            return this.fileExt.toLowerCase();
        }
    }]);

    return LanguageType;
}();

exports.LanguageType = LanguageType;

/***/ }),
/* 5 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });
var KeyCode;
(function (KeyCode) {
    KeyCode[KeyCode["Enter"] = 13] = "Enter";
    KeyCode[KeyCode["Up"] = 38] = "Up";
    KeyCode[KeyCode["Down"] = 40] = "Down";
})(KeyCode || (KeyCode = {}));

var LanguageTypesDOMModel = function () {
    function LanguageTypesDOMModel() {
        _classCallCheck(this, LanguageTypesDOMModel);

        this.languageTypeList = null;
        this.firstListElem = null;
        this.lastListElem = null;
        this.selectedElem = null;
        this.textOfSelectedLiElem = null;
        this.inputField = null;
    }

    _createClass(LanguageTypesDOMModel, [{
        key: "getIndexOfElem",
        value: function getIndexOfElem(selectedElem) {
            return [].findIndex.call(this.languageTypeListItems, function (elem) {
                return elem === selectedElem;
            });
        }
    }, {
        key: "init",
        value: function init() {
            var _this = this;

            this.languageTypeList = document.querySelector('#languageTypes');
            this.languageTypeListItems = document.querySelectorAll('#languageTypes li');
            this.firstListElem = document.querySelector('#languageTypes > li');
            this.lastListElem = document.querySelector('#languageTypes > li:last-child');
            this.inputField = document.querySelector('input');
            // TODO: Remove handler of each elem, befor setting again.
            [].forEach.call(this.languageTypeListItems, function (item) {
                item.addEventListener('click', function () {
                    _this.selectedElem = document.querySelector('.selected');
                    if (_this.selectedElem) {
                        _this.selectedElem.classList.toggle('selected');
                    }
                    item.classList.toggle('selected');
                    // TODO: Test the click handler event.
                    console.log("clicked");
                    _this.inputField.focus();
                });
            });
        }
    }, {
        key: "moveUp",
        value: function moveUp() {
            if (this.selectedElem) {
                if (!this.PreviousElement) {
                    this.selectedElem.classList.remove('selected');
                    this.selectedElem = this.lastListElem;
                    this.textOfSelectedLiElem = this.selectedElem.lastChild;
                    this.selectedElem.classList.toggle('selected');
                    this.languageTypeList.scrollTop = 800;
                } else {
                    if (this.selectedElem.offsetTop < 450) {
                        this.languageTypeList.scrollTop = this.getIndexOfElem(this.selectedElem) / this.selectedElem.offsetHeight;
                    }
                    this.selectedElem.classList.remove('selected');
                    this.selectedElem = this.PreviousElement;
                    this.textOfSelectedLiElem = this.selectedElem.lastChild;
                    this.selectedElem.classList.toggle('selected');
                }
            } else {
                this.selectedElem = this.LastListElem;
                this.textOfSelectedLiElem = this.selectedElem.lastChild;
                this.selectedElem.classList.toggle('selected');
                this.languageTypeList.scrollTop = 800;
            }
        }
    }, {
        key: "moveDown",
        value: function moveDown() {
            if (this.selectedElem) {
                if (this.selectedElem.offsetTop > 400) {
                    this.languageTypeList.scrollTop = this.getIndexOfElem(this.selectedElem) * this.selectedElem.offsetHeight;
                }
                if (!this.NextElement) {
                    this.selectedElem.classList.remove('selected');
                    this.selectedElem = this.FirstListElem;
                    this.textOfSelectedLiElem = this.selectedElem.lastChild;
                    this.selectedElem.classList.toggle('selected');
                    this.languageTypeList.scrollTop = 0;
                } else {
                    this.selectedElem.classList.remove('selected');
                    this.selectedElem = this.NextElement;
                    this.textOfSelectedLiElem = this.selectedElem.lastChild;
                    this.selectedElem.classList.toggle('selected');
                }
            } else {
                this.selectedElem = this.FirstListElem;
                this.textOfSelectedLiElem = this.selectedElem.lastChild;
                this.selectedElem.classList.toggle('selected');
            }
        }
    }, {
        key: "handleItemSelectionWithArrowKeys",
        value: function handleItemSelectionWithArrowKeys() {
            var _this2 = this;

            document.querySelector('body').addEventListener('keydown', function (evt) {
                _this2.SelectedElem = document.querySelector('.selected');
                if (evt.keyCode === KeyCode.Up) {
                    evt.preventDefault();
                    _this2.moveUp();
                } else if (evt.keyCode === KeyCode.Down) {
                    evt.preventDefault();
                    _this2.moveDown();
                }
            });
        }
    }, {
        key: "selectFirstElem",
        value: function selectFirstElem() {
            var _this3 = this;

            this.inputField.addEventListener('keyup', function (e) {
                if (e.keyCode !== KeyCode.Down && e.keyCode !== KeyCode.Up) {
                    _this3.selectedElem = document.querySelector('.selected');
                    if (_this3.selectedElem) {
                        _this3.selectedElem.classList.remove('selected');
                    }
                    if (!!_this3.inputField.value) {
                        _this3.firstListElem && _this3.firstListElem.classList.add('selected');
                    } else {
                        _this3.firstListElem && _this3.firstListElem.classList.remove('selected');
                    }
                }
            });
        }
    }, {
        key: "getDataFromSelectedElem",
        value: function getDataFromSelectedElem() {
            var _this4 = this;

            console.log(this.selectedElem);
            this.selectedElem && this.selectedElem.addEventListener('keydown', function (e) {
                if (e.keyCode === KeyCode.Enter) {
                    console.log(_this4.selectedElem);
                }
            });
        }
    }, {
        key: "List",
        get: function get() {
            return this.languageTypeList;
        }
    }, {
        key: "LanguageTypeListItems",
        get: function get() {
            return this.languageTypeListItems;
        }
    }, {
        key: "SelectedElem",
        get: function get() {
            return this.selectedElem;
        },
        set: function set(value) {
            this.selectedElem = value;
        }
    }, {
        key: "PreviousElement",
        get: function get() {
            return this.selectedElem.previousElementSibling;
        }
    }, {
        key: "NextElement",
        get: function get() {
            return this.selectedElem.nextElementSibling;
        }
    }, {
        key: "FirstListElem",
        get: function get() {
            return this.firstListElem;
        }
    }, {
        key: "LastListElem",
        get: function get() {
            return this.lastListElem;
        }
    }]);

    return LanguageTypesDOMModel;
}();

exports.LanguageTypesDOMModel = LanguageTypesDOMModel;

/***/ }),
/* 6 */
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ })
/******/ ]);
//# sourceMappingURL=app.js.map