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

    <el-dialog v-model="yearCardDialogVisible" title="2022年 年卡使用情况" center>
    <div>
      <!-- <el-checkbox
        v-for="(yearCardTouristSpot, index) in yearCardTouristSpots"
        :key="index"
        v-model="checked1" :label="yearCardTouristSpot" size="large" /> -->
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

const yearCardXuanWuTouristSpots = ['明孝陵景区', '宝船厂遗址公园', '固城湖水慢城（高淳区）', '南京王谢故居（夫子庙内）',
  '李香君故居（夫子庙内）', '大报恩寺（不含夜游）', '云幽谷旅游区（浦口区）', '龚贤纪念馆（清凉山内）', '魏紫熙艺术馆（清凉山内）',
  '李剑晨艺术馆（清凉山内）', '国防教育馆', '万成生态园（浦口区）', '金牛湖风景区', '江宁汤山方山国家地质公园博物馆', '大金山风景区',
  '天生桥风景区', '汤山古猿人洞', '明文化村（阳山碑材）',
  '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园', '达摩古洞景区', '长江观音景区', '燕子矶公园', '市民俗博物馆（甘熙宅第）',
  '南京城墙景区〔中华门段）', '南京城墙景区台城段（不含夜游）', '毗卢寺景区', '阅江楼景区', '总统府', '六朝博物馆', '红山森林动物园', '莫愁湖公园',
  '紫金山天文台', '白马石刻公园', '美龄宫', '中山植物园（含南园）', '音乐台', '灵谷景区', '秦大士故居', '芥子园（芥子园+惜抱轩)', '周园（不含周村）', '瞻园（太平天国历史博物馆）（不含夜游）',
  '郭兴庄园', '无想山景区', '江宁织造博物馆', '南京市博物馆（朝天宫）']

const yearCardGuLouTouristSpots = ['明孝陵景区', '宝船厂遗址公园', '固城湖水慢城（高淳区）', '南京王谢故居（夫子庙内）',
  '李香君故居（夫子庙内）', '大报恩寺（不含夜游）', '云幽谷旅游区（浦口区）', '龚贤纪念馆（清凉山内）', '魏紫熙艺术馆（清凉山内）',
  '李剑晨艺术馆（清凉山内）', '国防教育馆', '万成生态园（浦口区）', '金牛湖风景区', '江宁汤山方山国家地质公园博物馆', '大金山风景区',
  '天生桥风景区', '汤山古猿人洞', '明文化村（阳山碑材）',
  '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园', '达摩古洞景区', '长江观音景区', '燕子矶公园', '市民俗博物馆（甘熙宅第）',
  '南京城墙景区〔中华门段）', '南京城墙景区台城段（不含夜游）', '毗卢寺景区', '阅江楼景区', '总统府', '六朝博物馆', '红山森林动物园', '莫愁湖公园',
  '紫金山天文台', '白马石刻公园', '美龄宫', '中山植物园（含南园）', '音乐台', '灵谷景区', '秦大士故居', '芥子园（芥子园+惜抱轩)', '周园（不含周村）', '瞻园（太平天国历史博物馆）（不含夜游）',
  '郭兴庄园', '无想山景区', '江宁织造博物馆', '南京市博物馆（朝天宫）']

const yearCardQinHuaiTouristSpots = ['明孝陵景区', '宝船厂遗址公园', '固城湖水慢城（高淳区）', '南京王谢故居（夫子庙内）',
  '李香君故居（夫子庙内）', '大报恩寺（不含夜游）', '云幽谷旅游区（浦口区）', '龚贤纪念馆（清凉山内）', '魏紫熙艺术馆（清凉山内）',
  '李剑晨艺术馆（清凉山内）', '国防教育馆', '万成生态园（浦口区）', '金牛湖风景区', '江宁汤山方山国家地质公园博物馆', '大金山风景区',
  '天生桥风景区', '汤山古猿人洞', '明文化村（阳山碑材）',
  '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园', '达摩古洞景区', '长江观音景区', '燕子矶公园', '市民俗博物馆（甘熙宅第）',
  '南京城墙景区〔中华门段）', '南京城墙景区台城段（不含夜游）', '毗卢寺景区', '阅江楼景区', '总统府', '六朝博物馆', '红山森林动物园', '莫愁湖公园',
  '紫金山天文台', '白马石刻公园', '美龄宫', '中山植物园（含南园）', '音乐台', '灵谷景区', '秦大士故居', '芥子园（芥子园+惜抱轩)', '周园（不含周村）', '瞻园（太平天国历史博物馆）（不含夜游）',
  '郭兴庄园', '无想山景区', '江宁织造博物馆', '南京市博物馆（朝天宫）']

const yearCardGaoChunTouristSpots = ['明孝陵景区', '宝船厂遗址公园', '固城湖水慢城（高淳区）', '南京王谢故居（夫子庙内）',
  '李香君故居（夫子庙内）', '大报恩寺（不含夜游）', '云幽谷旅游区（浦口区）', '龚贤纪念馆（清凉山内）', '魏紫熙艺术馆（清凉山内）',
  '李剑晨艺术馆（清凉山内）', '国防教育馆', '万成生态园（浦口区）', '金牛湖风景区', '江宁汤山方山国家地质公园博物馆', '大金山风景区',
  '天生桥风景区', '汤山古猿人洞', '明文化村（阳山碑材）',
  '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园', '达摩古洞景区', '长江观音景区', '燕子矶公园', '市民俗博物馆（甘熙宅第）',
  '南京城墙景区〔中华门段）', '南京城墙景区台城段（不含夜游）', '毗卢寺景区', '阅江楼景区', '总统府', '六朝博物馆', '红山森林动物园', '莫愁湖公园',
  '紫金山天文台', '白马石刻公园', '美龄宫', '中山植物园（含南园）', '音乐台', '灵谷景区', '秦大士故居', '芥子园（芥子园+惜抱轩)', '周园（不含周村）', '瞻园（太平天国历史博物馆）（不含夜游）',
  '郭兴庄园', '无想山景区', '江宁织造博物馆', '南京市博物馆（朝天宫）']

const yearCardPuKouTouristSpots = ['云幽谷旅游区（浦口区）', '万成生态园（浦口区）', '珍珠泉名石艺术馆', '珍珠泉旅游度假区（含狮虎园）', '南京老山国家森林公园']

const yearCardLiuHeTouristSpots = ['金牛湖风景区']

const yearCardJiangNingTouristSpots = ['江宁汤山方山国家地质公园博物馆', '汤山古猿人洞', '明文化村（阳山碑材）']

const yearCardLiShuiTouristSpots = ['大金山风景区', '天生桥风景区', '周园（不含周村）', '郭兴庄园', '无想山景区']

const yearCardQiXiaTouristSpots = ['达摩古洞景区', '长江观音景区', '燕子矶公园']

const yearCardJianYeTouristSpots = ['莫愁湖公园']

const yearCardDialogVisible = ref(false)
const checked1 = ref(true)
const checked2 = ref(false)
</script>

<style lang="scss" scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
