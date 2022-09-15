<template>
  <div>
    <div style="text-align: center;">
      <el-button type="primary" @click="yearCardDialogVisible = true">年卡使用情况</el-button>
    </div>

    <el-timeline>
      <el-timeline-item timestamp="2018/4/12" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/12 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/3" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/3 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/2" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/2 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/2" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/2 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/2" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/2 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/2" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/2 20:46</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>

    <el-dialog v-model="yearCardDialogVisible" title="2022年 年卡使用情况" center style="width: 880px" :show-close="false">
      <div>
        <el-tabs type="border-card" tab-position="top" style="width: 830px" class="demo-tabs">
          <el-tab-pane label="玄武区">
            <el-checkbox
              v-for="(yearCardXuanWuTouristSpot, index) in yearCardXuanWuTouristSpots"
              :key="index"
              v-model="yearCardXuanWuTouristSpotsCheck[index]" :label="yearCardXuanWuTouristSpot" size="large" :change="UpdateTouristSpot(index)"/>
          </el-tab-pane>
          <el-tab-pane label="鼓楼区">
            <el-checkbox
              v-for="(yearCardGuLouTouristSpot, index) in yearCardGuLouTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardGuLouTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="秦淮区">
            <el-checkbox
              v-for="(yearCardQinHuaiTouristSpot, index) in yearCardQinHuaiTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardQinHuaiTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="高淳区">
            <el-checkbox
              v-for="(yearCardGaoChunTouristSpot, index) in yearCardGaoChunTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardGaoChunTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="浦口区">
            <el-checkbox
              v-for="(yearCardPuKouTouristSpot, index) in yearCardPuKouTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardPuKouTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="六合区">
            <el-checkbox
              v-for="(yearCardLiuHeTouristSpot, index) in yearCardLiuHeTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardLiuHeTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="江宁区">
            <el-checkbox
              v-for="(yearCardJiangNingTouristSpot, index) in yearCardJiangNingTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardJiangNingTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="溧水区">
            <el-checkbox
              v-for="(yearCardLiShuiTouristSpot, index) in yearCardLiShuiTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardLiShuiTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="栖霞区">
            <el-checkbox
              v-for="(yearCardQiXiaTouristSpot, index) in yearCardQiXiaTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardQiXiaTouristSpot" size="large" />
          </el-tab-pane>
          <el-tab-pane label="建邺区">
            <el-checkbox
              v-for="(yearCardJianYeTouristSpot, index) in yearCardJianYeTouristSpots"
              :key="index"
              v-model="checked1" :label="yearCardJianYeTouristSpot" size="large" />
          </el-tab-pane>
        </el-tabs>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="yearCardDialogVisible = false">返回</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ElButton, ElDialog } from 'element-plus'

const yearCardXuanWuTouristSpots = ['明孝陵景区', '灵谷景区', '毗卢寺景区', '南京城墙景区台城段（不含夜游）', '白马石刻公园',
  '中山植物园（含南园）', '总统府', '紫金山天文台', '音乐台', '美龄宫', '六朝博物馆', '红山森林动物园', '江宁织造博物馆']

const yearCardXuanWuTouristSpotsCheck = [false, false, false, false, false, false, false, false, false, false, false, false, false]

const yearCardGuLouTouristSpots = ['宝船厂遗址公园', '龚贤纪念馆（清凉山内）', '魏紫熙艺术馆（清凉山内）',
  '李剑晨艺术馆（清凉山内）', '国防教育馆', '阅江楼景区']

const yearCardQinHuaiTouristSpots = ['南京王谢故居（夫子庙内）', '李香君故居（夫子庙内）', '大报恩寺（不含夜游）',
  '市民俗博物馆（甘熙宅第）', '南京城墙景区〔中华门段）', '秦大士故居', '芥子园（芥子园+惜抱轩)', '瞻园（太平天国历史博物馆）（不含夜游）', '南京市博物馆（朝天宫）']

const yearCardGaoChunTouristSpots = ['固城湖水慢城（高淳区）']

const yearCardPuKouTouristSpots = ['云幽谷旅游区（浦口区）', '万成生态园（浦口区）', '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园']

const yearCardLiuHeTouristSpots = ['金牛湖风景区']

const yearCardJiangNingTouristSpots = ['江宁汤山方山国家地质公园博物馆', '汤山古猿人洞', '明文化村（阳山碑材）']

const yearCardLiShuiTouristSpots = ['大金山风景区', '天生桥风景区', '周园（不含周村）', '郭兴庄园', '无想山景区']

const yearCardQiXiaTouristSpots = ['达摩古洞景区', '长江观音景区', '燕子矶公园']

const yearCardJianYeTouristSpots = ['莫愁湖公园']

const yearCardDialogVisible = ref(false)
const checked1 = ref(true)
const checked2 = ref(false)

const UpdateTouristSpot = (a) => {
  console.log(a)
}
</script>

<style lang="scss" scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
