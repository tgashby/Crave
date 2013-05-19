-- SQL script to prune the data in the database
-- .schema

SELECT DISTINCT Long_Desc FROM Food_Description WHERE Search LIKE ('%brown%') OR SearchAlt LIKE ('%brown%');

SELECT Shrt_Desc FROM Food_Description WHERE Search LIKE 'brownies' OR SearchAlt LIKE 'brownies';

Select * FROM Nutrient_Definition def INNER JOIN Nutrient_Data data ON def.Nutr_No = data.Nutr_No WHERE data.NDB_NO = 5000;