.phony: build

erase:
	@echo "--[CLEANING DOCKER]---------------------------------------------------"
	@docker rmi -f ortolanph:books-v1-dockerapps
	@echo "----------------------------------------------------------------------"

build: erase
	@echo "--[BUILDING DOCKER IMAGE]---------------------------------------------"
	@docker build -t ortolanph:books-v1-dockerapps .
	@echo "----------------------------------------------------------------------"

create: build
	@echo "--[]------------------------------------------------------------------"
	@docker run -d \
             -p 32118:1521 \
             --name=books-v1 \
             --volume ~/docker/oracle-xe:/opt/oracle/oradata \
             ortolanph:books-v1-dockerapps
	@echo "----------------------------------------------------------------------"

start: create
	@echo "--[]------------------------------------------------------------------"
	@docker start books-v1
	@echo "----------------------------------------------------------------------"