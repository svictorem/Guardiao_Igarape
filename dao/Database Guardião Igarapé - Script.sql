create database guardiao_igarape_db;
use guardiao_igarape_db;

create table voluntario(
	voluntario_id int primary key auto_increment,
    voluntario_nome varchar(255) not null,
    telefone varchar(20)
);

create table acao_ambiental(
	acao_id int primary key auto_increment,
    acao_local varchar(255) not null,
    acao_data date not null,
    duracao_horas double not null,
    acao_tipo varchar(100) not null
);

create table participacao(
	participacao_id int primary key auto_increment,
    voluntario_id int not null,
    acao_id int not null,
    constraint fk_voluntario foreign key (voluntario_id) references voluntario(voluntario_id),
    constraint fk_acao foreign key (acao_id) references acao_ambiental(acao_id)
);

INSERT INTO voluntario (voluntario_nome, telefone) VALUES
('Ana Clara Souza', '(92) 99123-4567'),
('Carlos Eduardo Lima', '(92) 98456-7890'),
('Beatriz Mendes', '(92) 99234-5678'),
('Gabriel Ribeiro', '(92) 98112-3456'),
('Fernanda Oliveira', '(92) 99345-6789'),
('Lucas Gonzaga', '(92) 98223-4567'),
('Mariana Castro', '(92) 99456-7890'),
('Rafael Vasconcelos', '(92) 98334-5678'),
('Juliana Paes', '(92) 99567-8901'),
('Thiago Alencar', '(92) 98445-6789');

INSERT INTO acao_ambiental (acao_local, acao_data, duracao_horas, acao_tipo) VALUES
('Igarapé do Mindu - Parque do Mindu', '2026-03-10', '04:00:00', 'LIMPEZA'),
('Igarapé do Quarenta - Educandos', '2026-03-15', '05:00:00', 'LIMPEZA'),
('Igarapé do Gigante - Redenção', '2026-03-22', '03:00:00', 'PLANTIO'),
('Igarapé do Franco - Compensa', '2026-04-05', '06:00:00', 'LIMPEZA'),
('Igarapé Água Branca - Tarumã', '2026-04-12', '04:00:00', 'EDUCACAO_AMBIENTAL'),
('Igarapé do Mindu - Setor B', '2026-04-20', '03:30:00', 'PLANTIO'),
('Igarapé do Quarenta - Japiim', '2026-05-02', '05:00:00', 'LIMPEZA'),
('Igarapé do Passeio do Bindá', '2026-05-18', '02:30:00', 'EDUCACAO_AMBIENTAL'),
('Igarapé do Goiabanal', '2026-06-01', '04:00:00', 'PLANTIO'),
('Igarapé do Franco - Boulevard', '2026-06-15', '06:00:00', 'LIMPEZA');

INSERT INTO participacao (voluntario_id, acao_id) VALUES
(1, 1), -- Ana na Limpeza do Mindu
(1, 3), -- Ana no Plantio do Gigante
(2, 1), -- Carlos na Limpeza do Mindu
(2, 2), -- Carlos na Limpeza do Quarenta
(3, 4), -- Beatriz na Limpeza do Franco
(4, 5), -- Gabriel na Educação Ambiental no Água Branca
(5, 3), -- Fernanda no Plantio do Gigante
(6, 6), -- Lucas no Plantio do Mindu
(7, 7), -- Mariana na Limpeza do Quarenta
(8, 8); -- Rafael na Educação Ambiental do Bindá

select*from voluntario;
select*from acao_ambiental;
select*from participacao;

-- 1. Remove a restrição antiga
ALTER TABLE participacao DROP FOREIGN KEY fk_voluntario;

-- 2. Recria a restrição adicionando o ON DELETE CASCADE
ALTER TABLE participacao 
ADD CONSTRAINT fk_voluntario 
FOREIGN KEY (voluntario_id) REFERENCES voluntario(voluntario_id) 
ON DELETE CASCADE;
