package dao;

import model.AcaoAmbiental;
import model.Participacao;
import model.Voluntario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipacaoDAO {

    public void cadastrar(Voluntario voluntario, AcaoAmbiental acao) {
        String sql = "INSERT INTO participacao (voluntario_id, acao_id) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, voluntario.getId());
            stmt.setInt(2, acao.getId());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // Se a entidade Participacao for instanciada após isso e precisar de ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Participacao> listarPorVoluntario(int voluntarioId) {
        List<Participacao> lista = new ArrayList<>();
        String sql = "SELECT p.participacao_id, p.voluntario_id, p.acao_id FROM participacao p WHERE p.voluntario_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, voluntarioId);
            ResultSet rs = stmt.executeQuery();
            
            VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
            AcaoAmbientalDAO acaoDAO = new AcaoAmbientalDAO();
            
            Voluntario voluntario = voluntarioDAO.buscarPorId(voluntarioId);
            
            while (rs.next()) {
                int acaoId = rs.getInt("acao_id");
                AcaoAmbiental acao = acaoDAO.buscarPorId(acaoId);
                Participacao p = new Participacao(voluntario, acao);
                p.setId(rs.getInt("participacao_id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Participacao> listarTodas() {
        List<Participacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM participacao";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
            AcaoAmbientalDAO acaoDAO = new AcaoAmbientalDAO();
            
            while (rs.next()) {
                int voluntarioId = rs.getInt("voluntario_id");
                int acaoId = rs.getInt("acao_id");
                Voluntario v = voluntarioDAO.buscarPorId(voluntarioId);
                AcaoAmbiental a = acaoDAO.buscarPorId(acaoId);
                
                Participacao p = new Participacao(v, a);
                p.setId(rs.getInt("participacao_id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
