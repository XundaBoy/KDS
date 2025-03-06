package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Usuario;
import app.entity.Venda;
import app.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioService usuarioService;

    public String realizarVenda(Long jogoId, Long usuarioVendedorId, Long usuarioCompradorId) {
        // Buscar o jogo e os usuários com os serviços
        Jogo jogo = jogoService.findById(jogoId).orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        Usuario usuarioVendedor = usuarioService.findById(usuarioVendedorId).orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
        Usuario usuarioComprador = usuarioService.findById(usuarioCompradorId).orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        // Verificando se o vendedor tem o jogo
        if (!jogo.getUsuario().equals(usuarioVendedor)) {
            throw new RuntimeException("O vendedor não possui este jogo.");
        }

        // Criando a venda
        Venda venda = new Venda();
        venda.setJogo(jogo);
        venda.setUsuarioVendedor(usuarioVendedor);
        venda.setUsuarioComprador(usuarioComprador);

        // Alterando o dono do jogo para o comprador
        jogo.setUsuario(usuarioComprador);
        jogoService.save(jogo); // Atualizando o dono do jogo no banco de dados

        // Salvando a venda no banco de dados
        vendaRepository.save(venda);

        // Obtendo o preço do jogo
        float valor = jogo.getValor();

        // Retornando uma mensagem de sucesso com o valor da venda
        return String.format("Venda realizada com sucesso: O jogo '%s' foi vendido para '%s' por '%s' no valor de R$%.2f.",
                             jogo.getNome(), usuarioComprador.getNome(), usuarioVendedor.getNome(), valor);
    }
    public String delete (long id) {
		vendaRepository.deleteById(id);
		return "Usuario deletado com sucesso!";
	}
	
	public  Optional<Venda> findById(Long id) {
		return this.vendaRepository.findById(id);
	}
	
	
	
	public List<Venda> findAll(){
		List<Venda> lista = new ArrayList<>();
		lista = this.vendaRepository.findAll();
		return lista;
	}
	
	public List<Venda> findByJogo(Jogo jogo) {
		return vendaRepository.findByJogo(jogo);
	}
	
	public List<Venda> findByJogo_Console(Console console){
		return vendaRepository.findByJogo_Console(console);
	}
}
