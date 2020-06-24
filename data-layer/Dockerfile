FROM ubuntu:18.04

RUN apt update
RUN apt-get install -y python3.6 python3-pip python3-dev build-essential gcc \
    libsnmp-dev snmp-mibs-downloader

RUN pip3 install --upgrade pip

RUN mkdir /app
WORKDIR /app
COPY . /app

RUN pip3 install kafka-python
RUN pip3 install pprint 
RUN pip3 --version

COPY start.sh /app
RUN ["chmod", "+x", "start.sh"]
WORKDIR /app
ENTRYPOINT ["sh","start.sh"]