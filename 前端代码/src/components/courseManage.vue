<template>

      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-menu-item index="1">
            <span>导航一</span>
          <!-- <el-menu-item-group title="分组一">
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template #title>选项4</template>
            <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu> -->
        </el-menu-item>
        <el-menu-item index="2">
          <span>导航二</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <span>导航三</span>
        </el-menu-item>
        <el-menu-item index="4">
          <span>导航四</span>
        </el-menu-item>
      </el-menu>
   
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  methods: {
    handleOpen(key: string, keyPath: string[]) {
      console.log(key, keyPath);
    },
    handleClose(key: string, keyPath: string[]) {
      console.log(key, keyPath);
    }
  }
});
</script>

<style scoped>
/* 可以根据需要添加样式 */
.el-menu-item {
  height: 157px; /* 设置菜单项的固定高度 */
  line-height: 157px; /* 使文本垂直居中 */
  padding-left: 20px; /* 左侧内边距（调整到你需要的距离） */
  padding-right: 20px; /* 右侧内边距（调整到你需要的距离） */
  text-align: center; /* 文本水平居中 */
}

.el-menu {
  width: 100px; /* 设置菜单栏宽度 */
  height: 100%; /* 充满父容器高度 */
  position: fixed; /* 设置为固定定位，相对于视口左侧定位 */
  left: 0; /* 紧贴页面左侧 */
  top: 280px; /* 紧贴页面顶部 */
}

</style>

