<?php require view::dir().'head.php';?>
<!--左边-->

<div id="left">
  <div class="card">
    <div class="title"> <span><?php echo MONITORING_CONTENT?></span></div>
    <div class="content"><!--这里的css写得有问题-->
      <ul>
        <li>
          <?php if($content=='memory'):?>
          <span style="color:#0F0">■</span>&nbsp;
          <?php endif?>
          <a href="<?php echo url('view', 'view', 'serverid='.$serverid.'&content=memory')?>" title="<?php echo MONITORING_CONTENT_MEMORY?>"><?php echo MONITORING_CONTENT_MEMORY?></a></li>
        <li>
          <?php if($content=='loadavg'):?>
          <span style="color:#0F0">■</span>&nbsp;
          <?php endif?>
          <a href="<?php echo url('view', 'view', 'serverid='.$serverid.'&content=loadavg')?>" title="<?php echo MONITORING_CONTENT_LOADAVG?>"><?php echo MONITORING_CONTENT_LOADAVG?></a></li>
      </ul>
    </div>
  </div>
</div>
<!--右边-->
<div id="right">
  <form action="<?php echo url('view', 'view', 'serverid='.$serverid.'&content=memory')?>" method="post">
    <table cellpadding="0"  cellspacing="0" border="0" width="100%">
      <tr>
        <td><ul id="serach_condition">
            <li><?php echo SERVER?>
              <select onchange="change_server(this.value)">
                <?php foreach($server_list as $server):?>
                <option value="<?php echo $server['id']?>"<?php if($serverid == $server['id']):?> selected="selected"<?php endif?>><?php echo $server['name']?></option>
                <?php endforeach?>
              </select>
            </li>
            <?php $date_arr = explode('-', $date);$date1 = date('Y-m-d',$date_arr[0]);$date2 = date('Y-m-d',$date_arr[1]);?>
            <li><a href="<?php $today = date('Y-m-d');echo url('view', 'view', 'serverid='.$serverid.'&content=memory&st='.$today.'&et='.$today)?>" <?php ;if($today===$date1 && $today === $date2 && $date1 === $date2 && $is_custom === 'false'):?>class="current"<?php endif?>><?php echo TODAY?></a></li>
            <li><a href="<?php $yesterday = $day_break - 86400;$date_ = date('Y-m-d', $yesterday);echo url('view', 'view', 'serverid='.$serverid.'&content=memory&st='.$date_.'&et='.$date_)?>" <?php if($yesterday == $date && $is_custom === 'false'):?>class="current"<?php endif?>><?php echo YESTERDAY?></a></li>
            <li><a href="<?php $days = 7; $that_day = date('Y-m-d', $day_break - 518400);echo url('view', 'view', 'serverid='.$serverid.'&content=memory&st='.$that_day.'&et='.$today)?>" <?php if($days === $diff && $is_custom === 'false'):?> class="current"<?php endif?>><?php echo sprintf(RECENT_X_DAY, $days)?></a></li>
            <li><a href="<?php $days = 15;$that_day = date('Y-m-d', $day_break - 1209600);echo url('view', 'view', 'serverid='.$serverid.'&content=memory&st='.$that_day.'&et='.$today)?>" <?php if($days === $diff && $is_custom === 'false'):?> class="current"<?php endif?>><?php echo sprintf(RECENT_X_DAY, $days)?></a></li>
            <li><a href="<?php $days = 30;$that_day = date('Y-m-d', $day_break - 2505600);echo url('view', 'view', 'serverid='.$serverid.'&content=memory&st='.$that_day.'&et='.$today)?>" <?php if($days === $diff && $is_custom === 'false'):?> class="current"<?php endif?>><?php echo sprintf(RECENT_X_DAY, $days)?></a></li>
            <li><a href="javascript:void(0)" onclick="show_custom()"<?php if($is_custom === 'true'):?> class="current"<?php endif?>><?php echo BY_CUSTOM?></a></li>
          </ul></td>
      </tr>
    </table>
    <div id="custom"> <?php echo TIME_RANGE?>
      <input type="text" class="text" onclick="rcalendar(this)" style="width:80px" name="custom1" value="<?php echo $custom1?>"/>
      -
      <input type="text" class="text" onclick="rcalendar(this)" style="width:80px" name="custom2" value="<?php echo $custom2?>"/>
      <input type="submit" class="btn" value="<?php echo SEARCH?>"/>
    </div>
  </form>
  <?php if($data):?>
  <script type="text/javascript">
