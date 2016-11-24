#!/bin/bash

mkdir -p ~/.ipython/kernels/jupyla/

START_SCRIPT_PATH="~/Desktop/MUIC/OPL/Project/Branch/jupyla/src/main/scala/com/jupyla/webserver/"

## Change the above path to directory that contains Blog.scala
PYTHON_PATH=$(which scala)
CONTENT='{
   "argv": ["'${PYTHON_PATH}'", "'${START_SCRIPT_PATH}'", "{connection_file}"],
                "display_name": "Jupyla",
                "language": "scala"
}'

echo $CONTENT > ~/.ipython/kernels/jupyla/kernel.json
