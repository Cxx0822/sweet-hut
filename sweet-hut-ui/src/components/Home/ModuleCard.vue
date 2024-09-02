<template>
  <div class="module-card">
    <img :src="getAssetsImageFile(`home/${props.image}.png`)" alt="暂无图片">

    <div class="content">
      <span class="title">
        {{ props.title }}
      </span>

      <span class="sub-title">
        {{ props.subTitle }}
      </span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue'
import { getAssetsImageFile } from '@/utils'

const props = defineProps({
  image: {
    type: String,
    required: true
  },
  title: {
    type: String,
    required: true
  },
  subTitle: {
    type: String,
    required: true
  }
})
</script>

<style lang="scss" scoped>
.module-card {
  width: 13vw;
  height: 6vh;

  border: 1px solid rgba(0, 0, 0, .125);
  border-radius: 0.5vw;
  padding: 1vw;

  display: grid;
  grid-template-columns: 1fr 3fr;
  justify-items: center;
  align-items: center;
  gap: 0.5vw;

  box-shadow: 0 0 0.5vw rgba(0, 0, 0, .125);
  cursor: pointer;

  img {
    width: 2.5vw;
  }

  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1vh;

    .title {
      font-size: 1vw;
    }

    .sub-title {
      font-size: 0.8vw;
    }
  }
}
</style>
