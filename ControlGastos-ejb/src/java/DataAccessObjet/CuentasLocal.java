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
public interface CuentasLocal {
    
    /**
     * 
     * @return 
     */
    public List<Entities.Cuenta> getAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public Entities.Cuenta getById(int id);
    
    /**
     * 
     * @param name
     * @return 
     */
    public Entities.Cuenta getByName(String name);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean add(Entities.Cuenta entity);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean update(Entities.Cuenta entity);
    
    /**
     * 
     * @param id 
     * @return  
     */
    public boolean delete(int id);
    
}
