import { useState } from "react";
import styles from "./Cadastro.module.css";
import mensagemDeErro from "../Erros";

function Cadastro({ irParaLista }) {
  const [origem, setOrigem] = useState("");
  const [destino, setDestino] = useState("");
  const [estacaoInicial, setEstacaoInicial] = useState("");
  const [estacaoFinal, setEstacaoFinal] = useState("");
  const [duracaoMinutos, setDuracaoMinutos] = useState("");
  const [qtdBaldeacoes, setQtdBaldeacoes] = useState("");
  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState(null);
  const [sucesso, setSucesso] = useState(false);

  async function cadastrar(e) {
    e.preventDefault(); 

    setErro(null);
    setSucesso(false);
    setCarregando(true);

    const rota = {
      origem: origem,
      destino: destino,
      estacaoInicial: estacaoInicial,
      estacaoFinal: estacaoFinal,
      duracaoMinutos: Number(duracaoMinutos),
      qtdBaldeacoes: Number(qtdBaldeacoes),
    };

    try {
      const resposta = await fetch("http://localhost:8080/rotas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(rota),
      });

      if (!resposta.ok) {
        throw new Error("Erro ao cadastrar rota");
      }

      setOrigem("");
      setDestino("");
      setEstacaoInicial("");
      setEstacaoFinal("");
      setDuracaoMinutos("");
      setQtdBaldeacoes("");
      setSucesso(true);
    } catch (e) {
      setErro(mensagemDeErro(e));
    } finally {
      setCarregando(false);
    }
  }

  return (
    <form className={styles.form} onSubmit={cadastrar}>
      <div className={styles.cabecalho}>
        <h1>Cadastrar rota</h1>
        <button type="button" onClick={irParaLista}>
          Minhas rotas
        </button>
      </div>

      <div className={styles.linha}>
        <input
          placeholder="Origem"
          value={origem}
          onChange={(e) => setOrigem(e.target.value)}
          required
        />
        <input
          placeholder="Destino"
          value={destino}
          onChange={(e) => setDestino(e.target.value)}
          required
        />
      </div>

      <div className={styles.linha}>
        <input
          placeholder="Estação inicial"
          value={estacaoInicial}
          onChange={(e) => setEstacaoInicial(e.target.value)}
          required
        />
        <input
          placeholder="Estação final"
          value={estacaoFinal}
          onChange={(e) => setEstacaoFinal(e.target.value)}
          required
        />
      </div>

      <div className={styles.linha}>
        <input
          type="number"
          placeholder="Duração (min)"
          min="1"
          value={duracaoMinutos}
          onChange={(e) => setDuracaoMinutos(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="Quantidade de baldeações"
          min="0" 
          value={qtdBaldeacoes}
          onChange={(e) => setQtdBaldeacoes(e.target.value)}
          required
        />
      </div>

      <button type="submit" disabled={carregando}>
        {carregando ? "Salvando..." : "Salvar"}
      </button>

      {erro && <p className={styles.erro}>{erro}</p>}
      {sucesso && <p className={styles.sucesso}>Rota cadastrada!</p>}
    </form>
  );
}

export default Cadastro;
