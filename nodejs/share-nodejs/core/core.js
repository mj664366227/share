module.exports = root=>{
    return {
        //所有涉及到路径的问题放这里
        path:{
            root:root,
            core:root+'/framework',
            app:root+'/application',
            config:root+'/config',
            model: root+'/model',
            view: root+'/view',
            service: root+'/service',
            tmp: root+'/tmp',
        },
        //框架启动端口 是否在入口文件设置更加合理?
        port:3311,
        rest:['api'],//restful api 的定位 避免传统接口冲突
        web:{
            module:'home',//默认模块
            controller:'index',//默认控制层
            action:'index'//默认执行函数
        },
        cdn:'/static',
        cookie:{
            secret:'1234567890~!@',
            version:'0.26',//控制cookie 版本
        }
    }
}
