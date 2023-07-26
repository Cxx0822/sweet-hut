<template>
  <div class="travel-journal-container">
    <h3>旅行日志</h3>
    <div class="title-container">
      <el-date-picker
          v-model="travelRecordInfo.selectYear"
          type="year"
          placeholder="选择某一年"
      />

      <el-tooltip effect="dark" content="新增游玩景点" placement="bottom">
        <el-button :icon="CirclePlus" circle @click="addTravelRecordClick"></el-button>
      </el-tooltip>

      <el-tooltip effect="dark" content="未来旅游计划" placement="bottom">
        <el-button :icon="Checked" circle @click="router.push('/travel/plan')"></el-button>
      </el-tooltip>

      <el-tooltip effect="dark" content="旅游记录统计" placement="bottom">
        <el-button :icon="Histogram" circle></el-button>
      </el-tooltip>
    </div>

    <div class="travel-item-container" >
      <el-card shadow="always"
               v-for="(journal, index) in travelRecordInfo.journalList"
               :key="index">
        <h3 style="text-align: center;margin: 0">{{ journal.title }}</h3>

        <el-descriptions
            class="margin-top"
            :column="2"
            border
            style="width: 100%"
        >
          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon><Calendar /></el-icon>
                日期
              </div>
            </template>
            {{ journal.date }}
          </el-descriptions-item>

          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon><user /></el-icon>
                人员
              </div>
            </template>
            {{ journal.person }}
          </el-descriptions-item>

          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon><Location /></el-icon>
                地区
              </div>
            </template>
            {{ journal.area }}
          </el-descriptions-item>

          <el-descriptions-item align="center">
            <template #label>
              <div class="cell-item">
                <el-icon><OfficeBuilding /></el-icon>
                景点
              </div>
            </template>
            {{ journal.place }}
          </el-descriptions-item>
        </el-descriptions>

        <el-carousel :interval="4000" type="card" height="200px" style="width: 100%">
          <el-carousel-item v-for="(item, index) in journal.image.split('*')" :key="index">
            <img :src="travelRecordInfo.imageBaseSrc +
                      journal.date + '/' + journal.place + '/' + item"
                  style="width: 100%; height: 100%"
                  alt="空"/>
          </el-carousel-item>
        </el-carousel>

        <el-input
            v-model="journal.content"
            maxlength="1000"
            placeholder="旅游日志"
            show-word-limit
            type="textarea"
            :autosize="{ minRows: 10, maxRows: 10 }"
        />

      </el-card>
    </div>

    <el-pagination layout="prev, pager, next"
                   :default-page-size="travelRecordInfo.pageSize"
                   :total="travelRecordInfo.totalPages"
                   @current-change="currentPageChange" />

    <el-dialog
        v-model="travelRecordInfo.addTravelRecordDialogVisible"
        title="新增旅游景点"
        draggable center>
      <el-form ref="addTravelRecordFormRef"
               :model="travelRecordInfo.addTravelRecord"
               :rules="addTravelRecordRules">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="标题" prop="title">
              <el-input v-model="travelRecordInfo.addTravelRecord.title" clearable/>
            </el-form-item>

            <el-form-item label="日期" prop="date">
              <el-date-picker
                  v-model="travelRecordInfo.addTravelRecord.date"
                  type="date"
                  placeholder="选择日期"
              />
            </el-form-item>

            <el-form-item label="地点" prop="area">
              <el-cascader
                  v-model="travelRecordInfo.addTravelRecord.area"
                  placeholder="选择地点"
                  clearable
                  :options="pcTextArr">
              </el-cascader>
            </el-form-item>

            <el-form-item label="人员" prop="person">
              <el-checkbox-group v-model="travelRecordInfo.addTravelRecord.person">
                <el-checkbox label="哥哥" />
                <el-checkbox label="宝宝" />
              </el-checkbox-group>
            </el-form-item>

            <el-form-item label="景点" prop="place">
              <el-input v-model="travelRecordInfo.addTravelRecord.place" clearable/>
            </el-form-item>

            <el-form-item label="日志" prop="content">
              <el-input
                  v-model="travelRecordInfo.addTravelRecord.content"
                  maxlength="1000"
                  placeholder="旅游日志"
                  show-word-limit
                  type="textarea"
                  :autosize="{ minRows: 10, maxRows: 10 }"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="照片" prop="imageList">
              <el-upload
                  :file-list="travelRecordInfo.addTravelRecord.imageList"
                  list-type="picture-card"
                  accept=".png,.jpg"
                  :auto-upload="false"
                  :on-change="handleChange"
                  :on-remove="handleRemove"
                  :class="{'upload-image': travelRecordInfo.isShowAddImageButton }">
                <el-icon><Plus /></el-icon>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary"
                   @click="submitTravelRecordClick(addTravelRecordFormRef)">新增</el-button>
        <el-button @click="travelRecordInfo.addTravelRecordDialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import { ElButton, ElDialog, ElMessage, FormInstance, FormRules, UploadFile, UploadFiles } from 'element-plus'
