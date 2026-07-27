async function carregarPerfis() {
  const container = document.getElementById('lista-perfis');
  const status = document.getElementById('status-lista');
  container.innerHTML = '';
  status.textContent = 'Carregando perfis...';

  try {
    const resposta = await fetch('/api/perfis');
    const texto = await resposta.text();

    if (!resposta.ok) {
      status.textContent = texto || 'sem conexao com banco';
      return;
    }

    if (!texto || texto === 'Nenhum perfil encontrado, crie um para começar') {
      status.textContent = 'Nenhum perfil encontrado, crie um para começar';
      return;
    }

    const perfis = JSON.parse(texto);

    if (!Array.isArray(perfis) || perfis.length === 0) {
      status.textContent = 'Nenhum perfil encontrado, crie um para começar';
      return;
    }

    status.textContent = '';
    container.innerHTML = perfis.map(perfil => `
      <div class="col-12 col-md-6 col-lg-4">
        <div class="card-demanda p-3 h-100">
          <div class="d-flex justify-content-between align-items-start mb-2">
            <h5 class="fw-semibold mb-0">${perfil.nome || 'Sem nome'}</h5>
            <span class="badge bg-primary-subtle text-primary-emphasis">${perfil.status || 'Sem status'}</span>
          </div>
          <p class="text-secondary mb-2"><i class="bi bi-geo-alt me-2"></i>${perfil.local || 'Local não informado'}</p>
          <small class="text-secondary">ID: ${perfil.id || 'N/A'}</small>
        </div>
      </div>
    `).join('');
  } catch (error) {
    status.textContent = 'sem conexao com banco';
  }
}

carregarPerfis();