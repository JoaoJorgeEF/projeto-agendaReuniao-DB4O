package dao;

import com.db4o.query.Query;
import modelo.Convidado;

import java.util.List;

public class DAOConvidado extends DAO<Convidado>{

    public Convidado read(Object chave) {
        String convidado = (String) chave;

        Query q = manager.query();
        q.constrain(Convidado.class);
        q.descend("nome").constrain(convidado).contains();
        List<Convidado> result = q.execute();

        if (result.size() == 0)
            return null;
        return result.get(0);
    }
}
