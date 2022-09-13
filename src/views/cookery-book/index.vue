<template>
  <div class="cookery-book-list">
    <el-card class="box-card">
      <el-table
        :data="tableData"
        :header-cell-style="{ background:'#eee', color:'#606266', fontSize:'18px' }"
        :cell-style="{ fontSize:'16px' }"
        border
        stripe
        style="width: 1350px">
        <el-table-column type="index" align="center" width="50" />
        <el-table-column prop="name" label="菜谱名称" align="center" width="180"/>
        <el-table-column prop="type" label="菜系名称" align="center" width="180" />
        <el-table-column prop="first_date" label="首次完成时间" align="center" width="180" />
        <el-table-column prop="creator" label="完成人" align="center" width="180" />
        <el-table-column label="成品展示" align="center" width="180">
          <template #default="scope">
            <img :src="scope.row.image" width="90" height="90">
          </template>
        </el-table-column>
        <el-table-column align="center" width="400">
          <template #header>
            <el-input style="width: 150px; margin-right: 10px" placeholder="搜索菜谱" />
            <button class="add-cookery-book-button">新增菜谱</button>
          </template>
          <template #default="scope">
            <button class="view-details-button" @click="viewDetails(scope.row)">
              <div class="svg-wrapper-1">
                <div class="svg-wrapper">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                    <path fill="none" d="M0 0h24v24H0z"></path>
                    <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
                  </svg>
                </div>
              </div>
              <span>详细信息</span>
            </button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const tableData = [
  {
    name: '红烧鲫鱼',
    type: '川菜',
    first_date: '2022-09-09',
    creator: '哥哥',
    image: 'src/assets/cookery/fish.png'
  },
  {
    name: '紫菜蛋汤',
    type: '川菜',
    first_date: '2022-09-09',
    creator: '宝宝',
    image: 'src/assets/cookery/紫菜蛋汤.png'
  }
]

const viewDetails = (row:any) => {
  router.push('/cookery-book/detail')
}
</script>

<style lang="scss">
.cookery-book-list {
  .el-card {
    display:flex;
    justify-content: center;

    min-height: 700px;

    cursor: pointer;
    background: rgb(255, 255, 255);
    border-radius: 5px;
    border: 1px solid rgba(0, 0, 255, .2);
    transition: all .2s;
    box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, .2);
  }
}
</style>

<style scope lang="scss">
.cookery-book-list {
  .add-cookery-book-button {
    width: 9em;
    height: 3em;
    border-radius: 30em;
    font-size: 15px;
    font-family: inherit;
    border: none;
    position: relative;
    overflow: hidden;
    z-index: 1;
    box-shadow: 6px 6px 12px #c5c5c5,
                -6px -6px 12px #ffffff;
  }

  .add-cookery-book-button::before {
    content: '';
    width: 0;
    height: 3em;
    border-radius: 30em;
    position: absolute;
    top: 0;
    left: 0;
    background-image: linear-gradient(to right, #0fd850 0%, #f9f047 100%);
    transition: .5s ease;
    display: block;
    z-index: -1;
  }

  .add-cookery-book-button:hover::before {
    width: 9em;
  }

  .view-details-button {
    margin: auto;
    font-family: inherit;
    font-size: 20px;
    background: royalblue;
    color: white;
    padding: 0.7em 1em;
    padding-left: 0.9em;
    display: flex;
    align-items: center;
    border: none;
    border-radius: 16px;
    overflow: hidden;
    transition: all 0.2s;
  }

  .view-details-button span {
    display: block;
    margin-left: 0.3em;
    transition: all 0.3s ease-in-out;
  }

  .view-details-button svg {
    display: block;
    transform-origin: center center;
    transition: transform 0.3s ease-in-out;
  }

  .view-details-button:hover .svg-wrapper {
    animation: fly-1 0.6s ease-in-out infinite alternate;
  }

  .view-details-button:hover svg {
    transform: translateX(1.2em) rotate(45deg) scale(1.1);
  }

  .view-details-button:hover span {
    transform: translateX(5em);
  }

  .view-details-button:active {
    transform: scale(0.95);
  }

  @keyframes fly-1 {
    from {
      transform: translateY(0.1em);
    }

    to {
      transform: translateY(-0.1em);
    }
  }
}
</style>
