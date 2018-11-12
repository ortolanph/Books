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
    IP_ADDRESS=$( docker inspect $CONTAINER_NAME | jq -r $JQ_IP_ADDRESS )
}

function report() {
  echo "===== [ $CONTAINER_NAME ] ====="
  echo "IP Address : localhost"
  echo "Port       : 5432"
  echo "Database   : $PG_DB"
  echo "User       : $PG_USER"
  echo "Password   : $PG_PASSWORD"
  echo ""
}

function createStructure() {
    CONTAINER_NAME=$1
    
    docker cp ./$DEFAULT_DDL \
           $CONTAINER_NAME:/tmp/$DEFAULT_DDL
           
    docker exec -it $CONTAINER_NAME \
           psql -v ON_ERROR_STOP=1 -d $PG_DB -U $PG_USER -a -f /tmp/$DEFAULT_DDL -o /tmp/script.log
                
    docker cp $CONTAINER_NAME:/tmp/script.log ./script.log       
}

function createPGSQLDockerImage() {
    CONTAINER_NAME=$1
    PG_DB=$2
    PG_USER=$3
    PG_PASSWORD=$4
    CONFIG_NAME=$5

    docker run --restart=always \
               --detach=true \
			   --name $CONTAINER_NAME \
               -e POSTGRES_DB=$PG_DB \
               -e POSTGRES_USER=$PG_USER \
               -e POSTGRES_PASSWORD=$PG_PASSWORD \
               -p 5432:5432 \
               -i postgres:latest

	sleep 10
	
	docker start $CONTAINER_NAME
	
    createStructure $CONTAINER_NAME
    report
}
