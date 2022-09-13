<template>
  <div class="cookery-book-show">
    <h1 style="font-size: 48px;">成品展示</h1>

    <el-carousel :interval="4000" type="card" height="400px">
      <el-carousel-item v-for="item in tableData" :key="item">
        <img :src="item.image" width="400" height="400">
      </el-carousel-item>
    </el-carousel>

    <button class="show-more" @click="showMore">
      <div class="svg-wrapper-1">
        <div class="svg-wrapper">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
            <path fill="none" d="M0 0h24v24H0z"></path>
            <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
          </svg>
        </div>
      </div>
      <span>查看更多</span>
    </button>
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const tableData = [
  {
    name: '红烧鲫鱼',
    type: '川菜',
    first_done: '2022-09-09',
    creator: '哥哥',
    image: new URL('../../assets/cookery/fish.png', import.meta.url).href
  },
  {
    name: '紫菜蛋汤',
    type: '川菜',
    first_done: '2022-09-09',
    creator: '宝宝',
    image: new URL('../../assets/cookery/zicaidantang.png', import.meta.url).href
  }
]

const showMore = () => {
  router.push('/cookery-book/list')
}
</script>

<style lang="scss">
.cookery-book-show {
  text-align: center;

  .el-carousel {
    margin-left: 100px;
    margin-right: 100px;
  }

  .el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 400px;
    margin: 0;
    text-align: center;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }
}
</style>

<style scope lang="scss">
.cookery-book-show {
  .show-more {
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

  .show-more span {
    display: block;
    margin-left: 0.3em;
    transition: all 0.3s ease-in-out;
  }

  .show-more svg {
    display: block;
    transform-origin: center center;
    transition: transform 0.3s ease-in-out;
  }

  .show-more:hover .svg-wrapper {
    animation: fly-1 0.6s ease-in-out infinite alternate;
  }

  .show-more:hover svg {
    transform: translateX(1.2em) rotate(45deg) scale(1.1);
  }

  .show-more:hover span {
    transform: translateX(5em);
  }

  .show-more:active {
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
