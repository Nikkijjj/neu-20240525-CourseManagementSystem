import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import { fileURLToPath, URL } from 'node:url'; // 导入需要的模块

export default defineConfig({
  plugins: [
    vue(),
  ],
  server: {
    port: 5175,
    open: true,
    cors: true,
    proxy: {
      '/api': {
        target: 'http://localhost:9000',
        changeOrigin: true,
        rewrite: (path) => path.replace('/api', ''),
      },
      '/image': {
        target: 'https://gitee.com',
        changeOrigin: true,
        rewrite: (path) => path.replace('/image', ''),
      },
    },
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
});
