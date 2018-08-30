import Vue from 'vue';
import './main.scss';

import App from './App.vue';

new Vue({
    el: '#app',
    render(h) {
        return h('App');
    },
    components: {
        App
    }
});