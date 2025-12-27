package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Usuario;
import app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    
    public Usuario save(Usuario usuario) {

        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        usuario.setPassword(null);       // Senha gerenciada pelo Keycloak
        usuario.setRole("ROLE_USER");    // Controlado externamente depois

        return usuarioRepository.save(usuario);
    }


    
    public Usuario update(Long id, Usuario dados) {

        Usuario existente = findById(id);

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setTelefone(dados.getTelefone());
        existente.setCidade(dados.getCidade());
        existente.setRanking(dados.getRanking());

        return usuarioRepository.save(existente);
    }


    public void delete(Long id) {
        if(!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }


    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> findByNomeStartingWithIgnoreCase(String nome){
        return usuarioRepository.findByNomeStartingWithIgnoreCase(nome);
    }
}
