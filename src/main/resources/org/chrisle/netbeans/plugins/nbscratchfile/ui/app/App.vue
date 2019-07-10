<template>
  <div>
    <language-search></language-search>
    <language-list></language-list>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import { Component } from "vue-property-decorator";

import LanguageTypeSearchFieldComponent from "./LanguageTypeSearchFieldComponent/LanguageTypeSearchFieldComponent.vue";
import LanguageTypeListComponent from "./LanguageTypeListComponent/LanguageTypeListComponent.vue";

@Component({
  components: {
    "language-search": LanguageTypeSearchFieldComponent,
    "language-list": LanguageTypeListComponent
  }
})
export default class App extends Vue {
  constructor() {
    super();
  }
}
</script>