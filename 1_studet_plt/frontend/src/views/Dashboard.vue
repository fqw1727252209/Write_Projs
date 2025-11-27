<template>
  <div class="dashboard">
    <h2 class="page-title">数据概览</h2>
    
    <el-row :gutter="20" class="stat-cards">
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background: #409EFF;">
            <el-icon :size="30"><Calendar /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ statistics.totalPlans || 0 }}</div>
            <div class="stat-label">学习计划</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background: #67C23A;">
            <el-icon :size="30"><List /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ statistics.totalTasks || 0 }}</div>
            <div class="stat-label">学习任务</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background: #E6A23C;">
            <el-icon :size="30"><Reading /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ statistics.totalCourses || 0 }}</div>
            <div class="stat-label">课程数量</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-icon" style="background: #F56C6C;">
            <el-icon :size="30"><TrendCharts /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ statistics.taskCompletionRate || 0 }}%</div>
            <div class="stat-label">任务完成率</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="24">
        <el-card>
          <template #header>
            <span>快捷操作</span>
          </template>
          <el-space wrap :size="15">
            <el-button type="primary" @click="goTo('/plan')">
              <el-icon><Plus /></el-icon> 新建计划
            </el-button>
            <el-button type="success" @click="goTo('/task')">
              <el-icon><Plus /></el-icon> 新建任务
            </el-button>
            <el-button type="warning" @click="goTo('/course')">
              <el-icon><Plus /></el-icon> 添加课程
            </el-button>
            <el-button type="info" @click="goTo('/note')">
              <el-icon><Plus /></el-icon> 写笔记
            </el-button>
          </el-space>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getUserStatistics } from '@/api/statistics'

const router = useRouter()
const statistics = ref({})

onMounted(async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  if (userInfo && userInfo.id) {
    const res = await getUserStatistics(userInfo.id)
    statistics.value = res.data
  }
})

const goTo = (path) => {
  router.push(path)
}
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.page-title {
  margin-bottom: 20px;
  color: #333;
}

.stat-cards {
  margin-bottom: 20px;
}

.stat-card {
  display: flex;
  align-items: center;
  padding: 20px;
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  margin-right: 20px;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 14px;
  color: #666;
}
</style>

