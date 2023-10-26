How to start:
  1. Run docker container with Postgres
     docker run --name megafair-pg -p 5432:5432 -e POSTGRES_USER=megafair -e POSTGRES_PASSWORD=olga1999 -e POSTGRES_DB=megafairdb -d postgres:13.3