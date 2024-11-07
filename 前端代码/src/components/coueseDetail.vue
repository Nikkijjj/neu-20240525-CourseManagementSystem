<template>
  <div class="content">
    <el-form label-width="auto" style="max-width: 600px;">
      <el-text class="mx-1" style="display: flex;justify-content: center;font-size: 26px;margin-bottom: 40px;margin-top: 20px;">查看课程详情</el-text>
      <el-form-item label="课程名称">
        <el-input v-model="courseName" />
      </el-form-item>
      <el-form-item label="课程作者">
        <el-input v-model="author" />
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

      <el-form-item label="课程描述">
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
          <UploadImg  :coverUrl="image3" @uploaded="handleImageUpload"/>
      </el-form-item>

      <el-form-item label="课程视频" prop="uploadedImage">
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
    import { watch } from 'vue';
    import { format } from 'date-fns';
    import axios from 'axios';
    import { useRoute, useRouter } from 'vue-router';
    import { onMounted } from 'vue';
    // import selectedCourses from './Test1.vue';
    
</script>
  
<script lang="ts" setup>

  
  const uploadMapping = '/api/upload/'
  const displayUrl = ref('');
  const uploadUrl = '/api/course/update'; // 替换成实际的后端上传视频的接口地址
  const videoUrl = ref(''); // 响应式变量，用于存储上传成功后视频的地址
  // do not use same name with ref
  // const form = reactive({
  //   courseName: '',
  //   author: '',
  //   credit: '',
  //   courseHour: '',
  //   courseType: '',
  //   beginTime: '',
  //   endTime: '',
  //   courseDescription:'',
  //   courseVideo:''
  // })
  const courseName = ref('');
  const author = ref('');
  const credit = ref('');
  const courseHour = ref('');
  const courseType = ref('');
  const beginTime = ref('');
  const endTime = ref('');
  const courseDescription = ref('');
  const courseVideo = ref('');
  let image3 = ref('');

  const router = useRouter();
  const route = useRoute();

  const message = ElMessage;
  const formattedBeginTime = ref('');
  const formattedEndTime = ref('');

   // 定义课程类型
  interface Course {
    courseId: number;
    courseName: string;
    author: string;
    credit: number;
    courseHour: number;
    courseType: string;
    courseCover: string;
    courseDescription: string;
    state: boolean;
    beginTime: string;
    endTime: string;
    courseVideo: string;
  }

  const courses = ref<Course[]>([]);

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

  onMounted(() => {
    searchCourseById();
  });

const searchCourseById = () => {

  axios.get('/api/course/searchById', {
  params: {
    courseId: route.params.courseId,
  }
  })
  .then(response => {
  const { isOk, msg, course } = response.data; // 解构响应数据中的字段

  if (isOk) {
    courseName.value = course.courseName;
    author.value =  course.author;
    credit.value = course.credit;
    courseHour.value = course.courseHour;
    courseType.value = course.courseType;
    courseDescription.value = course.courseDescription;
    beginTime.value = course.beginTime;
    endTime.value = course.endTime;
    image3.value = course.courseCover;
    videoUrl.value = course.courseVideo;

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

  const handleImageUpload = (imageUrl: any) => {
        image3.value = imageUrl.download_url;
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
      if (response.code === 200) {
        videoUrl.value = uploadMapping + response.data; // 假设后端返回的数据中包含视频的访问 URL
        displayUrl.value = response.data;
      } else {
        ElMessage.error(response.msg); // 处理上传失败，显示错误消息
      }
    };

  const goBack = () => {
    // 使用 router 的 push 方法进行路由导航
    router.push('/test')
  };
</script>
  
<style scoped>
  .content{
    display: flex;
    justify-content: center;
    margin-top: 20px;
  }

  .button {
        display: grid;
        place-items: center; /* 水平和垂直居中 */
        width: 35%; /* 设置按钮宽度为父元素宽度的50% */
        height: 40px; /* 设置按钮固定高度 */
        margin-left: 200px;
    }

</style>