<template>
<div>
    <h1>姓名：{{name}}</h1>
    <h2>{{age}}</h2>
    <button @click="golist">$route.router.go查看</button>
    <a v-link="{ path: '/foo' }">v-link查看列表</a>
    <a v-link="{ path: '/bar' }">回去主页</a>
</div>
</template>
<script>
    export default {//这里是官方的写法，默认导出，ES6
        data () {

            this.$http({url: '/share.git/blog/?action=index.demo', method: 'GET'}).then(function (response) {
                      this.name = response.data.url;
                  }, function (response) {
                      // error callback
                  });

            return {
                name:"guowenfh",
                age:"21"
            }
        },
        methods :{
            golist () {//方法，定义路由跳转，注意这里必须使用this，不然报错
                this.$route.router.go({name:"list"});
            }
        }
    }
</script>
<style></style>
<!-- 样式自行设置，或者直接看源码就好 -->