
build:
	cd payment; ./mvnw clean package -Dmaven.test.skip; cd ..;docker-compose build --pull --no-cache

build_rabbitmq: cd Rabbitmq; docker-compose up -d ; cd ..
run:
	 docker-compose up
reload:
	make stop; make build; make run
stop:
	docker compose down

shutdownApp:
	@curl -X POST http://localhost:8085/actuator/shutdown

