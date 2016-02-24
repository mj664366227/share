<?php require view::dir().'head.php';?>

<div class="settings-pane"> <a href="#" data-toggle="settings-pane" data-animate="true"> &times; </a>
  <div class="settings-pane-inner">
    <div class="row">
      <div class="col-md-4">
        <div class="user-info">
          <div class="user-image"> <a href="extra-profile.html"> <img src="assets/images/user-2.png" class="img-responsive img-circle" /> </a> </div>
          <div class="user-details">
            <h3> <a href="extra-profile.html">John Smith</a> 
              
              <!-- Available statuses: is-online, is-idle, is-busy and is-offline --> 
              <span class="user-status is-online"></span> </h3>
            <p class="user-title">Web Developer</p>
            <div class="user-links"> <a href="extra-profile.html" class="btn btn-primary">Edit Profile</a> <a href="extra-profile.html" class="btn btn-success">Upgrade</a> </div>
          </div>
        </div>
      </div>
      <div class="col-md-8 link-blocks-env">
        <div class="links-block left-sep">
          <h4> <span>Notifications</span> </h4>
          <ul class="list-unstyled">
            <li>
              <input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk1" />
              <label for="sp-chk1">Messages</label>
            </li>
            <li>
              <input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk2" />
              <label for="sp-chk2">Events</label>
            </li>
            <li>
              <input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk3" />
              <label for="sp-chk3">Updates</label>
            </li>
            <li>
              <input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk4" />
              <label for="sp-chk4">Server Uptime</label>
            </li>
          </ul>
        </div>
        <div class="links-block left-sep">
          <h4> <a href="#"> <span>Help Desk</span> </a> </h4>
          <ul class="list-unstyled">
            <li> <a href="#"> <i class="fa-angle-right"></i> Support Center </a> </li>
            <li> <a href="#"> <i class="fa-angle-right"></i> Submit a Ticket </a> </li>
            <li> <a href="#"> <i class="fa-angle-right"></i> Domains Protocol </a> </li>
            <li> <a href="#"> <i class="fa-angle-right"></i> Terms of Service </a> </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="copyrights">Collect from <a href="http://www.mycodes.net/"  title="网站模板">网站模板</a></div>
