# Inserts values from USDA datasets into the mini database

import sqlite3
import csv

# Append a Search Term to the end of each line in the file
f = open("data/temp.csv", 'r')
i = 0
j = 0
try:
	reader = csv.reader(f, delimiter='^')#, quotechar='~')
	for row in reader:
		line = row[0]
		for i in range(1, 14):
			line += "^" + row[i]
		info = row[2]
		search = info[0:info.find(',')]
		line += "^" + search
		idx = info.find(',') + 2
		idxAlt = info.find(',', idx)
		if idxAlt == -1:
			searchAlt = info[idx: len(info)]
		else:
			searchAlt = info[idx:idxAlt]
		line += "^" + searchAlt
		print line
finally:
	f.close()
