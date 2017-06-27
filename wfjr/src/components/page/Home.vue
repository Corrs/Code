<template>
  <div>
    <el-row>
      <el-col :xs="0" :sm="0" :md="3" :lg="3">
        <LeftNav></LeftNav> 
      </el-col>
      <el-col :xs="24" :sm="24" :md="21" :lg="21">
        <RHeader></RHeader>
        <div>
          <router-view></router-view>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import LeftNav from '@/components/common/LeftNav.vue'
import RHeader from '@/components/common/RHeader.vue'
export default {
  data () {
      return {
          msg: "首页，登录成功！"
      }
  },
  components: {
    LeftNav,
    RHeader
  },
  created () {
    this.getPageHeight();
  },
  mounted () {
    
  },
  methods: {
    
  }
}
</script>

<style>

</style>


