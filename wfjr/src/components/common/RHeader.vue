<template>
  <div class="right-header">
    <div class="toggle">
      <a id="menu_toggle" @click="changeNav"><i class="icon iconfont" :class="{'icon-tabulation': !toggle, 'icon-31guanbi': toggle}"></i></a>
    </div>
    <div class="navbar-right">
      <ul>
        <li>
          <el-menu  class="el-menu-demo" mode="horizontal">
            <el-submenu index="1">
              <template slot="title">
                <a class="user-profile">
                  <img src="~assets/img/img.jpg" alt="">
                  <span v-text="truename"></span>
                  <span class=" fa fa-angle-down"></span>
                </a>
              </template>
              <el-menu-item index="1"><i class="icon iconfont icon-wo"></i>个人信息</el-menu-item>
              <el-menu-item index="2"><i class="icon iconfont icon-gongnengjianyi"></i>设置</el-menu-item>
              <el-menu-item index="3"><i class="icon iconfont icon-bangzhu"></i>帮助</el-menu-item>
              <el-menu-item index="4"><i class="icon iconfont icon-shanchu"></i>退出登录</el-menu-item>
            </el-submenu>
          </el-menu>
        </li>
        <li class="open">
          <el-badge :value="badgeValue" :max="99" class="item">
            <a class="badge"><i class="icon iconfont icon-duanxin"></i></a>
            <ul v-if="showBadge" class="dropdown-menu list-unstyled msg_list">
              <li>
                <a></a>
              </li>
            </ul>
          </el-badge>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      toggle: true,
      truename: '曹书博',
      userImg: './assets/img/img.jpg',
      badgeValue: 2,
      showBadge: false
    }
  },
  mounted () {
    this.showToggle();
  },
  computed: {
    
  },
  methods: {
    changeNav() {
      this.toggle = !this.toggle
      this.$emit('changeNav')
    },
    showToggle() {
      this.toggle = !(jr.getClientWidth() < 768);
    }
  }
}
</script>

<style>
img {
    vertical-align: middle;
    border: 0;
}

a {
  cursor: pointer;
}

a:hover {
  text-decoration: none;
}

.right-header {
  height: 55px;
  background: #EDEDED;
  border-bottom: 1px solid #D9DEE4;
  /*margin-bottom: 10px;*/
}

.toggle {
  float: left;
  padding-left: 15px;
}

.toggle a i, .badge i {
  font-size: 30px;
  line-height: 55px;
}

.badge i {
  font-size: 25px;
  line-height: 60px;
}

.navbar-right {
    margin: 0;
    width: 70%;
    float: right;
}

.navbar-right li {
    float: right;
}

.navbar-right li>a>img {
    max-width: none;
}

.user-profile img {
    width: 29px;
    height: 29px;
    border-radius: 50%;
    margin-right: 10px;
}

.el-menu--horizontal .el-submenu .el-submenu__title {
    height: 55px !important;
    line-height: 55px !important;
    border-bottom: 5px solid transparent !important;
    background: #EDEDED !important;
}

.el-menu--horizontal .el-submenu>.el-menu {
    background: #EDEDED !important;
    top: 58px !important;
    padding: 0 !important;
}
.el-menu-item {
    background: #EDEDED !important;
    text-align: left !important;
}

.el-menu-item.is-active {
    color: #48576a !important;
}

.el-badge__content.is-fixed {
  top: 20px !important;
  right: 15px !important;
}

.iconfont {
  font-size: 14px;
  margin-right: 3px;
}

.open {
  min-width: 300px;
  text-align: right;
}

.dropdown-menu {
    position: absolute;
    background: #fff;
    margin-top: 0;
    border: 1px solid #D9DEE4;
    box-shadow: none;
    -webkit-box-shadow: none;
    top: 55px;
    right: 0;
    left: auto;
    display: block;
    min-width: 300px;
}

.dropdown-menu .msg_list {
    width: 300px;
}

.list-unstyled {
    padding-left: 0;
    list-style: none;
}

ul.msg_list li {
    background: #f7f7f7;
    /*padding: 5px;*/
    display: -ms-flexbox;
    display: flex;
    margin: 6px 6px 0;
    width: 96% !important;
}

.dropdown-menu li>a {
    display: block;
    padding: 3px 20px;
    font-weight: 400;
    line-height: 1.42857143;
    color: #5A738E;
    white-space: nowrap;
}
</style>


