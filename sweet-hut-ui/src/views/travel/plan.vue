<template>
  <div class="travel-plan-div">
    <el-card class="box-card">
      <el-button class="back-button" type="primary" @click="backToIndex">返回</el-button>

      <el-table
        :data="travelPlanData"
        :header-cell-style="{ background:'#eee', color:'#606266', fontSize:'18px' }"
        :cell-style="{ fontSize:'16px' }"
        border
        stripe
        style="width: 100%">
        <!-- min-width可以百分比设置 -->
        <el-table-column type="index" align="center" min-width="5%"/>
        <el-table-column prop="region" label="地区" align="center" min-width="20%"/>
        <el-table-column prop="name" label="名称" align="center" min-width="20%" />
        <el-table-column prop="date" label="时间" align="center" min-width="20%" />
        <el-table-column label="是否完成" align="center" min-width="15%">
          <template #default="scope">
            <el-tag v-if="scope.isDone" type="success">是</el-tag>
            <el-tag v-else type="danger">否</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" min-width="20%">
          <template #header>
            <el-button type="primary">新增</el-button>
          </template>
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
const router = useRouter()

interface PlanIF {
  region: string;
  name: string;
  date: string;
  isDone: boolean;
}

const travelPlanData: PlanIF[] = [
  {
    region: '江苏南京溧水',
    name: '天生桥',
    date: '2022-09-09',
    isDone: false
  },
  {
    region: '江苏南京溧水',
    name: '无想水镇',
    date: '2022-09-09',
    isDone: false
  }
]

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
.travel-plan-div {
  .back-button {
    position: absolute;
    top: 1%;
    z-index: 2;
  }
}
</style>
