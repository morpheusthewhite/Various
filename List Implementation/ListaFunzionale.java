/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package listafunzionale;
//ogni operazione restituisce un nuovo oggetto;
//bisogna fare override di equals, hashcode ma non di clone

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
    
    public Lista<T> listaVuota()
    {
        return new Lista<T>();
    }
    
    public boolean estVuota()
    {
        return init==null;
    }
    
    public Lista<T> cons(T e)
    {
        //inserisce e come primo elemento
        Nodo<T> temp=new Nodo(e);
        temp.next=this.init;
        return new Lista<T>(temp);
    }
    
    public T car()
    {
        return init.info;
    }
    
    public Lista<T> cdr()
    {
        return new Lista<T>(init.next);
    }
    
    public boolean equals(Object o)
    {
        if(o==null || o.getClass()!=this.getClass())
            return false;
        Lista<T> other=(Lista<T>)o;
        
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
    
    public int lunghezza()
    {
        if(this.init==null)
            return 0;
        else
            return (new Lista(init.next)).lunghezza()+1;
    }
    
    public Lista<T> aggiuntaCoda(T e)
    {
        
        if(this.init==null)
        {
            Nodo<T> temp=new Nodo(e);
            this.init=temp;
            return this;
        }
        else
        {
            //controlla meglio
            Lista<T> res=new Lista<T>();
            res.init=new Nodo<T>(this.init.info);
            Lista<T> temp=(new Lista<T>(init.next)).aggiuntaCoda(e);
            res.init.next=temp.init;
            return res;
        }
    }
    
    public T index(int i)
    {
        if(i==0)
            return init.info;
        else
        {
            return (new Lista<T>(this.init.next)).index(i-1);
        }
    }
    
    public Lista<T> append(Lista<T> append_from)
    {
        if(this.init==null)
        {
            return append_from;
        }
        else
        {
            //controlla meglio
            Lista<T> res=new Lista<T>();
            res.init=new Nodo<T>(this.init.info);
            Lista<T> temp=(new Lista<T>(init.next)).append(append_from);
            res.init.next=temp.init;
            return res;
        }
    }
    
    public Lista<T> setIndex(T e, int i)
    {
        if(i==0)
        {
            Nodo<T> temp_node=new Nodo(e);
            temp_node.next=this.init;
            this.init=temp_node;
            return this;
        }
        else
        {
            Lista<T> res=new Lista<T>();
            res.init=new Nodo<T>(this.init.info);
            Lista<T> temp=(new Lista<T>(init.next)).setIndex(e, i-1);
            res.init.next=temp.init;
            return res;
        }
    }

    @Override
    public String toString() {
        return "Lista{" + "init=" + init + '}';
    }
    
    
}