//切换服务器
change_server = function(serverid){
	window.location.href = window.location.href.replace(/serverid=\d+/, 'serverid=' + serverid); 
}

//显示自定义筛选面板
var flag = <?php echo $is_custom?>, delay = 400;
if(flag){
	$('#custom').show(delay);
} else {
	$('#custom').hide(delay);
}
show_custom = function(){
	if(!flag){
		$('#custom').show(delay);
		flag = true;
	} else {
		$('#custom').hide(delay);
		flag = false;
	}
}

$(function () {
    $('#view').highcharts({
        chart: {
            zoomType: 'x'
        },
        title: {
            <?php if($st==$et):?>
				text: '<?php echo date('Y-m-d',$st).' '.$servers.VIEW_MEMORY_USAGE?>'
				<?php else:?>
				text: '<?php echo date('Y-m-d',$st).' '.TO.' '.date('Y-m-d',$et).' '.$servers.VIEW_MEMORY_USAGE?>'
				<?php endif?>
        },
        xAxis: {
            type: 'datetime',
            minRange: 86400000 // fourteen days
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },

        series: [{
            type: 'area',
            name: 'USD to EUR',
            pointInterval: 60 * 1000,
            pointStart: Date.UTC(2006, 0, 1),
            data: <?php echo json_encode($data)?>
        }]
    });
});

/*
$(function () {
    var chart;
	var r = 2;
    $(document).ready(function() {
		Highcharts.setOptions({  
        global: {  
            useUTC: false  
        }  
    });  
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'view',
                type: 'spline'
            },
            title: {
				<?php if($st==$et):?>
				text: '<?php echo date('Y-m-d',$st).' '.$servers.VIEW_MEMORY_USAGE?>'
				<?php else:?>
				text: '<?php echo date('Y-m-d',$st).' '.TO.' '.date('Y-m-d',$et).' '.$servers.VIEW_MEMORY_USAGE?>'
				<?php endif?>
            },
            xAxis: {
                type: 'datetime',
				maxZoom:  3600000,
        		max:<?php echo $et * 1000?>  ,
            },
            yAxis: {
                title: {
                    text: ''
                },
				labels: {
             		formatter: function() {
						return size(this.value, r);
					}
				},
				showFirstLabel: false
            },
            tooltip: {
                formatter: function() {
                     return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +' '+ size(this.y, r);
                }
            },
            plotOptions: {
                spline: {
                    lineWidth: 2,
                    states: {
                        hover: {
                            lineWidth: 2
                        }
                    },
                    marker: {
                        enabled: false,
                        states: {
                            hover: {
                                enabled: true,
                                symbol: 'circle',
                                radius: 5,
                                lineWidth: 1
                            }
                        }
                    },
                    pointInterval: 60 * 1000,
                	pointStart: <?php echo $st * 1000?>,
                }
            },
            series:<?php echo str_replace('}}','}]',str_replace('{{','[{',preg_replace('/\d+:{name/','{name',str_replace(',data:','\',data:',str_replace('{name:','{name:\'',str_replace('"','',urldecode(json_encode($data['data']))))))))?>,
            navigation: {
                menuItemStyle: {
                    fontSize: '10px'
                }
            }
        });
    }); 
});*/
</script>
  <div id="view" style="width: 100%; height: 400px; margin: 0 auto"></div>
  <?php endif?>
</div>
<?php require view::dir().'foot.php';?>
