#!/bin/sh
sudo cp liblpsolve55.so /usr/local/lib\
&& sudo cp libcolamd.so.3.1.0 /usr/local/lib\
&& sudo ldconfig\
&& echo liblpsolve55.so and libcolamd.so.3.1.0 installed successfully.
