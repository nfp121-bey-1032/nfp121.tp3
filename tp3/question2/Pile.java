package question2;

import question1.PilePleineException;
import question1.PileVideException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A remplacer en partie par votre classe Pile de la question 1.
 * 
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class Pile implements PileI {

    private Object[] zone;
    private int ptr;

    public Pile(int taille) {
        if(taille <= 0) taille = CAPACITE_PAR_DEFAUT;
        this.zone = new Object[taille];
        this.ptr = 0;
    }

    public Pile() {
        this(CAPACITE_PAR_DEFAUT);
    }

    public void empiler(Object object) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();
        this.zone[this.ptr] = object;
        this.ptr++;
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        this.ptr--;
        return zone[ptr];
    }

    public Object sommet() throws PileVideException {
        if(estVide())
            throw new PileVideException();
        return zone[ptr-1];
    }

    public int capacite() {
        return zone.length;
    }

    public int taille() {
        return ptr;
    }

    public boolean estVide() {
        return ptr == 0;
    }

    public boolean estPleine() {
        return ptr == zone.length;
    }

    public boolean equals(Object object){
        if(object == null) return false;
        
        if(! (object instanceof PileI))
            return false;
        PileI pile = (PileI)object;
        
        
        if(super.equals(object))
            return true;
      
        int capacite = this.capacite();
        int taille = this.taille();
        if(capacite != pile.capacite())
            return false;
        if(taille != pile.taille())
            return false;
            
        if(taille == 0) return true;
        
        Pile tempPile = new Pile(taille);
        
        boolean elementsEgaux;
        
        for(int i=taille-1; i>=0 ; i--){
            try{
                elementsEgaux = false;
                
                if(pile.sommet() == null){
                    if(zone[i] == null) 
                        elementsEgaux = true;
                    }   
                else if(zone[i] == null){
                    if(pile.sommet() == null) 
                        elementsEgaux = true;
                }
                else if(pile.sommet().equals(zone[i])){
                    elementsEgaux = true;
                }
                if(elementsEgaux)
                    tempPile.empiler(pile.depiler());
                else{
                    remplirPile(tempPile, pile);
                    return false;
                }
            } catch(PilePleineException ppe){ppe.printStackTrace();}
            catch(PileVideException pve){pve.printStackTrace();}
        }  
      
        remplirPile(tempPile, pile);
        
        return true;
    }
    
    private void remplirPile(PileI tempPile, PileI pile){
        int taillePile = tempPile.taille();
        for(int i=0; i<taillePile; i++){
            try{
                pile.empiler(tempPile.depiler());
            } catch(PileVideException pve){pve.printStackTrace();}
            catch(PilePleineException ppe){ppe.printStackTrace();}
        }
    }

    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = ptr - 1; i >= 0; i--) {
            sb.append(zone[i].toString());
            if (i > 0)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}