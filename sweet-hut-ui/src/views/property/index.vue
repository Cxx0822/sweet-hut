<template>
  <div class="property-container">
    <h3>资产收支记录</h3>
    <div class="title-container">
      <el-date-picker
          v-model="propertyRecordInfo.selectYear"
          type="year"
          placeholder="选择某一年"
          @change="selectYearChange"
      />

      <el-tooltip effect="dark" content="新增资产记录" placement="bottom">
        <el-button circle @click="addPropertyRecordClick">
          <el-icon><CirclePlus /></el-icon>
        </el-button>
      </el-tooltip>
    </div>

    <el-table
        :data="propertyRecordInfo.propertyRecordList"
        border
        row-key="id"
        style="width: 100%"
        height="calc(100vh - 60px - 60px - 200px)">
      <el-table-column prop="date" label="日期" align="center" min-width="15%" />
      <el-table-column prop="type" label="类别" align="center" min-width="10%" >
        <template #default="scope">
          <el-tag v-show="scope.row.type !== ''"
                  :type="scope.row.type === '收入' ? 'success' : 'danger'">
            {{ scope.row.type }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="role" label="角色" align="center" min-width="10%">
        <template #default="scope">
          <el-tag v-show="scope.row.role !== ''"
                  :type="scope.row.role === '哥哥' ? 'success' : 'info'">
            {{ scope.row.role }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="amount" label="金额" align="center" min-width="20%"/>
      <el-table-column prop="remark" label="备注" align="center" min-width="20%"/>

      <el-table-column label="操作" align="center" min-width="20%">
        <template #default="scope" >
          <el-button v-show="scope.row.type !== ''"
                     @click="handleEditPropertyRecord(scope.$index, scope.row)">编辑</el-button>
          <el-button v-show="scope.row.type !== ''" type="danger"
                     @click="handleDeletePropertyRecord(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
        v-model="propertyRecordInfo.propertyRecordDialogVisible"
        :title="propertyRecordInfo.propertyRecordDialogTitle"
        draggable center>
      <el-form ref="propertyRecordFormRef"
               :model="propertyRecordInfo.propertyRecord"
               :rules="propertyRecordRules">
          <el-form-item label="日期" prop="date">
            <el-date-picker
                v-model="propertyRecordInfo.propertyRecord.date"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="选择一天"
            />
          </el-form-item>

          <el-form-item label="角色" prop="role">
            <el-radio-group v-model="propertyRecordInfo.propertyRecord.role">
              <el-radio label="哥哥">哥哥</el-radio>
              <el-radio label="宝宝">宝宝</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="类型" prop="type">
            <el-radio-group v-model="propertyRecordInfo.propertyRecord.type">
              <el-radio label="收入">收入</el-radio>
              <el-radio label="支出">支出</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="金额" prop="amount">
            <el-input-number v-model="propertyRecordInfo.propertyRecord.amount"
                             :precision="0" :min="1" :max="1000000" />
          </el-form-item>

          <el-form-item label="备注" prop="remark">
            <el-input
                v-model="propertyRecordInfo.propertyRecord.remark"
                maxlength="30"
                placeholder="备注"
                show-word-limit
                type="text"
            />
          </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary"
                   @click="submitPropertyRecordClick(propertyRecordFormRef)">确定</el-button>
        <el-button @click="propertyRecordInfo.propertyRecordDialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { CirclePlus } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox, FormInstance, FormRules } from 'element-plus'
import {
  addPropertyRecord,
  deletePropertyRecord,
  getAllPropertyRecordByYear,
  updatePropertyRecord
} from '@/api/propertyRecord'

interface propertyRecordIF {
  id?: number;
  date: string;
  type: string;
  role: string;
  amount: number;
  remark: string;
}

const propertyRecordFormRef = ref<FormInstance>()

const propertyRecordRules = reactive<FormRules<propertyRecordIF>>({
  date: [
    { required: true, message: '请选择日期', trigger: 'change' }
  ],
  type: [
    { required: true, message: '请选择类别', trigger: 'change' }
  ],
  role: [
    { required: true, message: '请选择角色', trigger: 'change' }
  ],
  amount: [
    { required: true, message: '请输入金额', trigger: 'blur' }
  ],
  remark: [
    { required: true, message: '请输入备注信息', trigger: 'blur' }
  ]
})

const propertyRecordInfo = reactive({
  selectYear: new Date(),
  propertyRecordList: [],
  propertyRecordDialogTitle: '',
  propertyRecordDialogVisible: false,
  propertyRecord: {},
  isAddPropertyRecord: false
})

onMounted(() => {
  getPropertyRecord(propertyRecordInfo.selectYear.getFullYear().toString())
})

const selectYearChange = () => {
  getPropertyRecord(propertyRecordInfo.selectYear.getFullYear().toString())
}

/**
 * 获取资产记录
 * @param year 年份
 */
const getPropertyRecord = async (year: string) => {
  const { data } = await getAllPropertyRecordByYear(year)
  propertyRecordInfo.propertyRecordList = data.propertyRecordInfoList
}

/**
 * 点击新增资产记录
 */
const addPropertyRecordClick = () => {
  resetPropertyRecord()
  propertyRecordInfo.isAddPropertyRecord = true
  propertyRecordFormRef.value?.resetFields()
  propertyRecordInfo.propertyRecordDialogVisible = true
  propertyRecordInfo.propertyRecordDialogTitle = '新增资产记录'
}

/**
 * 重置资产记录
 */
const resetPropertyRecord = () => {
  propertyRecordInfo.propertyRecord.date = ''
  propertyRecordInfo.propertyRecord.amount = 0
  propertyRecordInfo.propertyRecord.role = ''
  propertyRecordInfo.propertyRecord.type = ''
  propertyRecordInfo.propertyRecord.remark = ''
}

/**
 * 编辑资产记录
 */
const handleEditPropertyRecord = (index: number, row: propertyRecordIF) => {
  propertyRecordFormRef.value?.resetFields()
  propertyRecordInfo.isAddPropertyRecord = false

  propertyRecordInfo.propertyRecord.id = row.id - 100
  propertyRecordInfo.propertyRecord.date = row.date
  propertyRecordInfo.propertyRecord.amount = row.amount
  propertyRecordInfo.propertyRecord.role = row.role
  propertyRecordInfo.propertyRecord.type = row.type
  propertyRecordInfo.propertyRecord.remark = row.remark

  propertyRecordInfo.propertyRecordDialogVisible = true
  propertyRecordInfo.propertyRecordDialogTitle = '编辑资产记录'
}

/**
 * 提交新增资产记录表单
 * @param formEl 表单
 */
const submitPropertyRecordClick = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      if (propertyRecordInfo.isAddPropertyRecord) {
        const result = await addPropertyRecord(propertyRecordInfo.propertyRecord)
        if (result.success) {
          await getPropertyRecord(propertyRecordInfo.selectYear.getFullYear().toString())
          propertyRecordInfo.propertyRecordDialogVisible = false
          ElMessage.success('新增资产记录成功')
        } else {
          ElMessage.error('新增资产记录失败')
        }
      } else {
        const result = await updatePropertyRecord(propertyRecordInfo.propertyRecord)
        if (result.success) {
          await getPropertyRecord(propertyRecordInfo.selectYear.getFullYear().toString())
          propertyRecordInfo.propertyRecordDialogVisible = false
          ElMessage.success('更新资产记录成功')
        } else {
          ElMessage.error('更新资产记录失败')
        }
      }
    } else {
      ElMessage.error('请先正确填写数据')
    }
  })
}

/**
 * 删除资产记录
 */
const handleDeletePropertyRecord = (index: number, row: propertyRecordIF) => {
  ElMessageBox.confirm(
    '确定删除该资产记录?',
    '注意',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  )
    .then(async () => {
      const result = await deletePropertyRecord(row.id - 100)
      if (result.success) {
        ElMessage.success('删除成功')
        await getPropertyRecord(propertyRecordInfo.selectYear.getFullYear().toString())
      } else {
        ElMessage.error('删除失败')
      }
    })
    .catch(() => {

    })
}
</script>

<style scoped lang="scss">
.property-container{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;

  h3 {
    padding: 0;
    margin: 0;
  }

  .title-container {
    display: flex;
    flex-direction: row;
    gap: 10px;
  }
}
</style>
