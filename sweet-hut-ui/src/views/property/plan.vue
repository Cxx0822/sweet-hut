<template>
  <div class="property-plan-container">
    <h3>存钱计划</h3>

    <el-button class="back-button" circle color="#3F9DFD" @click="router.push('/property/list')">
      <!--        <back theme="outline" size="24" fill="#333"/>-->
      返回
    </el-button>

    <el-table :data="propertyPlanInfo.propertyPlanList" border style="width: 100%">
      <el-table-column type="index" align="center" width="50">
        <template #header>
          <el-button circle @click="addPropertyPlanClick">
            <el-icon><CirclePlus /></el-icon>
          </el-button>
        </template>
      </el-table-column>
      <el-table-column prop="year" label="年份" align="center" min-width="20%" />
      <el-table-column prop="role" label="角色" align="center" min-width="20%" />
      <el-table-column prop="planNumber" label="计划累计存钱数" align="center" min-width="20%"/>
      <el-table-column prop="realNumber" label="实际累计存钱数" align="center" min-width="20%"/>

      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="propertyPlanInfo.dialogFormVisible"
               :title="propertyPlanInfo.dialogFormTitle" center>
      <el-form :model="propertyPlanInfo.propertyPlan" :rules="rules" ref="propertyPlanFormRef">
        <el-form-item label="年份" prop="year">
          <el-input v-model="propertyPlanInfo.propertyPlan.year" placeholder="请输入年份" />
        </el-form-item>

        <el-form-item label="角色" prop="role">
          <el-radio-group v-model="propertyPlanInfo.propertyPlan.role">
            <el-radio label="哥哥">哥哥</el-radio>
            <el-radio label="宝宝">宝宝</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="计划存钱数" prop="planNumber">
          <el-input v-model="propertyPlanInfo.propertyPlan.planNumber" placeholder="请输入景点名称" />
        </el-form-item>

        <el-form-item label="实际存钱数" prop="date">
          <el-input v-model="propertyPlanInfo.propertyPlan.realNumber" placeholder="请输入景点名称" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click=submitPropertyPlanForm(propertyPlanFormRef)>确定</el-button>
       <el-button @click="propertyPlanInfo.dialogFormVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { CirclePlus } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { FormInstance, FormRules } from 'element-plus'

const router = useRouter()

const propertyPlanFormRef = ref<FormInstance>()

interface propertyPlanTF {
  year: string;
  role: string;
  planNumber: number;
  realNumber: number;
}

const propertyPlanInfo = reactive({
  propertyPlanList: [
    {
      year: '2023',
      role: '哥哥',
      planNumber: 0,
      realNumber: 0
    }
  ],
  dialogFormVisible: false,
  dialogFormTitle: '',
  propertyPlan: {} as propertyPlanTF,
  isAddPropertyPlan: false
})

const rules = reactive<FormRules<propertyPlanTF>>({
  year: [
    { required: true, message: '请输入年份', trigger: 'blur' }
  ],
  role: [
    { required: true, message: '请选择角色', trigger: 'change' }
  ],
  planNumber: [
    { required: true, message: '请输入计划存钱数', trigger: 'blur' }
  ]
})

const addPropertyPlanClick = () => {
  resetPropertyPlan()
  propertyPlanInfo.isAddPropertyPlan = true
  propertyPlanInfo.dialogFormTitle = '新增存钱计划'
  propertyPlanInfo.dialogFormVisible = true
  propertyPlanFormRef.value?.resetFields()
}

const resetPropertyPlan = () => {
  propertyPlanInfo.propertyPlan.year = ''
  propertyPlanInfo.propertyPlan.role = ''
  propertyPlanInfo.propertyPlan.planNumber = 0
  propertyPlanInfo.propertyPlan.realNumber = 0
}

const submitPropertyPlanForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}

const handleEdit = (index: number, row: propertyPlanTF) => {
  propertyPlanInfo.propertyPlan.year = row.year
  propertyPlanInfo.propertyPlan.role = row.role
  propertyPlanInfo.propertyPlan.planNumber = row.planNumber
  propertyPlanInfo.propertyPlan.realNumber = row.realNumber

  propertyPlanInfo.isAddPropertyPlan = false
  propertyPlanInfo.dialogFormTitle = '编辑存钱计划'
  propertyPlanInfo.dialogFormVisible = true
  propertyPlanFormRef.value?.resetFields()
}

const handleDelete = (index: number, row: propertyPlanTF) => {
  console.log(index, row)
}
</script>

<style scoped lang="scss">
.property-plan-container {
  h3 {
    text-align: center;
    margin: 0;
    padding: 0;
  }

  .back-button {
    position: absolute;
    top: 8%;
    z-index: 2;
  }

  .el-table {
    margin-top: 10px;
  }
}
</style>
