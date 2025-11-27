<template>
  <div class="note-page">
    <div class="page-header">
      <h2>学习笔记</h2>
      <el-button type="primary" @click="showDialog = true">
        <el-icon><Plus /></el-icon> 新建笔记
      </el-button>
    </div>

    <el-card>
      <el-input v-model="searchKeyword" placeholder="搜索笔记..." style="width: 300px; margin-bottom: 20px;">
        <template #append>
          <el-button @click="handleSearch"><el-icon><Search /></el-icon></el-button>
        </template>
      </el-input>

      <el-table :data="noteList" style="width: 100%">
        <el-table-column prop="title" label="标题" width="200" />
        <el-table-column prop="category" label="分类" width="120" />
        <el-table-column prop="content" label="内容" show-overflow-tooltip />
        <el-table-column prop="isFavorite" label="收藏" width="80">
          <template #default="{ row }">
            <el-icon v-if="row.isFavorite === 1" color="#f56c6c"><StarFilled /></el-icon>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="showDialog" title="笔记管理" width="700px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="标题">
          <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="form.category">
            <el-option label="课堂笔记" value="课堂笔记" />
            <el-option label="读书笔记" value="读书笔记" />
            <el-option label="心得体会" value="心得体会" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="form.content" type="textarea" :rows="8" />
        </el-form-item>
        <el-form-item label="收藏">
          <el-switch v-model="form.isFavorite" :active-value="1" :inactive-value="0" />
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
import { getNoteList, createNote, updateNote, deleteNote, searchNotes } from '@/api/note'

const noteList = ref([])
const showDialog = ref(false)
const searchKeyword = ref('')
const form = reactive({
  id: null,
  userId: null,
  title: '',
  content: '',
  category: '其他',
  isFavorite: 0
})

onMounted(() => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  form.userId = userInfo.id
  loadNoteList()
})

const loadNoteList = async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const res = await getNoteList({ userId: userInfo.id })
  noteList.value = res.data
}

const handleSearch = async () => {
  if (searchKeyword.value) {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'))
    const res = await searchNotes({ userId: userInfo.id, keyword: searchKeyword.value })
    noteList.value = res.data
  } else {
    loadNoteList()
  }
}

const handleEdit = (row) => {
  Object.assign(form, row)
  showDialog.value = true
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除吗？', '提示', {
    type: 'warning'
  }).then(async () => {
    await deleteNote(id)
    ElMessage.success('删除成功')
    loadNoteList()
  })
}

const handleSubmit = async () => {
  if (form.id) {
    await updateNote(form)
    ElMessage.success('更新成功')
  } else {
    await createNote(form)
    ElMessage.success('创建成功')
  }
  showDialog.value = false
  loadNoteList()
}
</script>

<style scoped>
.note-page {
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

