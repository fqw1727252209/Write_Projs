<template>
  <div class="resource-page">
    <div class="page-header">
      <h2>学习资源</h2>
      <el-button type="primary" @click="showDialog = true">
        <el-icon><Upload /></el-icon> 上传资源
      </el-button>
    </div>

    <el-card>
      <el-table :data="resourceList" style="width: 100%">
        <el-table-column prop="resourceName" label="资源名称" width="200" />
        <el-table-column prop="fileName" label="文件名" width="200" />
        <el-table-column prop="fileType" label="类型" width="100" />
        <el-table-column prop="fileSize" label="大小(KB)" width="120" />
        <el-table-column prop="resourceType" label="分类" width="120">
          <template #default="{ row }">
            {{ ['', '课件', '习题', '参考资料', '其他'][row.resourceType] }}
          </template>
        </el-table-column>
        <el-table-column prop="downloadCount" label="下载次数" width="100" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" type="success" @click="handleDownload(row)">下载</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="showDialog" title="上传资源" width="600px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="资源名称">
          <el-input v-model="form.resourceName" />
        </el-form-item>
        <el-form-item label="资源分类">
          <el-select v-model="form.resourceType">
            <el-option label="课件" :value="1" />
            <el-option label="习题" :value="2" />
            <el-option label="参考资料" :value="3" />
            <el-option label="其他" :value="4" />
          </el-select>
        </el-form-item>
        <el-form-item label="选择文件">
          <el-upload
            ref="uploadRef"
            :auto-upload="false"
            :limit="1"
            :on-change="handleFileChange"
          >
            <el-button type="primary">选择文件</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="handleUpload" :loading="uploading">上传</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getResourceList, uploadResource, deleteResource, downloadResource } from '@/api/resource'

const resourceList = ref([])
const showDialog = ref(false)
const uploading = ref(false)
const uploadFile = ref(null)
const form = reactive({
  resourceName: '',
  resourceType: 4
})

onMounted(() => {
  loadResourceList()
})

const loadResourceList = async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const res = await getResourceList({ userId: userInfo.id })
  resourceList.value = res.data
}

const handleFileChange = (file) => {
  uploadFile.value = file.raw
}

const handleUpload = async () => {
  if (!uploadFile.value) {
    ElMessage.warning('请选择文件')
    return
  }
  
  uploading.value = true
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const formData = new FormData()
  formData.append('file', uploadFile.value)
  formData.append('userId', userInfo.id)
  formData.append('resourceName', form.resourceName)
  formData.append('resourceType', form.resourceType)
  
  try {
    await uploadResource(formData)
    ElMessage.success('上传成功')
    showDialog.value = false
    loadResourceList()
  } catch (error) {
    console.error(error)
  } finally {
    uploading.value = false
  }
}

const handleDownload = async (row) => {
  await downloadResource(row.id)
  ElMessage.success('下载次数已更新')
  loadResourceList()
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    await deleteResource(id)
    ElMessage.success('删除成功')
    loadResourceList()
  })
}
</script>

<style scoped>
.resource-page {
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

