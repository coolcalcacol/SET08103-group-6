FROM mysql
WORKDIR /tmp
COPY *.sql /tmp/
COPY world.sql /docker-entrypoint-initdb.d
ENV MYSQL_ROOT_PASSWORD group6Pass