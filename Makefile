build:
	docker-compose build --pull --no-cache

run:
	 docker-compose up
reload:
	make stop; docker-compose build; make run
stop:
	docker compose down

shutdownApp:
	@curl -X POST http://localhost:8085/actuator/shutdown

