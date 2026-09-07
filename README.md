# 🚇 Transporte Urbano

> Sistema para cadastro, consulta e remoção de rotas de transporte urbano, voltado para linhas de metrô e trem. Permite registrar o trajeto entre estações, informando origem, destino, duração e quantidade de baldeações.

## Estrutura

```text
projeto-individual-3SEM/
├── backend/
├── frontend/
└── README.md
```

---

## Tecnologias

* **Backend:** Java, Spring Boot, JdbcTemplate, H2
* **Frontend:** React, JavaScript, JSX, CSS Modules
* **Comunicação:** API REST

---

## Funcionamento

```text
React → API REST → Spring Boot → H2
```

---

## Executar

**Backend:** abrir a pasta `backend` no IntelliJ e executar a classe principal do Spring Boot.

**Frontend:**

```bash
cd frontend
npm install
npm run dev
```

Backend: `http://localhost:8080` | Frontend: `http://localhost:5173`

---

Mais informações:

* [Backend](./backend/README-back.md)
* [Frontend](./frontend/README-front.md)
