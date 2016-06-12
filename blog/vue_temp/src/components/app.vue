<script>
//es6
export default {
    el : '#app',
    data(){
        return {
            name : "xiaobai",
            age : '27'
        }
    }
}
</script>
<template>
<div>
    <h1>姓名：{{name}}</h1>
    <h1>年龄：{{age}}</h1>
</div>
</template>
<style>
body{
    background-color: #eee;
}
h1{
    background-color : #eee;
    color:red;
    transform : translate(10%,10%);
}
h1:hover{
    height : 100px;
}
h2{
background-color : #999;}

</style>