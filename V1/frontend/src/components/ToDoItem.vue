<template>
  <div>
    <input type="checkbox" :id="id" :checked="isDone" />
    <label :for="id">{{label}}</label>
  </div>
</template>

<script>
import uniqueId from "lodash.uniqueid";

export default {
  name: "ToDoItem",
  props: {
    label: { required : true, type : String},
    done: { default : false, type : Boolean}
  },
  data() { // Vue JS에서 사용되는 데이터 보관함 (Object 자료형)
    return {
      id: 'todo-' + uniqueId(),
      isDone : this.done,
    }
  }
};

</script>

<style scoped>

</style>