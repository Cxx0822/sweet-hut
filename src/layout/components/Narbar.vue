<template>
  <div class="narbar-div">
    <el-menu
      :default-active="'/'"
      mode="horizontal"
      router
      :ellipsis="false"
    >
      <el-menu-item index="/home">Home</el-menu-item>
      <el-menu-item index="/cookery-book">菜谱</el-menu-item>
      <el-menu-item index="/travel">旅行</el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped lang="scss">
.narbar-div {
  float: right;
}
</style>
