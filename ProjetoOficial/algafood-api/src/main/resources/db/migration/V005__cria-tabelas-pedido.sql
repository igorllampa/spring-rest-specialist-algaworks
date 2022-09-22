create table pedido(
  id bigint not null auto_increment,
  subtotal decimal(10, 2) not null,
  taxa_frete decimal(10,2) not null,
  valor_total decimal(10,2) not null,
  data_criacao datetime not null,
  data_confirmacao datetime null,
  data_cancelamento datetime null,
  data_entrega datetime null,
  status varchar(10) not null,

  endereco_cidade_id bigint not null,
	endereco_cep varchar(9) not null,
	endereco_logradouro varchar(100) not null,
	endereco_numero varchar(20) not null,
	endereco_complemento varchar(60) null,
	endereco_bairro varchar(60) not null,

  forma_de_pagamento_id bigint not null,
  restaurante_id bigint not null,
  usuario_cliente_id bigint not null,

  primary key(id),

  foreign key fk_pedido_endereco_cidade (endereco_cidade_id) references cidade (id),
  foreign key fk_pedido_forma_de_pagamento (forma_de_pagamento_id) references forma_de_pagamento (id),
  foreign key fk_pedido_restaurante (restaurante_id) references restaurante (id),
  foreign key fk_pedido_usuario_cliente (usuario_cliente_id) references usuario (id)

) engine=InnoDB charset=utf8;

create table item_pedido(
  id bigint not null auto_increment,
  quantidade smallint(6) not null,
  preco_unitario decimal(10,2) not null,
  preco_total decimal(10,2) not null,
  observacao varchar(255) null,
  produto_id bigint not null,
  pedido_id bigint not null,

  primary key(id),
  unique key uk_item_pedido_produto (pedido_id, produto_id),

  foreign key fk_item_pedido_produto (produto_id) references produto(id),
  foreign key fk_item_pedido_pedido (pedido_id) references pedido(id)

) engine=InnoDB charset=utf8;