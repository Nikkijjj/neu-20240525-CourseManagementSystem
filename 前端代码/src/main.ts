import { createApp } from 'vue';

import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css'; // 导入 Element Plus 的样式
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


import App from './App.vue';

//引入路由器
import router from './router';

//创建一个应用
const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
//使用路由器
app.use(router)

// 全局注册 ElementPlus 组件
app.use(ElementPlus);

//挂在整个应用到app容器中
app.mount('#app');
