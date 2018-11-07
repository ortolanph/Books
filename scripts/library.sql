create table owner (
    id serial primary key,
    name varchar(20) not null,
    pass text not null,
    salt text not null
);

create table caixa (
    id serial primary key,
    owner_id int not null references owner(id),
    titulo varchar(50) not null,
    descricao text
);

create table obra (
    id serial primary key,
    owner_id int not null references owner(id),
    titulo varchar(255) not null,
    tipo byte not null,
    barcode varchar(13)
);
