#!/bin/bash
#This script creates desktop entries. Launch it with -h to see all commands

ENTRY=$"[Desktop Entry]

# The type as listed above
Type=Application

# The version of the desktop entry specification to which this file complies
Version=1.0

# The name of the application
Name=_INSERT_NAME_HERE_

# A comment which can/will be used as a tooltip
Comment=_INSERT_COMMENT_HERE_

# The path to the folder in which the executable is placed
Path=/path/to/folder/executable

# The executable of the application, possibly with arguments (including path possibly)
Exec=/path/to/executable

# The name of the icon that will be used to display this entry
Icon=_INSERT_ICON_NAME_HERE_

# Describes whether this application needs to be run in a terminal or not
Terminal=false

# Describes the categories in which this entry should be shown (must be separated by comma and closed by comma (as shown below))
Categories=Education;Languages;Java;"

case $1 in
	"")
		echo "desk -h for help"
		;;
    -h) 
        echo "Creates and modify desktop entry (requires root privileges)"
        echo "usage: desk [OPTIONS] [filename]"
        echo "-h: help"
        echo "-l: list desktop entry folder"
        echo "-r: remove specified desktop entry"
        echo "-m: modify specified desktop entry"
        
        ;;
    -l)
        sudo ls /usr/local/share/applications/
        ;;
    -r)
        sudo rm /usr/local/share/applications/$2.desktop
        ;;
    -m)
        sudo nano /usr/local/share/applications/$2.desktop
        ;;
    *)
        sudo echo "$ENTRY" | sudo tee /usr/local/share/applications/$1.desktop > /dev/null
        sudo nano /usr/local/share/applications/$1.desktop
        RES=`sudo echo "$ENTRY" | sudo diff /usr/local/share/applications/$1.desktop -`
        if [ -z "$RES" ]
            then
                sudo rm /usr/local/share/applications/$1.desktop
                echo "File unmodified, removing it..."
        fi
        ;;
esac