import { useRouter } from 'vue-router'
import { Calendar, Checked, CirclePlus, Histogram, Location, OfficeBuilding, Plus, User } from '@element-plus/icons-vue'
import { pcTextArr } from 'element-china-area-data'
import { addTravelRecord, addTravelRecordImage, getAllTravelRecordByPage } from '@/api/travelRecord'
import { formattedDate } from '@/utils/dateUtil'

const router = useRouter()

interface addTravelRecordIF {
  title: string;
  date: string;
  area: string[];
  person: string[];
  place: string;
  imageList: UploadFiles;
  content: string;
}

const addTravelRecordFormRef = ref<FormInstance>()

const emptyAddTravelRecord: addTravelRecordIF = {
  area: [], content: '', date: '', imageList: [], person: [], place: '', title: ''
}

const travelRecordInfo = reactive({
  selectYear: '',
  journalList: [],
  imageBaseSrc: 'https://d428233v29.imdo.co/downloads/travelJournal/',
  // imageBaseSrc: 'http://192.168.5.80:8080/downloads/travelJournal/',
  addTravelRecordDialogVisible: false,
  addTravelRecord: emptyAddTravelRecord,
  isShowAddImageButton: false,
  pageSize: 10,
  totalPages: 1
})

const addTravelRecordRules = reactive<FormRules<addTravelRecordIF>>({
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' }
  ],
  date: [
    { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
  ],
  area: [
    { type: 'array', required: true, message: '请选择地点', trigger: 'change' }
  ],
  person: [
    { type: 'array', required: true, message: '请选择人员', trigger: 'change' }
  ],
  place: [
    { required: true, message: '请输入景点', trigger: 'blur' }
  ],
  imageList: [
    { type: 'array', required: true, message: '请上传照片', trigger: 'change' }
  ],
  content: [
  ]
})

onMounted(() => {
  currentPageChange(1)
})

/**
 * 获取旅游记录
 */
const currentPageChange = async (value: number) => {
  const { data } = await getAllTravelRecordByPage(value, travelRecordInfo.pageSize)
  travelRecordInfo.journalList = data.travelRecordList.records
  travelRecordInfo.totalPages = data.travelRecordList.total
}

/**
 * 点击新增旅游记录
 */
const addTravelRecordClick = () => {
  travelRecordInfo.addTravelRecord = emptyAddTravelRecord
  addTravelRecordFormRef.value?.resetFields()
  travelRecordInfo.addTravelRecordDialogVisible = true
}

// 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用,function(file, fileList)
const handleChange = (file: UploadFile, fileList: UploadFiles) => {
  if (file.raw?.size / 1024 / 1024 > 10) {
    ElMessage.error('图片大小不能超过10MB')
    return
  }

  // 最多上传6张照片
  travelRecordInfo.addTravelRecord.imageList = fileList
  travelRecordInfo.isShowAddImageButton = fileList.length === 6
}

// 删除文件之前的钩子，参数为上传的文件和文件列表，若返回 false 或者返回 Promise 且被 reject，则停止删除。function(file, fileList)
const handleRemove = (file: UploadFile, fileList: UploadFiles) => {
  travelRecordInfo.addTravelRecord.imageList = fileList
  travelRecordInfo.isShowAddImageButton = fileList.length === 6
}

/**
 * 提交新增旅游景点表单
 * @param formEl 表单
 */
const submitTravelRecordClick = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      // 图片列表
      const imageList = new FormData()
      let imageName = ''
      travelRecordInfo.addTravelRecord.imageList.forEach((file) => {
        imageList.append('imageList', file.raw as Blob)
        imageName += `${file.name}*`
      })

      // 旅游记录
      const travelRecord = {
        title: travelRecordInfo.addTravelRecord.title,
        date: formattedDate(travelRecordInfo.addTravelRecord.date as Date),
        area: travelRecordInfo.addTravelRecord.area.join('-'),
        person: travelRecordInfo.addTravelRecord.person.join(','),
        place: travelRecordInfo.addTravelRecord.place,
        content: travelRecordInfo.addTravelRecord.content,
        image: imageName.slice(0, -1)
      }

      travelRecordInfo.addTravelRecord.imageList = []

      const addTravelRecordResult = await addTravelRecord(travelRecord)
      const addTravelRecordImageResult = await addTravelRecordImage(imageList,
        travelRecord.date, travelRecord.place)

      if (addTravelRecordResult.success && addTravelRecordImageResult.success) {
        travelRecordInfo.addTravelRecordDialogVisible = false
        await currentPageChange(1)
        ElMessage.success('新增旅游景点成功')
      } else {
        ElMessage.error('新增旅游景点失败')
      }
    } else {
      ElMessage.error('请先正确填写数据')
    }
  })
}

const viewPlan = () => {
  router.push('/travel/plan')
}
</script>

<style lang="scss">
.travel-journal-container {
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

  .travel-item-container {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    gap: 20px;
    overflow: auto;

    .el-card {
      overflow: auto;

      .el-card__body {
        width: 600px;
        display: flex;
        flex-direction: column;
        gap: 10px;
      }
    }

    .el-carousel__item h3 {
      color: #475669;
      opacity: 0.75;
      line-height: 200px;
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

  .upload-image {
    .el-upload--picture-card {
      display: none;
    }
  }
}
</style>
