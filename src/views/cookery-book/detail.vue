<template>
  <div class="cookery-book-detail">
    <el-card class="box-card">
      <el-form :model="form" label-width="120px">
        <el-form-item label="菜谱名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="菜系名称">
          <el-select v-model="form.type" placeholder="选择菜系名称">
            <el-option label="川菜" value="shanghai" />
            <el-option label="粤菜" value="beijing" />
          </el-select>
        </el-form-item>
        <el-form-item label="首次完成时间">
          <el-date-picker
              v-model="form.first_date"
              type="date"
              placeholder="Pick a date"
              style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="完成人">
          <el-radio-group v-model="form.creator">
            <el-radio label="哥哥" />
            <el-radio label="宝宝" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="材料">
          <el-input type="textarea" />
        </el-form-item>
        <el-form-item label="制作流程">
          <el-input type="textarea" />
        </el-form-item>
        <el-form-item label="注意事项">
          <el-input type="textarea" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>

      <button class="back-button" @click="backToIndex">
        <svg height="16" width="16" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 1024 1024"><path d="M874.690416 495.52477c0 11.2973-9.168824 20.466124-20.466124 20.466124l-604.773963 0 188.083679 188.083679c7.992021 7.992021 7.992021 20.947078 0 28.939099-4.001127 3.990894-9.240455 5.996574-14.46955 5.996574-5.239328 0-10.478655-1.995447-14.479783-5.996574l-223.00912-223.00912c-3.837398-3.837398-5.996574-9.046027-5.996574-14.46955 0-5.433756 2.159176-10.632151 5.996574-14.46955l223.019353-223.029586c7.992021-7.992021 20.957311-7.992021 28.949332 0 7.992021 8.002254 7.992021 20.957311 0 28.949332l-188.073446 188.073446 604.753497 0C865.521592 475.058646 874.690416 484.217237 874.690416 495.52477z"></path></svg>
        <span>返回</span>
      </button>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const form = reactive({
  name: '红烧鲫鱼',
  type: '川菜',
  first_date: '2022-09-09',
  creator: '哥哥',
  image: 'src/assets/cookery/fish.png'
})

const onSubmit = () => {
  console.log('submit!')
}

const backToIndex = () => {
  router.push('/cookery-book/list')
}
</script>

<style lang="scss">
.cookery-book-detail {
  .el-card {
    text-align: center;
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

<style lang="scss" scoped>
.cookery-book-detail {
  .back-button {
    display: flex;
    height: 3em;
    width: 100px;
    align-items: center;
    justify-content: center;
    background-color: #eeeeee4b;
    border-radius: 3px;
    letter-spacing: 1px;
    transition: all 0.2s linear;
    cursor: pointer;
    border: none;
    background: gray;
  }

  .back-button > svg {
    margin-right: 5px;
    margin-left: 5px;
    font-size: 20px;
    transition: all 0.4s ease-in;
  }

  .back-button:hover > svg {
    font-size: 1.2em;
    transform: translateX(-5px);
  }

  .back-button:hover {
    box-shadow: 9px 9px 33px #d1d1d1, -9px -9px 33px #ffffff;
    transform: translateY(-2px);
  }
}
</style>
