package br.com.junior.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.junior.entidade.Usuario;
import br.com.junior.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/todos")
    public List<Usuario> pesquisarTodo() {
        return usuarioRepositorio.findAll();
    }

    @GetMapping("/pesquisarPorNome/{nome}")
    public List<Usuario> pesquisarPorNome(@PathVariable String nome) {
//        return usuarioRepositorio.findByNomeContaining(nome);
        return usuarioRepositorio.pesquisarPorNome(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> pesquisarPorId(@PathVariable Long id) {
        Optional<Usuario> usu = usuarioRepositorio.findById(id);
        if (usu.isPresent()) {
            return ResponseEntity.ok(usu.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (!usuarioRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        usuarioRepositorio.save(usuario);
        return ResponseEntity.ok(usuario);
    }
//    @PutMapping()
//    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
//        if (!usuarioRepositorio.existsById(usuario.getId())) {
//            return ResponseEntity.notFound().build();
//        }
//        usuarioRepositorio.save(usuario);
//        return ResponseEntity.ok(usuario);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> excluir(@PathVariable Long id) {
        if (!usuarioRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
