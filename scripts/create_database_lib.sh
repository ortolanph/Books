#!/bin/bash

# Database Variables
CONTAINER_NAME=""
PG_DB=""
PG_USER=""
PG_PASSWORD=""
IP_ADDRESS=""

# Configuration files
CONFIG_FILE=""
CONFIG_NAME=""

function ipAddress() {
    IP_ADDRESS=$( sudo docker inspect $CONTAINER_NAME | jq -r $JQ_IP_ADDRESS )
}

function report() {
  echo "===== [ $CONTAINER_NAME ] ====="
  echo "IP Address : $IP_ADDRESS"
  echo "Port       : 5432"
  echo "Database   : $PG_DB"
  echo "User       : $PG_USER"
  echo "Password   : $PG_PASSWORD"
  echo ""
}

function createStructure() {
    CONTAINER_NAME=$1

    sudo docker cp ./$DEFAULT_DDL \
                $CONTAINER_NAME:/docker-entrypoint-initdb.d/$DEFAULT_DDL
    sudo docker exec $CONTAINER_NAME \
                psql -t -d $PG_DB -U $PG_USER -h $IP_ADDRESS -f "docker-entrypoint-initdb.d/$DEFAULT_DDL"
}

function createPGSQLDockerImage() {
    CONTAINER_NAME=$1
    PG_DB=$2
    PG_USER=$3
    PG_PASSWORD=$4
    CONFIG_NAME=$5

    sudo docker run --name $CONTAINER_NAME \
                    -e POSTGRES_DB=$PG_DB \
                    -e POSTGRES_USER=$PG_USER \
                    -e POSTGRES_PASSWORD=$PG_PASSWORD \
                    -d postgres

    createStructure $CONTAINER_NAME
    ipAddress
    report
}
