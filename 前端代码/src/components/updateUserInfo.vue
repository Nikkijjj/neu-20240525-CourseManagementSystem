<template>
  <div class="content" style="height: 660px;">
      <el-form ref="userForm" style="margin-left: 200px;margin-right: 200px;margin-top: 10px">
        <div style="margin: 20px ; text-align: center; font-size: 20px;margin-left: 50px;margin-bottom: 50px"><b>修改个人信息</b></div>
        <el-form-item label="用户头像" prop="uploadedImage" >
          <UploadImg  :coverUrl="image3" style="margin-right: 1px;margin-left: 23px" @uploaded="handleImageUpload"/>
        </el-form-item>
          <el-form-item prop="nickname" label="用户昵称" >
              <el-input size="medium" style="margin-right: 1px;margin-left: 23px;width: 500px;" v-model="nickname"
                        ></el-input>
          </el-form-item>

          <!-- <el-form-item label="原本密码" prop="newPw">
              <el-input size="medium" style="margin-right: 1px;margin-left: 23px;width: 500px;" show-password placeholder="请输入旧密码" v-model="oldPw">
              </el-input>
          </el-form-item>

          <el-form-item label="新设密码" prop="newPw">
              <el-input size="medium" style="margin-right: 1px;margin-left: 23px;width: 500px;" show-password placeholder="请输入新密码" v-model="newPw">
              </el-input>
          </el-form-item>

          <el-form-item label="确认密码" prop="newPw">
              <el-input size="medium" style="margin-right: 1px;margin-left: 23px;width: 500px;" show-password placeholder="请再次输入密码" v-model="confirmPw">
              </el-input>
          </el-form-item> -->
      
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

          <el-link @click="openDialog">
            修改密码
          </el-link>

          <!-- <PasswordDialog
            v-model:visible="dialogVisible"
            @submit="handleSubmit"
          /> -->
          <el-form-item style="margin: 10px 0; text-align: right;margin-left: 300px;margin-top: 50px;">
              <el-button class='button' type="primary" size="large" autocomplete="off" @click="goBack" style="width: 100px;">返 回</el-button>
              <el-button class='button' type="primary" size="large" autocomplete="off" @click="edit" style="margin-left: 100px;width: 100px;">修 改</el-button>
          </el-form-item>

      </el-form>
  </div>
  <!-- <el-dialog
    title="修改密码"
    :visible="visible"
    @close="handleCancel"
  >
    <el-form :model="pwdForm">
      <el-form-item label="原密码">
        <el-input 
          v-model="pwdForm.oldPwd"
          show-password
        />
      </el-form-item>

      <el-form-item label="新密码">
        <el-input v-model="pwdForm.newPwd" ></el-input>
      </el-form-item>

      <el-form-item label="确认密码">
        <el-input v-model="pwdForm.confirmPwd" ></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handleSubmit">修改</el-button>
    </span>
  </el-dialog> -->
</template>
  
<script lang="ts">
    import UploadImg from '@/components/UploadImg_jhy.vue';
    import { ref } from 'vue';
    import { ElMessage, ElMessageBox } from 'element-plus';
    import axios from 'axios';
    import { useRoute, useRouter } from 'vue-router';
    import { onMounted } from 'vue';
    // import selectedCourses from './Test1.vue';
</script>
    
  <script lang="ts" setup>
  
  // const pwdForm = reactive({
  //   oldPwd: '',
  //   newPwd: '', 
  //   confirmPwd: ''
  // })
  
    const uploadUrl = '/api/course/update'; // 替换成实际的后端上传视频的接口地址
    const videoUrl = ref(''); // 响应式变量，用于存储上传成功后视频的地址

    const userName = ref('');
    const nickname = ref('');
    const phone = ref('');
    const email = ref('');
    const createTime = ref('');
    const role = ref('');
    const tenantId = ref('');
    const logo = ref('');
    let image3 = ref('');
    const newPw = ref('');
    const oldPw = ref('');
    const confirmPw = ref('');
  
    const router = useRouter();
    const route = useRoute();
  
    const message = ElMessage;
  
     // 定义用户类型
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

    onMounted(() => {
      searchCourseById();
      searchLogoById();
    });

    const dialogVisible = ref(false)

    const openDialog = () => {
      dialogVisible.value = true
    }

      // 关闭对话框
  const handleCancel = () => {
    dialogVisible.value = false
  }

    // 修改密码提交
  // const handleSubmit = () => {
  //   axios.get('/api/user/updatePw', {
  //   params: {
  //     username: route.query.username,
  //     newPwd: pwdForm.newPwd
  //   }
  //   })
  //   .then(response => {
  //   const { isOk, msg} = response.data; // 解构响应数据中的字段
  
  //   if (isOk) {
  //     message.success(msg); // 显示成功消息
  //   } else {
  //     // 如果查询失败
  //     message.info(msg); // 显示信息消息，可能是未找到匹配的课程
  //   }
  //   })
  //   .catch(error => {
  //     message.error('修改失败！');
  //   });
  // }

    const goBack = () => {
    // 使用 router 的 push 方法进行路由导航
    router.push('/test/:username')
  };

  const edit = () => {
    axios.get('/api/user/updateInfo', {
    params: {
      username: route.query.username,
      nickname: nickname.value,
      phone: phone.value,
      email: email.value,
      tenantId: tenantId.value,
      role: role.value,
      createTime: createTime.value,
      image3: image3.value
    }
    })
    .then(response => {
    const { isOk, msg} = response.data; // 解构响应数据中的字段
  
    if (isOk) {
      message.success(msg); // 显示成功消息
    } else {
      // 如果查询失败
      message.info(msg); // 显示信息消息，可能是未找到匹配的课程
    }
    })
    .catch(error => {
      message.error('修改失败！');
    });
  }
  const searchCourseById = () => {
  
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
      console.error('课程搜索失败', error);
      message.error('课程搜索失败！');
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
  
    const handleImageUpload = (imageUrl: any) => {
          image3.value = imageUrl.download_url;
    };
  </script>
    
  <style scoped>
    /* .content{
      display: flex;
      justify-content: center;
      margin-top: 20px;
    } */
    
  
  </style>