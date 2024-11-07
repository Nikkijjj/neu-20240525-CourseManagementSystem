<template>
    <div class="content" style="height: 660px">
        <el-icon size="60" ><UserFilled /></el-icon>
        <div style="margin: 60px auto; background-color: #fff; width: 350px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
            <el-form :rules="rules" ref="userForm">
                <el-form-item prop="tenantname">
                    <el-input size="medium" style="margin: 10px 0" placeholder="请输入企业名称" v-model="input">
                    <template v-slot:prefix>
                        <el-icon size="20">
                            <HomeFilled />
                        </el-icon>
                    </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input size="medium" style="margin: 10px 0" placeholder="请输入用户名" v-model="input2">
                    <template v-slot:prefix>
                        <el-icon size="20">
                            <UserFilled />
                        </el-icon>
                    </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" style="margin: 10px 0" show-password placeholder="请输入密码" v-model="input3">
                    <template v-slot:prefix>
                        <el-icon size="20">
                            <Lock/>
                        </el-icon>
                    </template>
                    </el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button class='button' type="primary" size="medium" autocomplete="off" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
        
    
</template>

<script lang="ts">
    import { ref } from 'vue';
    import axios from 'axios';
    import { RouterView, useRouter} from 'vue-router';
    import { Edit, HomeFilled, Lock, Share, UserFilled } from '@element-plus/icons-vue';
    
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
    export default {
    name:'tenantManagerLogin',
   }
</script>

<script lang="ts" setup>
    const input = ref('');
    const input2 = ref('');
    const input3 = ref('');
    const router = useRouter();

    const rules = {
        name: [
            {required: true, message: '请输入用户名', trigger:'blur'},
            {min:3, max: 10, message: '长度在3到10个字符', trigger: 'blur'}
        ],
        password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
    };

    async function login(){
        try{
            const response = await axios.post('/api/user/login',{
                tenantname: input.value,
                userName: input2.value,
                password: input3.value
            });

            //请求成功处理
            console.log('登录成功!');
            router.push({
                name:'Layout_2',
                query: {
                    username:input2.value
                }
            })
        } catch (error) {
            console.error('登录失败！');
        }
    }
    
</script>

<style scoped>
    .content{
        /* background-image: url('/src/images/backpic.jpg'); */
        background-color: aliceblue;
        background-size: cover;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        padding: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 50px;
    }
    .button {
        display: grid;
        place-items: center; /* 水平和垂直居中 */
        width: 40%; /* 设置按钮宽度为父元素宽度的50% */
        height: 40px; /* 设置按钮固定高度 */
        margin-left: 105px;
    }

</style>