import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'

// 引入路由
import router from './router'

// 引入element-ui组件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'dayjs/locale/zh-cn' // 中文
import locale from 'element-plus/lib/locale/lang/zh-cn' // 中文

// 引入icon-park
import '@icon-park/vue-next/styles/index.css'

// 创建Vue3实例
const app = createApp(App)
// 使用路由
app.use(router)
// 使用Pinia 需要ts 4.5以上
app.use(createPinia())
// 使用Element UI Plus
app.use(ElementPlus, { locale })
// 挂载到根组件上
app.mount('#app')
