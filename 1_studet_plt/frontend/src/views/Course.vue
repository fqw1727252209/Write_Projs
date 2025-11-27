<template>
  <div class="course-page">
    <div class="page-header">
      <h2>课程表管理</h2>
      <el-button type="primary" @click="showDialog = true">
        <el-icon><Plus /></el-icon> 添加课程
      </el-button>
    </div>

    <el-card>
      <el-table :data="courseList" style="width: 100%">
        <el-table-column prop="courseName" label="课程名称" width="150" />
        <el-table-column prop="teacher" label="教师" width="100" />
        <el-table-column prop="weekDay" label="星期" width="80">
          <template #default="{ row }">
            星期{{ ['日', '一', '二', '三', '四', '五', '六'][row.weekDay] }}
          </template>
        </el-table-column>
        <el-table-column label="节次" width="100">
          <template #default="{ row }">
            {{ row.startSection }}-{{ row.endSection }}节
          </template>
        </el-table-column>
        <el-table-column prop="location" label="地点" width="120" />
        <el-table-column prop="credit" label="学分" width="80" />
        <el-table-column prop="semester" label="学期" width="120" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="showDialog" title="课程管理" width="600px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="课程名称">
          <el-input v-model="form.courseName" />
        </el-form-item>
        <el-form-item label="教师">
          <el-input v-model="form.teacher" />
        </el-form-item>
        <el-form-item label="星期">
          <el-select v-model="form.weekDay">
            <el-option label="星期一" :value="1" />
            <el-option label="星期二" :value="2" />
            <el-option label="星期三" :value="3" />
            <el-option label="星期四" :value="4" />
            <el-option label="星期五" :value="5" />
            <el-option label="星期六" :value="6" />
            <el-option label="星期日" :value="7" />
          </el-select>
        </el-form-item>
        <el-form-item label="开始节次">
          <el-input-number v-model="form.startSection" :min="1" :max="12" />
        </el-form-item>
        <el-form-item label="结束节次">
          <el-input-number v-model="form.endSection" :min="1" :max="12" />
        </el-form-item>
        <el-form-item label="地点">
          <el-input v-model="form.location" />
        </el-form-item>
        <el-form-item label="学分">
          <el-input-number v-model="form.credit" :min="0" :max="10" :step="0.5" />
        </el-form-item>
        <el-form-item label="学期">
          <el-input v-model="form.semester" placeholder="如：2024-2025-1" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getCourseList, createCourse, updateCourse, deleteCourse } from '@/api/course'

const courseList = ref([])
const showDialog = ref(false)
const form = reactive({
  id: null,
  userId: null,
  courseName: '',
  teacher: '',
  weekDay: 1,
  startSection: 1,
  endSection: 2,
  location: '',
  credit: 0,
  semester: ''
})

onMounted(() => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  form.userId = userInfo.id
  loadCourseList()
})

const loadCourseList = async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const res = await getCourseList({ userId: userInfo.id })
  courseList.value = res.data
}

const handleEdit = (row) => {
  Object.assign(form, row)
  showDialog.value = true
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    await deleteCourse(id)
    ElMessage.success('删除成功')
    loadCourseList()
  })
}

const handleSubmit = async () => {
  if (form.id) {
    await updateCourse(form)
    ElMessage.success('更新成功')
  } else {
    await createCourse(form)
    ElMessage.success('创建成功')
  }
  showDialog.value = false
  loadCourseList()
}
</script>

<style scoped>
.course-page {
  padding: 20px;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.page-header h2 {
  margin: 0;
  color: #333;
}
</style>

