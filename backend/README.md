# 🚇 Transporte Urbano — Backend

> API REST desenvolvida com **Java + Spring Boot + JdbcTemplate**, utilizando **H2** como banco de dados.

## Rotas

| Método | Endpoint      | Descrição         |
| ------ | ------------- | ----------------- |
| GET    | `/rotas`      | Lista as rotas    |
| POST   | `/rotas`      | Cadastra uma rota |
| DELETE | `/rotas/{id}` | Remove uma rota   |

---

## Campos

* `origem`
* `destino`
* `estacaoInicial`
* `estacaoFinal`
* `duracaoMinutos`
* `qtdBaldeacoes`

O `id` é gerado automaticamente pelo banco.

---

## Exemplos

### POST `/rotas`

**Requisição:**

```json
{
  "origem": "Casa",
  "destino": "Faculdade",
  "estacaoInicial": "Vila Prudente",
  "estacaoFinal": "Consolação",
  "duracaoMinutos": 35,
  "qtdBaldeacoes": 1
}
```

**Resposta — 201:**

```json
{
  "id": 6,
  "origem": "Casa",
  "destino": "Faculdade",
  "estacaoInicial": "Vila Prudente",
  "estacaoFinal": "Consolação",
  "duracaoMinutos": 35,
  "qtdBaldeacoes": 1
}
```

**Resposta — 400:**

```text
HTTP 400 Bad Request
```

Retornado quando algum campo obrigatório é inválido ou quando `duracaoMinutos <= 0` ou `qtdBaldeacoes < 0`.

---

### GET `/rotas`

**Resposta — 200:**

```json
[
  {
    "id": 1,
    "origem": "Casa",
    "destino": "Faculdade",
    "estacaoInicial": "Vila Prudente",
    "estacaoFinal": "Consolação",
    "duracaoMinutos": 35,
    "qtdBaldeacoes": 1
  }
]
```

---

### DELETE `/rotas/{id}`

**Requisição:**

```text
DELETE /rotas/1
```

**Resposta — 204:**

```text
HTTP 204 No Content
```

**Resposta — 404:**

```text
HTTP 404 Not Found
```

Retornado quando a rota informada não existe.

---

## Status HTTP

* `200` — GET realizado
* `201` — rota cadastrada
* `204` — rota removida
* `400` — dados inválidos
* `404` — rota não encontrada

---

## CORS

CORS liberado para:

`http://localhost:5173`

---

## Banco de dados

Utiliza **H2 em memória** — não requer nenhuma configuração externa, o banco é criado automaticamente ao subir a aplicação.

O script de criação da tabela e inserção dos dados de exemplo está em:
- `src/main/resources/schema.sql` — usado automaticamente pelo Spring Boot na inicialização
- `script.sql` (raiz do projeto, junto ao `pom.xml`) — cópia idêntica, disponibilizada como entregável

```sql
CREATE TABLE if not exists rota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    origem VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL,
    estacaoInicial VARCHAR(100) NOT NULL,
    estacaoFinal VARCHAR(100) NOT NULL,
    duracaoMinutos INT NOT NULL,
    qtdBaldeacoes INT NOT NULL
    );

INSERT INTO rota (origem, destino, estacaoInicial, estacaoFinal, duracaoMinutos, qtdBaldeacoes) VALUES
    ('Casa', 'Faculdade', 'Vila Prudente', 'Consolação', 35, 1),
    ('Casa', 'Museu do Ipiranga', 'Tamanduateí', 'Alto do Ipiranga', 15, 0),
    ('Trabalho', 'Mercado Municipal', 'Vila Madalena', 'São Bento', 25, 1),
    ('Casa', 'Catedral da Sé', 'São Caetano do Sul', 'Sé', 30, 1),
    ('Hotel', 'Aeroporto de Guarulhos', 'Paraíso', 'Engenheiro Goulart', 50, 2);
```
---

## Execução

Abrir o projeto no IntelliJ e executar a classe principal do Spring Boot.

API disponível em:

`http://localhost:8080`
