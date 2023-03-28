/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package DataAccessObjet;

import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author farid
 */
@Local
public interface MovimientosLocal {
    
    /**
     * 
     * @return 
     */
    public List<Entities.Movimiento> getAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public Entities.Movimiento getById(int id);
    
    /**
     * 
     * @param entitiy
     * @return 
     */
    public List<Entities.Movimiento> getByCuenta(Entities.Cuenta entitiy);
    
    /**
     * 
     * @param id
     * @return 
     */
    public List<Entities.Movimiento> getByCuentaId(int id);
    
    /**
     * 
     * @param entitiy
     * @return 
     */
    public double getSaldoByCuenta(Entities.Cuenta entitiy);
    
    /**
     * 
     * @param id
     * @return 
     */
    public double getSaldoByCuentaId(int id);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean add(Entities.Movimiento entity);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean update(Entities.Movimiento entity);
    
    /**
     * 
     * @param id 
     * @return  
     */
    public boolean delete(int id);
    
}
