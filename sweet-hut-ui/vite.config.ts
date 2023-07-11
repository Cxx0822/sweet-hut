import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 需要安装 npm install @types/node --save-dev
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  define: {
    'process.env': {}
  },
  base: './',
  resolve: {
    // 配置路径别名
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  }
})
