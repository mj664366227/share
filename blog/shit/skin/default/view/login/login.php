<?php require view::dir().'head.php';?>
<div class="top-content">
  <div class="inner-bg">
    <div class="container">
      <div class="row">
        <div class="col-sm-6 col-sm-offset-3 form-box">
          <div class="form-bottom">
            <form role="form" action="" method="post" class="login-form">
              <div class="form-group">
                <label class="sr-only" for="username">Username</label>
                <input type="text" name="username" class="form-username form-control" id="form-username" placeholder="登录名...">
              </div>
              <div class="form-group">
                <label class="sr-only" for="password">Password</label>
                <input type="password" name="password" class="form-password form-control" id="form-password" placeholder="登录密码...">
              </div>
              <button type="submit" class="btn">登录</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
