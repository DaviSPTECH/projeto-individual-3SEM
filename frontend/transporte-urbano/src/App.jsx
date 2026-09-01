import { useState } from "react";
import Cadastro from "./Componentes/Cadastro/Cadastro";
import MinhasRotas from "./Componentes/MinhasRotas/MinhasRotas";

function App() {
  const [tela, setTela] = useState("cadastro");

  return (
    <>
      {tela === "cadastro" && <Cadastro irParaLista={() => setTela("lista")} />}
      {tela === "lista" && <MinhasRotas irParaCadastro={() => setTela("cadastro")} />}
    </>
  );
}

export default App;
