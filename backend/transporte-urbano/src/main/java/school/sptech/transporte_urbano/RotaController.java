package school.sptech.transporte_urbano;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    @PostMapping
    public ResponseEntity<Rota> criarRota(@RequestBody Rota rota) {

        if (rota.getDestino() == null || rota.getDestino().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        if (rota.getEstacaoInicial() == null || rota.getEstacaoInicial().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        if (rota.getEstacaoFinal() == null || rota.getEstacaoFinal().isBlank()) {
            return ResponseEntity.status(400).build();
        }

        if (rota.getDuracaoMinutos() == null || rota.getDuracaoMinutos() <= 0) {
            return ResponseEntity.status(400).build();
        }

        if (rota.getQtdBaldeacoes() == null || rota.getQtdBaldeacoes() < 0) {
            return ResponseEntity.status(400).build();
        }

        String sql = "insert into rota (destino, estacaoInicial, estacaoFinal, duracaoMinutos, qtdBaldeacoes) " +
                "values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, rota.getDestino());
            ps.setString(2, rota.getEstacaoInicial());
            ps.setString(3, rota.getEstacaoFinal());
            ps.setInt(4, rota.getDuracaoMinutos());
            ps.setInt(5, rota.getQtdBaldeacoes());

            return ps;
        }, keyHolder);

        Integer idGerado = keyHolder.getKeyAs(Integer.class);
        rota.setId(idGerado);
        return ResponseEntity.status(201).body(rota);
    }
}
