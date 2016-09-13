package.path = package.path .. ";?;F:\\nginx\\www\\share\\shareLua\\util\\?.lua;F:\\nginx\\www\\share\\shareLua\\?.lua"
require "time"
require "config"
require "system"


print(ROOT)
print(now())
print(get_os())

tbl = {"alpha", "beta", "gamma"}
print(table.concat(tbl, ", "))

print(os.getenv("LUA_PATH"))





require ("luasql.mysql")
function mysql_conn()
  env = luasql.mysql()
  conn = env:connect("test", "mysql-user", "passwd", "localhost")
  res = conn:execute("select count(*) cnt from t_test")
  row = res:fetch({}, 'a')
  return row.cnt
end;
print(string.format("count in table t_test is %d\n", mysql_conn()))












