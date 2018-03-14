import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Main {

    public static void main(String[] args) {
        Set<Regina> posizionate=new HashSet<Regina>();
        risolvi(8, posizionate);
    }
    
    public static void risolvi(int R_rimaste, Set<Regina> posizionate)
    {
        if(R_rimaste==0)    //caso base
        {
            if(scacchiera_corretta(posizionate))
            {
                System.out.println("Trovata soluzione: "+ posizionate);
                return;
            }
        }
        else
        {
            Regina temp;
            //chiamate ricorsive
            for(int j=0; j<8; j++)
            {
                temp=new Regina(8-R_rimaste,j);
                if(!posizionate.contains(temp)) //chiamata ricorsiva solo se regina non presente nella casella
                {
                    posizionate.add(temp);
                    risolvi(R_rimaste-1, posizionate);
                    posizionate.remove(temp);
                }
            }
        }
    }
    
    public static boolean scacchiera_corretta(Set<Regina> posizionate)
    {
        Iterator<Regina> it=posizionate.iterator(), it2;
        Regina next, next2;
        while (it.hasNext()) {
            next = it.next();
            it2=posizionate.iterator();
            while (it2.hasNext()) {
                next2 = it2.next();
                //verifico che le due regine next e next2 non si minaccino a vicenda
                if(next2.equals(next))continue;
                else if(next.y==next2.y || (next.x+next.y)==(next2.x+next2.y) || (next.x+8-next.y)==(next2.x+8-next2.y))
                {
                    return false;
                }
            }
        }
        return true;
    }
}

