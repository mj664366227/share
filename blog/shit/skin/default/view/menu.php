<div class="sidebar-menu toggle-others fixed">
  <div class="sidebar-menu-inner">
    <header class="logo-env">
      <div class="logo"><a href="<?php echo url('admin','index')?>">我的博客管理后台</a></div>
      <div class="mobile-menu-toggle visible-xs"><a href="#" data-toggle="user-info-menu"><i class="fa-bell-o"></i><span class="badge badge-success">7</span></a><a href="#" data-toggle="mobile-menu"><i class="fa-bars"></i></a></div>
      <div class="settings-icon"><a href="#" data-toggle="settings-pane" data-animate="true"><i class="linecons-cog"></i></a></div>
    </header>
    <ul id="main-menu" class="main-menu">
      <li<?php if($action==='cms.post'){?> class="active"<?php }?>><a href="<?php echo url('cms','post')?>"><span class="title">发表文章</span></a></li>
      <li<?php if($action==='admin.setting'){?> class="active"<?php }?>><a href="<?php echo url('admin','setting')?>"><span class="title">设置</span></a></li>
    </ul>
  </div>
</div>
<div class="main-content">
  <nav class="navbar user-info-navbar" role="navigation">
    <ul class="user-info-menu right-links list-inline list-unstyled">
      <li class="dropdown user-profile"><a href="javascript:void(0)" data-toggle="dropdown"><img src="assets/images/user-4.jpg" alt="user-image" class="img-circle img-inline userpic-32" width="28"><span>ruanzhijun&nbsp;<i class="fa-angle-down"></i></span></a>
        <ul class="dropdown-menu user-profile-menu list-unstyled">
          <li class="last"><a href="<?php echo url('login','logout')?>"><i class="fa-lock"></i>&nbsp;退出</a></li>
        </ul>
      </li>
    </ul>
  </nav>