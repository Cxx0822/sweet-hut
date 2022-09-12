<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <!-- 导航栏 -->
        <Narbar />
      </el-header>

      <!-- 主体区域 -->
      <el-main>
        <AppMain />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import AppMain from '@/layout/components/AppMain.vue'
import Narbar from '@/layout/components/Narbar.vue'
</script>

<style lang="scss">
</style>

<style lang="scss" scoped>
</style>
