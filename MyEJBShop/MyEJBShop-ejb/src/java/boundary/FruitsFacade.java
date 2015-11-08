/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary;

import entities.Fruits;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexandre
 */
@Stateless
public class FruitsFacade extends AbstractFacade<Fruits> implements FruitsFacadeLocal {
    @PersistenceContext(unitName = "MyEJBShop-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FruitsFacade() {
        super(Fruits.class);
    }

    @Override
    public Fruits findByName(String name) {
     Query q = em.createNamedQuery("Fruits.findByName");     
     q.setParameter("name", name);     
     return (Fruits)q.getSingleResult();    
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getFruitsList() {
        ArrayList outList = new ArrayList();     
        try {         
            Iterator i = findAll().iterator();         
            while (i.hasNext()) {             
                Fruits f = (Fruits) i.next();             
                outList.add(f.getName() + ", " + f.getPrice() + " kr, " +
                        f.getQuantity() + " in stock");
            }
        }
        catch (Exception e) {        
            outList.add(e.toString());     
        }
        return outList; 
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
