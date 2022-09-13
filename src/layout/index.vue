<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <!-- 导航栏 -->
        <Narbar />
      </el-header>

      <!-- 主体区域 -->
      <el-container>
        <el-aside width="250px">
          <Aside />
        </el-aside>

        <el-main>
          <AppMain />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import AppMain from '@/layout/components/AppMain.vue'
import Narbar from '@/layout/components/Narbar.vue'
import Aside from '@/layout/components/Aside.vue'
</script>

<style lang="scss">
</style>

<style lang="scss" scoped>
</style>
