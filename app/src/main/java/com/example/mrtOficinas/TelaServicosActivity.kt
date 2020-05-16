package com.example.mrtOficinas

import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_servicos.*

class TelaServicosActivity:DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicos)
        supportActionBar?.title = "Novo serviço"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        novo_servico.setOnClickListener{
            val servicos = Servicos()
            servicos.nome=nome_servico.text.toString()
            servicos.descricao=descricao_servico.text.toString()
            servicos.valor= valor_servico.text.toString()
            servicos.foto=foto_servico.text.toString()

            taskAtualizar(servicos)

        }
    }

    private fun taskAtualizar(servicos: Servicos) {
        // Thread para salvar a discilpina
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