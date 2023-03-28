/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package DataAccessObjet;

import jakarta.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farid
 */
@Stateless
public class Tags extends Connections implements TagsLocal {
    
    public Tags () {
        super();
    }

    @Override
    public List<Entities.Tag> getAll() {
        List<Entities.Tag> entities = new ArrayList<>();
        String sql = "SELECT * FROM tags";
        Statement statement = null;
        
        try {
            statement = (Statement) getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                boolean activo = resultSet.getBoolean("activo");
                Entities.Tag entity = new Entities.Tag(id, nombre, activo);
                entities.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return entities;
    }

    @Override
    public Entities.Tag getById(int id) {
        Entities.Tag entity = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM tags WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = new Entities.Tag(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getBoolean("activo")
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return entity;
    }

    @Override
    public boolean add(Entities.Tag entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("INSERT INTO tags(nombre, activo) VALUES(?, ?)");
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setBoolean(2, entity.getActivo());

            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }

    @Override
    public boolean update(Entities.Tag entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("UPDATE tags SET nombre = ?, activo = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setBoolean(2, entity.getActivo());
            preparedStatement.setInt(3, entity.getId());

            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }

    @Override
    public boolean delete(int id) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM tags WHERE id = ?");
            preparedStatement.setInt(1, id);

            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return success;
    }
    
    /**
     * 
     * @return 
     */
    public static String List() {
                String Lista = """
                                <select class="select form-control" name="tag">
                                    <option disabled selected>Seleccione los tags</option>""";
        
        for(Entities.Tag tag: new Tags().getAll()) {
            if (tag.getActivo())
                Lista += "<option value=\""+tag.getId()+"\">"+tag.getNombre()+"</option>";
        }
        
        return Lista + "</select>";
    }
    
    /**
     * 
     * @param idSelected
     * @return 
     */
    public static String List(int idSelected) {
                String Lista = """
                                <select class="select form-control" name="tag">
                                    <option disabled selected>Seleccione los tags</option>""";
        
        for(Entities.Tag tag: new Tags().getAll()) {
            if (tag.getActivo())
                Lista += "<option value=\""+tag.getId()+"\""+(tag.getId()==idSelected? " selected": "")+">"+tag.getNombre()+"</option>";
        }
        
        return Lista + "</select>";
    }
}
