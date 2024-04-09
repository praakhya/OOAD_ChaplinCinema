import csv
import json
from json import JSONDecodeError
pathOfCSV = "/Users/praakhya/insecure/movie_company.txt" #input("Enter csv file path: ")
rows = []
with open(pathOfCSV, newline='') as csvfile:
    spamreader = csv.reader(csvfile, delimiter='\t', quotechar='|')
    for row in spamreader:
        print(row[1])
        movieID = row[0]
        try:
            companyDict = json.loads(row[1])
            print("Company Dict", companyDict)
            for pair in companyDict:
                print(pair)
                if ("id" in pair):
                    rows.append([movieID, pair["id"]])
        except JSONDecodeError as e:
            f = open("logFile.txt", "a")
            f.write(row[1]+"\n")
            f.close()
        #print(movieID, ":", companyDict)
    with open("movie_company.csv", "w") as f:
        csv_writer = csv.writer(f)
        csv_writer.writerows(rows)
        print("written")
print("closed")