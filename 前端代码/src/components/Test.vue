<template>
  <div class="searchPart" style="margin-top: 10px;">
    <el-input
      v-model="input"
      style="max-width: 500px"
      placeholder="请输入课程名称"
      class="input-with-select"
    >
      <template #prepend>
        <el-button :icon="Search" @click="searchCourseByName()"/>
      </template>
    </el-input>
  </div>
  <div class="mb-4" style="margin-top: 10px;margin-left: 10px;">
    <el-button type="success" @click="toAddPage()">添加课程</el-button>
    <el-button type="warning" @click="toUpdatePage()">更新课程</el-button>
    <el-button type="danger" @click="deleteCourse()">删除课程</el-button>
    <el-button type="primary" @click="exportToExcel()">导出课程</el-button>
    <el-button type="primary" @click="checkFoodDetail()">查看详情</el-button>
  </div>
  <el-table :data="pagedData" style="width: 90%;margin-left: 10px;margin-top: 10px" @selection-change="onSelectionChange">
    <el-table-column type="selection" width="55" />
    <el-table-column property="courseId" label="课程序号" width="150">
    </el-table-column>
    <el-table-column property="courseName" label="课程名称" width="220" />
    <el-table-column
      property="courseDescription"
      label="课程简介"
      width="450"
      show-overflow-tooltip
    />
    <el-table-column
      property="state"
      label="审核状态"
      width="130"
      show-overflow-tooltip
    >
    <template #default="scope">
        <span>{{ scope.row.state ? '已审核' : '未审核' }}</span>
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
  import { ElMessage, ElMessageBox } from 'element-plus'
  import { useRouter } from 'vue-router'
  import { useRoute } from 'vue-router'
  import * as XLSX from 'xlsx';
  import { watchEffect } from 'vue'
 

  // 定义课程类型
  interface Course {
    courseId: number;
    courseName: string;
    courseDescription: string;
    state: boolean;
  }

  const input = ref('')
  const tableData = ref<Course[]>([]) // 定义课程数据的数组
  const tableData2 = ref<Course[]>([])
  const message = ElMessage
  const router = useRouter(); // 获取 router 对象
  const selectedCourse = ref<Course | null>(null); // 声明用于存储选中课程信息的变量

  const currentPage = ref(1)
  const pageSize = 10

  // 在页面加载时自动调用获取课程列表数据
  onMounted(() => {
    fetchCourseList();
  })

  const fetchCourseList = () => {
    axios.post('/api/course/list')
      .then(response => {
        // 从后端返回的所有课程数据
        const allCourses: any[] = response.data.course; 

        const filteredCourses: Course[] = allCourses.map(course => ({
          courseId: course.courseId,
          courseName: course.courseName,
          courseDescription: course.courseDescription,
          state: course.state
        }));

        // 更新表格数据
        tableData.value = filteredCourses;
        tableData2.value = allCourses;

        updatePagedData();

        // 显示成功消息
        message.success("课程列表加载成功！");
      })
      .catch(error => {
        console.error("课程列表加载错误", error);
      });
  };

  // router.push({
  //     name:'checkUserInfo',
  //     params: {username}
  //   })

  const toAddPage = () => {
    // 使用 router 的 push 方法进行路由导航
    router.push({ name: 'addCourse' }).catch((error: any) => {
      console.error('Failed to navigate:', error);
      ElMessage.error('页面跳转失败'); // 可以添加错误提示
    });
  };

  // const toUpdatePage = () => {
  //   // 使用 router 的 push 方法进行路由导航
  //   router.push({ name: 'updateCourse' }).catch((error: any) => {
  //     console.error('Failed to navigate:', error);
  //     ElMessage.error('页面跳转失败'); // 可以添加错误提示
  //   });
  // };

  const toUpdatePage = () => {
    if (selectedCourse.value) {
      router.push({
        name: 'updateCourse',
        params: {
          courseId: selectedCourse.value.courseId
        }
      }).catch((error: any) => {
        console.error('Failed to navigate:', error);
        ElMessage.error('页面跳转失败'); // 如果导航失败，则显示错误信息
      });
    } else {
      ElMessage.warning('请选择要更新的课程');
    }
  };

  const searchCourseByName = () => {

      // 检查搜索框内容是否为空
  if (input.value.trim() === '') {
    fetchCourseList(); // 加载所有课程数据
    return;
  }
  axios.get('/api/course/fuzzySearch', {
      params: {
        courseName: input.value.trim()
      }
    })
    .then(response => {
      const { isOk, msg, course } = response.data; // 解构响应数据中的字段

      if (isOk) {
        // 如果查询成功
        const extractedCourses = course.map((course: { courseId: any; courseName: any; courseDescription: any; state: any }) => ({
        courseId: course.courseId,
        courseName: course.courseName, 
        courseDescription: course.courseDescription,
        state: course.state  
      }));

        tableData.value = extractedCourses; // 更新表格数据为单个课程对象
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

  //查看课程详情
  const checkFoodDetail = () => {
    if (selectedCourse.value) {
      router.push({
        name: 'courseDetail',
        params: {
          courseId: selectedCourse.value.courseId
        }
      }).catch((error: any) => {
        console.error('Failed to navigate:', error);
        ElMessage.error('页面跳转失败'); // 如果导航失败，则显示错误信息
      });
    } else {
      ElMessage.warning('请选择要查看的课程');
    }
  };

// 导出课程到 Excel
const exportToExcel = () => {
  ElMessageBox.confirm('确定要导出课程到 Excel 吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    // 用户点击了确定按钮
    const wb = XLSX.utils.book_new(); // 创建工作簿
    const wsData = [];
    const header = Object.keys(tableData2.value[0] || {}); // 获取表头
    wsData.push(header);
    tableData2.value.forEach(course => {
      const row = Object.values(course);
      wsData.push(row);
    });
    const ws = XLSX.utils.aoa_to_sheet(wsData); // 转换为工作表

    XLSX.utils.book_append_sheet(wb, ws, 'Courses'); // 将工作表添加到工作簿

    const wbBlob = XLSX.write(wb, { bookType: 'xlsx', type: 'array' }); // 生成 Excel 文件的 Blob

    // 创建下载链接并下载
    const blob = new Blob([wbBlob], { type: 'application/octet-stream' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'courses.xlsx';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }).catch(() => {
    // 用户点击了取消按钮或者点击了遮罩层
    console.log('取消导出课程');
  });
};


  const selectedCourses = ref<Course[]>([]);

  const onSelectionChange = (selection: Course[]) => {
  selectedCourses.value = selection; // 更新选中的课程列表
  if (selection.length > 0) {
    selectedCourse.value = selection[0]; // 选择第一项作为当前选中的课程
  } else {
    selectedCourse.value = null; // 如果没有选中项，则清空选中的课程
  }
};


  const deleteCourse = () => {
  if (selectedCourses.value.length === 0) {
    message.warning("请先选择要删除的课程");
    return;
  }

  ElMessageBox.confirm('确定要删除选中的课程吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    const deletePromises = selectedCourses.value.map(course =>
      axios.post('/api/course/delete', null, {
        params: {
          courseId: course.courseId,
        },
      })
    );

    Promise.all(deletePromises)
      .then(results => {
        const successCount = results.filter(result => result.data.isOk).length;
        const failureCount = results.length - successCount;

        if (successCount > 0) {
          message.success(`成功删除${successCount}门课程`);
        }
        if (failureCount > 0) {
          message.error(`${failureCount}门课程删除失败`);
        }

        fetchCourseList();
      })
      .catch(error => {
        console.error("删除课程失败", error);
        message.error("删除课程失败！");
      });
  }).catch(() => {
    message.info('已取消删除');
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
