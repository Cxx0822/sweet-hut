<template>
  <div class="travel-journal-container">
    <div class="title-container">
      <el-date-picker
          v-model="travelJournalInfo.selectYear"
          type="year"
          placeholder="选择某一年"
      />

      <el-tooltip effect="dark" content="新增游玩景点" placement="bottom">
        <el-button :icon="CirclePlus" circle @click="addTravelJournalClick"></el-button>
      </el-tooltip>

      <el-tooltip effect="dark" content="未来旅游计划" placement="bottom">
        <el-button :icon="Checked" circle @click="router.push('/travel/plan')"></el-button>
      </el-tooltip>

      <el-tooltip effect="dark" content="旅游景点统计" placement="bottom">
        <el-button :icon="Histogram" circle></el-button>
      </el-tooltip>

    </div>

    <div class="travel-item-container" >
      <el-card shadow="always" v-for="(journal, index) in travelJournalInfo.journalList" :key="index">
        <h3 style="text-align: center;margin: 0px">{{ journal.title }}</h3>

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
          <el-carousel-item v-for="item in 6" :key="item">
            <h3 text="2xl" justify="center">{{ item }}</h3>
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

    <el-pagination layout="prev, pager, next" :total="1000" />

    <el-dialog v-model="yearCardDialogVisible" title="2022年 年卡使用情况" center style="width: 880px" :show-close="false">
      <div>
        <el-tabs type="border-card" tab-position="top" style="width: 830px" class="demo-tabs">
          <el-tab-pane
              v-for="(touristSpots, key, index) in yearCardTouristSpots"
              :key="index"
              :label=key>
            <el-checkbox
                v-for="(touristSpot, index) in touristSpots"
                :key="index"
                v-model="yearCardTouristSpotsCheck[key][index]" :label="touristSpot" size="large" :check="yearCardTouristSpotsCheck[key][index]"/>
          </el-tab-pane>
        </el-tabs>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="yearCardDialogVisible = false">返回</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog
        v-model="travelJournalInfo.addTravelJournalDialogVisible"
        title="新增旅游景点"
        draggable center>
      <el-form ref="addTravelJournalFormRef" :model="travelJournalInfo.addTravelJournal" :rules="addTravelJournalRules">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="标题" prop="title">
              <el-input v-model="travelJournalInfo.addTravelJournal.title" clearable/>
            </el-form-item>

            <el-form-item label="日期" prop="date">
              <el-date-picker
                  v-model="travelJournalInfo.addTravelJournal.date"
                  type="date"
                  placeholder="选择日期"
              />
            </el-form-item>

            <el-form-item label="地点" prop="area">
              <el-cascader
                  v-model="travelJournalInfo.addTravelJournal.area"
                  placeholder="选择地点"
                  clearable
                  :options="pcTextArr">
              </el-cascader>
            </el-form-item>

            <el-form-item label="人员" prop="person">
              <el-checkbox-group v-model="travelJournalInfo.addTravelJournal.person">
                <el-checkbox label="哥哥" />
                <el-checkbox label="宝宝" />
              </el-checkbox-group>
            </el-form-item>

            <el-form-item label="景点" prop="place">
              <el-input v-model="travelJournalInfo.addTravelJournal.place" clearable/>
            </el-form-item>

            <el-form-item label="日志" prop="content">
              <el-input
                  v-model="travelJournalInfo.addTravelJournal.content"
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
                  :file-list="travelJournalInfo.addTravelJournal.imageList"
                  list-type="picture-card"
                  :auto-upload="false"
                  :on-change="handleChange"
                  :on-remove="handleRemove">
                <el-icon><Plus /></el-icon>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitTravelJournalClick(addTravelJournalFormRef)">新增</el-button>
        <el-button @click="travelJournalInfo.addTravelJournalDialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElButton, ElDialog, ElMessage, FormInstance, FormRules, UploadFile, UploadFiles } from 'element-plus'
import { useRouter } from 'vue-router'
import { Calendar, CirclePlus, Checked, Location, Histogram, OfficeBuilding, User, Plus } from '@element-plus/icons-vue'
import { pcTextArr } from 'element-china-area-data'

const router = useRouter()

interface addTravelJournalIF {
  title: string;
  date: string;
  area: string[];
  person: string[];
  place: string;
  imageList: UploadFiles;
  content: string;
}

const addTravelJournalFormRef = ref<FormInstance>()

const emptyAddTravelJournal: addTravelJournalIF = {
  area: [], content: '', date: '', imageList: [], person: [], place: '', title: ''
}

