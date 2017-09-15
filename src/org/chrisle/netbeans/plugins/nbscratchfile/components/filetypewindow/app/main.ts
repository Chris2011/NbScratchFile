import {App} from "./app";
import {} from './main.scss';

//import {WebpackRequire} from 'webpack-env';
//
//declare var require: WebpackRequire;
//
//var files = require.context('./icons', false, /\.svg$/);
//files.keys().forEach(files);

const starter: App = new App();
starter.main();