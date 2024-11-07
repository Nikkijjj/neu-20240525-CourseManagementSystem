<template>
    <div class="firstPage">
      <div class="title">
        <h1 style="font-size: 40px;color: azure;display: flex;justify-content: center;">测 盟 汇 管 理 系 统</h1>
      </div>

    <div class="navigate">
      <el-button to="/superManagerLogin"  @click="superManagerLogin" style="color: white;background-color: rgba(96, 96, 96, 0.9);margin-left: 0px;margin-right: 30px;margin-bottom: 260px;margin-top: 200px;font-size: large;width: 250px;height: 300px;font-size: 20px;" :icon="Avatar">超级管理员登录</el-button>
      <el-button to="/tenantManagerLogin" @click="tenantManagerLogin" style="color: white;background-color: rgba(96, 96, 96, 0.9);margin-left: 30px;margin-right: 30px;margin-bottom: 260px;margin-top: 200px;font-size: large;width: 250px;height: 300px;font-size: 20px;" :icon="UserFilled">租户管理员登录</el-button>
      <el-button to="/register" @click="register" style="color: white;background-color: rgba(96, 96, 96, 0.9);margin-left: 30px;margin-right: 0px;;margin-bottom: 260px;margin-top: 200px;font-size: large;width: 250px;height: 300px ;font-size: 20px;" :icon="List">用户注册</el-button>
    </div>
      
    </div>
</template>
  
  <script lang="ts">
    import { defineComponent } from 'vue';
    import { useRouter} from 'vue-router';
    import { Avatar, UserFilled, List } from '@element-plus/icons-vue';
    //JS或TS
    export default defineComponent({
      name:'App',//组件名
    })
  </script>
  
  <script lang="ts" setup>
  
    const router = useRouter();
  
    function superManagerLogin() {
      router.push('superManagerLogin')
    }
  
    function tenantManagerLogin(){
      router.push('tenantManagerLogin')
    }
  
    function register(){
      router.push('register')
    }
  </script>
  
  <style scoped>
    /*样式*/ 
    .navigate{
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .firstPage{
      background-image: url("/src/images/111.jpg");
      background-size: cover;
    }
    
  </style>