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
public interface TercerosLocal {
    
    /**
     * 
     * @return 
     */
    public List<Entities.Tercero> getAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public Entities.Tercero getById(int id);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean add(Entities.Tercero entity);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean update(Entities.Tercero entity);
    
    /**
     * 
     * @param id 
     * @return  
     */
    public boolean delete(int id);
}
