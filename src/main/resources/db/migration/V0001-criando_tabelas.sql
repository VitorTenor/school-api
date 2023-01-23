create table if not exists users (
    id serial primary key,
    nome varchar(255) not null,
    email varchar(255) not null,
    password_hash varchar(255) not null
    created_at timestamp not null,
    updated_at timestamp not null
);

create table if not exists alunos (
    id serial primary key,
    nome varchar(255) not null,
    sobrenome varchar(255) not null
    email varchar(255) not null,
    idade int not null,
    peso float not null,
    altura float not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table if not exists fotos (
    id serial primary key,
    originalname varchar(255) not null,
    filename varchar(255) not null
    aluno_id serial not null references alunos(id),
    created_at timestamp not null,
    updated_at timestamp not null
);
