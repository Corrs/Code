<template>
  <div class="left-nav">
      <ul>
          <li v-for="route in routes" :key="route.path">
              <router-link :to="route.path">
                <i :class="route.icon"></i>
                <div v-text="route.pathName"></div>
              </router-link>
          </li>
      </ul>
  </div>
</template>

<script>
import router from '@/router'
import Pos from '@/components/page/Pos'
import Goods from '@/components/page/Goods'
import $ from '@/assets/jquery'
export default {
  name: "leftNav",
  data () {
      return {
        routes: [
            {
                path: '/',
                name: 'Pos',
                component: Pos,
                icon: 'icon iconfont icon-goumai',
                pathName: '收银'
            },
            {
                path: '/goods',
                name: 'Goods',
                component: Goods,
                icon: 'icon iconfont icon-hanbao',
                pathName: '商品'
            }
        ]     
      }
  },
  mounted () {
      router.addRoutes(this.routes);
    //   debugger;
      //jQuery(".left-nav").height($("body").clientHeight);
  }
}
</script>

<style>
a {
    color: white;
    text-decoration: none;
}
.left-nav {
    color: #fff;
    font-size: 10px;
    height: 100%;
    background-color: #1D8CE0;
    float: left;
    text-align: center;
    width: 100%;
}

.left-nav ul {
    list-style: none;
    padding: 0;
    margin: 0;
    margin: 0 auto;
}

.left-nav ul li {
    text-align: center;
    border-bottom: 1px solid #20a0ff;
    padding: 10px;
    cursor: pointer;
}

.iconfont {
    font-size: 24px;
}

</style>


