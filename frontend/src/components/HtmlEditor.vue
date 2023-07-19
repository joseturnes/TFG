<template>
  <div ref="editor"></div>
</template>

<script>
import { onMounted, ref } from "vue";
import Quill from "quill";

export default {
  name: "HtmlEditor",
  setup() {
    const editor = ref(null);

    onMounted(() => {
      new Quill(editor.value, {
        theme: "snow"
      });
    });

    return {
      editor
    };
  }
};
</script>


<style scoped>
.editor-container {
  display: flex;
  flex-direction: column;
  height: 100%; /* O cambia esto a cualquier altura que desees */
  border: 1px solid #cccccc;
  border-radius: 4px;
}

.editor-content {
  flex-grow: 1;
  padding: 10px;
}
</style>