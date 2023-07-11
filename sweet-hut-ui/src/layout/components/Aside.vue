<template>
  <div class="aside-div">
    <el-card class="box-card">
    </el-card>
  </div>
</template>

<script lang="ts" setup>
</script>

<style lang="scss">
.aside-div {
  .el-card {
    display:flex;
    justify-content: center;

    min-height: 700px;

    cursor: pointer;
    background: rgb(255, 255, 255);
    border-radius: 5px;
    border: 1px solid rgba(0, 0, 255, .2);
    transition: all .2s;
    box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, .2);
  }
}
</style>
