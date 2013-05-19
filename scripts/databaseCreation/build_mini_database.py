# Builds a mini sqlite3 database from the given USDA ascii files

import sqlite3
conn = sqlite3.connect('USDA.db')
#conn.execute('pragma foreign_keys=ON')
c = conn.cursor()

# Create Tables (Built using USDA data files)
# Create Food Group Description Table
c.execute('''CREATE TABLE Food_Group_Description(
    FdGrp_Cd INTEGER PRIMARY KEY,
    FdGrp_Desc TEXT
    )''')

# Create Food Description Table
c.execute('''CREATE TABLE Food_Description(
    NDB_No INTEGER PRIMARY KEY,
    FdGrp_Cd INTEGER,
    Long_Desc TEXT,
    Shrt_Desc TEXT,
    ComName TEXT,
    ManufacName TEXT,
    Survey TEXT,
    Ref_desc TEXT,
    Refuse INTEGER,
    SciName TEXT,
    N_Factor REAL,
    Pro_Factor REAL,
    Fat_Factor REAL,
    CHO_Factor REAL,
    Search TEXT,
    SearchAlt TEXT,
    FOREIGN KEY(FdGrp_Cd) REFERENCES Food_Group_Description(FdGrp_Cd)
    )''')

# Create Weight Table
c.execute('''CREATE TABLE Weight(
    NDB_No INTEGER,
    Seq INTEGER,
    Amount REAL,
    Msre_Desc TEXT,
    Gm_Wgt REAL,
    Num_Data_Pts INTEGER,
    Std_Dev REAL,
    PRIMARY KEY(NDB_No, Seq),
    FOREIGN KEY(NDB_No) REFERENCES Food_Description(NDB_No)
    )''')

# Create Nutrient Definition Table
c.execute('''CREATE TABLE Nutrient_Definition(
    Nutr_No INTEGER PRIMARY KEY,
    Units TEXT,
    Tagname TEXT,
    NutrDesc TEXT,
    Num_Dec TEXT,
    SR_Order INTEGER
    )''')

# Create Nutrient Data Table
c.execute('''CREATE TABLE Nutrient_Data(
    NDB_No INTEGER,
    Nutr_No INTEGER,
    Nutr_Val REAL,
    Num_Data_Pts INTEGER,
    Std_Error REAL,
    Src_Cd INTEGER,
    Deriv_Cd TEXT,
    Ref_NDB_No INTEGER,
    Add_Nutr_Mark TEXT,
    Num_Studies INTEGER,
    Min REAL,
    Max REAL,
    DF INTEGER,
    Low_EB REAL,
    Up_EB REAL,
    Stat_cmt TEXT,
    AddMod_Date TEXT,
    CC TEXT,
    PRIMARY KEY(NDB_No, Nutr_No),
    FOREIGN KEY(NDB_No) REFERENCES Food_Description(NDB_No),
    FOREIGN KEY(Nutr_No) REFERENCES Nutrient_Definition(Nutr_No)
    )''')

conn.commit()

conn.close()