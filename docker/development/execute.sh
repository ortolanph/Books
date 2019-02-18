#!/usr/bin/env bash

docker build -t ortolanph:books-v1-dockerapps .

docker run -d \
  -p 32118:1521 \
  --name=books-v1 \
  --volume ~/docker/oracle-xe:/opt/oracle/oradata \
  ortolanph:books-v1-dockerapps

docker start books-v1-dockerapps