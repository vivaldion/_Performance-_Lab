


import os


for i in range(1,5):
    foldername = f"task{i}"
    if not os.path.exists(foldername):
        os.mkdir(foldername)
    with open(f"{os.getcwd()}\{foldername}\{foldername}.py" , "a") as file:
        file.close()

