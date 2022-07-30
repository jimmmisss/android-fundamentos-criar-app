package br.com.legalizzr.agenda.ui.activity;

import static br.com.legalizzr.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import static br.com.legalizzr.agenda.ui.activity.ConstantesActivities.TITULO_LISTA_APPBAR;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.legalizzr.agenda.R;
import br.com.legalizzr.agenda.model.Aluno;
import br.com.legalizzr.agenda.ui.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {

    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        configuraLista();
        setTitle(TITULO_LISTA_APPBAR);
        configuraFabNovoAluno();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraListenerDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            var alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        var vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> abreFormularioModoInsereAluno());
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
}
