
build:
	make build_java; docker-compose build --pull --no-cache

build_rabbitmq: cd Rabbitmq; docker-compose up -d ; cd ..
run:
	 docker-compose up
reload:
	make stop; make build; make run
stop:
	docker compose down

shutdownApp:
	@curl -X POST http://localhost:8085/actuator/shutdown

build_java:
	make build_notification_service; make build_invoice_service; make build_payment_service; make build_client_service

build_notification_service:
	cd notification_service; make build; cd ..

build_invoice_service:
	cd invoice_service; make build; cd ..

build_payment_service:
	cd payment; make build; cd ..

build_client_service:
	cd client_service; make build; cd ..
