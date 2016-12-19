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
                  <td>albumid</td>
                  <td>歌曲所属专辑的id号</td>
                </tr>
                <tr>
                  <td>picture</td>
                  <td>专辑的封面</td>
                </tr>
                <tr>
                  <td>name</td>
                  <td>歌曲名字</td>
                </tr>
                <tr>
                  <td>artist</td>
                  <td>歌手</td>
                </tr>
                <tr>
                  <td>url</td>
                  <td>歌曲地址</td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td class="color">XML格式</td>
          </tr>
          <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/xml/radio.php?channel=ktv" target="_blank" title="http://lovemusic.cc/app/xml/radio.php?channel=ktv">http://lovemusic.cc/app/xml/radio.php?channel=ktv（频道名称）10条版</a></td>
          </tr>
          <tr>
            <td class="link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://lovemusic.cc/app/xml/radio1.php?channel=ktv" target="_blank" title="http://lovemusic.cc/app/xml/radio1.php?channel=ktv">http://lovemusic.cc/app/xml/radio1.php?channel=ktv（频道名称）1条版</a></td>
          </tr>
            <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
&lt;radio&gt;
 &lt;song&gt;
   &lt;albumid&gt;331&lt;/albumid&gt;
   &lt;picture&gt;http://localhost/thumbs/thumbs/cache/w190h190/
   admin/upload/9c7b2b5472d2396d9d98693590eaffe4_72252958.jpeg&lt;/picture&gt; 
   &lt;title&gt;Awesome As Fuck(Live)&lt;/title&gt; 
   &lt;artist&gt;Green Day&lt;/artist&gt;
   &lt;url&gt;http://myurl.com/mp3.mp3&lt;/url&gt;
 &lt;/song&gt;
   ....................
&lt;/radio&gt;
</pre></td>
          </tr>
          <tr>
            <td class="color">JSON格式</td>
          </tr>
          <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/json/radio.php?channel=ktv" target="_blank" title="http://lovemusic.cc/app/json/radio.php?channel=ktv">http://lovemusic.cc/app/json/radio.php?channel=ktv（频道名称）10条版</a></td>
          </tr>
           <tr>
            <td class="link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://lovemusic.cc/app/json/radio1.php?channel=ktv" target="_blank" title="http://lovemusic.cc/app/json/radio1.php?channel=ktv">http://lovemusic.cc/app/json/radio1.php?channel=ktv（频道名称）1条版</a></td>
          </tr>
          <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
[
  {"albumid":"321",
   "picture":"http://localhost/thumbs/thumbs/cache/w190h190/
   admin/upload/812bb027b086b2b8cb1342b844c1a78b_38305631.jpeg",
   "title":"T-time 新歌+精选CD1",
   "artist":"蔡健雅",
   "url":"http://myurl.com/mp3.mp3"
  },
  ....................
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