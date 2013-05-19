# Path to databases Folder
#DBPath = ../../assets/databases/
#zippedDB = USDA.zip

# Clean up the data files we need
cat data/FD_GROUP.txt | LANG=C tr -d \~ > data/fd_group.csv
cat data/WEIGHT.txt | LANG=C tr -d \~ > data/weight.csv
cat data/FOOD_DES.txt | LANG=C tr -d \~\" > data/temp.csv
cat data/NUTR_DEF.txt | LANG=C tr -d \~ > data/nutr_def.csv
cat data/NUT_DATA.txt | LANG=C tr -d \~ > data/nut_data.csv

# Add search term to food_des csv file
python < add_search_term.py > data/food_des.csv

# Remove the database if it already exists
rm USDA.db   #Remove local copy
#rm ../../assets/databases/usda.zip  #Remove zipped copy in databases

# Build database structure
python < build_mini_database.py

# Import Data into Database
sqlite3 'USDA.db' < insert_data.sql

# Prune the databse
sqlite3 'USDA.db' < prune_data.sql

# Compress the DB
zip USDA.zip USDA.db

# Move Compressed DB (will prompt before overwriting existing DB)
mv -i USDA.zip ../../assets/databases/usda.zip

# Remove the cleaned up data files, we are finished
rm data/fd_group.csv
rm data/weight.csv
rm data/food_des.csv
rm data/temp.csv
rm data/nutr_def.csv
rm data/nut_data.csv