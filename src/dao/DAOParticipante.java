package dao;


import com.db4o.query.Query;
import modelo.Participante;


import java.util.List;

public class DAOParticipante extends DAO<Participante>{

    public Participante read (Object chave) {
        String participante = (String) chave;

        Query q = manager.query();
        q.constrain(Participante.class);
        q.descend("nome").constrain(participante).contains();
        List<Participante> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }

//consulta : Quais os participantes que tem reunião com participante de nome N no mês M?//


    public List<Participante> consultarParticipante(String nome, int mes) {
        Query q = manager.query();
        q.constrain(Participante.class);
        q.descend("reunioes").descend("participantes").descend("nome").constrain(nome).contains();
        q.descend("reunioes").descend("datahora").constrain("/"+mes+"/").like();

        List<Participante> result = q.execute();
        return result;
    }

}

