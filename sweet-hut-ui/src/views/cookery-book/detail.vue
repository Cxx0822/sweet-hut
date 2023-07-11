<template>
  <div class="cookery-book-detail">
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="12">
          <div class="title-div">
            <el-tag class="ml-2" type="info" size="large" round>基础信息</el-tag>
          </div>
          <el-form :model="cookeryBookInfo" label-width="70px">
            <el-form-item label="菜谱名称">
              <el-input v-model="cookeryBookInfo.name" />
            </el-form-item>
            <el-form-item label="菜系名称">
              <el-select v-model="cookeryBookInfo.type" placeholder="选择菜系名称">
                <el-option label="川菜" value="shanghai" />
                <el-option label="粤菜" value="beijing" />
              </el-select>
            </el-form-item>
            <el-form-item label="完成时间">
              <el-date-picker
                  v-model="cookeryBookInfo.first_date"
                  type="date"
                  placeholder="Pick a date"
                  style="width: 100%"
              />
            </el-form-item>
            <el-form-item label="完成人">
              <el-radio-group v-model="cookeryBookInfo.creator">
                <el-radio label="哥哥" />
                <el-radio label="宝宝" />
              </el-radio-group>
            </el-form-item>
            <el-form-item label="材料">
              <div style="margin-bottom: 10px">
                <el-input v-model="materialInfo.name" placeholder="名称" style="width: 100px;"/>
                <el-input-number v-model="materialInfo.number" :min="0" :precision="2" style="width: 150px;"/>
                <el-select v-model="materialInfo.company" filterable allow-create :reserve-keyword="false" placeholder="单位" style="width: 80px;">
                  <el-option v-for="item in materialInfo.companyOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
                <el-button type="primary" :icon="Plus" circle @click="plusMaterial"/>
                <el-button type="primary" :icon="Minus" circle :disabled="materialInfo.materialList.length === 0" @click="minusMaterial"/>
              </div>
              <el-input v-model="materialInfo.materials" disabled :rows="8" type="textarea" />
            </el-form-item>
            <el-form-item label="注意事项">
              <el-input v-model="cookeryBookInfo.note" :rows="5" type="textarea" />
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12">
          <div class="title-div">
            <el-tag class="ml-2" type="info" size="large" round>具体步骤</el-tag>
          </div>
          <div class="new-step-div">
            <el-input v-model="stepInfo.newStep" autosize type="textarea" placeholder="请添加步骤" style="width: 70%; margin-right: 5px;" />
            <el-button type="primary" :icon="Plus" circle @click="plusStep"/>
            <el-button type="primary" :icon="Minus" circle :disabled="stepInfo.stepList.length === 0" @click="minusStep"/>
          </div>

          <el-steps direction="vertical" align-center style="height: 490px;">
            <!-- 使用模板字符串拼接 -->
            <el-step
              v-show="stepInfo.stepList.length > 0"
              v-for="(value, index) in stepInfo.stepList"
              :key="index"
              :title="`步骤${index + 1}`"
              :description="value"
              />
          </el-steps>
        </el-col>
      </el-row>

      <div style="text-align: center;">
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button>取消</el-button>
      </div>

      <button class="back-button" @click="backToIndex">
        <svg t="1663131339342" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4852" width="32" height="32"><path d="M675.328 117.717333A425.429333 425.429333 0 0 0 512 85.333333C276.352 85.333333 85.333333 276.352 85.333333 512s191.018667 426.666667 426.666667 426.666667 426.666667-191.018667 426.666667-426.666667c0-56.746667-11.093333-112-32.384-163.328a21.333333 21.333333 0 0 0-39.402667 16.341333A382.762667 382.762667 0 0 1 896 512c0 212.074667-171.925333 384-384 384S128 724.074667 128 512 299.925333 128 512 128c51.114667 0 100.8 9.984 146.986667 29.12a21.333333 21.333333 0 0 0 16.341333-39.402667z m-324.693333 373.013334l174.464-174.485334a21.141333 21.141333 0 0 0-0.192-29.973333 21.141333 21.141333 0 0 0-29.973334-0.192l-208.256 208.256a20.821333 20.821333 0 0 0-6.122666 14.976c0.042667 5.418667 2.133333 10.837333 6.314666 14.997333l211.178667 211.2a21.141333 21.141333 0 0 0 29.973333 0.213334 21.141333 21.141333 0 0 0-0.213333-29.973334l-172.629333-172.629333 374.997333 2.602667a20.693333 20.693333 0 0 0 21.034667-21.034667 21.482667 21.482667 0 0 0-21.333334-21.333333l-379.242666-2.624z" fill="#3D3D3D" p-id="4853"></path></svg>
        <span>返回</span>
      </button>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { Plus, Minus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const router = useRouter()

const cookeryBookInfo = reactive({
  name: '红烧鲫鱼',
  type: '川菜',
  first_date: '2022-09-09',
  creator: '哥哥',
  materials: '',
  image: 'src/assets/cookery/fish.png',
  note: ''
})

// #region 材料
const materialInfo = reactive({
  newMaterial: '',
  name: '',
  number: 0,
  company: '',
  materials: '',
  materialList: [] as Array<String>,
  companyOptions: [
    {
      value: '克',
      label: '克'
    },
    {
      value: '个',
      label: '个'
    },
    {
      value: '勺',
      label: '勺'
    }]
})

const plusMaterial = () => {
  if (materialInfo.name === '' || materialInfo.number === 0 || materialInfo.company === '') {
    ElMessage.error('请输入正确的材料！')
  } else {
    const index = materialInfo.materialList.length + 1
    materialInfo.newMaterial = index + '. ' + materialInfo.name + ': ' + materialInfo.number + materialInfo.company
    materialInfo.materialList.push(materialInfo.newMaterial)

    materialInfo.name = ''
    materialInfo.number = 0
    materialInfo.company = ''

    updateMaterial()
  }
}

const minusMaterial = () => {
  materialInfo.materialList.pop()
  updateMaterial()
}

const updateMaterial = () => {
  materialInfo.materials = ''
  for (let i = 0; i < materialInfo.materialList.length; i++) {
    materialInfo.materials += materialInfo.materialList[i] + '\n'
  }
}

// #endregion

const stepInfo = reactive({
  newStep: '',
  stepList: [] as Array<String>,
  steps: ''
})

const plusStep = () => {
  if (stepInfo.newStep === '') {
    ElMessage.error('请输入正确的步骤！')
  } else {
    stepInfo.stepList.push(stepInfo.newStep)
    stepInfo.newStep = ''
  }
}

const minusStep = () => {
  stepInfo.stepList.pop()
}

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
    .el-row {
      margin-bottom: 20px;
    }
    .el-row:last-child {
      margin-bottom: 0;
    }
    .el-col {
      border-radius: 4px;
    }

    .grid-content {
      border-radius: 4px;
      min-height: 36px;
    }

    .el-tag {
      font-size: 24px;
      color: black;
    }

    .el-button, .el-input, .el-input-number, .el-select {
      margin-right: 5px;
    }

    .el-steps--vertical {
      padding: 10px;
      overflow: auto;
      border: 1px solid rgba(0, 0, 255, .2);
    }
  }
}
</style>

<style lang="scss" scoped>
.cookery-book-detail {
  .title-div {
    text-align: center;
    margin-bottom: 10px;
  }

  .new-step-div {
    margin-bottom: 10px;
  }

  .back-button {
    position: absolute;
    top: 1%;

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
