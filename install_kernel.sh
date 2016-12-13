#!/bin/bash

mkdir -p ~/.local/share/jupyter/kernels/jupyla/

START_SCRIPT_PATH="~/Desktop/jupyla/src/main/scala/com/jupyla/webserver/"

## Change the above path to directory that contains Blog.scala
PYTHON_PATH=$(which scala)
CONTENT='{
   "argv": ["'${PYTHON_PATH}'", "'${START_SCRIPT_PATH}'", "{connection_file}"],
                "display_name": "Jupyla",
                "language": "scala"
}'

echo $CONTENT > ~/.local/share/jupyter/kernels/jupyla/kernel.json
