<template>
  <div id="app">
    <el-row>
      <el-col :xs="0" :sm="1" :md="1">
        <leftNav></leftNav>
      </el-col>
      <el-col :xs="24" :sm="23" :md="23">
        <div class="main">
          <transition name="fade" mode="out-in">
            <router-view></router-view>
          </transition>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import leftNav from '@/components/common/leftNav'
export default {
  name: 'app',
  components: {
    leftNav
  },
  mounted () {
    var leftNav = document.getElementsByClassName("left-nav");
    if(typeof leftNav != "undefined" && leftNav.length > 0) {
      leftNav[0].style.height = document.body.clientHeight + "px";
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif, 微软雅黑, 幼圆;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.main {
  background-color: #fff2f7;
  height: 100%;
  overflow: hidden;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0
}
</style>
