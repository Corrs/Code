<template>
  <div>
    <el-row>
      <el-col v-if="toggle" :xs="0" :sm="0" :md="3" :lg="3">
        <LeftNav></LeftNav> 
      </el-col>
      <el-col :xs="24" :sm="24" :md="md" :lg="lg">
        <RHeader @changeNav="changeNav"></RHeader>
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
          toggle: true,
          md: 21,
          lg: 21
      }
  },
  components: {
    LeftNav,
    RHeader
  },
  created () {
    
  },
  mounted () {
    
  },
  methods: {
    changeNav() {
      this.toggle = !this.toggle;
      this.md = this.toggle ? 21 : 24;
      this.lg = this.md;
    }
  }
}
</script>

<style>

</style>


