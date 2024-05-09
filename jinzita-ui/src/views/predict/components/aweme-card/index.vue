<script>
import VideoCard from '@/views/predict/components/aweme-card/video-card.vue'

export default {
  name: 'AwemeCard',
  components: { VideoCard },
  props: {
    aweme_info: {},
  },
  data() {
    return {
      videoInfo: {},
      coverUrl: '',
      videoUrl: '',
      aweme_title: '',
    }
  },
  mounted() {
    this.videoInfo = this.aweme_info.video;
    this.coverUrl = this.videoInfo.cover.url_list[this.videoInfo.cover.url_list.length - 1];
    this.videoUrl = this.aweme_info.share_url;
    this.aweme_title = this.aweme_info.desc;
  }
}
</script>

<template>
  <div class="aweme-card">
    <a :href="videoUrl" target="_blank">
      <div class="video-cover">
        <img :src="coverUrl">
      </div>
      <p class="aweme-title">{{ aweme_title }}</p>
    </a>
  </div>
</template>

<style scoped lang="scss">
.aweme-card {
  width: 100%;
  padding: 8px;
  .video-cover {
    border-radius: 12px;
    height: 208px;
    width: 100%;
    background-color: #99a9bf;
    overflow: hidden;
    img {
      height: 100%;
      width: 100%;
      border-radius: 12px;
      transition: all .3s ease-in;
      transform: scale(1);
    }
  }
  .aweme-title {
    height: 44px;
    text-overflow: ellipsis;
    -webkit-line-clamp: 2;
    color: rgba( 255,255,255 ,.9);
    -webkit-box-orient: vertical;
    margin-top: 8px;
    font-size: 14px;
    font-weight: 500;
    line-height: 22px;
    transition-property: margin;
    transition-duration: .35s;
    display: -webkit-box;
    overflow: hidden;
  }
}
</style>