const travelJournalInfo = reactive({
  selectYear: '',
  journalList: [{
    title: '夜游无想水镇',
    date: '2023-07-09',
    area: '江苏省-南京市',
    person: '哥哥,宝宝',
    place: '无想水镇',
    content: ''
  }, {
    title: '镇江一日游',
    date: '2023-07-10',
    area: '江苏省-镇江市',
    person: '哥哥,宝宝',
    place: '北固山,焦山',
    content: '吃了锅盖面'
  }],
  addTravelJournalDialogVisible: false,
  addTravelJournal: emptyAddTravelJournal
})

const addTravelJournalRules = reactive<FormRules<addTravelJournalIF>>({
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

const yearCardTouristSpots = {
  XuanWu: ['明孝陵景区', '灵谷景区', '毗卢寺景区', '南京城墙景区台城段（不含夜游）', '白马石刻公园',
    '中山植物园（含南园）', '总统府', '紫金山天文台', '音乐台', '美龄宫', '六朝博物馆', '红山森林动物园', '江宁织造博物馆'],
  GuLou: ['宝船厂遗址公园', '龚贤纪念馆（清凉山内）', '魏紫熙艺术馆（清凉山内）',
    '李剑晨艺术馆（清凉山内）', '国防教育馆', '阅江楼景区'],
  QinHuai: ['南京王谢故居（夫子庙内）', '李香君故居（夫子庙内）', '大报恩寺（不含夜游）',
    '市民俗博物馆（甘熙宅第）', '南京城墙景区〔中华门段）', '秦大士故居', '芥子园（芥子园+惜抱轩)', '瞻园（太平天国历史博物馆）（不含夜游）', '南京市博物馆（朝天宫）'],
  GaoChun: ['固城湖水慢城（高淳区）'],
  PuKou: ['云幽谷旅游区（浦口区）', '万成生态园（浦口区）', '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园'],
  LiuHe: ['金牛湖风景区'],
  JiangNing: ['江宁汤山方山国家地质公园博物馆', '汤山古猿人洞', '明文化村（阳山碑材）'],
  LiShui: ['大金山风景区', '天生桥风景区', '周园（不含周村）', '郭兴庄园', '无想山景区'],
  QiXia: ['达摩古洞景区', '长江观音景区', '燕子矶公园'],
  JianYe: ['莫愁湖公园']
}

const yearCardTouristSpotsCheck = reactive({
  XuanWu: Array(yearCardTouristSpots.XuanWu.length).fill(false),
  GuLou: Array(yearCardTouristSpots.GuLou.length).fill(false),
  QinHuai: Array(yearCardTouristSpots.QinHuai.length).fill(false),
  GaoChun: Array(yearCardTouristSpots.GaoChun.length).fill(false),
  PuKou: Array(yearCardTouristSpots.PuKou.length).fill(false),
  LiuHe: Array(yearCardTouristSpots.LiuHe.length).fill(false),
  JiangNing: Array(yearCardTouristSpots.JiangNing.length).fill(false),
  LiShui: Array(yearCardTouristSpots.LiShui.length).fill(false),
  QiXia: Array(yearCardTouristSpots.QiXia.length).fill(false),
  JianYe: Array(yearCardTouristSpots.JianYe.length).fill(false)
})

const yearCardDialogVisible = ref(false)

const addTravelJournalClick = () => {
  travelJournalInfo.addTravelJournal = emptyAddTravelJournal
  travelJournalInfo.addTravelJournalDialogVisible = true
}

// 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用,function(file, fileList)
const handleChange = (file: UploadFile, fileList: UploadFiles) => {
  travelJournalInfo.addTravelJournal.imageList = fileList
}

// 删除文件之前的钩子，参数为上传的文件和文件列表，若返回 false 或者返回 Promise 且被 reject，则停止删除。function(file, fileList)
const handleRemove = (file: UploadFile, fileList: UploadFiles) => {
  travelJournalInfo.addTravelJournal.imageList = fileList
}

const submitTravelJournalClick = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log(travelJournalInfo.addTravelJournal)
      const formData = new FormData()
      formData.append('title', travelJournalInfo.addTravelJournal.title)
      formData.append('date', travelJournalInfo.addTravelJournal.date)
      formData.append('area', travelJournalInfo.addTravelJournal.area.join('-'))
      formData.append('person', travelJournalInfo.addTravelJournal.person.join(','))
      formData.append('place', travelJournalInfo.addTravelJournal.place)
      travelJournalInfo.addTravelJournal.imageList.forEach((file) => {
        formData.append('imageList', file.raw as Blob)
      })
      formData.append('content', travelJournalInfo.addTravelJournal.content)

      travelJournalInfo.addTravelJournal.imageList = []
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
}
</style>
