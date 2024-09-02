<template>
  <main class="home-container">
    <div class="top-container">

    </div>

    <section class="main-container">
<!--      <aside class="anniversary-container">-->

<!--      </aside>-->

<!--      <div class="module-container">-->
<!--        <module-card image="property" title="家庭资产" subTitle="👦赚钱养家 👧貌美如花"/>-->
<!--        <module-card image="food" title="美食记录" subTitle="努力✊才有收获"/>-->
<!--        <module-card image="travel" title="旅行记录" subTitle="甜蜜💖瞬间有你陪伴"/>-->
<!--        <module-card image="diary" title="日常生活" subTitle="点点滴滴💧 长长久久"/>-->
<!--        <module-card image="wish" title="心愿清单" subTitle="有目标🚩才有奋斗"/>-->
<!--        <module-card image="us" title="关于我们" subTitle="经历👫才会成长"/>-->
<!--      </div>-->

      <aside class="time-line-container">
        <h2>CSS3 Timeline</h2>
        <p>Please set the $vertical variable to false to see the horizontal version.</p>
        <ul id='timeline'>
          <li class='work'>
            <input class='radio' id='work5' name='works' type='radio' checked>
            <div class="relative">
              <label for='work5'>Lorem ipsum dolor sit amet</label>
              <span class='date'>12 May 2013</span>
              <span class='circle'></span>
            </div>
            <div class='content'>
              <p>content</p>
            </div>
          </li>
          <li class='work'>
            <input class='radio' id='work4' name='works' type='radio'>
            <div class="relative">
              <label for='work4'>Lorem ipsum dolor sit amet</label>
              <span class='date'>11 May 2013</span>
              <span class='circle'></span>
            </div>
            <div class='content'>
              <p>content</p>
            </div>
          </li>
          <li class='work'>
            <input class='radio' id='work3' name='works' type='radio'>
            <div class="relative">
              <label for='work3'>Lorem ipsum dolor sit amet</label>
              <span class='date'>10 May 2013</span>
              <span class='circle'></span>
            </div>
            <div class='content'>
              <p>content</p>
            </div>
          </li>
          <li class='work'>
            <input class='radio' id='work2' name='works' type='radio'>
            <div class="relative">
              <label for='work2'>Lorem ipsum dolor sit amet</label>
              <span class='date'>09 May 2013</span>
              <span class='circle'></span>
            </div>
            <div class='content'>
              <p>content</p>
            </div>
          </li>
          <li class='work'>
            <input class='radio' id='work1' name='works' type='radio'>
            <div class="relative">
              <label for='work1'>Lorem ipsum dolor sit amet</label>
              <span class='date'>08 May 2013</span>
              <span class='circle'></span>
            </div>
            <div class='content'>
              <p>content</p>
            </div>
          </li>
        </ul>
      </aside>
    </section>
  </main>
</template>

<script setup lang="ts">
import ModuleCard from '@/components/Home/ModuleCard.vue'
</script>

<style lang="scss">
.home-container {
  height: 100%;

  display: grid;
  grid-template-rows: 35% 60% 5%;
  align-items: center;

  .top-container {

  }

  .main-container {
    height: 100%;
    display: grid;
    // grid-template-columns: 1fr 2fr 3fr;
    // align-items: center;
    gap: 1vw;

    .module-container {
      height: 25vh;

      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 2vw;
    }

    .time-line-container {

      background-color: #67c23a;
    }
  }
}

/* -------------------------------------
 * timeline
 * ------------------------------------- */
#timeline {
  list-style: none;
  margin: 50px 0 30px 120px;
  padding-left: 30px;
  border-left: 8px solid #eee9dc;
}
#timeline li {
  margin: 0px 0;
  position: relative;
}
#timeline p {
  margin: 0 0 10px;
}

.date {
  margin-top: -10px;
  top: 50%;
  left: -158px;
  font-size: 0.95em;
  line-height: 20px;
  position: absolute;
}

.circle {
  margin-top: -10px;
  top: 50%;
  left: -44px;
  width: 10px;
  height: 10px;
  background: #48b379;
  border: 5px solid #eee9dc;
  border-radius: 50%;
  display: block;
  position: absolute;
}

.content {
  max-height: 20px;
  padding: 50px 20px 0;
  border-color: transparent;
  border-width: 2px;
  border-style: solid;
  border-radius: 0.5em;
  position: relative;
}
.content:before, .content:after {
  content: "";
  width: 0;
  height: 0;
  border: solid transparent;
  position: absolute;
  pointer-events: none;
  right: 100%;
}
.content:before {
  border-right-color: inherit;
  border-width: 20px;
  top: 50%;
  margin-top: -20px;
}
.content:after {
  border-right-color: #48b379;
  border-width: 17px;
  top: 50%;
  margin-top: -17px;
}
.content p {
  max-height: 0;
  color: transparent;
  text-align: justify;
  word-break: break-word;
  hyphens: auto;
  overflow: hidden;
}

label {
  font-size: 1.3em;
  position: absolute;
  z-index: 100;
  cursor: pointer;
  top: 20px;
  transition: transform 0.2s linear;
}

.radio {
  display: none;
}

.radio:checked + .relative label {
  cursor: auto;
  transform: translateX(42px);
}
.radio:checked + .relative .circle {
  background: #f98262;
}
.radio:checked ~ .content {
  max-height: 180px;
  border-color: #eee9dc;
  margin-right: 20px;
  transform: translateX(20px);
  transition: max-height 0.4s linear, border-color 0.5s linear, transform 0.2s linear;
}
.radio:checked ~ .content p {
  max-height: 200px;
  color: #eee9dc;
  transition: color 0.3s linear 0.3s;
}
</style>
