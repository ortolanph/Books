.phony: clean
PORT=8090
JAR_FILE=books.jar

clean:
	@echo "--[LIMPANDO PROJETO]--------------------------------------------------"
	@mvn clean -q
	@echo "----------------------------------------------------------------------"
	
dependencies:
	@echo "--[RESOLVENDO DEPENDÊNCIAS]-------------------------------------------"
	@mvn dependency:resolve -q
	@echo "----------------------------------------------------------------------"
	
compile: clean dependencies
	@echo "--[COMPILANDO]--------------------------------------------------------"
	@mvn install -q
	@echo "----------------------------------------------------------------------"

run: compile
	@echo "--[EXECUTANDO]--------------------------------------------------------"
	@java -Dserver.port=$(PORT) -jar target/$(JAR_FILE)
	@echo "----------------------------------------------------------------------"

debug: compile
	@echo "--[EXECUTANDO COM DEBUG]----------------------------------------------"
	@java -Dserver.port=$(PORT) \
	      -jar \
	      -Xdebug \
	      -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8888 \
	      -jar target/$(JAR_FILE)
	@echo "----------------------------------------------------------------------"
