package app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.entity.Usuario;
import app.exception.UsernameAlreadyUsedException;
import app.repository.UsuarioRepository;

@Service
public class UsuarioService {

		@Autowired
		private UsuarioRepository usuarioRepository;
		/*
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		public String save (Usuario usuario) {
			if(usuarioRepository.existsByCpf(usuario.getCpf())) {
				throw new IllegalArgumentException("Cpf ja cadastrado");
				
				
			}
			 if (usuario.getTelefone() == null || usuario.getTelefone().trim().isEmpty()) {
		            usuario.setStatusCadastro("INCOMPLETO"); 
		        } else {
		            usuario.setStatusCadastro("COMPLETO"); 
		        }
			 
			String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
		     usuario.setPassword(senhaCriptografada); 
		     usuario.setRole("ROLE_USER");
			 
			usuarioRepository.save(usuario);
			return "Usuario salvo com sucesso!";
		}
		*/
		public String update (Usuario usuario, Long id) {
			usuario.setId(id);
			this.usuarioRepository.save(usuario);
			return "Usuario atualizado com sucesso";
		}
		
		public String delete (long id) {
			usuarioRepository.deleteById(id);
			return "Usuario deletado com sucesso!";
		}
		
		public  Optional<app.entity.Usuario> findById(Long id) {
			return this.usuarioRepository.findById(id);
		}
		
		public List<app.entity.Usuario> findByNomeStartingWithIgnoreCase(String nome){
			return this.usuarioRepository.findByNomeStartingWithIgnoreCase(nome);
		}
		
		public List<app.entity.Usuario> findAll(){
			List<app.entity.Usuario> lista = new ArrayList<>();
			lista = this.usuarioRepository.findAll();
			return lista;
		}
		
		
	    public Usuario connect(Usuario user) throws UsernameAlreadyUsedException {
			Usuario dbUser = usuarioRepository.findByUsername(user.getUsername());

	        if (dbUser != null) {

	            if (dbUser.getConnected()) {
	                throw new UsernameAlreadyUsedException("This user is already connected: " + dbUser.getUsername());
	            }

	            dbUser.setConnected(true);
	            return usuarioRepository.save(dbUser);
	        }

	        user.setConnected(true);
	        return usuarioRepository.save(user);
	    }

	    
	    public Usuario disconnect(Usuario user) {
	        if (user == null) {
	            return null;
	        }

	        Usuario dbUser = usuarioRepository.findByUsername(user.getUsername());
	        if (dbUser == null) {
	            return user;
	        }

	        dbUser.setConnected(false);
	        return usuarioRepository.save(dbUser);
	    }

		
		
}
