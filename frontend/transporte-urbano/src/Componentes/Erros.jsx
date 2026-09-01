function mensagemDeErro(e) {
  if (e instanceof TypeError) {
    return "Não foi possível conectar ao servidor";
  }
  return e.message;
}

export default mensagemDeErro;