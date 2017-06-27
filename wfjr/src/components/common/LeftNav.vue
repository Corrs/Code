<template>
  <div class="nav">
    我是左侧导航
  </div>
</template>

<script>
import router from '@/router'

export default {
  data () {
    return {
      
    }
  },
  created () {
    
  },
  mounted () {
    $(".nav").height(jr.getClientHeight());
  }
}
</script>

<style scoped>
.nav {
    background-color: #2A3F54 !important;
    color: #ECF0F1;
    padding: 0;
    margin: 0;
}
</style>


