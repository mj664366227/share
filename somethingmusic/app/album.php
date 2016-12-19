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
                  <td>id</td>
                  <td>当前专辑的id号</td>
                </tr>
                <tr>
                  <td>category</td>
                  <td>当前专辑的风格</td>
                </tr>
                <tr>
                  <td>title</td>
                  <td>当前专辑的名称</td>
                </tr>
                <tr>
                  <td>artist</td>
                  <td>当前专辑的歌手</td>
                </tr>
                <tr>
                  <td>time</td>
                  <td>当前专辑的发行时间</td>
                </tr>
                <tr>
                  <td>company</td>
                  <td>唱片公司</td>
                </tr>
                <tr>
                  <td>area</td>
                  <td>来源地区</td>
                </tr>
                <tr>
                  <td>language</td>
                  <td>歌曲语种</td>
                </tr>
                <tr>
                  <td>text</td>
                  <td>专辑介绍</td>
                </tr>
                <tr>
                  <td>hit</td>
                  <td>专辑点击率</td>
                </tr>
                <tr>
                  <td>picture</td>
                  <td>专辑封面地址</td>
                </tr>
                <tr>
                  <td>author</td>
                  <td>添加者</td>
                </tr>
                <tr>
                  <td>url</td>
                  <td>歌曲地址</td>
                </tr>
                <tr>
                  <td>pid</td>
                  <td>歌曲所在专辑的序号</td>
                </tr>
                <tr>
                  <td>have_played</td>
                  <td>试听次数</td>
                </tr>
                <tr>
                  <td>download</td>
                  <td>下载次数</td>
                </tr>
                <tr>
                  <td>title</td>
                  <td>歌曲名字（和上面的不一样）</td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td class="color">XML格式</td>
          </tr>
          <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/xml/album.php?id=30" target="_blank" title="http://lovemusic.cc/app/xml/album.php?id=30">http://lovemusic.cc/app/xml/album.php?id=30</a>（id是专辑id号，必选！）</td>
          </tr>
          <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
&lt;album&gt;
  &lt;detail&gt;
    &lt;category&gt;电影原声&lt;/category&gt;
    &lt;title&gt;I wanna be 麦晓岚&lt;/title&gt;
    &lt;artist&gt;合辑&lt;/artist&gt;
    &lt;time&gt;2010-06-17&lt;/time&gt;
    &lt;company&gt;Sui Seng&lt;/company&gt;
    &lt;area&gt;港台&lt;/area&gt;
    &lt;language&gt;国语 英语&lt;/language&gt;
    &lt;picture&gt;http://lovemusic.cc/thumbs/thumbs/cache/w190h190/admin/upload/
    ddafe523d73fc3d99ced670043efabae_33436584.jpeg
    &lt;/picture&gt;
    &lt;text&gt;Joe为了跟闹分手的女友Flora复合,透过神秘网站Break Up Club拆散其他情
    侣达到目的。他还把自己的故事以偷拍形式交给黄真真导演,继续无赖谈情说爱。
    Flora与型人画家打得火热,Joe方知她是不可取替。戏中戏的设定,镜头内有镜头的
    摆位,黄真真走出后座,瞄准了新世纪男女脆弱的亲密关系,徘徊爱与痛的边缘,是他
    也是你和我;薛凯琪与房祖名演技蜕变后再度甜蜜蜜合作,擦出无穷火花
    &lt;/text&gt;
  &lt;/detail&gt;
  &lt;songlist&gt;
    &lt;song pid="1" url="http://myurl.com/mp3.mp3" have_played="5" download="0"&gt;
    I wanna be-麦晓岚&lt;/song&gt;
    &lt;song pid="2" url="http://myurl.com/mp3.mp3" have_played="5" download="0"&gt;
    Mr.Right-黄诗诗&lt;/song&gt;
    &lt;song pid="3" url="http://myurl.com/mp3.mp3" have_played="2" download="0"&gt;
    Heaven in the Moonlight-Tomos Griffiths&lt;/song&gt;
    &lt;song pid="4" url="http://myurl.com/mp3.mp3" have_played="3" download="0"&gt;
    You seem so far away-黄真真&lt;/song&gt;
    &lt;song pid="5" url="http://myurl.com/mp3.mp3" have_played="2" download="0"&gt;
    I am crazy for you-Jessica Garraway&lt;/song&gt;
    &lt;song pid="6" url="http://myurl.com/mp3.mp3" have_played="2" download="0"&gt;
    Our time is now-陈文婷&lt;/song&gt;
    &lt;song pid="7" url="http://myurl.com/mp3.mp3" have_played="2" download="0"&gt;
    Feelings on fire-陈文婷&lt;/song&gt;
    &lt;song pid="8" url="http://myurl.com/mp3.mp3" have_played="1" download="0"&gt;
    I think it's time-Tomos Griffiths&lt;/song&gt;
    &lt;song pid="9" url="http://myurl.com/mp3.mp3" have_played="1" download="0"&gt;
    I'm sorry-陈文婷&lt;/song&gt;
    &lt;song pid="10" url="http://myurl.com/mp3.mp3" have_played="1" download="0"&gt;
    手分手-黄诗诗&lt;/song&gt;
    &lt;song pid="11" url="http://myurl.com/mp3.mp3" have_played="0" download="0"&gt;
    Heaven in the Moonlight(DJ Mix)-Tomos Griffiths&lt;/song&gt;
  &lt;/songlist&gt;
&lt;/album&gt;</pre></td>
          </tr>
          <tr>
            <td class="color">JSON格式</td>
          </tr>
           <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/json/album.php?id=30" target="_blank" title="http://lovemusic.cc/app/json/album.php?id=30">http://lovemusic.cc/app/json/album.php?id=30</a>（id是专辑id号，必选！）</td>
          </tr> <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
{
  "id":"30",
  "category":"电影原声",
  "title":"分手说爱你(电影原声大碟)",
  "artist":"合辑",
  "time":"2010-06-17",
  "company":"Sui Seng",
  "area":"港台",
  "language":"国语 英语  ",
  "text":"Joe为了跟闹分手的女友Flora复合,透过神秘网站BreakUpClub拆散其他情侣达
  到目的。他还把自己的故事以偷拍形式交给黄真真导演,继续无赖谈情说爱。Flora与型
  人画家打得火热,Joe方知她是不可取替。戏中戏的设定,镜头内有镜头的摆位,黄真真走
  出后座,瞄准了新世纪男女脆弱的亲密关系,徘徊爱与痛的边缘,是他也是你和我;薛凯琪
  与房祖名演技蜕变后再度甜蜜蜜合作,擦出无穷火花",
  "hit":"23",
  "picture":"http://lovemusic.cc/thumbs/thumbs/cache/w190h190/admin/upload/
  ddafe523d73fc3d99ced670043efabae_33436584.jpeg",
}

/*曲目列表*/
[
  {
     "artist":"合辑",
     "album":"分手说爱你(电影原声大碟)",
     "title":"Heaven in the Moonlight(DJ Mix)-Tomos Griffiths",
     "url":"http://myurl.com/mp3.mp3",
     "category":"电影原声",
     "pid":"11",
     "have_played":"0",
     "download":"0"
  },
  {
      "artist":"合辑",
      "album":"分手说爱你(电影原声大碟)",
      "title":"I'm sorry-陈文婷",
      "url":"http://myurl.com/mp3.mp3",
      "category":"电影原声",
      "pid":"9",
      "have_played":"1",
      "download":"0"
  }
..................
]
            </pre></td></tr>
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