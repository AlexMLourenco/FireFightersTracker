#!/bin/sh
if [ $(docker ps | grep logstash | wc -l) -gt 0 ]
then 
    echo "Container is Running!"
    docker stop $(docker inspect --format="{{.Id}}" $1)
    echo "Container Stopped!"
    docker rm $(docker inspect --format="{{.Id}}" $1)
    echo "Container Removed!"
    docker rmi -f 192.168.160.99:5000/$1
    echo "Image Removed!"  
    
elif [ $(docker container ls -a | grep logstash | wc -l) -gt 0 ]
then
    echo "Container is not running!"
    docker rm $(docker inspect --format="{{.Id}}" $1)
    echo "container removed"
    docker rmi -f 192.168.160.99:5000/$1
    echo "Image Removed!"
else 
    echo "Container not found"
    exit 1
fi
