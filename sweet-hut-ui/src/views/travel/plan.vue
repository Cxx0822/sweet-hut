<template>
  <div class="travel-plan-container">
    <h3>未来旅游计划</h3>
    <el-button class="back-button" circle color="#3F9DFD" @click="router.push('/travel/list')">
<!--        <back theme="outline" size="24" fill="#333"/>-->
      返回
    </el-button>

    <el-table
      :data="travelPlanInfo.travelPlanList"
      border
      stripe
      style="width: 100%">
      <!-- min-width可以百分比设置 -->
      <el-table-column type="index" align="center" min-width="5%">
        <template #header>
          <el-tooltip effect="dark" content="新增游玩景点" placement="bottom">
            <el-button circle color="#3F9DFD" @click="addTravelPlan">
              <el-icon><Plus /></el-icon>
            </el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="area" label="地区" align="center" min-width="20%"/>
      <el-table-column prop="place" label="旅游景点" align="center" min-width="20%" />
      <el-table-column prop="date" label="完成日期" align="center" min-width="20%" />
      <el-table-column label="是否完成" align="center" min-width="15%">
        <template #default="scope">
          <el-tag v-if="scope.isDone" type="success">是</el-tag>
          <el-tag v-else type="danger">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" min-width="20%">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="travelPlanInfo.dialogFormVisible"
               :title="travelPlanInfo.dialogFormTitle" center>
      <el-form :model="travelPlanInfo.travelPlan" :rules="rules" ref="travelPlanFormRef">
        <el-form-item label="地区" prop="area">
          <el-cascader
              v-model="travelPlanInfo.travelPlan.area"
              placeholder="选择地点"
              clearable
              :options="pcTextArr">
          </el-cascader>
        </el-form-item>

        <el-form-item label="旅游景点" prop="place">
          <el-input v-model="travelPlanInfo.travelPlan.place" placeholder="请输入景点名称" />
        </el-form-item>

        <el-form-item label="完成日期" prop="date" v-if="!travelPlanInfo.isAddTravelPlan">
          <el-date-picker
              v-model="travelPlanInfo.travelPlan.date"
              type="date"
              value-format="YYYY-MM-DD"
              placeholder="选择一天"
          />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click=submitTravelPlanForm(travelPlanFormRef)>确定</el-button>
       <el-button @click="travelPlanInfo.dialogFormVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'
import { Plus } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import { pcTextArr } from 'element-china-area-data'
import { FormInstance, FormRules } from 'element-plus'

const router = useRouter()

interface travelPlanIF {
  date: string;
  area: string;
  place: string;
  isDone?: boolean;
}

const travelPlanFormRef = ref<FormInstance>()

const travelPlanInfo = reactive({
  travelPlanList: [
    {
      area: '江苏-南京',
      place: '天生桥',
      date: '',
      isDone: false
    },
    {
      area: '江苏-南京',
      place: '无想水镇',
      date: '',
      isDone: false
    }
  ],
  travelPlan: {} as travelPlanIF,
  dialogFormVisible: false,
  dialogFormTitle: '',
  isAddTravelPlan: false
})

const rules = reactive<FormRules<travelPlanIF>>({
  area: [
    { required: true, message: '请选择地点', trigger: 'change' }
  ],
  place: [
    { required: true, message: '请输入景点', trigger: 'blur' }
  ]
})

const addTravelPlan = () => {
  resetTravelPlan()
  travelPlanInfo.isAddTravelPlan = true
  travelPlanInfo.dialogFormTitle = '新增旅游计划'
  travelPlanInfo.dialogFormVisible = true
  travelPlanFormRef.value?.resetFields()
}

const resetTravelPlan = () => {
  travelPlanInfo.travelPlan.area = ''
  travelPlanInfo.travelPlan.date = ''
  travelPlanInfo.travelPlan.place = ''
}

const submitTravelPlanForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}

const handleEdit = (index: number, row: travelPlanIF) => {
  travelPlanInfo.travelPlan.area = row.area
  travelPlanInfo.travelPlan.place = row.place
  travelPlanInfo.travelPlan.date = row.date
  travelPlanInfo.travelPlan.isDone = row.isDone

  travelPlanInfo.isAddTravelPlan = false
  travelPlanInfo.dialogFormTitle = '编辑旅游计划'
  travelPlanInfo.dialogFormVisible = true
  travelPlanFormRef.value?.resetFields()
}

const handleDelete = (index: number, row: travelPlanIF) => {
  console.log(index, row)
}
</script>

<style lang="scss" scoped>
.travel-plan-container {
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
