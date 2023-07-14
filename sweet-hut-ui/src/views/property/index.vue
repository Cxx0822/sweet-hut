<template>
  <div class="property-container">
    <h3>大额收支记录</h3>
    <div class="title-container">
      <el-date-picker
          v-model="propertyRecordInfo.selectYear"
          type="year"
          placeholder="选择某一年"
          @change="selectYearChange"
      />

      <el-tooltip effect="dark" content="新增资产记录" placement="bottom">
        <el-button :icon="CirclePlus" circle @click="addPropertyRecordClick"></el-button>
      </el-tooltip>
    </div>

    <el-table
        :data="propertyRecordInfo.propertyRecordList"
        border
        show-summary
        :summary-method="getSummaries"
        row-key="id"
        style="width: 100%">
      <el-table-column prop="date" label="日期" align="center" min-width="20%" />
      <el-table-column prop="type" label="类别" align="center" min-width="20%" >
        <template #default="scope">
          <el-tag v-show="scope.row.type !== ''" :type="scope.row.type === '收入' ? 'success' : 'danger'">{{ scope.row.type }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="role" label="角色" align="center" min-width="20%">
        <template #default="scope">
          <el-tag v-show="scope.row.role !== ''" :type="scope.row.role === '哥哥' ? 'success' : 'info'">{{ scope.row.role }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="amount" label="金额" align="center" min-width="20%"/>
      <el-table-column prop="remark" label="备注" align="center" />
    </el-table>

    <el-dialog
        v-model="propertyRecordInfo.addPropertyRecordDialogVisible"
        title="新增资产记录"
        draggable center>
      <el-form ref="addPropertyRecordFormRef" :model="propertyRecordInfo.addTravelRecord" :rules="addPropertyRecordRules">
          <el-form-item label="日期" prop="date">
            <el-date-picker
                v-model="propertyRecordInfo.addTravelRecord.date"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="选择一天"
            />
          </el-form-item>

          <el-form-item label="角色" prop="role">
            <el-radio-group v-model="propertyRecordInfo.addTravelRecord.role">
              <el-radio label="哥哥">哥哥</el-radio>
              <el-radio label="宝宝">宝宝</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="类型" prop="type">
            <el-radio-group v-model="propertyRecordInfo.addTravelRecord.type">
              <el-radio label="收入">收入</el-radio>
              <el-radio label="支出">支出</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="金额" prop="amount">
            <el-input-number v-model="propertyRecordInfo.addTravelRecord.amount" :precision="0" :min="1" :max="1000000" />
          </el-form-item>

          <el-form-item label="备注" prop="remark">
            <el-input
                v-model="propertyRecordInfo.addTravelRecord.remark"
                maxlength="30"
                placeholder="备注"
                show-word-limit
                type="text"
            />
          </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitPropertyRecordClick(addPropertyRecordFormRef)">新增</el-button>
        <el-button @click="propertyRecordInfo.addPropertyRecordDialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import type { TableColumnCtx } from 'element-plus'
import { CirclePlus } from '@element-plus/icons-vue'
import { ElMessage, FormInstance, FormRules } from 'element-plus'
import { addPropertyRecord, getAllPropertyRecordByYear } from '@/api/propertyRecord'

interface propertyRecordIF {
  date: string;
  type: string;
  role: string;
  amount: number;
  remark: string;
}

const emptyAddTravelRecord: propertyRecordIF = {
  amount: 0, date: '', remark: '', role: '', type: ''
}

const addPropertyRecordFormRef = ref<FormInstance>()

const addPropertyRecordRules = reactive<FormRules<propertyRecordIF>>({
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
  addPropertyRecordDialogVisible: false,
  addTravelRecord: emptyAddTravelRecord
})

const propertyData = [
  {
    date: '2023-06-09',
    type: '收入',
    role: '哥哥',
    amount: '14329',
    remark: '工资'
  },
  {
    date: '2023-06-30',
    type: '支出',
    role: '哥哥',
    amount: '2500',
    remark: '云逸瑜伽健身年卡'
  },
  {
    date: '2023-05-11',
    type: '支出',
    role: '哥哥',
    amount: '1730',
    remark: '台式机'
  },
  {
    date: '2023-05-10',
    type: '收入',
    role: '哥哥',
    amount: '14329',
    remark: '工资'
  },
  {
    date: '2023-04-10',
    type: '收入',
    role: '哥哥',
    amount: '14329',
    remark: '工资'
  },
  {
    date: '2023-03-13',
    type: '支出',
    role: '哥哥',
    amount: '9100',
    remark: '半年房租'
  },
  {
    date: '2023-03-10',
    type: '收入',
    role: '哥哥',
    amount: '14479',
    remark: '工资'
  },
  {
    date: '2023-02-14',
    type: '支出',
    role: '哥哥',
    amount: '3999',
    remark: '华为平板MatePad pro'
  },
  {
    date: '2023-02-19',
    type: '支出',
    role: '哥哥',
    amount: '2098',
    remark: '汽车保养4次'
  },
  {
    date: '2023-02-10',
    type: '收入',
    role: '哥哥',
    amount: '13724',
    remark: '工资'
  },
  {
    date: '2023-02-10',
    type: '收入',
    role: '哥哥',
    amount: '26423',
    remark: '年终奖'
  },
  {
    date: '2023-01-04',
    type: '支出',
    role: '哥哥',
    amount: '1101',
    remark: '宝宝爸妈礼物'
  },
  {
    date: '2023-01-10',
    type: '收入',
    role: '哥哥',
    amount: '13724',
    remark: '工资'
  }
]

interface SummaryMethodProps<T = propertyRecordIF> {
  columns: Array<TableColumnCtx<T>>;
  data: T[];
}

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
  propertyRecordInfo.addTravelRecord = emptyAddTravelRecord
  addPropertyRecordFormRef.value?.resetFields()
  propertyRecordInfo.addPropertyRecordDialogVisible = true
}

/**
 * 提交新增资产记录表单
 * @param formEl 表单
 */
const submitPropertyRecordClick = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      console.log(propertyRecordInfo.addTravelRecord.date)
      const result = await addPropertyRecord(propertyRecordInfo.addTravelRecord)
      if (result.success) {
        await getPropertyRecord(propertyRecordInfo.selectYear.getFullYear().toString())
        propertyRecordInfo.addPropertyRecordDialogVisible = false
        ElMessage.success('新增资产记录成功')
      } else {
        ElMessage.error('新增资产记录失败')
      }
    } else {
      ElMessage.error('请先正确填写数据')
    }
  })
}

const getSummaries = (param: SummaryMethodProps) => {
  const { columns, data } = param
  const sums: string[] = []
  columns.forEach((column, index) => {
    if (index === 0) {
      sums[index] = '当前结余'
      return
    }

    if (index === 4) {
      sums[index] = '152103'
    }
  })

  return sums
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
