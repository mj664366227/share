-module(c_client).
-compile(export_all).

start() ->
	[Host, Port, Str] = init:get_plain_arguments(),
	{ok, Socket} = gen_tcp:connect(Host, list_to_integer(Port), [binary, {packet, 4}]),
	ok = gen_tcp:send(Socket, term_to_binary(Str)),
	io:format("connect server ~p ok, port ~p...~n", [Host, Port]),
	receive 
		{tcp, Socket, Bin} ->
			io:format("Client Received Binary = ~p~n", [Bin]),
			Val = binary_to_term(Bin),
			io:format("Recv = ~p~n", [Val])
%% 			gen_tcp:close(Socket)
	end.