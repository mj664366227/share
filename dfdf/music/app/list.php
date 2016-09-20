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
              </table></td>
          </tr>
          <tr>
            <td class="color">XML格式</td>
          </tr>
          <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/xml/list.php?page=1" target="_blank" title="http://lovemusic.cc/app/xml/list.php?page=1">http://lovemusic.cc/app/xml/list.php?page=1</a></td>
          </tr> <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
&lt;album&gt;
  &lt;id&gt;611&lt;/id&gt;
  &lt;category&gt;另类说唱&lt;/category&gt;
  &lt;title&gt;未知艺术家&lt;/title&gt;
  &lt;artist&gt;in3(阴三儿)&lt;/artist&gt;
  &lt;time&gt;2008-04-25&lt;/time&gt;
  &lt;company&gt;独立发行&lt;/company&gt;
  &lt;area&gt;大陆&lt;/area&gt;
  &lt;language&gt;国语&lt;/language&gt;
  &lt;text&gt;北京地下说唱，UNDERGROUND HIPHOP 又名: 阴三儿 YINSANER / IN 3 他们
  登上了美国西雅图杂志 http://seattletimes.nwsource.com/html/nati...p14.ht
  ml他们用北京俚语为自己冠以名号，他们以戏虐调侃的姿态记录生活。 陈昊然，贾
  伟，孟国栋，这三个表征迥异却同样乖张狂放的家伙，用自己的方式向所有人证明
  了北京地下HIP HOP正在以最真实的姿态活着！ MC BATTLE， OPEN MIC， IRON MIC，
  SECTION 6等众多与HIP HOP有关的活动赐予了他们最珍贵的东西。 他们学会了如何
  在舞台上找到自己，如何在所有人面前彻底释放，就像一场捍卫音乐的战争，这就
  是他们的生活，THE LIFE IS AT WAR 07年MIDI音乐节IN3专场，他们用属于自己的
  方式，源自生活的创作，在全国乐迷面前肆无忌惮地诠释了北京地下HIP HOP最狂躁
  的幽默。 当"NO MONEY NO FRIEND"变成了无数人的MSN签名，当"都得死"变成了大
  家的口头禅，当"北京欢迎你回来"走进了ADIDAS新装发布会现场，当"北京晚报"变
  成了身边朋友点击搜索的重点对象，当阴三儿与扭曲的机器一起叫嚣着"都别废话"，
  用最原始的声音将HIP HOP与摇滚乐融合，那些渴望真实，内心躁动着的人们开始逐
  一苏醒，因为阴三儿正在用自己的生活入侵着我们的生活。 没错，阴三儿用音乐向
  身边的"伪人"们动了粗，但请不要误会了北京地下音乐的态度。他们长得不漂亮，
  也不是变态杀人狂，
  &lt;/text&gt;
  &lt;hit&gt;89&lt;/hit&gt;
  &lt;picture&gt;http://lovemusic.cc/thumbs/thumbs/cache/w190h190/admin/upload/
  bce509ce0c660167ebab4f5e8ce8ae51_41654955.jpeg&lt;/picture&gt;
  &lt;author&gt;ricky&lt;/author&gt;
&lt;/album&gt;
..............................
/*只输出最尾页码*/
&lt;lastpage&gt;34&lt;/lastpage&gt;
</pre></td>
          </tr>
          <tr>
            <td class="color">JSON格式</td>
          </tr>
           <tr>
            <td class="link">调用URL：<a href="http://lovemusic.cc/app/json/list.php?page=1" target="_blank" title="http://lovemusic.cc/app/json/list.php?page=1">http://lovemusic.cc/app/json/list.php?page=1</a></td>
          </tr> <tr>
            <td class="color">示例代码</td>
          </tr>
          <tr>
            <td class="code"><pre>
[
  {"id":"611",
  "category":"另类说唱",
  "title":"未知艺术家",
  "artist":"in3(阴三儿)",
  "time":"2008-04-25",
  "company":"独立发行",
  "area":"大陆",
  "language":"国语  ",
  "text":"北京地下说唱，UNDERGROUNDHIPHOP又名:阴三儿YINSANER/IN3他们登上了美国
  西雅图杂志http://seattletimes.nwsource.com/html/nati...p14.html他们用北京俚
  语为自己冠以名号，他们以戏虐调侃的姿态记录生活。陈昊然，贾伟，孟国栋，这三个
  表征迥异却同样乖张狂放的家伙，用自己的方式向所有人证明了北京地下HIPHOP正在以
  最真实的姿态活着！MCBATTLE，OPENMIC，IRONMIC，SECTION6等众多与HIPHOP有关的活
  动赐予了他们最珍贵的东西。他们学会了如何在舞台上找到自己，如何在所有人面前彻
  底释放，就像一场捍卫音乐的战争，这就是他们的生活，THELIFEISATWAR07年MIDI音乐
  节IN3专场，他们用属于自己的方式，源自生活的创作，在全国乐迷面前肆无忌惮地诠
  释了北京地下HIPHOP最狂躁的幽默。当"NOMONEYNOFRIEND"变成了无数人的MSN签名，当
  "都得死"变成了大家的口头禅，当"北京欢迎你回来"走进了ADIDAS新装发布会现场，当
  "北京晚报"变成了身边朋友点击搜索的重点对象，当阴三儿与扭曲的机器一起叫嚣着
  "都别废话"，用最原始的声音将HIPHOP与摇滚乐融合，那些渴望真实，内心躁动着的人
  们开始逐一苏醒，因为阴三儿正在用自己的生活入侵着我们的生活。没错，阴三儿用音
  乐向身边的"伪人"们动了粗，但请不要误会了北京地下音乐的态度。他们长得不漂亮，
  也不是变态杀人狂，",
  "hit":"89",
  "picture":http://lovemusic.cc/thumbs/thumbs/cache/w190h190/admin/upload/bce
  509ce0c660167ebab4f5e8ce8ae51_41654955.jpeg",
  "author":"ricky"
  }
]
/*只输出最尾页码*/
{"lastpage":"90"}
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