<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'jinzitaDoc',
  data() {
    return {
      url: 'http://doc.jinzita.vip/jinzita-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>