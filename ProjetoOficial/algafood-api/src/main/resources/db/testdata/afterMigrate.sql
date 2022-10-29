set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_de_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_de_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_de_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;


insert into cozinha (nome) values ('TAILANDEZA');
insert into cozinha (nome) values ('MEXICANA');
insert into cozinha (nome) values ('ITALIANA');
insert into cozinha (nome) values ('ALEMA');

insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Minas Gerais');

insert into cidade (nome, estado_id) values ('Campos do Jordão', 1);
insert into cidade (nome, estado_id) values ('Camanducaia-Monte Verde', 2);

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values ('Nápoles', 18.10, 3, '15910000', 'Rua dos Testes', '123', 'Rota dos Restaurantes', 'Bairro dos testes', '1', utc_timestamp, utc_timestamp, true);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values ('Trattoria Salvador', 52.10, 3, '15910000', 'Rua dos Testes', '123', 'Rota dos Restaurantes', 'Bairro dos testes', '1', utc_timestamp, utc_timestamp, true);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Tudo', 'Teste de dev.', 10.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Salada', 'Teste de dev.', 1.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Burguer', 'Teste de dev.', 15.00, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Ovo', 'Teste de dev.', 18.10, 0, 2);

insert into permissao (nome, descricao) values ('Cadastro de restaurante', 'Permite que o usuário faça cadastros de restaurantes');
insert into permissao (nome, descricao) values ('Cadastro de cidade', 'Permite que o usuário faça cadastros de cidades');

insert into forma_de_pagamento (descricao) values ('Pix');
insert into forma_de_pagamento (descricao) values ('Cartão Crédito');

insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 1);
insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 2);
insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (2, 2);