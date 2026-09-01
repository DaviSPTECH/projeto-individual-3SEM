CREATE TABLE if not exists rota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    origem VARCHAR(45) NOT NULL,
    destino VARCHAR(45) NOT NULL,
    estacaoInicial VARCHAR(45) NOT NULL,
    estacaoFinal VARCHAR(45) NOT NULL,
    duracaoMinutos INT NOT NULL,
    qtdBaldeacoes INT NOT NULL
);

INSERT INTO rota (origem, destino, estacaoInicial, estacaoFinal, duracaoMinutos, qtdBaldeacoes) VALUES
    ('Casa', 'Faculdade', 'Vila Prudente', 'Consolação', 35, 1),
    ('Casa', 'Museu do Ipiranga', 'Tamanduateí', 'Alto do Ipiranga', 15, 0),
    ('Trabalho', 'Mercado Municipal', 'Vila Madalena', 'São Bento', 25, 1),
    ('Casa', 'Catedral da Sé', 'São Caetano do Sul', 'Sé', 30, 1),
    ('Hotel', 'Aeroporto de Guarulhos', 'Paraíso', 'Engenheiro Goulart', 50, 2);