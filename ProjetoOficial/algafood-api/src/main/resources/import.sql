insert into cozinha (nome) values ('TAILANDEZA');
insert into cozinha (nome) values ('MEXICANA');
insert into cozinha (nome) values ('ITALIANA');
insert into cozinha (nome) values ('ALEMA');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Nápoles', 18.10, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Trattoria Salvador', 52.10, 3);

insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Minas Gerais');

insert into cidade (nome, estado_id) values ('Campos do Jordão', 1);
insert into cidade (nome, estado_id) values ('Camanducaia-Monte Verde', 2);

insert into permissao (nome, descricao) values ('Cadastro de restaurante', 'Permite que o usuário faça cadastros de restaurantes');
insert into permissao (nome, descricao) values ('Cadastro de cidade', 'Permite que o usuário faça cadastros de cidades');

insert into forma_de_pagamento (descricao) values ('Pix');
insert into forma_de_pagamento (descricao) values ('Cartão Crédito');

insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 1);
insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 2);
insert into restaurante_forma_de_pagamento (restaurante_id, forma_de_pagamento_id) values (2, 2);