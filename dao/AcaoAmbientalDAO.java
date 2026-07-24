package dao;

import model.AcaoAmbiental;
import model.EducacaoAmbiental;
import model.Limpeza;
import model.Plantio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcaoAmbientalDAO {

    public void cadastrar(AcaoAmbiental acao) {
        String sql = "INSERT INTO acao_ambiental (acao_local, acao_data, duracao_horas, acao_tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, acao.getLocal());
            stmt.setDate(2, Date.valueOf(acao.getData()));
            stmt.setDouble(3, acao.getDuracaoHoras());
            
            String tipo = "";
            if (acao instanceof Limpeza) {
                tipo = "Limpeza";
            } else if (acao instanceof Plantio) {
                tipo = "Plantio";
            } else if (acao instanceof EducacaoAmbiental) {
                tipo = "EducacaoAmbiental";
            }
            stmt.setString(4, tipo);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                acao.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AcaoAmbiental buscarPorId(int id) {
        String sql = "SELECT * FROM acao_ambiental WHERE acao_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return instanciarAcao(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AcaoAmbiental> listarTodas() {
        List<AcaoAmbiental> lista = new ArrayList<>();
        String sql = "SELECT * FROM acao_ambiental";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                lista.add(instanciarAcao(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(AcaoAmbiental acao) {
        String sql = "UPDATE acao_ambiental SET acao_local = ?, acao_data = ?, duracao_horas = ? WHERE acao_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, acao.getLocal());
            stmt.setDate(2, Date.valueOf(acao.getData()));
            stmt.setDouble(3, acao.getDuracaoHoras());
            stmt.setInt(4, acao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM acao_ambiental WHERE acao_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private AcaoAmbiental instanciarAcao(ResultSet rs) throws SQLException {
        String tipo = rs.getString("acao_tipo");
        if (tipo != null) {
            tipo = tipo.trim().toUpperCase();
        } else {
            tipo = "";
        }
        
        AcaoAmbiental acao;
        
        if ("LIMPEZA".equals(tipo)) {
            acao = new Limpeza(rs.getString("acao_local"), rs.getDate("acao_data").toLocalDate(), rs.getDouble("duracao_horas"), 0);
        } else if ("PLANTIO".equals(tipo)) {
            acao = new Plantio(rs.getString("acao_local"), rs.getDate("acao_data").toLocalDate(), rs.getDouble("duracao_horas"), 0);
        } else if ("EDUCACAO_AMBIENTAL".equals(tipo) || "EDUCACAO AMBIENTAL".equals(tipo) || "EDUCACAOAMBIENTAL".equals(tipo) || "EDUCAÇÃO AMBIENTAL".equals(tipo)) {
            acao = new EducacaoAmbiental(rs.getString("acao_local"), rs.getDate("acao_data").toLocalDate(), rs.getDouble("duracao_horas"), 0);
        } else {
            // Fallback default para evitar NPE
            acao = new AcaoAmbiental(rs.getString("acao_local"), rs.getDate("acao_data").toLocalDate(), rs.getDouble("duracao_horas")) {
                @Override
                public double fatorDeImpacto() {
                    return 1.0;
                }
            };
        }
        
        acao.setId(rs.getInt("acao_id"));
        return acao;
    }
}
