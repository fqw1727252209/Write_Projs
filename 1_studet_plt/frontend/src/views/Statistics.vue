<template>
  <div class="statistics-page">
    <h2>学习统计</h2>
    
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>计划完成情况</span>
          </template>
          <div ref="planChartRef" style="height: 300px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>任务完成情况</span>
          </template>
          <div ref="taskChartRef" style="height: 300px;"></div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="24">
        <el-card>
          <template #header>
            <span>数据统计</span>
          </template>
          <el-descriptions :column="3" border>
            <el-descriptions-item label="学习计划总数">{{ statistics.totalPlans || 0 }}</el-descriptions-item>
            <el-descriptions-item label="已完成计划">{{ statistics.completedPlans || 0 }}</el-descriptions-item>
            <el-descriptions-item label="计划完成率">{{ statistics.planCompletionRate || 0 }}%</el-descriptions-item>
            <el-descriptions-item label="学习任务总数">{{ statistics.totalTasks || 0 }}</el-descriptions-item>
            <el-descriptions-item label="已完成任务">{{ statistics.completedTasks || 0 }}</el-descriptions-item>
            <el-descriptions-item label="任务完成率">{{ statistics.taskCompletionRate || 0 }}%</el-descriptions-item>
            <el-descriptions-item label="课程总数">{{ statistics.totalCourses || 0 }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { getUserStatistics } from '@/api/statistics'

const statistics = ref({})
const planChartRef = ref(null)
const taskChartRef = ref(null)

onMounted(async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const res = await getUserStatistics(userInfo.id)
  statistics.value = res.data
  
  await nextTick()
  initCharts()
})

const initCharts = () => {
  // 计划完成情况饼图
  const planChart = echarts.init(planChartRef.value)
  planChart.setOption({
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '计划',
        type: 'pie',
        radius: '50%',
        data: [
          { value: statistics.value.completedPlans || 0, name: '已完成' },
          { value: (statistics.value.totalPlans || 0) - (statistics.value.completedPlans || 0), name: '未完成' }
        ]
      }
    ]
  })

  // 任务完成情况饼图
  const taskChart = echarts.init(taskChartRef.value)
  taskChart.setOption({
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '任务',
        type: 'pie',
        radius: '50%',
        data: [
          { value: statistics.value.completedTasks || 0, name: '已完成' },
          { value: (statistics.value.totalTasks || 0) - (statistics.value.completedTasks || 0), name: '未完成' }
        ]
      }
    ]
  })
}
</script>

<style scoped>
.statistics-page {
  padding: 20px;
}

.statistics-page h2 {
  margin-bottom: 20px;
  color: #333;
}
</style>

