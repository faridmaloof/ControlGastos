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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farid
 */
@Stateless
public class Cuentas extends Connections implements CuentasLocal {
    
    MovimientosLocal movimientos;
    
    public Cuentas () {
        super();
        movimientos = new Movimientos();
    }

    @Override
    public List<Entities.Cuenta> getAll() {
        List<Entities.Cuenta> entities = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";
        Statement statement = null;
        
        try {
            statement = (Statement) getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                boolean esVisible = resultSet.getBoolean("es_visible");
                boolean activo = resultSet.getBoolean("activo");
                double saldo = 0;
                for(Entities.Movimiento movimiento: movimientos.getByCuentaId(id)) {
                    saldo += (movimiento.getValor() * ("Egreso".equals(movimiento.getNaturaleza().trim())? -1: 1));
                }
                
                Entities.Cuenta entity = new Entities.Cuenta(id, nombre, saldo, esVisible, activo);
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
    public Entities.Cuenta getById(int id) {
        Entities.Cuenta entity = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM cuentas WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            double saldo = 0;
            for(Entities.Movimiento movimiento: movimientos.getByCuentaId(id)) {
                saldo += (movimiento.getValor() * ("Egreso".equals(movimiento.getNaturaleza().trim())? -1: 1));
            }
            if (resultSet.next()) {
                entity = new Entities.Cuenta(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        saldo,
                        resultSet.getBoolean("es_visible"),
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
    public Entities.Cuenta getByName(String name) {
        Entities.Cuenta entity = null;
        PreparedStatement preparedStatement = null;
        String sql = "";
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM cuentas WHERE nombre = ?");
            preparedStatement.setString(1, name);
            
            sql = preparedStatement.toString();
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = new Entities.Cuenta(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getBoolean("es_visible"),
                        resultSet.getBoolean("activo")
                );
                
                
                double saldo = 0;
                for(Entities.Movimiento movimiento: movimientos.getByCuentaId(entity.getId())) {
                    saldo += (movimiento.getValor() * ("Egreso".equals(movimiento.getNaturaleza().trim())? -1: 1));
                }
                entity.setSaldo(saldo);
            }
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, ex);
            }
        }
        Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql);

        return entity;
    }

    @Override
    public boolean add(Entities.Cuenta entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("INSERT INTO cuentas(nombre, es_visible, activo) VALUES(?, ?, ?)");
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setBoolean(3, entity.getEsVisible());
            preparedStatement.setBoolean(2, entity.getActivo());

            boolean saveTemp = true;
            if (preparedStatement.executeUpdate() > 0 && entity.getSaldo()!= 0) {
                Entities.Movimiento temp = new Entities.Movimiento();
                temp.setNaturaleza(entity.getSaldo() >= 0? "Ingreso": "Egreso");
                temp.setFecha(new Date());
                temp.setValor(entity.getSaldo());
                temp.setCatergoriaId(0);
                temp.setTagId(0);
                temp.setTerceroId(0);
                temp.setCuentaId(getByName(entity.getNombre()).getId());                
                saveTemp = movimientos.add(temp);
            }
            success = saveTemp;
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
    public boolean update(Entities.Cuenta entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("UPDATE cuentas SET nombre = ?, es_visible = ?, activo = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setBoolean(2, entity.getEsVisible());
            preparedStatement.setBoolean(3, entity.getActivo());
            preparedStatement.setInt(4, entity.getId());

            double saldo = movimientos.getSaldoByCuenta(entity);
            
            boolean saveTemp = true;
            if (preparedStatement.executeUpdate() > 0 && saldo != 0) {
                Entities.Movimiento temp = new Entities.Movimiento();
                temp.setNaturaleza(entity.getSaldo() >= 0? "Ingreso": "Egreso");
                temp.setFecha(new Date());
                temp.setValor(entity.getSaldo());
                temp.setCatergoriaId(0);
                temp.setTagId(0);
                temp.setTerceroId(0);
                temp.setCuentaId(getByName(entity.getNombre()).getId());                
                saveTemp = movimientos.add(temp);
            }
            
            success = saveTemp;
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
            preparedStatement = getConnection().prepareStatement("UPDATE cuentas SET activo = 0 WHERE id = ?");
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
                                <select class="select form-control" name="cuenta" required>
                                    <option disabled selected>Seleccione una cuenta</option>""";
        
        for(Entities.Cuenta cuenta: new Cuentas().getAll()) {
            if (cuenta.getActivo())
                Lista += "<option value=\""+cuenta.getId()+"\">"+cuenta.getNombre()+"</option>";
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
                                <select class="select form-control" name="cuenta" required>
                                    <option disabled selected>Seleccione una cuenta</option>""";
        
        for(Entities.Cuenta cuenta: new Cuentas().getAll()) {
            if (cuenta.getActivo())
                Lista += "<option value=\""+cuenta.getId()+"\""+(cuenta.getId()==idSelected? " selected": "")+">"+cuenta.getNombre()+"</option>";
        }
        
        return Lista + "</select>";
    }
}
