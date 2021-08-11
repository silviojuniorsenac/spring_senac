package br.com.junior.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.junior.entidade.Usuario;
import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeContaining(String nome);
    
    @Query("from Usuario where nome like %:nome%")
    List<Usuario> pesquisarPorNome(String nome);
    
}
