<template>
  <div class="travel-plan-container">
    <el-card class="box-card">
      <h3 style="text-align: center">未来旅游计划</h3>
      <el-button class="back-button" circle color="#3F9DFD" @click="backToIndex">
        <back theme="outline" size="24" fill="#333"/>
      </el-button>

      <el-table
        :data="travelPlanInfo.travelPlan"
        border
        stripe
        style="width: 100%">
        <!-- min-width可以百分比设置 -->
        <el-table-column type="index" align="center" min-width="5%">
          <template #header>
            <el-tooltip effect="dark" content="新增游玩景点" placement="bottom">
              <el-button :icon="Plus" circle color="#3F9DFD"/>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="area" label="地区" align="center" min-width="20%"/>
        <el-table-column prop="place" label="景点" align="center" min-width="20%" />
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
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'
import { Plus } from '@element-plus/icons-vue'
import { Back } from '@icon-park/vue-next'
import { reactive } from 'vue'

const router = useRouter()

interface PlanIF {
  date: string;
  area: string;
  place: string;
  isDone: boolean;
}

const travelPlanInfo = reactive({
  travelPlan: [
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
  ]
})

const backToIndex = () => {
  router.push('/travel/list')
}

const handleEdit = (index: number, row: PlanIF) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: PlanIF) => {
  console.log(index, row)
}
</script>

<style lang="scss" scoped>
.travel-plan-container {
  .back-button {
    position: absolute;
    top: 12%;
    z-index: 2;
  }
}
</style>
