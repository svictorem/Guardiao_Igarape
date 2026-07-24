package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Voluntario;

public class VoluntarioDAO {

    public void cadastrar(Voluntario voluntario) {
        String sql = "INSERT INTO voluntario (voluntario_nome, telefone) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, voluntario.getNome());
            stmt.setString(2, voluntario.getTelefone());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                voluntario.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Voluntario buscarPorId(int id) {
        String sql = "SELECT * FROM voluntario WHERE voluntario_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Voluntario v = new Voluntario(rs.getString("voluntario_nome"), rs.getString("telefone"));
                v.setId(rs.getInt("voluntario_id"));
                return v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Voluntario buscarPorNome(String nome) {
        String sql = "SELECT * FROM voluntario WHERE voluntario_nome = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Voluntario v = new Voluntario(rs.getString("voluntario_nome"), rs.getString("telefone"));
                v.setId(rs.getInt("voluntario_id"));
                return v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Voluntario> listarTodos() {
        List<Voluntario> lista = new ArrayList<>();
        String sql = "SELECT * FROM voluntario";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Voluntario v = new Voluntario(rs.getString("voluntario_nome"), rs.getString("telefone"));
                v.setId(rs.getInt("voluntario_id"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Voluntario voluntario) {
        String sql = "UPDATE voluntario SET voluntario_nome = ?, telefone = ? WHERE voluntario_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, voluntario.getNome());
            stmt.setString(2, voluntario.getTelefone());
            stmt.setInt(3, voluntario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM voluntario WHERE voluntario_id = ?";
        try (Connection conn = ConexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
