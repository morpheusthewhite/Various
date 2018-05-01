#!/bin/bash
#This is a bash script that changes the wallpaper, switching among the .jpg images in the below path (images must be saved with progressive names: 0.jpg, 1.jpg ...) reading the last wallpaper set from the count file, in countFolder folder, updating it at the end
export count_filename="/home/username/Pictures/AutomatedWallpapers/countFolder/count"
export path_to_images="/home/username/Pictures/AutomatedWallpapers/"

read count <$count_filename

#finding n (number of images)
export n=`find $path_to_images -maxdepth 1 -type f| wc -l `

#changes the wallpaper
gsettings set org.gnome.desktop.background picture-uri $path_to_images$count.jpg
gsettings set org.gnome.desktop.background picture-options spanned

#updates the counter
let "count=($count+1)%n"

echo $count > $count_filename
