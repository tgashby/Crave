-- SQL script to insert data into the database
-- .schema

.separator ^
.import 'data/fd_group.csv' Food_Group_Description
.import 'data/weight.csv' Weight
.import 'data/food_des.csv' Food_Description
.import 'data/nutr_def.csv' Nutrient_Definition
.import 'data/nut_data.csv' Nutrient_Data