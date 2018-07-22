#!/usr/bin/python3.6

import argparse, os, subprocess
from PIL import Image


def build_parser():
    parser = argparse.ArgumentParser(description='Convert images to jpeg scaling them.')
    parser.add_argument('image', metavar='image', type=str, nargs='+',
                        help='Image to be converted and eventually scaled')
    parser.add_argument("-s --size", metavar='WIDTHxHEIGHT', type=str, nargs=1,
                        help="new size of the image (default is 720x1280)", default="720x1280")
    parser.add_argument("--no-resize", nargs=1, help="if True, does not resize the image (default=False)", default=False,
                        metavar="True/False", type=bool)
    return parser


def get_new_filename(image_filename):

    for i in range(len(image_filename)-1, 0, -1):
        if image_filename[i] == '.':
            return image_filename[:i-1] + ".jpeg"

    return image_filename+".jpeg"


def main():
    parser = build_parser()

    args = parser.parse_args()  # variables parsed from command

    args_dict = vars(args)

    size_str = args_dict["s __size"].split("x")  # get dimensions from parser
    size = (int(size_str[0]), int(size_str[1]))

    must_not_be_resized = args_dict["no_resize"]

    for image in args_dict["image"]:
        print()

        if not os.path.exists(image):
            print("Error: " + image + " does not exist")
            continue

        im = Image.open(image, "r")

        if not must_not_be_resized:
            im.thumbnail(size, Image.ANTIALIAS)

        new_filename = get_new_filename(image)

        im.save(new_filename, "JPEG")

        print("Converted "+image+". Output:")
        subprocess.Popen(["file", new_filename]).wait()


if __name__=="__main__":
    main()