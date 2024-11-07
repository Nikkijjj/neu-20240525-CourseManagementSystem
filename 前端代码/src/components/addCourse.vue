<template>
  <div class="addForm">
    <el-form ref="courseForm" :rules="rules" label-width="auto" style="max-width: 600px;">
      <el-text class="mx-1" style="display: flex;justify-content: center;font-size: 26px;margin-bottom: 40px;margin-top: 20px;">添加课程信息</el-text>
      <el-form-item label="课程名称"  prop="courseName">
        <el-input type="text"v-model="courseName" />
      </el-form-item>
      <el-form-item label="课程作者" prop="author">
        <el-input type="text" v-model="author" />
      </el-form-item>
      <el-form-item label="课程学分">
        <el-input v-model="credit" />
      </el-form-item>
      <el-form-item label="课程学时">
        <el-input v-model="courseHour" />
      </el-form-item>
      <el-form-item label="课程类型">
        <el-select v-model="courseType" placeholder="请选择课程类型">
          <el-option label="理论" value="理论" />
          <el-option label="实践" value="实践" />
        </el-select>
      </el-form-item>

      <el-form-item label="课程时间">
        <el-col :span="11">
          <el-date-picker
            v-model="beginTime"
            type="date"
            placeholder="开课时间"
            style="width: 100%"
          />
        </el-col>

        <el-col :span="2" class="text-center">
          <span class="text-gray-500" style="margin-left: 18px;">-</span>
        </el-col>

        <el-col :span="11">
          <el-date-picker
            v-model="endTime"
            placeholder="结课时间"
            style="width: 100%"
          />
        </el-col>
      </el-form-item>
      
    

      <el-form-item label="课程描述"  prop="courseDescription">
        <el-input
          v-model="courseDescription"
          maxlength="400"
          style="width: 600px;"
          placeholder="请输入课程描述（不超过400字）"
          show-word-limit
          type="textarea"
  />
      </el-form-item>
    <div class="imageAndVideo" style="display: flex; justify-content: space-between;">
      <el-form-item label="课程封面" prop="uploadedImage">
          <UploadImg @uploaded="handleImageUpload" />
      </el-form-item>

      <el-form-item label="课程视频" prop="uploadedVideo">
        <el-upload
          v-model="courseVideo"
          class="upload-demo"
          ref="upload"
          :action="uploadUrl"
          :on-success="handleSuccess"
          :show-video-list="false"
          :before-upload="beforeUploadVideo"
        >
          <el-button type="primary">点击上传课程视频</el-button>
          <template #tip>
            <div class="el-upload__tip">
              mp4文件 视频大小不超过100MB.
            </div>
          </template>
        </el-upload>
        <video v-if="videoUrl" :src="videoUrl" controls style="max-width: 266px;"></video>
      </el-form-item>
    </div>
      <el-form-item style="display: flex;justify-content: center;margin-left: 240px;margin-top: 50px">
        <el-button type="primary" @click="onSubmit" style="width: 120px;height: 40px;">提交</el-button>
      </el-form-item>
      
    </el-form>
  </div>
</template>

<script lang="ts">
    import UploadImg from '@/components/UploadImg_jhy.vue';
    import { ref } from 'vue';
    import { ElMessage } from 'element-plus';
    import { watch } from 'vue';
    import { format } from 'date-fns';
    import axios from 'axios';
    import { useRouter } from 'vue-router';
</script>
  
<script lang="ts" setup>

  const uploadUrl = ' /api/file/upload';
  const uploadMapping = '/api/upload/'
  const videoUrl = ref(''); // 响应式变量，用于存储上传成功后视频的地址
  const courseName = ref('');
  const author = ref('');
  const credit = ref('');
  const courseHour = ref('');
  const courseType = ref('');
  const beginTime = ref('');
  const endTime = ref('');
  const courseDescription = ref('');
  let courseVideo = ref('');
  let image2 = ref('');

  const formattedBeginTime = ref('');
  const formattedEndTime = ref('');

  const router = useRouter();

  const rules = {
    courseName: [
      { required: true, message: '请输入课程名称', trigger: 'blur' }
    ],
    author: [
      { required: true, message: '请输入课程作者', trigger: 'blur' }
    ],
    courseDescription: [
      { required: true, message: '请输入课程描述', trigger: 'blur' }
    ]
    // 可以根据需要添加其他字段的验证规则
  };

  watch(beginTime, (newValue:any) => {
    if (newValue instanceof Date) {
      formattedBeginTime.value = format(newValue, 'yyyy-MM-dd');
    }
  });

  watch(endTime, (newValue:any) => {
    if (newValue instanceof Date) {
      formattedEndTime.value = format(newValue, 'yyyy-MM-dd');
    }
  });

  // 监听表单项变化，重新验证
  watch(courseName, () => validateField('courseName'));
  watch(author, () => validateField('author'));
  watch(courseDescription, () => validateField('courseDescription'));

  async function onSubmit() {
  // 检查表单必填项是否填写
  const isValid = await validateForm();
  
  if (!isValid) {
    ElMessage.error('请填写必填项！');
    return;
  }
  
  console.log(image2.value);
  // console.log(courseVideo.value);
  try {
    const response = await axios.post('/api/course/add', {
      courseName: courseName.value,
      author: author.value,
      credit: credit.value,
      courseHour: courseHour.value,
      courseType: courseType.value,
      beginTime: formattedBeginTime.value,
      endTime: formattedEndTime.value,
      courseDescription: courseDescription.value,
      courseVideo: videoUrl.value,
      courseCover: image2.value
    });
    console.log('课程添加成功！');
    ElMessage.success('课程更新成功！');
    router.push('/test1');
  } catch (error) {
    console.error('课程添加失败！', error);
    ElMessage.error('课程添加失败！');
  }
}

async function validateForm() {
  try {
    await Promise.all([
      validateField('courseName'),
      validateField('author'),
      validateField('courseDescription')
      // 可以根据需要添加其他必填项的验证
    ]);
    return true;
  } catch (error) {
    return false;
  }
}

const validateField = async (field: keyof typeof rules) => {
  const rulesForField = rules[field];
  const value = eval(field + '.value'); // 通过eval获取对应字段的值

  for (let i = 0; i < rulesForField.length; i++) {
    const rule = rulesForField[i];
    if (rule.required && (!value || value.trim() === '')) {
      ElMessage.error(rule.message);
      throw new Error(rule.message);
    }
  }
}

  const handleImageUpload = (imageUrl: any) => {
        image2.value = imageUrl.download_url;
  };


  // 文件上传前的处理函数
  const beforeUploadVideo = (file:any) => {
      const isVideo = file.type.startsWith('video/');
      if (!isVideo) {
        ElMessage.error('请上传视频文件');
      }
      const isLt100M = file.size / 1024 / 1024 < 100;
      if (!isLt100M) {
        ElMessage.error('上传视频大小不能超过 100MB');
      }
      return isVideo && isLt100M; // 返回 true 表示验证通过，允许上传
    };

    // 文件上传成功的处理函数
    const handleSuccess = (response:any) => {
      console.log("文件上传成功",response);
      
      if (response.code === 200) {
        videoUrl.value = uploadMapping + response.data; // 假设后端返回的数据中包含视频的访问 URL
      } else {
        ElMessage.error(response.msg); // 处理上传失败，显示错误消息
      }
    };

</script>
  
<style scoped>
  .addForm{
    display: flex;
    justify-content: center;
    margin-top: 20px;
  }

</style>