package br.com.legalizzr.agenda;

import android.app.Application;

import br.com.legalizzr.agenda.dao.AlunoDAO;
import br.com.legalizzr.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Wesley Pereira", "48 999437427", "jimmmisss@gmail.com"));
        dao.salva(new Aluno("Fadia Pereira", "48 999437427", "fadia@gmail.com"));
    }
}
