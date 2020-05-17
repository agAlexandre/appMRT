package com.example.mrtOficinas

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_servicos.*

class TelaServicosActivity:DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicos)
        supportActionBar?.title = "Novo serviço"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        novo_servico.setOnClickListener{

            if (AndroidUtils.isInternetDisponivel(this)){
                val servicos = Servicos()
                servicos.nome=nome_servico.text.toString()
                servicos.descricao=descricao_servico.text.toString()
                servicos.valor= valor_servico.text.toString()
                servicos.foto=foto_servico.text.toString()

                taskAtualizar(servicos)
            }
            else{
                Toast.makeText(this, "Não é possível realizar cadastro, sem conexão...", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun taskAtualizar(servicos: Servicos) {
        Thread {
            ServicosService.save(servicos)
            runOnUiThread {
                finish()
            }
        }.start()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}