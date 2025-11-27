<template>
  <div class="plan-page">
    <div class="page-header">
      <h2>学习计划管理</h2>
      <el-button type="primary" @click="showDialog = true">
        <el-icon><Plus /></el-icon> 新建计划
      </el-button>
    </div>

    <el-card>
      <el-tabs v-model="activeTab" @tab-change="handleTabChange">
        <el-tab-pane label="全部" name="all"></el-tab-pane>
        <el-tab-pane label="进行中" name="0"></el-tab-pane>
        <el-tab-pane label="已完成" name="1"></el-tab-pane>
        <el-tab-pane label="已过期" name="2"></el-tab-pane>
      </el-tabs>

      <el-table :data="planList" style="width: 100%">
        <el-table-column prop="planName" label="计划名称" width="200" />
        <el-table-column prop="planGoal" label="计划目标" />
        <el-table-column prop="startDate" label="开始日期" width="120" />
        <el-table-column prop="endDate" label="结束日期" width="120" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag v-if="row.status === 0" type="primary">进行中</el-tag>
            <el-tag v-else-if="row.status === 1" type="success">已完成</el-tag>
            <el-tag v-else type="info">已过期</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新建/编辑对话框 -->
    <el-dialog v-model="showDialog" :title="dialogTitle" width="600px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="计划名称">
          <el-input v-model="form.planName" placeholder="请输入计划名称" />
        </el-form-item>
        <el-form-item label="计划目标">
          <el-input v-model="form.planGoal" placeholder="请输入计划目标" />
        </el-form-item>
        <el-form-item label="开始日期">
          <el-date-picker v-model="form.startDate" type="date" placeholder="选择开始日期" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="结束日期">
          <el-date-picker v-model="form.endDate" type="date" placeholder="选择结束日期" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="计划描述">
          <el-input v-model="form.description" type="textarea" :rows="4" placeholder="请输入计划描述" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="进行中" :value="0" />
            <el-option label="已完成" :value="1" />
            <el-option label="已过期" :value="2" />
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
import { getPlanList, createPlan, updatePlan, deletePlan } from '@/api/plan'

const activeTab = ref('all')
const planList = ref([])
const showDialog = ref(false)
const dialogTitle = ref('新建计划')
const form = reactive({
  id: null,
  userId: null,
  planName: '',
  planGoal: '',
  startDate: '',
  endDate: '',
  description: '',
  status: 0
})

onMounted(() => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  form.userId = userInfo.id
  loadPlanList()
})

const loadPlanList = async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const params = { userId: userInfo.id }
  if (activeTab.value !== 'all') {
    params.status = parseInt(activeTab.value)
  }
  const res = await getPlanList(params)
  planList.value = res.data
}

const handleTabChange = () => {
  loadPlanList()
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑计划'
  Object.assign(form, row)
  showDialog.value = true
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除这个计划吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deletePlan(id)
    ElMessage.success('删除成功')
    loadPlanList()
  })
}

const handleSubmit = async () => {
  if (form.id) {
    await updatePlan(form)
    ElMessage.success('更新成功')
  } else {
    await createPlan(form)
    ElMessage.success('创建成功')
  }
  showDialog.value = false
  resetForm()
  loadPlanList()
}

const resetForm = () => {
  form.id = null
  form.planName = ''
  form.planGoal = ''
  form.startDate = ''
  form.endDate = ''
  form.description = ''
  form.status = 0
}
</script>

<style scoped>
.plan-page {
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

