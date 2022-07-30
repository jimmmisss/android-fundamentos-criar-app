package br.com.legalizzr.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.legalizzr.agenda.dao.AlunoDAO;
import br.com.legalizzr.agenda.model.Aluno;
import br.com.legalizzr.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final Context context;
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        dao = new AlunoDAO();
    }

    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Está certo de remover aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    var alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }
}
