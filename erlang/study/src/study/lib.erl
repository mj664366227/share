%% 函数库
-module(lib).
-compile(export_all).

%% for循环
for(Max, Max, F) -> 
	[F(Max)];
for(I, Max, F) -> 
	[F(I)|for(I+1, Max, F)].

%% 快速排序(这样的写法很低效的)
qsort([]) -> [];
qsort([Pivot|T]) ->
	qsort([X || X <- T, X < Pivot]) 
	++ [Pivot] ++ 
	qsort([X || X <- T, X >= Pivot]).

%% 毕达哥斯拉三元组
pythag(N) -> 
	[ {A, B, C} ||
	  A <- lists:seq(1, N),
	  B <- lists:seq(1, N),
	  C <- lists:seq(1, N),
	  A + B + C =< N,
	  A * A + B * B =:= C * C
	 ].