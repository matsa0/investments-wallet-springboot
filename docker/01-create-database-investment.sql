CREATE USER "pg-investment" WITH PASSWORD 'wallet-investment-password';
CREATE DATABASE "wallet-investment"
ALTER DATABASE "wallet-investment" OWNER TO "pg-investment";