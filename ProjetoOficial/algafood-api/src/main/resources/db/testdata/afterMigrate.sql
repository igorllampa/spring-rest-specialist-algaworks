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
delete from restaurante_usuario_responsavel;
delete from usuario;
delete from usuario_grupo;
delete from item_pedido;
delete from pedido;

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

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);              

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Tudo', 'Teste de dev.', 10.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Salada', 'Teste de dev.', 1.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Burguer', 'Teste de dev.', 15.00, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Ovo', 'Teste de dev.', 18.10, 0, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-Teste', 'Teste de dev.', 10.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-a', 'Teste de dev.', 1.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-B', 'Teste de dev.', 15.00, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('X-C', 'Teste de dev.', 18.10, 0, 2);

insert into permissao (nome, descricao) values ('Cadastro de restaurante', 'Permite que o usuário faça cadastros de restaurantes');
insert into permissao (nome, descricao) values ('Cadastro de cidade', 'Permite que o usuário faça cadastros de cidades');

insert into forma_de_pagamento (descricao) values ('Pix');
insert into forma_de_pagamento (descricao) values ('Cartão Crédito');

insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 1);
insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 2);
insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (2, 2);

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);

insert into usuario_grupo(usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2), (3, 3), (4, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 1), (1, 2), (2, 3), (3, 3), (4, 4);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_de_pagamento_id, endereco_cidade_id, endereco_cep, 
    				endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    				status, data_criacao, subtotal, taxa_frete, valor_total)
			values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
					'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
				 values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
				 values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, restaurante_id, usuario_cliente_id, forma_de_pagamento_id, endereco_cidade_id, endereco_cep, 
        			endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
        			status, data_criacao, subtotal, taxa_frete, valor_total)
			values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
					'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
				 values (3, 2, 6, 1, 79, 79, 'Ao ponto'); 