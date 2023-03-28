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
public class Terceros extends Connections implements TercerosLocal {
    
    public Terceros () {
        super();
    }

    @Override
    public List<Entities.Tercero> getAll() {
        List<Entities.Tercero> entities = new ArrayList<>();
        String sql = "SELECT * FROM terceros";
        Statement statement = null;
        
        try {
            statement = (Statement) getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numeroIdentificacion = resultSet.getString("numero_identificacion");
                String nombre = resultSet.getString("nombre");
                boolean activo = resultSet.getBoolean("activo");
                Entities.Tercero entity = new Entities.Tercero(id, numeroIdentificacion, nombre, activo);
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
    public Entities.Tercero getById(int id) {
        Entities.Tercero entity = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM terceros WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = new Entities.Tercero(
                        resultSet.getInt("id"),
                        resultSet.getString("numero_identificacion"),
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
    public boolean add(Entities.Tercero entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("INSERT INTO terceros(numero_identificacion, nombre, activo) VALUES(?, ?, ?)");
            preparedStatement.setString(1, entity.getNumeroIdentificacion());            
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setBoolean(3, entity.getActivo());

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
    public boolean update(Entities.Tercero entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("UPDATE terceros SET numero_identificacion = ?, nombre = ?, activo = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getNumeroIdentificacion());            
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setBoolean(3, entity.getActivo());
            preparedStatement.setInt(4, entity.getId());

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
            preparedStatement = getConnection().prepareStatement("UPDATE terceros SET activo = 0 WHERE id = ?");
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
                                <select class="select form-control" name="tercero">
                                    <option disabled selected>Seleccione un tercero</option>""";
        
        for(Entities.Tercero tercero: new Terceros().getAll()) {
            if (tercero.getActivo())
                Lista += "<option value=\""+tercero.getId()+"\">"+tercero.getNombre()+"</option>";
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
                                <select class="select form-control" name="tercero">
                                    <option disabled selected>Seleccione un tercero</option>""";
        
        for(Entities.Tercero tercero: new Terceros().getAll()) {
            if (tercero.getActivo())
                Lista += "<option value=\""+tercero.getId()+"\""+(tercero.getId()==idSelected? " selected": "")+">"+tercero.getNombre()+"</option>";
        }
        
        return Lista + "</select>";
    }
}
