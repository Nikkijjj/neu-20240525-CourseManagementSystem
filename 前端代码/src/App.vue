<template>
  <div class="app">
    <div class="content">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script lang="ts">
  import { defineComponent } from 'vue';
  import { RouterView } from 'vue-router';
  //JS或TS
  export default defineComponent({
    name:'App',//组件名
    // components:{loginMenu},
  })
</script>

<script lang="ts" setup>

  // const router = useRouter();

  // function superManagerLogin() {
  //   router.push('superManagerLogin')
  // }

  // function tenantManagerLogin(){
  //   router.push('tenantManagerLogin')
  // }

  // function register(){
  //   router.push('register')
  // }
</script>

<style>
 
  body{
    overflow-y: hidden;
  }
  h1,h2,p{
    margin: 0;
    padding: 0;
  }
  a{
    text-decoration: none;
    color:#000;
  }
</style>