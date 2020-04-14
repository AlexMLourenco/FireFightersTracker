from kafka import KafkaProducer
from kafka.errors import KafkaError
import json 
import csv
import pprint
import time

from os import listdir
from os.path import isfile, join
onlyfiles = [f for f in listdir('./dataset/') if isfile(join('./dataset/', f))]

print(str(onlyfiles))

firefighters = {'vr12': {'gps': [], 'env':[], 'hr': []}, 'a1': {'gps': [], 'env':[], 'hr': []}, 'a2': {'gps': [], 'env':[], 'hr': []}}

for f in onlyfiles:
    if f.split('_')[1].split('.')[0] == 'gps':
        with open('./dataset/' + f, newline='') as csvfile:
            spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')

            for row in spamreader:
                print(str(row))
                firefighters[f.split('_')[0]]['gps'].append({
                    'date': row[0],
                    'gps_tag_lat': row[1],
                    'gps_tag_long': row[2],
                    'gps_time_tag': row[3],
                    'gps_alt_tag': row[4]
                })

# pprint.pprint(firefighters['a1']['gps'])
# producer = KafkaProducer(bootstrap_servers=['localhost:9092'])

# # Asynchronous by default
# future = producer.send('', b'raw_bytes')

# # Block for 'synchronous' sends
# try:
#     record_metadata = future.get(timeout=10)
# except KafkaError:
#     # Decide what to do if produce request failed...
#     log.exception()
#     pass

# # Successful result returns assigned partition and offset
# print (record_metadata.topic)
# print (record_metadata.partition)
# print (record_metadata.offset)

# produce json messages
producer = KafkaProducer(value_serializer=lambda m: json.dumps(m).encode('ascii'))
for firefighter in firefighters.keys():
    for value in firefighters[firefighter]['gps']:
        value['fighterID'] = firefighter
        value['type'] = 'gps'
        producer.send('fighters', value)
        print('Produced information to topic firefighters')
        time.sleep(5)

# # produce asynchronously
# for _ in range(100):
#     producer.send('Flights', b'msg')

def on_send_success(record_metadata):
    print(record_metadata.topic)
    print(record_metadata.partition)
    print(record_metadata.offset)

def on_send_error(excp):
    log.error('I am an errback', exc_info=excp)
    # handle exception

# produce asynchronously with callbacks
#producer.send('Flights', b'raw_bytes').add_callback(on_send_success).add_errback(on_send_error)

# block until all async messages are sent
producer.flush()

# configure multiple retries
producer = KafkaProducer(retries=5)