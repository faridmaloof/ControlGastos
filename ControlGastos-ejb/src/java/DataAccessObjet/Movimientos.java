/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package DataAccessObjet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.Stateless;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farid
 */
@Stateless
public class Movimientos extends Connections implements MovimientosLocal {
    
    public Movimientos () {
        super();
    }

    @Override
    public List<Entities.Movimiento> getAll() {
        List<Entities.Movimiento> entities = new ArrayList<>();
        String sql = "SELECT * FROM movimientos ORDER BY fecha DESC, Id Desc";
        Statement statement = null;
        
        try {
            statement = (Statement) getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String naturaleza = resultSet.getString("naturaleza");
                Date fecha = resultSet.getDate("fecha");
                double valor = resultSet.getDouble("valor");
                Integer idCatergoria = resultSet.getInt("id_catergoria");
                Integer idCuenta = resultSet.getInt("id_cuenta");
                Integer idTag = resultSet.getInt("id_tag");
                Integer idTercero = resultSet.getInt("id_tercero");
                Entities.Movimiento entity = new Entities.Movimiento(id, naturaleza, fecha, valor, idCatergoria, idCuenta, idTag, idTercero);
                entities.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, e);
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
    public Entities.Movimiento getById(int id) {
        Entities.Movimiento entity = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM movimientos WHERE id = ? ORDER BY fecha DESC, Id Desc");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = new Entities.Movimiento(
                    resultSet.getInt("id"),
                    resultSet.getString("naturaleza"),
                    resultSet.getDate("fecha"),
                    resultSet.getDouble("valor"),
                    resultSet.getInt("id_catergoria"),
                    resultSet.getInt("id_cuenta"),
                    resultSet.getInt("id_tag"),
                    resultSet.getInt("id_tercero")
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
    
    /**
     * 
     * @param entitiy
     * @return 
     */
    @Override
    public List<Entities.Movimiento> getByCuenta(Entities.Cuenta entitiy) {
        return getByCuentaId(entitiy.getId());
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public List<Entities.Movimiento> getByCuentaId(int id) {
        List<Entities.Movimiento> entities = new ArrayList<>();
        String sql = "SELECT * FROM movimientos WHERE id_cuenta = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                String naturaleza = resultSet.getString("naturaleza");
                Date fecha = resultSet.getDate("fecha");
                double valor = resultSet.getDouble("valor");
                Integer idCatergoria = resultSet.getInt("id_catergoria");
                Integer idCuenta = resultSet.getInt("id_cuenta");
                Integer idTag = resultSet.getInt("id_tag");
                Integer idTercero = resultSet.getInt("id_tercero");
                Entities.Movimiento entity = new Entities.Movimiento(id, naturaleza, fecha, valor, idCatergoria, idCuenta, idTag, idTercero);
                entities.add(entity);
            }
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return entities;
    }
    
    /**
     * 
     * @param entitiy
     * @return 
     */
    @Override
    public double getSaldoByCuenta(Entities.Cuenta entitiy) {
        return getSaldoByCuentaId(entitiy.getId());
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public double getSaldoByCuentaId(int id) { 
        double saldo = 0;
        for ( Entities.Movimiento movimiento: getByCuentaId(id)) {
            saldo += (("Ingreso".equals(movimiento.getNaturaleza())? 1: -1) * movimiento.getValor());
        }
        
        return saldo;
    }

    @Override
    public boolean add(Entities.Movimiento entity) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        String sql = "";
        try {
            preparedStatement = getConnection().prepareStatement("INSERT INTO movimientos(naturaleza, fecha, valor, id_tag, id_tercero, id_cuenta, id_catergoria) VALUES(?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getNaturaleza());
            preparedStatement.setDate(2, new java.sql.Date(entity.getFecha().getTime()));                
            preparedStatement.setDouble(3, entity.getValor());
            
            if (entity.getTagId()==0)
                preparedStatement.setNull(4, java.sql.Types.NULL);
            else
                preparedStatement.setInt(4, entity.getTagId());
            
            if (entity.getTerceroId()==0)
                preparedStatement.setNull(5, java.sql.Types.NULL);
            else
                preparedStatement.setInt(5, entity.getTerceroId());
            
            if (entity.getCuentaId()==0)
                preparedStatement.setNull(6, java.sql.Types.NULL);
            else
                preparedStatement.setInt(6, entity.getCuentaId());
            
            if (entity.getCatergoriaId()==0)
                preparedStatement.setNull(7, java.sql.Types.NULL);
            else
                preparedStatement.setInt(7, entity.getCatergoriaId());

            success = preparedStatement.executeUpdate()> 0;
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, ex);
            }
        }

        return success;
    }

    @Override
    public boolean update(Entities.Movimiento entity) {
        boolean success = false;       
        PreparedStatement preparedStatement = null;
        String sql ="";
        
        try {
            preparedStatement = getConnection().prepareStatement("UPDATE movimientos SET naturaleza = ?, fecha = ?, valor = ?, id_tag = ?, id_tercero = ?, id_cuenta = ?, id_catergoria = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getNaturaleza());
            preparedStatement.setDate(2, new java.sql.Date(entity.getFecha().getTime()));
            preparedStatement.setDouble(3, entity.getValor());
            
            if (entity.getTagId()==0)
                preparedStatement.setNull(4, java.sql.Types.NULL);
            else
                preparedStatement.setInt(4, entity.getTagId());
            
            if (entity.getTerceroId()==0)
                preparedStatement.setNull(5, java.sql.Types.NULL);
            else
                preparedStatement.setInt(5, entity.getTerceroId());
            
            if (entity.getCuentaId()==0)
                preparedStatement.setNull(6, java.sql.Types.NULL);
            else
                preparedStatement.setInt(6, entity.getCuentaId());
            
            if (entity.getCatergoriaId()==0)
                preparedStatement.setNull(7, java.sql.Types.NULL);
            else
                preparedStatement.setInt(7, entity.getCatergoriaId());
            preparedStatement.setInt(8, entity.getId());

            sql = preparedStatement.toString();
            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, ex);
            }
        }

        return success;
    }

    @Override
    public boolean delete(int id) {
        boolean success = false;
        PreparedStatement preparedStatement = null;
        String sql = "";
        
        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM movimientos WHERE id = ?");
            preparedStatement.setInt(1, id);
            sql = preparedStatement.toString();
            
            success = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, sql, ex);
            }
        }

        return success;
    }
}
