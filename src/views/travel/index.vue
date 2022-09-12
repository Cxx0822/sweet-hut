<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column type="index" align="center" width="50" />
    <el-table-column prop="date" label="景点名称" align="center" width="180"/>
    <el-table-column prop="name" label="Name" align="center" width="180" />
    <el-table-column prop="address" label="Address" align="center" />
  </el-table>
</template>

<script lang="ts" setup>
const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }
]
</script>