<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always --> 
  
  <!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. --> 
  <!-- Adding class "toggle-others" will keep only one menu item open at a time. --> 
  <!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
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
        <li> <a href="layout-variants.html"> <i class="linecons-desktop"></i> <span class="title">Layouts</span> </a>
          <ul>
            <li> <a href="layout-variants.html"> <span class="title">Layout Variants &amp; API</span> </a> </li>
            <li> <a href="layout-collapsed-sidebar.html"> <span class="title">Collapsed Sidebar</span> </a> </li>
            <li> <a href="layout-static-sidebar.html"> <span class="title">Static Sidebar</span> </a> </li>
            <li> <a href="layout-horizontal-menu.html"> <span class="title">Horizontal Menu</span> </a> </li>
            <li> <a href="layout-horizontal-plus-sidebar.html"> <span class="title">Horizontal &amp; Sidebar Menu</span> </a> </li>
            <li> <a href="layout-horizontal-menu-click-to-open-subs.html"> <span class="title">Horizontal Open On Click</span> </a> </li>
            <li> <a href="layout-horizontal-menu-min.html"> <span class="title">Horizontal Menu Minimal</span> </a> </li>
            <li> <a href="layout-right-sidebar.html"> <span class="title">Right Sidebar</span> </a> </li>
            <li> <a href="layout-chat-open.html"> <span class="title">Chat Open</span> </a> </li>
            <li> <a href="layout-horizontal-sidebar-menu-collapsed-right.html"> <span class="title">Both Menus &amp; Collapsed</span> </a> </li>
            <li> <a href="layout-boxed.html"> <span class="title">Boxed Layout</span> </a> </li>
            <li> <a href="layout-boxed-horizontal-menu.html"> <span class="title">Boxed &amp; Horizontal Menu</span> </a> </li>
            <li> <a href="#"> <span class="title">weidea.net</span> </a> </li>
          </ul>
        </li>
        <li> <a href="ui-panels.html"> <i class="linecons-note"></i> <span class="title">UI Elements</span> </a>
          <ul>
            <li> <a href="ui-panels.html"> <span class="title">Panels</span> </a> </li>
            <li> <a href="ui-buttons.html"> <span class="title">Buttons</span> </a> </li>
            <li> <a href="ui-tabs-accordions.html"> <span class="title">Tabs &amp; Accordions</span> </a> </li>
            <li> <a href="ui-modals.html"> <span class="title">Modals</span> </a> </li>
            <li> <a href="ui-breadcrumbs.html"> <span class="title">Breadcrumbs</span> </a> </li>
            <li> <a href="ui-blockquotes.html"> <span class="title">Blockquotes</span> </a> </li>
            <li> <a href="ui-progressbars.html"> <span class="title">Progress Bars</span> </a> </li>
            <li> <a href="ui-navbars.html"> <span class="title">Navbars</span> </a> </li>
            <li> <a href="ui-alerts.html"> <span class="title">Alerts</span> </a> </li>
            <li> <a href="ui-pagination.html"> <span class="title">Pagination</span> </a> </li>
            <li> <a href="ui-typography.html"> <span class="title">Typography</span> </a> </li>
            <li> <a href="ui-other-elements.html"> <span class="title">Other Elements</span> </a> </li>
          </ul>
        </li>
        <li> <a href="ui-widgets.html"> <i class="linecons-star"></i> <span class="title">Widgets</span> </a> </li>
        <li> <a href="mailbox-main.html"> <i class="linecons-mail"></i> <span class="title">Mailbox</span> <span class="label label-success pull-right">5</span> </a>
          <ul>
            <li> <a href="mailbox-main.html"> <span class="title">Inbox</span> </a> </li>
            <li> <a href="mailbox-compose.html"> <span class="title">Compose Message</span> </a> </li>
            <li> <a href="mailbox-message.html"> <span class="title">View Message</span> </a> </li>
          </ul>
        </li>
        <li> <a href="tables-basic.html"> <i class="linecons-database"></i> <span class="title">Tables</span> </a>
          <ul>
            <li> <a href="tables-basic.html"> <span class="title">Basic Tables</span> </a> </li>
            <li> <a href="tables-responsive.html"> <span class="title">Responsive Table</span> </a> </li>
            <li> <a href="tables-datatables.html"> <span class="title">Data Tables</span> </a> </li>
          </ul>
        </li>
        <li> <a href="forms-native.html"> <i class="linecons-params"></i> <span class="title">Forms</span> </a>
          <ul>
            <li> <a href="forms-native.html"> <span class="title">Native Elements</span> </a> </li>
            <li> <a href="forms-advanced.html"> <span class="title">Advanced Plugins</span> </a> </li>
            <li> <a href="forms-wizard.html"> <span class="title">Form Wizard</span> </a> </li>
            <li> <a href="forms-validation.html"> <span class="title">Form Validation</span> </a> </li>
            <li> <a href="forms-input-masks.html"> <span class="title">Input Masks</span> </a> </li>
            <li> <a href="forms-file-upload.html"> <span class="title">File Upload</span> </a> </li>
            <li> <a href="forms-editors.html"> <span class="title">Editors</span> </a> </li>
            <li> <a href="forms-sliders.html"> <span class="title">Sliders</span> </a> </li>
          </ul>
        </li>
        <li> <a href="extra-gallery.html"> <i class="linecons-beaker"></i> <span class="title">Extra</span> <span class="label label-purple pull-right hidden-collapsed">New Items</span> </a>
          <ul>
            <li> <a href="extra-icons-fontawesome.html"> <span class="title">Icons</span> <span class="label label-warning pull-right">4</span> </a>
              <ul>
                <li> <a href="extra-icons-fontawesome.html"> <span class="title">Font Awesome</span> </a> </li>
                <li> <a href="extra-icons-linecons.html"> <span class="title">Linecons</span> </a> </li>
                <li> <a href="extra-icons-elusive.html"> <span class="title">Elusive</span> </a> </li>
                <li> <a href="extra-icons-meteocons.html"> <span class="title">Meteocons</span> </a> </li>
              </ul>
            </li>
            <li> <a href="extra-maps-google.html"> <span class="title">Maps</span> </a>
              <ul>
                <li> <a href="extra-maps-google.html"> <span class="title">Google Maps</span> </a> </li>
                <li> <a href="extra-maps-advanced.html"> <span class="title">Advanced Map</span> </a> </li>
                <li> <a href="extra-maps-vector.html"> <span class="title">Vector Map</span> </a> </li>
              </ul>
            </li>
            <li> <a href="extra-gallery.html"> <span class="title">Gallery</span> </a> </li>
            <li> <a href="extra-calendar.html"> <span class="title">Calendar</span> </a> </li>
            <li> <a href="extra-profile.html"> <span class="title">Profile</span> </a> </li>
            <li> <a href="extra-login.html"> <span class="title">Login</span> </a> </li>
            <li> <a href="extra-lockscreen.html"> <span class="title">Lockscreen</span> </a> </li>
            <li> <a href="extra-login-light.html"> <span class="title">Login Light</span> </a> </li>
            <li> <a href="extra-timeline.html"> <span class="title">Timeline</span> </a> </li>
            <li> <a href="extra-timeline-center.html"> <span class="title">Timeline Centerd</span> </a> </li>
            <li> <a href="extra-notes.html"> <span class="title">Notes</span> </a> </li>
            <li> <a href="extra-image-crop.html"> <span class="title">Image Crop</span> </a> </li>
            <li> <a href="extra-portlets.html"> <span class="title">Portlets</span> </a> </li>
            <li> <a href="blank-sidebar.html"> <span class="title">Blank Page</span> </a> </li>
            <li> <a href="extra-search.html"> <span class="title">Search</span> </a> </li>
            <li> <a href="extra-invoice.html"> <span class="title">Invoice</span> </a> </li>
            <li> <a href="extra-not-found.html"> <span class="title">404 Page</span> </a> </li>
            <li> <a href="extra-tocify.html"> <span class="title">Tocify</span> </a> </li>
            <li> <a href="extra-loader.html"> <span class="title">Loading Progress</span> </a> </li>
            <li> <a href="extra-page-loading-ol.html"> <span class="title">Page Loading Overlay</span> </a> </li>
            <li> <a href="extra-notifications.html"> <span class="title">Notifications</span> </a> </li>
            <li> <a href="extra-nestable-lists.html"> <span class="title">Nestable Lists</span> </a> </li>
            <li> <a href="extra-scrollable.html"> <span class="title">Scrollable</span> </a> </li>
          </ul>
        </li>
        <li> <a href="charts-main.html"> <i class="linecons-globe"></i> <span class="title">Charts</span> </a>
          <ul>
            <li> <a href="charts-main.html"> <span class="title">Chart Variants</span> </a> </li>
            <li> <a href="charts-range.html"> <span class="title">Range Selector</span> </a> </li>
            <li> <a href="charts-sparklines.html"> <span class="title">Sparklines</span> </a> </li>
            <li> <a href="charts-map.html"> <span class="title">Map Charts</span> </a> </li>
            <li> <a href="charts-gauges.html"> <span class="title">Circular Gauges</span> </a> </li>
            <li> <a href="charts-bar-gauges.html"> <span class="title">Bar Gauges</span> </a> </li>
          </ul>
        </li>
        <li> <a href="#"> <i class="linecons-cloud"></i> <span class="title">Menu Levels</span> </a>
          <ul>
            <li> <a href="#"> <i class="entypo-flow-line"></i> <span class="title">Menu Level 1.1</span> </a>
              <ul>
                <li> <a href="#"> <i class="entypo-flow-parallel"></i> <span class="title">Menu Level 2.1</span> </a> </li>
                <li> <a href="#"> <i class="entypo-flow-parallel"></i> <span class="title">Menu Level 2.2</span> </a>
                  <ul>
                    <li> <a href="#"> <i class="entypo-flow-cascade"></i> <span class="title">Menu Level 3.1</span> </a> </li>
                    <li> <a href="#"> <i class="entypo-flow-cascade"></i> <span class="title">Menu Level 3.2</span> </a>
                      <ul>
                        <li> <a href="#"> <i class="entypo-flow-branch"></i> <span class="title">Menu Level 4.1</span> </a> </li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li> <a href="#"> <i class="entypo-flow-parallel"></i> <span class="title">Menu Level 2.3</span> </a> </li>
              </ul>
            </li>
            <li> <a href="#"> <i class="entypo-flow-line"></i> <span class="title">Menu Level 1.2</span> </a> </li>
            <li> <a href="#"> <i class="entypo-flow-line"></i> <span class="title">Menu Level 1.3</span> </a> </li>
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
        <li class="dropdown user-profile"> <a href="#" data-toggle="dropdown"> <img src="assets/images/user-4.jpg" alt="user-image" class="img-circle img-inline userpic-32" width="28" /> <span>ruanzhijun<i class="fa-angle-down"></i> </span> </a>
          <ul class="dropdown-menu user-profile-menu list-unstyled">
            <li><a href="#"><i class="fa-wrench"></i>设置</a></li>
            <li class="last"><a href="#"><i class="fa-lock"></i>&nbsp;退出</a></li>
          </ul>
        </li>
      </ul>
    </nav>
    <script type="text/javascript">
				jQuery(document).ready(function($)
				{	
				
					
					// Charts
					var xenonPalette = ['#68b828','#7c38bc','#0e62c7','#fcd036','#4fcdfc','#00b19d','#ff6264','#f7aa47'];
					
					// Pageviews Visitors Chart
					var i = 0,
						line_chart_data_source = [
						{ id: ++i, part1: 4, part2: 2 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 4, part2: 2 },
						{ id: ++i, part1: 3, part2: 1 },
						{ id: ++i, part1: 3, part2: 2 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 7, part2: 4 },
						{ id: ++i, part1: 9, part2: 5 },
						{ id: ++i, part1: 7, part2: 4 },
						{ id: ++i, part1: 7, part2: 3 },
						{ id: ++i, part1: 11, part2: 6 },
						{ id: ++i, part1: 10, part2: 8 },
						{ id: ++i, part1: 9, part2: 7 },
						{ id: ++i, part1: 8, part2: 7 },
						{ id: ++i, part1: 8, part2: 7 },
						{ id: ++i, part1: 8, part2: 7 },
						{ id: ++i, part1: 8, part2: 6 },
						{ id: ++i, part1: 15, part2: 5 },
						{ id: ++i, part1: 10, part2: 5 },
						{ id: ++i, part1: 9, part2: 6 },
						{ id: ++i, part1: 9, part2: 3 },
						{ id: ++i, part1: 8, part2: 5 },
						{ id: ++i, part1: 8, part2: 4 },
						{ id: ++i, part1: 9, part2: 5 },
						{ id: ++i, part1: 8, part2: 6 },
						{ id: ++i, part1: 8, part2: 5 },
						{ id: ++i, part1: 7, part2: 6 },
						{ id: ++i, part1: 7, part2: 5 },
						{ id: ++i, part1: 6, part2: 5 },
						{ id: ++i, part1: 7, part2: 6 },
						{ id: ++i, part1: 7, part2: 5 },
						{ id: ++i, part1: 8, part2: 5 },
						{ id: ++i, part1: 6, part2: 5 },
						{ id: ++i, part1: 5, part2: 4 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 6, part2: 3 },
					];
					
					$("#pageviews-visitors-chart").dxChart({
						dataSource: line_chart_data_source,
						commonSeriesSettings: {
							argumentField: "id",
							point: { visible: true, size: 5, hoverStyle: {size: 7, border: 0, color: 'inherit'} },
							line: {width: 1, hoverStyle: {width: 1}}
						},
						series: [
							{ valueField: "part1", name: "Pageviews", color: "#68b828" },
							{ valueField: "part2", name: "Visitors", color: "#eeeeee" },
						],
						legend: {
							position: 'inside',
							paddingLeftRight: 5
						},
						commonAxisSettings: {
							label: {
								visible: false
							},
							grid: {
								visible: true,
								color: '#f9f9f9'
							}
						},
						valueAxis: {
							max: 25
						},
						argumentAxis: {
					        valueMarginsEnabled: false
					    },
					});
					
					
					
					// Server Uptime Chart
					var bar1_data_source = [
						{ year: 1, 	europe: 10, americas: 0, africa: 5 },
						{ year: 2, 	europe: 20, americas: 5, africa: 15 },
						{ year: 3, 	europe: 30, americas: 10, africa: 15 },
						{ year: 4, 	europe: 40, americas: 15, africa: 30 },
						{ year: 5, 	europe: 30, americas: 10, africa: 20 },
						{ year: 6, 	europe: 20, americas: 5,  africa: 10 },
						{ year: 7, 	europe: 10, americas: 15, africa: 0 },
						{ year: 8, 	europe: 20, americas: 25, africa: 8 },
						{ year: 9, 	europe: 30, americas: 35, africa: 16 },
						{ year: 10,	europe: 40, americas: 45, africa: 24 },
						{ year: 11,	europe: 50, americas: 40, africa: 32 },
					];
					
					$("#server-uptime-chart").dxChart({
						dataSource: [
							{id: ++i, 	sales: 1},
							{id: ++i, 	sales: 2},
							{id: ++i, 	sales: 3},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 6},
							{id: ++i, 	sales: 7},
							{id: ++i, 	sales: 6},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 3},
							{id: ++i, 	sales: 4},
						],
					 
						series: {
							argumentField: "id",
							valueField: "sales",
							name: "Sales",
							type: "bar",
							color: '#7c38bc'
						},
						commonAxisSettings: {
							label: {
								visible: false
							},
							grid: {
								visible: false
							}
						},
						legend: {
							visible: false
						},
						argumentAxis: {
					        valueMarginsEnabled: true
					    },
						valueAxis: {
							max: 12
						},
						equalBarWidth: {
							width: 11
						}
					});
					
					
					
					// Average Sales Chart
					var doughnut1_data_source = [
						{region: "Asia", val: 4119626293},
						{region: "Africa", val: 1012956064},
						{region: "Northern America", val: 344124520},
						{region: "Latin America and the Caribbean", val: 590946440},
						{region: "Europe", val: 727082222},
						{region: "Oceania", val: 35104756},
						{region: "Oceania 1", val: 727082222},
						{region: "Oceania 3", val: 727082222},
						{region: "Oceania 4", val: 727082222},
						{region: "Oceania 5", val: 727082222},
					], timer;
					
					$("#sales-avg-chart div").dxPieChart({
						dataSource: doughnut1_data_source,
						tooltip: {
							enabled: false,
						  	format:"millions",
							customizeText: function() { 
								return this.argumentText + "<br/>" + this.valueText;
							}
						},
						size: {
							height: 90
						},
						legend: {
							visible: false
						},  
						series: [{
							type: "doughnut",
							argumentField: "region"
						}],
						palette: ['#5e9b4c', '#6ca959', '#b9f5a6'],
					});
					
					
					
					// Pageview Stats
					$('#pageviews-stats').dxBarGauge({
						startValue: -50,
						endValue: 50,
						baseValue: 0,
						values: [-21.3, 14.8, -30.9, 45.2],
						label: {
							customizeText: function (arg) {
								return arg.valueText + '%';
							}
						},
						//palette: 'ocean',
						palette: ['#68b828','#7c38bc','#0e62c7','#fcd036','#4fcdfc','#00b19d','#ff6264','#f7aa47'],
						margin : {
							top: 0
						}
					});
					
					var firstMonth = {
						dataSource: getFirstMonthViews(),
						argumentField: 'month',
						valueField: '2014',
						type: 'area',
						showMinMax: true,
						lineColor: '#68b828',
						minColor: '#68b828',
						maxColor: '#7c38bc',
						showFirstLast: false,
					},
					secondMonth = {
						dataSource: getSecondMonthViews(),
						argumentField: 'month',
						valueField: '2014',
						type: 'splinearea',
						lineColor: '#68b828',
						minColor: '#68b828',
						maxColor: '#7c38bc',
						pointSize: 6,
						showMinMax: true,
						showFirstLast: false
					},
					thirdMonth = {
						dataSource: getThirdMonthViews(),
						argumentField: 'month',
						valueField: '2014',
						type: 'splinearea',
						lineColor: '#68b828',
						minColor: '#68b828',
						maxColor: '#7c38bc',
						pointSize: 6,
						showMinMax: true,
						showFirstLast: false
					};
					
					function getFirstMonthViews() {
						return [{ month: 1, 2014: 7341 },
						{ month: 2, 2014: 7016 },
						{ month: 3, 2014: 7202 },
						{ month: 4, 2014: 7851 },
						{ month: 5, 2014: 7481 },
						{ month: 6, 2014: 6532 },
						{ month: 7, 2014: 6498 },
						{ month: 8, 2014: 7191 },
						{ month: 9, 2014: 7596 },
						{ month: 10, 2014: 8057 },
						{ month: 11, 2014: 8373 },
						{ month: 12, 2014: 8636 }];
					};
					
					function getSecondMonthViews() {
						return [{ month: 1, 2014: 189742 },
						{ month: 2, 2014: 181623 },
						{ month: 3, 2014: 205351 },
						{ month: 4, 2014: 245625 },
						{ month: 5, 2014: 261319 },
						{ month: 6, 2014: 192786 },
						{ month: 7, 2014: 194752 },
						{ month: 8, 2014: 207017 },
						{ month: 9, 2014: 212665 },
						{ month: 10, 2014: 233580 },
						{ month: 11, 2014: 231503 },
						{ month: 12, 2014: 232824 }];
					};
					
					function getThirdMonthViews() {
						return [{ month: 1, 2014: 398},
						{ month: 2, 2014: 422},
						{ month: 3, 2014: 431},
						{ month: 4, 2014: 481},
						{ month: 5, 2014: 551},
						{ month: 6, 2014: 449},
						{ month: 7, 2014: 442},
						{ month: 8, 2014: 482},
						{ month: 9, 2014: 517},
						{ month: 10, 2014: 566},
						{ month: 11, 2014: 630},
						{ month: 12, 2014: 737}];
					};
					
					
					$('.first-month').dxSparkline(firstMonth);
					$('.second-month').dxSparkline(secondMonth);
					$('.third-month').dxSparkline(thirdMonth);
					
					
					// Realtime Network Stats
					var i = 0,
						rns_values = [130,150],
						rns2_values = [39,50],
						realtime_network_stats = [];
					
					for(i=0; i<=100; i++)
					{
						realtime_network_stats.push({ id: i, x1: between(rns_values[0], rns_values[1]), x2: between(rns2_values[0], rns2_values[1])});
					}
					
					$("#realtime-network-stats").dxChart({
						dataSource: realtime_network_stats,
						commonSeriesSettings: {
							type: "area",
							argumentField: "id"
						},
						series: [
							{ valueField: "x1", name: "Packets Sent", color: '#7c38bc', opacity: .4 },
							{ valueField: "x2", name: "Packets Received", color: '#000', opacity: .5},
						],
						legend: {
							verticalAlignment: "bottom",
							horizontalAlignment: "center"
						},
						commonAxisSettings: {
							label: {
								visible: false
							},
							grid: {
								visible: true,
								color: '#f5f5f5'
							}
						},
						legend: {
							visible: false
						},
						argumentAxis: {
					        valueMarginsEnabled: false
					    },
						valueAxis: {
							max: 500
						},
						animation: {
							enabled: false
						}
					}).data('iCount', i);
					
					$('#network-realtime-gauge').dxCircularGauge({
						scale: {
							startValue: 0,
							endValue: 200,
							majorTick: {
								tickInterval: 50
							}
						},
						rangeContainer: {
							palette: 'pastel',
							width: 3,
							ranges: [
								{ startValue: 0, endValue: 50, color: "#7c38bc" },
								{ startValue: 50, endValue: 100, color: "#7c38bc" },
								{ startValue: 100, endValue: 150, color: "#7c38bc" },
								{ startValue: 150, endValue: 200, color: "#7c38bc" },
							],
						},
						value: 140,
						valueIndicator: {
							offset: 10,
							color: '#7c38bc',
							type: 'triangleNeedle',
							spindleSize: 12
						}
					});
					
					setInterval(function(){  networkRealtimeChartTick(rns_values, rns2_values); }, 1000);
					setInterval(function(){ networkRealtimeGaugeTick(); }, 2000);
					setInterval(function(){ networkRealtimeMBupdate(); }, 3000);
					
					
					
					// CPU Usage Gauge
					$("#cpu-usage-gauge").dxCircularGauge({
						scale: {
							startValue: 0,
							endValue: 100,
							majorTick: {
								tickInterval: 25
							}
						},
						rangeContainer: {
							palette: 'pastel',
							width: 3,
							ranges: [
								{ startValue: 0, endValue: 25, color: "#68b828" },
								{ startValue: 25, endValue: 50, color: "#68b828" },
								{ startValue: 50, endValue: 75, color: "#68b828" },
								{ startValue: 75, endValue: 100, color: "#d5080f" },
							],
						},
						value: between(30, 90),
						valueIndicator: {
							offset: 10,
							color: '#68b828',
							type: 'rectangleNeedle',
							spindleSize: 12
						}
					});
					
					
					// Resize charts
					$(window).on('xenon.resize', function()
					{
						$("#pageviews-visitors-chart").data("dxChart").render();
						$("#server-uptime-chart").data("dxChart").render();
						$("#realtime-network-stats").data("dxChart").render();
						
						$('.first-month').data("dxSparkline").render();
						$('.second-month').data("dxSparkline").render();
						$('.third-month').data("dxSparkline").render();
					});
					
				});
				
				function networkRealtimeChartTick(min_max, min_max2)
				{
					var $ = jQuery,
						i = 0;
					
					var chart_data = $('#realtime-network-stats').dxChart('instance').option('dataSource');
					
					var count = $('#realtime-network-stats').data('iCount');
					
					$('#realtime-network-stats').data('iCount', count + 1);
					
					chart_data.shift();
					chart_data.push({id: count + 1, x1: between(min_max[0],min_max[1]), x2: between(min_max2[0],min_max2[1])});
					
					$('#realtime-network-stats').dxChart('instance').option('dataSource', chart_data);
				}
				
				function networkRealtimeGaugeTick()
				{
					var nr_gauge = jQuery('#network-realtime-gauge').dxCircularGauge('instance');
					
					nr_gauge.value( between(50,200) );
				}
				
				function networkRealtimeMBupdate()
				{
					var $el = jQuery("#network-mbs-packets"),
						options = {
							useEasing : true, 
							useGrouping : true, 
							separator : ',', 
							decimal : '.', 
							prefix : '' ,
							suffix : 'mb/s' 
						},
						cntr = new countUp($el[0], parseFloat($el.text().replace('mb/s')), parseFloat(between(10,25) + 1/between(15,30)), 2, 1.5, options);
						
					cntr.start();
				}
				
				function between(randNumMin, randNumMax)
				{
					var randInt = Math.floor((Math.random() * ((randNumMax + 1) - randNumMin)) + randNumMin);
					
					return randInt;
				}
			</script>
    <div class="row">
      <div class="col-sm-3">
        <div class="xe-widget xe-counter" data-count=".num" data-from="0" data-to="99.9" data-suffix="%" data-duration="2">
          <div class="xe-icon"> <i class="linecons-cloud"></i> </div>
          <div class="xe-label"> <strong class="num">0.0%</strong> <span>Server uptime</span> </div>
        </div>
        <div class="xe-widget xe-counter xe-counter-purple" data-count=".num" data-from="1" data-to="117" data-suffix="k" data-duration="3" data-easing="false">
          <div class="xe-icon"> <i class="linecons-user"></i> </div>
          <div class="xe-label"> <strong class="num">1k</strong> <span>Users Total</span> </div>
        </div>
        <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="1000" data-to="2470" data-duration="4" data-easing="true">
          <div class="xe-icon"> <i class="linecons-camera"></i> </div>
          <div class="xe-label"> <strong class="num">1000</strong> <span>New Daily Photos</span> </div>
        </div>
      </div>
      <div class="col-sm-6">
        <div class="chart-item-bg">
          <div class="chart-label">
            <div class="h3 text-secondary text-bold" data-count="this" data-from="0.00" data-to="14.85" data-suffix="%" data-duration="1">0.00%</div>
            <span class="text-medium text-muted">More visitors</span> </div>
          <div id="pageviews-visitors-chart" style="height: 298px;"></div>
        </div>
      </div>
      <div class="col-sm-3">
        <div class="chart-item-bg">
          <div class="chart-label chart-label-small">
            <div class="h4 text-purple text-bold" data-count="this" data-from="0.00" data-to="95.8" data-suffix="%" data-duration="1.5">0.00%</div>
            <span class="text-small text-upper text-muted">Current Server Uptime</span> </div>
          <div id="server-uptime-chart" style="height: 134px;"></div>
        </div>
        <div class="chart-item-bg">
          <div class="chart-label chart-label-small">
            <div class="h4 text-secondary text-bold" data-count="this" data-from="0.00" data-to="320.45" data-decimal="," data-duration="2">0</div>
            <span class="text-small text-upper text-muted">Avg. of Sales</span> </div>
          <div id="sales-avg-chart" style="height: 134px; position: relative;">
            <div style="position: absolute; top: 25px; right: 0; left: 40%; bottom: 0"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-6">
        <div class="chart-item-bg">
          <div id="pageviews-stats" style="height: 320px; padding: 20px 0;"></div>
          <div class="chart-entry-view">
            <div class="chart-entry-label"> Pageviews </div>
            <div class="chart-entry-value">
              <div class="sparkline first-month"></div>
            </div>
          </div>
          <div class="chart-entry-view">
            <div class="chart-entry-label"> Visitors </div>
            <div class="chart-entry-value">
              <div class="sparkline second-month"></div>
            </div>
          </div>
          <div class="chart-entry-view">
            <div class="chart-entry-label"> Converted Sales </div>
            <div class="chart-entry-value">
              <div class="sparkline third-month"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-6">
        <div class="chart-item-bg">
          <div class="chart-label">
            <div id="network-mbs-packets" class="h1 text-purple text-bold" data-count="this" data-from="0.00" data-to="21.05" data-suffix="mb/s" data-duration="1">0.00mb/s</div>
            <span class="text-small text-muted text-upper">Download Speed</span> </div>
          <div class="chart-right-legend">
            <div id="network-realtime-gauge" style="width: 170px; height: 140px"></div>
          </div>
          <div id="realtime-network-stats" style="height: 320px"></div>
        </div>
        <div class="chart-item-bg">
          <div class="chart-label">
            <div id="network-mbs-packets" class="h1 text-secondary text-bold" data-count="this" data-from="0.00" data-to="67.35" data-suffix="%" data-duration="1.5">0.00%</div>
            <span class="text-small text-muted text-upper">CPU Usage</span>
            <p class="text-medium" style="width: 50%; margin-top: 10px">Sentiments two occasional affronting solicitude travelling and one contrasted. Fortune day out married parties.</p>
          </div>
          <div id="other-stats" style="min-height: 183px">
            <div id="cpu-usage-gauge" style="width: 170px; height: 140px; position: absolute; right: 20px; top: 20px"></div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Main Footer --> 
    <!-- Choose between footer styles: "footer-type-1" or "footer-type-2" --> 
    <!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) --> 
    <!-- Or class "fixed" to  always fix the footer to the end of page -->
    <footer class="main-footer sticky footer-type-1">
      <div class="footer-inner"> 
        
        <!-- Add your copyright text here -->
        <div class="footer-text"> &copy; 2014 <strong>Xenon</strong> More Templates <a href="http://www.uedsc.com" target="_blank" title="问说网">uedsc</a> </div>
        
        <!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
        <div class="go-up"> <a href="#" rel="go-top"> <i class="fa-angle-up"></i> </a> </div>
      </div>
    </footer>
  </div>
  
  
</div>

<?php require view::dir().'foot.php';?>
