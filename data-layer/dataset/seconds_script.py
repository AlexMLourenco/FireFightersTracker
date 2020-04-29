import sys
import csv
import os

initial_file = sys.argv

with open(initial_file[1], "r") as csv_file, open("tmp.csv", "w") as outFile:
    csv_reader = csv.reader(csv_file, delimiter=',')
    csv_writer = csv.writer(outFile, delimiter=';', escapechar=',', quoting=csv.QUOTE_NONE)
    line_count = 0
    check = ""
    seconds = 0
    line_count = 0
    for row in csv_reader:
        
        if check == row[0]:
            seconds = seconds + 10
            data = ([f'{row[0]}:{seconds};{row[1]};{row[2]};{row[3]};{row[4]};{row[5]};{row[6]};{row[7]};{row[8]}'])
            print(data)
            csv_writer.writerow(data)
            line_count = line_count + 1
        else:
            check = row[0]
            seconds = 0
            data = ([f'{row[0]}:00;{row[1]};{row[2]};{row[3]};{row[4]};{row[5]};{row[6]};{row[7]};{row[8]}'])
            print(data)
            csv_writer.writerow(data)
            line_count = line_count + 1

    print(f'Processed {line_count} lines.')

os.rename("tmp.csv", initial_file[1])