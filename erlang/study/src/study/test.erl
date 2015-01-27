%erlang study
-module(test).
-export([size/1]). 
-export([fac/1]). 
-export([max/2]). 
-export([min/2]). 
-export([area/1]). 
-export([hellow/0]). 

hellow() ->
	io:format("Hellow World...").


%%é¶ä¹
fac(1) ->  
	1; 
fac(N) -> 
	N * fac(N - 1).

%% è®¡ç®æä»¶å¤§å°Â
size(Size) ->
	if
		Size > 0 , Size < 1024 -> 
			{Size, bytes};
		Size >= 1024 , Size < 1048576 ->
			{trunc(Size / 1024), kb};
		Size >= 1048576 , Size < 1073741824 ->
			{trunc(Size / 1048576), mb};
		Size >= 1073741824 , Size < 1099511627776 ->
			{trunc(Size / 1073741824), gb};
		Size >= 1099511627776 , Size < 1125899906842624 ->
			{trunc(Size / 1099511627776), tb}
	end.		

%% æ±ä¸¤ä¸ªæ°ä¹é´çæå¤§å¼
max(X, Y) when X > Y -> X;
max(_X, Y) -> Y.

%% æ±ä¸¤ä¸ªæ°ä¹é´çæå°å¼
min(X, Y) when X < Y -> X;
min(_X, Y) -> Y.

%% 圆
area({rectangle, Width, Height}) ->
	Width * Height;
area({circle, R}) ->
	3.141592654 * R * R;
area({square, X}) ->
	X * X.













