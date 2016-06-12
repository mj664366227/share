//es6语法
import Vue from "../node_modules/vue/dist/vue.min.js";
//外部引入别的库都可以用这样的方式，比如jquery
//引入我们编写的测试用vue文件
import app from './components/app.vue';

Vue.config.debug = true;//开启错误提示

new Vue(app);

//import Vue from "vue";
//import  VueRouter from "vue-router";
//Vue.use(VueRouter);
//
//import index from './components/app.vue';
//import list from './components/list.vue';
//import hello from './components/hello.vue';
//
//Vue.config.debug = true;
//
//var App = Vue.extend({});
//
//var router = new VueRouter();
//
//router.map({
//    '/index':{
//        name : 'index',
//        component : index
//    },
//    '/list':{
//        name : 'list',
//        component : list
//    }
//});
//router.redirect({
//    '*' : '/index'
//});
//router.start(App,'#app');