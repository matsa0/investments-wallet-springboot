CREATE USER "pg-wallet-admin" WITH PASSWORD 'wallet-investment-password';
CREATE DATABASE "investment-wallet";
ALTER DATABASE "investment-wallet" OWNER TO "pg-wallet-admin";