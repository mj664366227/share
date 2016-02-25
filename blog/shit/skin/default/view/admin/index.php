<?php require view::dir().'head.php';?>

<div class="page-container">
  <div class="sidebar-menu toggle-others fixed">
    <div class="sidebar-menu-inner">
      <header class="logo-env"> 
        
        <!-- logo -->
        <div class="logo"> <a href="?action=admin.index">我的博客管理后台</a> </div>
        
        <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
        <div class="mobile-menu-toggle visible-xs"> <a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i> <span class="badge badge-success">7</span> </a> <a href="#" data-toggle="mobile-menu"> <i class="fa-bars"></i> </a> </div>
        
        <!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
        <div class="settings-icon"> <a href="#" data-toggle="settings-pane" data-animate="true"> <i class="linecons-cog"></i> </a> </div>
      </header>
      <ul id="main-menu" class="main-menu">
        <!-- add class "multiple-expanded" to allow multiple submenus to open --> 
        <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
        <li class="active opened active"> <a href="dashboard-1.html"> <i class="linecons-cog"></i> <span class="title">Dashboard</span> </a>
          <ul>
            <li class="active"> <a href="dashboard-1.html"> <span class="title">Dashboard 1</span> </a> </li>
            <li> <a href="dashboard-2.html"> <span class="title">Dashboard 2</span> </a> </li>
            <li> <a href="dashboard-3.html"> <span class="title">Dashboard 3</span> </a> </li>
            <li> <a href="dashboard-4.html"> <span class="title">Dashboard 4</span> </a> </li>
            <li> <a href="skin-generator.html"> <span class="title">Skin Generator</span> </a> </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <div class="main-content"> 
    
    <!-- User Info, Notifications and Menu Bar -->
    <nav class="navbar user-info-navbar" role="navigation"> 
      
      <!-- Right links for user info navbar -->
      <ul class="user-info-menu right-links list-inline list-unstyled">
        <li class="search-form"><!-- You can add "always-visible" to show make the search input visible -->
          
          <form method="get" action="extra-search.html">
            <input type="text" name="s" class="form-control search-field" placeholder="Type to search..." />
            <button type="submit" class="btn btn-link"> <i class="linecons-search"></i> </button>
          </form>
        </li>
        <li class="dropdown user-profile"> <a href="#" data-toggle="dropdown"> <img src="assets/images/user-4.jpg" alt="user-image" class="img-circle img-inline userpic-32" width="28" /> <span>ruanzhijun&nbsp;<i class="fa-angle-down"></i> </span> </a>
          <ul class="dropdown-menu user-profile-menu list-unstyled">
            <li><a href="#"><i class="fa-wrench"></i>设置</a></li>
            <li class="last"><a href="#"><i class="fa-lock"></i>&nbsp;退出</a></li>
          </ul>
        </li>
      </ul>
    </nav>
    <footer class="main-footer sticky footer-type-1">
      <div class="footer-inner">
        <div class="footer-text"> &copy; 2014 <strong>Xenon</strong> More Templates <a href="http://www.uedsc.com" target="_blank" title="问说网">uedsc</a> </div>
      </div>
    </footer>
  </div>
</div>
<?php require view::dir().'foot.php';?>
