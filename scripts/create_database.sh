#!/bin/bash

source create_database.conf
source create_database_lib.sh

createPGSQLDockerImage development \
                       library \
                       comicbookguy \
                       dev12345
