<template>
  <div class="cookery-book-list">
    <el-table
      :data="tableData"
      :header-cell-style="{ background:'#eee', color:'#606266', fontSize:'18px' }"
      :cell-style="{ fontSize:'16px' }"
      border
      stripe
      style="width: 100%">
      <el-table-column type="index" align="center" width="50" />
      <el-table-column prop="name" label="菜谱名称" align="center" width="180"/>
      <el-table-column prop="type" label="菜系名称" align="center" width="180" />
      <el-table-column prop="first_done" label="首次完成时间" align="center" width="180" />
      <el-table-column prop="creator" label="完成人" align="center" width="180" />
      <el-table-column label="成品展示" align="center" width="180">
        <template #default="scope">
          <img :src="scope.row.image" width="90" height="90">
        </template>
      </el-table-column>
      <el-table-column align="center">
        <template #header>
          <el-input style="width: 150px; margin-right: 10px" placeholder="搜索菜谱" />
          <button class="add-cookery-book-button">新增菜谱</button>
        </template>
        <template #default="scope">
          <button class="view-details-button">
            查看详细信息
          </button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
const tableData = [
  {
    name: '红烧鲫鱼',
    type: '川菜',
    first_done: '2022-09-09',
    creator: '哥哥',
    image: 'src/assets/cookery/fish.png'
  },
  {
    name: '紫菜蛋汤',
    type: '川菜',
    first_done: '2022-09-09',
    creator: '宝宝',
    image: 'src/assets/cookery/紫菜蛋汤.png'
  }
]
</script>

<style scope lang="scss">
.cookery-book-list {
  .add-cookery-book-button {
    background-color: #fff;
    z-index: 1;
    position: relative;
    overflow: hidden;
    color: red;
    border: 3px solid red;
    padding: 0px 10px;
    font-family: Arial;
    font-weight: 400;
    font-size: 1.05em;
    border-radius: 15px;
    transition: transform .3s ease, color .5s ease;
  }

  .add-cookery-book-button::before {
    content: '';
    inset: 0 0 0 0;
    margin: auto;
    height: 0px;
    width: 0px;
    position: absolute;
    background-color: red;
    z-index: -1;
    border-radius: 50%;
    transition: all .7s ease;
  }

  .add-cookery-book-button:active {
    transform: scale(.9);
  }

  .add-cookery-book-button:hover {
    color: #fff;
  }

  .add-cookery-book-button:hover:before {
    height: 200px;
    width: 200px;
  }

  .view-details-button {
    font-size: 18px;
    display: inline-block;
    outline: 0;
    border: 0;
    cursor: pointer;
    will-change: box-shadow,transform;
    background: radial-gradient( 100% 100% at 100% 0%, #89E5FF 0%, #5468FF 100% );
    box-shadow: 0px 0.01em 0.01em rgb(45 35 66 / 40%), 0px 0.3em 0.7em -0.01em rgb(45 35 66 / 30%), inset 0px -0.01em 0px rgb(58 65 111 / 50%);
    padding: 0 2em;
    border-radius: 0.3em;
    color: #fff;
    height: 2.6em;
    text-shadow: 0 1px 0 rgb(0 0 0 / 40%);
    transition: box-shadow 0.15s ease, transform 0.15s ease;
  }

  .view-details-button:hover {
    box-shadow: 0px 0.1em 0.2em rgb(45 35 66 / 40%), 0px 0.4em 0.7em -0.1em rgb(45 35 66 / 30%), inset 0px -0.1em 0px #3c4fe0;
    transform: translateY(-0.1em);
  }

  .view-details-button:active {
    box-shadow: inset 0px 0.1em 0.6em #3c4fe0;
    transform: translateY(0em);
  }
}
</style>
