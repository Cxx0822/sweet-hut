<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      
      <!-- 主体区域 -->
      <el-main>
        <AppMain />
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import AppMain from '@/layout/components/AppMain.vue'
</script>

<style lang="scss">
</style>

<style lang="scss" scoped>
</style>
