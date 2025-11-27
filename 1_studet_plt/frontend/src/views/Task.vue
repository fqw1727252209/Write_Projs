<template>
  <div class="task-page">
    <div class="page-header">
      <h2>学习任务管理</h2>
      <el-button type="primary" @click="showDialog = true">
        <el-icon><Plus /></el-icon> 新建任务
      </el-button>
    </div>

    <el-card>
      <el-table :data="taskList" style="width: 100%">
        <el-table-column prop="taskName" label="任务名称" width="200" />
        <el-table-column prop="priority" label="优先级" width="100">
          <template #default="{ row }">
            <el-tag v-if="row.priority === 1" type="danger">高</el-tag>
            <el-tag v-else-if="row.priority === 2" type="warning">中</el-tag>
            <el-tag v-else type="info">低</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="deadline" label="截止时间" width="180" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag v-if="row.status === 0">未开始</el-tag>
            <el-tag v-else-if="row.status === 1" type="warning">进行中</el-tag>
            <el-tag v-else type="success">已完成</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="任务内容" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="showDialog" title="任务管理" width="600px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="任务名称">
          <el-input v-model="form.taskName" />
        </el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="form.priority">
            <el-option label="高" :value="1" />
            <el-option label="中" :value="2" />
            <el-option label="低" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="截止时间">
          <el-date-picker v-model="form.deadline" type="datetime" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="任务内容">
          <el-input v-model="form.content" type="textarea" :rows="4" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status">
            <el-option label="未开始" :value="0" />
            <el-option label="进行中" :value="1" />
            <el-option label="已完成" :value="2" />
          </el-select>
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
import { getTaskList, createTask, updateTask, deleteTask } from '@/api/task'

const taskList = ref([])
const showDialog = ref(false)
const form = reactive({
  id: null,
  userId: null,
  taskName: '',
  priority: 2,
  deadline: '',
  content: '',
  status: 0
})

onMounted(() => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  form.userId = userInfo.id
  loadTaskList()
})

const loadTaskList = async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const res = await getTaskList({ userId: userInfo.id })
  taskList.value = res.data
}

const handleEdit = (row) => {
  Object.assign(form, row)
  showDialog.value = true
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    await deleteTask(id)
    ElMessage.success('删除成功')
    loadTaskList()
  })
}

const handleSubmit = async () => {
  if (form.id) {
    await updateTask(form)
    ElMessage.success('更新成功')
  } else {
    await createTask(form)
    ElMessage.success('创建成功')
  }
  showDialog.value = false
  loadTaskList()
}
</script>

<style scoped>
.task-page {
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

