<template>
    <div class="loginMenu">
        <i class="el-icon-s-home"></i>
        <h2>请选择入口</h2>
        <RouterLink to="/loginMenu/superManagerLogin"
        class='el-button'
        size="medium"
        type="primary"
        style="width: 150px;height: 50px;"
        @click="superManagerLogin">超级管理员登录</RouterLink>
        <RouterLink to="/tenantManagerLogin"
        class='el-button'
        size="medium"
        type="primary"
        style="width: 150px;height: 50px;"
        @click="tenantManagerLogin">租户管理员登录</RouterLink>
        <RouterLink to="/register"
        class='el-button'
        size="medium"
        type="primary"
        style="width: 150px;height: 50px;"
        @click="register">注册</RouterLink>
    </div>
    <div class="superManagerLogin">
       
    </div>
</template>

<script lang="ts">
   import { defineComponent } from 'vue';
   import { RouterLink, useRouter } from 'vue-router';
   export default defineComponent({
    name:'loginMenu',
   })

</script>

<script lang="ts" setup>

    const router = useRouter();

    function superManagerLogin(){
        router.push('superManagerLogin');
    }

    function tenantManagerLogin(){
        router.push('/home');
    }

    function register(){
        router.push('/register');
    }
</script>

<style>
   
</style>