-module(s_server).
-compile(export_all).


start() ->
	[P] = init:get_plain_arguments(),
	Port = list_to_integer(P),
	io:format("server started, bind port: ~p...~n", [Port]),
	{ok, Listen} = gen_tcp:listen(Port, [binary, 
										 {packet, 4}, 
										 {reuseaddr, true}, 
										 {active, true}]),
	{ok, Socket} = gen_tcp:accept(Listen),
	io:format("client connected..~n"),
%% 	gen_tcp:close(Socket),
	loop(Socket).

loop(Socket) ->
	receive 
		{tcp, Socket, Bin} ->
			io:format("Server received binary = ~p~n", [Bin]),
			Str = binary_to_term(Bin),
			io:format("Server receive = ~p~n", [Str]),
			io:format("Server response = ~p~n", [Str]),
			gen_tcp:send(Socket, term_to_binary(Str)),
			loop(Socket);
		{tcp_closed, Socket} ->
			io:format("Server Socket Closed...~n")
	end.