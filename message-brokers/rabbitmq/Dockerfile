FROM rabbitmq:management-alpine
COPY rabbitmq.conf /etc/rabbitmq/
COPY definitions.json /etc/rabbitmq/
RUN chown rabbitmq:rabbitmq /etc/rabbitmq/rabbitmq.conf /etc/rabbitmq/definitions.json
ENTRYPOINT ["rabbitmq-server"]