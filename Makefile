build:
	cd Rabbitmq; docker-compose up -d ; cd ..; cd payment; ./mvnw clean package; cd ..;docker-compose build --pull --no-cache

run:
	 docker-compose up
reload:
	make stop; make build; make run
stop:
	docker compose down

shutdownApp:
	@curl -X POST http://localhost:8085/actuator/shutdown

