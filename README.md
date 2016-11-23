# jupyla #

## Build & Run ##

```sh
$ cd jupyla
$ ./sbt
> jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

http://stackoverflow.com/questions/30492623/using-both-python-2-x-and-python-3-x-in-ipython-notebook
The Details

Create this directory: mkdir -p ~/.ipython/kernels/python3
Create this file ~/.ipython/kernels/python3/kernel.json with this content:
{
    "display_name": "IPython (Python 3)", 
    "language": "python", 
    "argv": [
        "python3", 
        "-c", "from IPython.kernel.zmq.kernelapp import main; main()", 
        "-f", "{connection_file}"
    ], 
    "codemirror_mode": {
        "version": 2, 
        "name": "ipython"
    }
}
Restart the notebook server.
Select „Python 3“ from the dropdown menu „New“
Work with a Python 3 Notebook
Select „Python 2“ from the dropdown menu „New“
Work with a Python 2 Notebook

Kernel Spec : do something like this
https://github.com/dsblank/simple_kernel/blob/master/install_script.sh

#!/bin/bash

mkdir -p ~/.ipython/kernels/jupyla/

START_SCRIPT_PATH="/Users/Nuch/Desktop/MUIC/OPL/Project/Branch/jupyla/src/main/scala/com/jupyla/webserver/"
## Change the above path to directory that contains Blog.scala
PYTHON_PATH=$(which scala)
CONTENT='{
   "argv": ["'${PYTHON_PATH}'", "'${START_SCRIPT_PATH}'", "{connection_file}"],
                "display_name": "Jupyla",
                "language": "scala"
}'
echo $CONTENT > ~/.ipython/kernels/jupyla/kernel.json