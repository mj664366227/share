#run.sh模板

path=$1 #项目路径
project=$2 #项目名称
mainClass=$3 #main入口

if [ ! $path ]; then
	echo 'please input the project path!'
	exit;
fi
if [ ! $project ]; then
	echo 'please input the project name!'
	exit;
fi
if [ ! $mainClass ]; then
	echo 'please input the main class!'
	exit;
fi

classpath=$path'../etc/'
for jar in `ls $path/lib/*.jar`
do
	classpath="$classpath:""$jar"
done

cmd='java -server -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Xloggc:'$path'/log/gc.log -classpath '$classpath' '$mainClass

$cmd