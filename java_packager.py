#creates folder for each file specified as second argument
#the folder has the same name of the file, but has no extension and the first letter is lower

import subprocess
import sys

argvalues = sys.argv

if(len(argvalues) > 1):
	directory=sys.argv[1]
	if(directory[len(directory)-1] != '/'):
		directory+='/'
	
else:
	directory="./"
	
print directory

filename=directory+"tmp_file"

#sends ls output to a temporary file
with open(filename, 'w+') as f:
    p=subprocess.Popen(['ls', '-p', directory], stdout=f)
    p.communicate()
    f.seek(0)
    ls_output = f.read()
#saves output to ls_output    

ls_output=ls_output.split("\n")

working_files=[]
for line in ls_output:
    if ('/' in line or '.py' in line or "tmp_file" in line):
        #this line is a folder
        pass
    else:
        working_files.append(line)

import os
#now working_files contains all files that must be moved to a new folder

for elem in working_files:
    if(len(elem) < 1):
        break
    elif(elem[0].islower() and '.' not in elem):
        print(elem+" won't be moved")
        continue
    package_name_str=elem[0].lower()+elem[1:]
    package_name = package_name_str.split(".")[0]
    os.mkdir(directory+package_name)
	
    subprocess.Popen(["mv", directory+elem, directory+package_name+"/"])

subprocess.Popen(["rm", filename])
