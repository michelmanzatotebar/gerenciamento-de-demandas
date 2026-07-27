document.getElementById('btnCriarPerfil').addEventListener('click', async () => {
  const nome = document.getElementById('nome').value.trim();
  const local = document.getElementById('local').value.trim();
  const statusEl = document.querySelector('input[name="status"]:checked');
  const status = statusEl ? statusEl.value : null;
  const msgErro = document.getElementById('msg-erro');

  msgErro.textContent = '';

  if (!nome) {
    msgErro.textContent = 'Nome é obrigatório';
    return;
  }

  if (!local) {
    msgErro.textContent = 'Local é obrigatório';
    return;
  }

  const perfil = { nome, local, status };

  try {
    const resposta = await fetch('/api/perfis', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(perfil)
    });

    if (resposta.status === 503) {
      msgErro.textContent = 'Sem conexão com banco';
      return;
    }

    if (!resposta.ok) {
      msgErro.textContent = 'Erro ao criar perfil';
      return;
    }

    window.location.href = 'listarPerfis.html';
  } catch (error) {
    msgErro.textContent = 'Sem conexão com banco';
  }
});