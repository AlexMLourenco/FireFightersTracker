FROM python:3-alpine

WORKDIR /usr/src/app

RUN pip3 install --no-cache-dir kafka-python
RUN pip3 install --no-cache-dir pprint 

COPY . .

CMD [ "python3", "producer.py" ]