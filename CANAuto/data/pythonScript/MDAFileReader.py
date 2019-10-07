# -*- coding: UTF-8 -*-
import os
from asammdf import MDF
import xlrd

folder_path = "../dat"
files=os.listdir(folder_path)

output_path = folder_path + "\\" + "datOut"
if not os.path.exists(output_path):
    os.mkdir(output_path)

for i in range(len(files)):
    file_path = folder_path + "\\" + files[i]
    source_data = MDF(file_path)
    output_file = output_path + "\\" + files[i]
    source_data.export("csv", output_file, single_time_base=True, raster=1)
