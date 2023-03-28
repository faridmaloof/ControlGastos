/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package DataAccessObjet;

import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author farid
 */
@Local
public interface CategoriasLocal {
    
    /**
     * 
     * @return 
     */
    public List<Entities.Categoria> getAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public Entities.Categoria getById(int id);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean add(Entities.Categoria entity);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean update(Entities.Categoria entity);
    
    /**
     * 
     * @param id 
     * @return  
     */
    public boolean delete(int id);
}
