-- SQL script to prune the data in the database
-- .schema

-- Removes Unwanted Food Groups
DELETE FROM Food_Description
WHERE FdGrp_Cd='0200' OR FdGrp_Cd='0300' OR FdGrp_Cd='0400' OR FdGrp_Cd='2100' OR FdGrp_Cd='3600';

-- Removes "raw" foods
DELETE FROM Food_Description
WHERE Long_Desc LIKE '% raw';

-- Remove Manufacturer Specific entries
DELETE FROM Food_Description
WHERE ManufacName NOT LIKE '';

-- Remove Kraft entries
DELETE FROM Food_Description
WHERE Long_Desc LIKE '%kraft%';

-- Remove Oscar Meyer entries
DELETE FROM Food_Description
WHERE Long_Desc LIKE '%oscar mayer%';

-- Remove LOUIS RICH entries
DELETE FROM Food_Description
WHERE Long_Desc LIKE '%louis rich%';

-- Remove Hormel entries
DELETE FROM Food_Description
WHERE Long_Desc LIKE '%hormel%';

-- Remove CARL BUDDIG entries
DELETE FROM Food_Description
WHERE Long_Desc LIKE '%carl buddig%';

-- Remove Game Meat Entries
DELETE FROM Food_Description 
WHERE Long_Desc Like '%game meat%';

-- Remove Frostings Entries
DELETE FROM Food_Description 
WHERE Long_Desc Like '%frostings%';

-- Remove Dessert topping Entries
DELETE FROM Food_Description 
WHERE Long_Desc Like '%dessert topping%';

-- Remove cream substitute Entries
DELETE FROM Food_Description 
WHERE Long_Desc Like '%cream substitute%';

-- Remove heart entries
DELETE FROM Food_Description 
WHERE Long_Desc Like '%heart%';

-- Remove gizzard entries
DELETE FROM Food_Description 
WHERE Long_Desc Like '%gizzard%';

-- Remove canned entries
DELETE FROM Food_Description
WHERE Long_Desc LIKE '%canned%' AND FdGrp_Cd NOT LIKE '600' AND FdGrp_Cd NOT LIKE '1100' AND FdGrp_Cd NOT LIKE '1600' AND FdGrp_Cd NOT LIKE '2200';