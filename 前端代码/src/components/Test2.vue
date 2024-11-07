<template>
    <div class="searchState" style="margin-top: 10px;">
      <el-select
        v-model="chooseState"
        placeholder="筛查审核状态"
        size="large"
        style="width: 240px"
      >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>
    <el-button :icon="Search" @click="searchCourseByState()" style="width: 50px" size="large"/>
  </div>
  <el-table :data="pagedData" style="width: 90%;margin-left: 50px;margin-top: 10px">
    <el-table-column type="selection" width="55" />
    <el-table-column property="courseId" label="课程序号" width="150" />
    <el-table-column property="courseName" label="课程名称" width="200" />
    <el-table-column
      property="courseDescription"
      label="课程简介"
      width="500"
      show-overflow-tooltip
    />
    <el-table-column
      label="审核状态"
      width="250"
      show-overflow-tooltip
    >
      <template #default="scope">
        <el-switch
          v-model="scope.row.state"
          size="large"
          active-text="已审核"
          inactive-text="未审核"
          @change="handleStateChange(scope.row)"
        />
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
  v-model:currentPage="currentPage"
      :page-size="pageSize"
      layout="prev, pager, next"
      :total="tableData.length"
      style="display: flex;justify-content: center;margin-top: 10px;"
  />
</template>

<script lang="ts" setup>
 import { ElTable } from 'element-plus'
 import { Search } from '@element-plus/icons-vue'
 import { ref } from 'vue'
 import { onMounted } from 'vue'
 import axios from 'axios'
 import { ElMessage } from 'element-plus'
 import { useRouter } from 'vue-router'
import { watchEffect } from 'vue'

 // 定义课程类型
 interface Course {
   courseId: number;
   courseName: string;
   courseDescription: string;
   state: boolean;
 }

 const tableData = ref<Course[]>([]) // 定义课程数据的数组
 const message = ElMessage
 const router = useRouter(); // 获取 router 对象
 const chooseState = ref('')

 const currentPage = ref(1)
 const pageSize = 10

 const options = [
  {
    value: false,
    label: '未审核',
  },
  {
    value: true,
    label: '已审核',
  },
  {
    value: '0909',
    label: '全部'
  }
]

 // 在页面加载时自动调用获取课程列表数据
 onMounted(() => {
   fetchCourseList()
 })

 const fetchCourseList = () => {
   axios.post('/api/course/list')
     .then(response => {
       // 从后端返回的所有课程数据
       const allCourses: any[] = response.data.course; // 假设后端返回的数据类型是 any[]，需要根据实际情况调整

       const filteredCourses: Course[] = allCourses.map(course => ({
         courseId: course.courseId,
         courseName: course.courseName,
         courseDescription: course.courseDescription,
         state: course.state
       }));

       // 更新表格数据
       tableData.value = filteredCourses;

       updatePagedData();

       // 显示成功消息
       message.success("课程列表加载成功！");
     })
     .catch(error => {
       console.error("课程列表加载错误", error);
     });
 }

 const handleStateChange = (course: Course) => {
   axios.post('/api/course/updateState', {
     courseId: course.courseId,
     state: course.state
   })
   .then(() => {
     message.success("课程状态更新成功！");
   })
   .catch(error => {
     console.error("课程状态更新失败", error);
     message.error("课程状态更新失败！");
   });
 }

  const searchCourseByState = () => {
    // 检查搜索框内容是否为空
    if (chooseState.value === '0909') {
    fetchCourseList(); // 加载所有课程数据
    return;
    }
    axios.get('/api/course/searchByState', {
    params: {
      state: chooseState.value
    }
    })
    .then(response => {
    const { isOk, msg, course } = response.data; // 解构响应数据中的字段

    if (isOk) {
      // 如果查询成功
      const searchedCourses: Course[] = course.map((course: any) => ({
          courseId: course.courseId,
          courseName: course.courseName,
          courseDescription: course.courseDescription,
          state: course.state
        }));

      tableData.value = searchedCourses; // 更新表格数据为单个课程对象
      updatePagedData();
      message.success(msg); // 显示成功消息
    } else {
      // 如果查询失败
      message.info(msg); // 显示信息消息，可能是未找到匹配的课程
      tableData.value = []; // 清空表格数据
    }
    })
    .catch(error => {
    console.error('课程搜索失败', error);
    message.error('课程搜索失败！');
    });
};

const pagedData = ref<Course[]>([])

const updatePagedData = () => {
  const startIndex = (currentPage.value - 1) * pageSize
  const endIndex = startIndex + pageSize
  pagedData.value = tableData.value.slice(startIndex, endIndex)
}

watchEffect(() => {
  updatePagedData();
})

</script>

<style scoped>
 .navigate{
   display: flex;
   justify-content: center;
   align-items: center;
 }
</style>
