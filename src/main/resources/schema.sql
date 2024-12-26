CREATE TABLE IF NOT EXISTS Run (
id INT NOT NULL,
title varchar(250) not null,
started_on timestamp not null,
completed_on timestamp not null,
miles int not null,
location varchar(10) not null,
primary key (id)
);