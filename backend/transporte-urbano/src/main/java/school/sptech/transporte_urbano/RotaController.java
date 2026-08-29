package school.sptech.transporte_urbano;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rotas")
public class RotaController {

    private final JdbcTemplate jdbcTemplate;

    public RotaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Rota>> listarRotas() {
        String sql = "select * from rota";
        List<Rota> rotas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Rota.class));
        return ResponseEntity.status(200).body(rotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rota> listarPorId(@PathVariable Integer id) {
        String sql = "select * from rota where id = ?";

        try {
            Rota rota = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Rota.class), id);
            return ResponseEntity.status(200).body(rota);
        }

        catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(404).build();
        }


    }
}
