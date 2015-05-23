namespace java com.share.soa.thrift.protocol

struct  ShareObject{
	1:i32 id1;
	2:i16 id2;
	3:string content;
	4:string media_from;
	5:string author;
}

#注释 ...
service TestShareObject {
	i32 test(1:i32 a),
	string testString(1:i32 n,2:bool aaa)
}