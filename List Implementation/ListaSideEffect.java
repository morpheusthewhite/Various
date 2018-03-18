/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listase;

/**
 *
 * @author studente15
 */
public class Lista<T> {
    private Nodo<T> init;
    
    public Lista ()
    {
        init=null;
    }
    
    private Lista(Nodo<T> temp)
    {
        init=temp;
    }
    
    public void listaVuota()
    {
        this.init = null;
        return;
    }
    
    public boolean estVuota()
    {
        return init==null;
    }
    
    public void cons(T e)
    {
        //inserisce e come primo elemento
        Nodo<T> temp=new Nodo(e);
        temp.next=init;
        init=temp;
    }
    
    public T car()
    {
        return init.info;
    }
    
    public void cdr()
    {
        init=init.next;
    }
    
    public boolean equals(Object o)
    {
        if(o==null || o.getClass()!=this.getClass())
            return false;
        Lista<T> other=(Lista<T>)o;
        //chiama funzione per lunghezza della lista
        
        Nodo<T> aux1=this.init, aux2=other.init;
        while(aux1!=null && aux2!=null)
        {
            if(aux1.info!=aux2.info)
                return false;
            aux1=aux1.next;
            aux2=aux2.next;
        }
        
        if(aux1!=aux2)
            return false;
        
        return true;
    }
    
    public int hashCode()
    {
        int res=0;
        Nodo<T> aux=init;
        while(aux!=null)
        {
            res+=(aux.info).hashCode();
        }
        return res;
    }
    
    public Lista<T> clone()
    {
        Lista<T> res=new Lista<T>();
        Nodo<T> aux=this.init, aux_res=null;
        while(aux!=null)
        {
            if(aux_res==null)
            {
                res.init=new Nodo<T>(aux.info);
                aux_res=res.init;
            }
            else
            {
                aux_res.next=new Nodo<T>(aux.info);
                aux_res=aux_res.next;
            }
            aux=aux.next;
        }
        return res;
    }

    @Override
    public String toString() {
        return "Lista{" + "init=" + init + '}';
    }
    
}
