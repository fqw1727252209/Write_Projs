<template>
  <div class="profile-page">
    <h2>个人信息</h2>
    
    <el-card style="max-width: 600px;">
      <el-form :model="userForm" label-width="100px">
        <el-form-item label="学号">
          <el-input v-model="userForm.studentId" disabled />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="userForm.username" />
        </el-form-item>
        <el-form-item label="专业">
          <el-input v-model="userForm.major" />
        </el-form-item>
        <el-form-item label="年级">
          <el-input v-model="userForm.grade" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="userForm.email" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="userForm.phone" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleUpdate">更新信息</el-button>
          <el-button @click="showPasswordDialog = true">修改密码</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 修改密码对话框 -->
    <el-dialog v-model="showPasswordDialog" title="修改密码" width="400px">
      <el-form :model="passwordForm" label-width="100px">
        <el-form-item label="原密码">
          <el-input v-model="passwordForm.oldPassword" type="password" show-password />
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="passwordForm.newPassword" type="password" show-password />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="passwordForm.confirmPassword" type="password" show-password />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showPasswordDialog = false">取消</el-button>
        <el-button type="primary" @click="handleUpdatePassword">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getUserInfo, updateUser, updatePassword } from '@/api/user'

const userForm = reactive({
  id: null,
  studentId: '',
  username: '',
  major: '',
  grade: '',
  email: '',
  phone: ''
})

const showPasswordDialog = ref(false)
const passwordForm = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

onMounted(async () => {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'))
  const res = await getUserInfo(userInfo.id)
  Object.assign(userForm, res.data)
})

const handleUpdate = async () => {
  await updateUser(userForm)
  ElMessage.success('更新成功')
  // 更新本地存储
  localStorage.setItem('userInfo', JSON.stringify(userForm))
}

const handleUpdatePassword = async () => {
  if (passwordForm.newPassword !== passwordForm.confirmPassword) {
    ElMessage.error('两次密码输入不一致')
    return
  }
  
  await updatePassword({
    userId: userForm.id,
    oldPassword: passwordForm.oldPassword,
    newPassword: passwordForm.newPassword
  })
  
  ElMessage.success('密码修改成功')
  showPasswordDialog.value = false
  passwordForm.oldPassword = ''
  passwordForm.newPassword = ''
  passwordForm.confirmPassword = ''
}
</script>

<style scoped>
.profile-page {
  padding: 20px;
}

.profile-page h2 {
  margin-bottom: 20px;
  color: #333;
}
</style>

