#!/bin/bash
JAVA_HOME="/app/soft/jdk1.8.0_20"
ENV=$1
if [ -z "$ENV" ]; then
    echo "ERROR:PLEASE SPEC ENV ARGS,SUCH AS dev,test,beta,release..  "
    echo "app exit"
    exit 1
fi
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/classes

SERVER_NAME=`sed '/server.display-name/!d;s/.*=//' classes/config/application.properties | tr -d '\r'`
SERVER_PORT=`sed '/server.port/!d;s/.*=//' classes/config/application.properties | tr -d '\r'`
#LOGS_FILE=`sed '/log4j.appender.logToFile.File/!d;s/.*=//' classes/log4j.properties | tr -d '\r'`
LOGS_FILE=`sed '/logging.path/!d;s/.*=//' classes/config/application.properties | tr -d '\r'`

if [ -z "$SERVER_NAME" ]; then
	SERVER_NAME=`hostname`
fi

echo "server name is $SERVER_NAME"
echo "server port is $SERVER_PORT"

PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
	SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
	if [ $SERVER_PORT_COUNT -gt 0 ]; then
		echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
		exit 1
	fi
fi

LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
	LOGS_DIR=`dirname $LOGS_FILE`
else
	LOGS_DIR=$DEPLOY_DIR/logs
fi
if [ ! -d $LOGS_DIR ]; then
	mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$2" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$2" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi
JAVA_MEM_OPTS=""
BITS=`file $JAVA_HOME/bin/java | grep 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx1g -Xms1g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
	JAVA_MEM_OPTS=" -server -Xms1024m -Xmx1024m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the $SERVER_NAME ...\c"
nohup $JAVA_HOME/bin/java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS org.oreframework.boot.bootstrap.OreBootApplication env=$ENV > $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1 
    if [ -n "$SERVER_PORT" ]; then
      COUNT=`netstat -an | grep $SERVER_PORT | wc -l`
    else
    	COUNT=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    fi
	if [ $COUNT -gt 0 ]; then
		break
	fi
done
echo "OK!"
PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"
