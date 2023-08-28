<template>
  <div class="Dialog">
    <div class="dialog-header">
      <h2>{{ title }}</h2>
    </div>
    <div class="dialog-content">
    <slot></slot>
  </div>
  <div class="dialog-footer">
      <span id="accept_button"><el-button size="mini" @click="closeDialog" >閉じる</el-button></span>
  </div>
</div>
  </template>
  <script>
  export default {
    props: {
      title: String
  },
    methods:{
        closeDialog(){
          this.$emit('close');
        },
      }
  }
  </script>
  <style>

  </style>