CREATE USER "pg-user" WITH PASSWORD 'wallet-user-password';
CREATE DATABASE "wallet-user"
ALTER DATABASE "wallet-user" OWNER TO "pg-user";