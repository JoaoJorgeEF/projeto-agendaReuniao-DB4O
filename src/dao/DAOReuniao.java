package dao;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import modelo.Convidado;
import modelo.Participante;
import modelo.Reuniao;
import java.util.List;
import com.db4o.query.Query;


public class DAOReuniao extends DAO<Reuniao>{

    public Reuniao read (Object chave) {
        int reuniao = (int) chave;

        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.descend("id").constrain(reuniao).contains();
        List<Reuniao> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }
    
    public void create(Reuniao obj){
        Reuniao reuniao = (Reuniao) obj;
        int id = super.getMaxId();
        id++;
        reuniao.setId(id);
        manager.store(reuniao);
    }

    //consultas:

    public List<Reuniao> consultarConvidado() {
        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.constrain(new Filtro() );
        List<Reuniao> result = q.execute();
        return result;
    }

    //Filtro

    class Filtro implements Evaluation {

        public void evaluate(Candidate candidate) {
            Reuniao r = (Reuniao) candidate.getObject();
            boolean resposta = false;
            if(r.getParticipantes().size() > 0)
                for (Participante p : r.getParticipantes())
                    if (p instanceof Convidado)
                        resposta = true;
            candidate.include(resposta);
        }
    }

}






