<?php include 'header.php';?>
<!--网站主体-->
<div class="body">
  <div class="main">
    <div class="result"></div>
    <div class="left">
      <div class="resources" id="api">
        <div class="title_div"><span class="title">API介绍</span></div>
        <table>
          <tr>
            <td class="color">参数列表</td>
          </tr>
          <tr>
            <td><table cellpadding="0" cellspacing="0">
                <tr>
                  <td><strong>字段</strong></td>
                  <td><strong>注释</strong></td>
                </tr>
                <tr>
                  <td>en_name</td>
                  <td>频道英文名（做电台的时候用作参数传递）</td>
                </tr>
                <tr>
                  <td>name</td>
                  <td>频道名称</td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td class="color">XML格式</td>
          </tr>
          <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/xml/channel.php" target="_blank" title="http://lovemusic.cc/app/xml/radio.php">http://lovemusic.cc/app/xml/channel.php</a></td>
          </tr>
          <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
&lt;list&gt;
  &lt;channel en_name="ktv"&gt;K歌最爱&lt;/channel&gt;
  &lt;channel en_name="new"&gt;最新歌曲&lt;/channel&gt; 
  &lt;channel en_name="old"&gt;经典老歌&lt;/channel&gt; 
  &lt;channel en_name="less"&gt;小众频道&lt;/channel&gt; 
  &lt;channel en_name="fskl"&gt;分手快乐&lt;/channel&gt; 
  &lt;channel en_name="yaogun"&gt;摇摇滚滚&lt;/channel&gt; 
  &lt;channel en_name="SQ"&gt;神曲惊魂&lt;/channel&gt; 
&lt;/list&gt;
</pre></td>
          </tr>
          <tr>
            <td class="color">JSON格式</td>
          </tr>
          <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/json/channel.php" target="_blank" title="http://lovemusic.cc/app/json/radio.php">http://lovemusic.cc/app/json/channel.php</a></td>
          </tr>
          <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
[
  {"name":"K歌最爱","en_name":"ktv"},
  {"name":"最新歌曲","en_name":"new"},
  {"name":"经典老歌","en_name":"old"},
  {"name":"小众频道","en_name":"less"},
  {"name":"分手快乐","en_name":"fskl"},
  {"name":"摇摇滚滚","en_name":"yaogun"},
  {"name":"神曲惊魂","en_name":"SQ"}
]
            </pre></td>
          </tr>
        </table>
      </div>
    </div>
    <div class="right">
      <div class="sort" id="api_catalog">
        <div class="title_div"><span class="title">API目录</span></div>
        <ul>
          <?php include 'left.php';?>
        </ul>
      </div>
    </div>
<?php include str_replace('app','',dirname(__FILE__)).'templates/template/deafault/foot.html';?>