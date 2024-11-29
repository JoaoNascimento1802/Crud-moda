document.getElementById('uploadForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    // Obtenha os dados do formulário
    const id = document.getElementById('id').value;
    const fileInput = document.getElementById('file');
    const file = fileInput.files[0];

    if (!file) {
        displayMessage('Por favor, selecione uma imagem.', 'error');
        return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
        const response = await fetch(`http://localhost:8080/image/upload/${id}`, {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const result = await response.json();
            displayMessage(`Upload bem-sucedido! URL da imagem: ${result.url}`, 'success');
        } else {
            const errorData = await response.json();
            displayMessage(`Erro: ${errorData.message || 'Ocorreu um problema no upload.'}`, 'error');
        }
    } catch (error) {
        console.error('Erro ao enviar a imagem:', error);
        displayMessage('Erro de conexão. Verifique o console para mais detalhes.', 'error');
    }
});

function displayMessage(message, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = message;
    messageDiv.style.color = type === 'success' ? 'green' : 'red';
}