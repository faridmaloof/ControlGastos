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
public interface TagsLocal {
    
    /**
     * 
     * @return 
     */
    public List<Entities.Tag> getAll();
    
    /**
     * 
     * @param id
     * @return 
     */
    public Entities.Tag getById(int id);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean add(Entities.Tag entity);
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public boolean update(Entities.Tag entity);
    
    /**
     * 
     * @param id 
     * @return  
     */
    public boolean delete(int id);
    
}
