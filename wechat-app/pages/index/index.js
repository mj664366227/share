//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Hello World',
    userInfo: {}
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
      var context = wx.createContext();

      context.rect(50,50,200,200)
      context.stroke()
      context.translate(50,50)
      context.rect(50,50,200,200)
      context.stroke();

      wx.drawCanvas({
        canvasId:1,
        actions:context.getActions()
      });
      
    console.log('onLoad')
    var that = this
  	//调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
      that.update()
    })
  }
})
