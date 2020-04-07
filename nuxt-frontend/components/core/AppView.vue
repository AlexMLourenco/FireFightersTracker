<template>
  <v-content>
    <div id="core-view">
      <v-fade-transition mode="out-in">
        <nuxt />
      </v-fade-transition>
    </div>
  </v-content>
</template>

<script>

  export default {
    metaInfo () {
      return {
        title: 'Vuetify Material Dashboard by CreativeTim'
      }
    },
    components: {
    }
  }
</script>

<style>
  #core-view {
    padding-bottom: 100px;
  }
</style>
