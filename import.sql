INSERT INTO tb_customer(id, name, email, cpf, birth) VALUES (1, 'Matheu', 'matheu@gmail.com', '931746508713', '2000-05-18');
INSERT INTO tb_customer(id, name, email, cpf, birth) VALUES (2, 'Lucas', 'lucas@gmail.com', '123424', '2000-05-18');
INSERT INTO tb_customer(id, name, email, cpf, birth) VALUES (3, 'Ana', 'ana@gmail.com', '745674674', '2000-05-18');
INSERT INTO tb_customer(id, name, email, cpf, birth) VALUES (4, 'Paulo', 'paulo@gmail.com', '71234601', '2000-05-18');
INSERT INTO tb_customer(id, name, email, cpf, birth) VALUES (5, 'Leandro', 'le@gmail.com', '871605387618', '2000-05-18');

INSERT INTO tb_account(id, activated, balance, type_acc, customer_id) VALUES (1, true, 0.0, 'CC', 1);
INSERT INTO tb_account(id, activated, balance, type_acc, customer_id) VALUES (2, true, 0.0, 'CC', 5);
INSERT INTO tb_account(id, activated, balance, type_acc, customer_id) VALUES (3, true, 0.0, 'CC', 2);
INSERT INTO tb_account(id, activated, balance, type_acc, customer_id) VALUES (4, true, 0.0, 'CC', 3);
INSERT INTO tb_account(id, activated, balance, type_acc, customer_id) VALUES (5, true, 0.0, 'CC', 4);