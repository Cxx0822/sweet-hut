<template>
  <section class="app-main">
    <router-view />
  </section>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
.app-main {
  width: 100%;
  height: 650px;
  position: relative;
  overflow: hidden;

  cursor: pointer;
  background: rgb(255, 255, 255);
  border-radius: 5px;
  // border: 1px solid rgba(0, 0, 255, .2);
  transition: all .2s;
  box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, .2);
}
</style>
