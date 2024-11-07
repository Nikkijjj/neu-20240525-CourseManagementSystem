<template>
    <div class="content" style="height: 660px;">
        <div style="margin: 20px ; text-align: center; font-size: 20px;margin-left: 40px;margin-top: 50px"><b>个人信息查看</b></div>
        <el-form ref="userForm" style="margin-left: 250px;margin-right: 300px;margin-top: 60px">

          <el-form-item label="用户头像" prop="uploadedImage" >
            <UploadImg  :coverUrl="image3" style="margin-right: 1px;margin-left: 23px" @uploaded="handleImageUpload"/>
          </el-form-item>

            <el-form-item prop="nickname" label="用户昵称">
                <el-input size="medium" style="margin-right: 1px;margin-left: 23px" v-model="nickname"
                          ></el-input>
            </el-form-item>
        
            <el-form-item prop="phone" label="手机号码">
                <el-input size="medium" style="margin-right: 1px;margin-left: 23px" v-model="phone"
                          ></el-input>
            </el-form-item>
            <el-form-item prop="email" label="用户邮箱">
                <el-input size="medium" style="margin-right: 1px;margin-left: 23px" v-model="email"
                          ></el-input>
            </el-form-item>
            <el-form-item prop="tenantId" label="所属部门">
                <el-input size="medium" style="margin-right: 1px;margin-left: 23px" v-model="tenantId"
                          ></el-input>
            </el-form-item>
            <el-form-item prop="role" label="所属角色">
                <el-input size="medium" style="margin-right: 1px;margin-left: 23px" v-model="role"
                          ></el-input>
            </el-form-item>
            <el-form-item prop="createTime" label="创建日期">
                <el-input size="medium" style="margin-right: 1px;margin-left: 23px" v-model="createTime"
                          ></el-input>
            </el-form-item>

            <el-form-item style="margin: 10px 0; text-align: right;margin-top: 50px;">
                <el-button class='button' type="primary" size="medium" autocomplete="off" @click="goBack">返 回</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>
  
  <script lang="ts">
      import UploadImg from '@/components/UploadImg_jhy.vue';
      import { ref } from 'vue';
      import { ElMessage } from 'element-plus';
      import axios from 'axios';
      import { useRoute, useRouter } from 'vue-router';
      import { onMounted } from 'vue';
      // import selectedCourses from './Test1.vue';
      
  </script>
    
  <script lang="ts" setup>
    const nickname = ref('');
    const phone = ref('');
    const email = ref('');
    const tenantId = ref('')
    const role = ref('');
    const createTime = ref('');
    let image3 = ref('');
  
    const router = useRouter();
    const route = useRoute();
  
    const message = ElMessage;
  
     // 定义课程类型
    interface User {
      userName: string;
      nickname: string;
      phone: string;
      email: number;
      createTime: string;
      role: string;
      tenantId: string;
      logo: string;
    }
  
    const user = ref<User[]>([]);
  
    onMounted(() => {
      searchUserById();
      searchLogoById();
    });
  
  const searchUserById = () => {
  
    axios.get('/api/user/searchByName', {
      params: {
        username: route.query.username,
      }
    })
    .then(response => {
    const { isOk, msg, user } = response.data; // 解构响应数据中的字段
  
    if (isOk) {
      nickname.value = user[0].nickname;
      phone.value =  user[0].phone;
      email.value = user[0].email;
      tenantId.value = user[0].tenantId;
      role.value = user[0].role;
      createTime.value = user[0].createTime;
  
      message.success(msg); // 显示成功消息
    } else {
      // 如果查询失败
      message.info(msg); // 显示信息消息，可能是未找到匹配的课程
    }
    })
    .catch(error => {
      console.error('信息搜索失败', error);
      message.error('信息搜索失败！');
    });
  };

const searchLogoById = () => {
  
    axios.get('/api/user/searchLogo', {
      params: {
        username: route.query.username,
      }
    })
    .then(response => {
    const { isOk, msg, user } = response.data; // 解构响应数据中的字段

    if (isOk) {
      image3.value = user;

      message.success(msg); // 显示成功消息
    } else {
      // 如果查询失败
      message.info(msg); // 显示信息消息，可能是未找到匹配的课程
    }
    })
    .catch(error => {
      console.error('信息搜索失败', error);
      message.error('信息搜索失败！');
    });
};

  
  
  const goBack = () => {
    // 使用 router 的 push 方法进行路由导航
    router.push('/test')
  };

  const handleImageUpload = (imageUrl: any) => {
        image3.value = imageUrl.download_url;
  };
  </script>
    
  <style scoped>
    .button {
        display: grid;
        place-items: center; /* 水平和垂直居中 */
        width: 40%; /* 设置按钮宽度为父元素宽度的50% */
        height: 40px; /* 设置按钮固定高度 */
        margin-left: 250px;
    }
  </style>