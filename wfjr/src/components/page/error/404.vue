<template>
  <div>
      <p v-text="msg"></p>
      您访问的页面不存在
  </div>
</template>
<script>
export default {
  data() {
      return {
        msg: "Error:404"
      };
  }
}
</script>

<style>

</style>

