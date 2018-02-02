#/bin/sh
ps -ax | grep tomcat | grep -v grep | awk '{print $1}' | xargs kill -9 
