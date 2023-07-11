<template>
  <el-container class="layout-container">
    <el-header>
      <!-- 导航栏 -->
      <Navbar />
    </el-header>

    <!-- 主体区域 -->
    <el-container class="main-container">
      <el-aside>
        <Aside />
      </el-aside>

      <el-main>
        <el-card>
          <AppMain />
        </el-card>
      </el-main>
    </el-container>

    <el-footer>
      Footer
    </el-footer>
  </el-container>
</template>

<script setup lang="ts">
import AppMain from '@/layout/components/AppMain.vue'
import Navbar from '@/layout/components/Navbar.vue'
import Aside from '@/layout/components/Aside.vue'
</script>

<style lang="scss">
</style>

<style lang="scss" scoped>
.layout-container {
  height: 100%;

 .main-container {
   padding: 10px;

   .el-aside {
     width: 250px;
   }

   .el-main {
     margin-left: 10px;
     padding: 0;

     .el-card {
       // 有1个border的宽度
       height: 98%;
     }
   }
 }
}
</style>
