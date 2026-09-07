import { useState, useEffect } from "react";
import styles from "./MinhasRotas.module.css";
import mensagemDeErro from "../Erros";

function MinhasRotas({ irParaCadastro }) {
  const [rotas, setRotas] = useState([])
  const [carregando, setCarregando] = useState(false)
  const [erro, setErro] = useState(null)

  async function buscarRotas() {
    setErro(null)
    setCarregando(true)

    try {
      const resposta = await fetch("http://localhost:8080/rotas")

      if (!resposta.ok) {
        throw new Error("Erro ao buscar rotas")
      }

      const dados = await resposta.json();
      setRotas(dados)

    } catch (e) {
      setErro(mensagemDeErro(e))

    } finally {
      setCarregando(false)
    }
  }

  useEffect(() => {
    buscarRotas();
  }, []);

  async function excluirRota(id) {
    setErro(null)

    try {
      const resposta = await fetch(`http://localhost:8080/rotas/${id}`, {
        method: "DELETE",
      });

      if (!resposta.ok) {
        throw new Error("Erro ao excluir rota!")
      }

      setRotas(rotas.filter((rota) => rota.id !== id))
    } catch (e) {
      setErro(mensagemDeErro(e))
    }

  }

  return (
    <div className={styles.pagina}>
      <h1>Minhas rotas</h1>

      <div className={styles.botoes}>
        <button className={styles.cadastrarRota} onClick={irParaCadastro}>Cadastrar rota</button>
        <button className={styles.buscar} onClick={buscarRotas} disabled={carregando}>
          {carregando ? "Buscando..." : "Recarregar"}
        </button>
      </div>

      {erro && <p className={styles.erro}>{erro}</p>}

      <div className={styles.lista}>
        {rotas.map((rota) => (
          <div key={rota.id} className={styles.rota}>
            <p><span className={styles.rotulo}>Origem:</span> {rota.origem}</p>
            <p><span className={styles.rotulo}>Destino:</span> {rota.destino}</p>
            <p><span className={styles.rotulo}>Estação inicial:</span> {rota.estacaoInicial}</p>
            <p><span className={styles.rotulo}>Estação final:</span> {rota.estacaoFinal}</p>
            <p><span className={styles.rotulo}>Duração:</span> {rota.duracaoMinutos} minutos</p>
            <p><span className={styles.rotulo}>Baldeações:</span> {rota.qtdBaldeacoes}</p>
            <button className={styles.excluir} onClick={() => excluirRota(rota.id)}>
              Excluir rota
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default MinhasRotas
