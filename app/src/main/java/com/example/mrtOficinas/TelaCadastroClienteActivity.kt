package com.example.mrtOficinas

import android.os.Bundle
import android.view.MenuItem
import com.example.mrtOficinas.R.layout.activity_cadastro_cliente
import kotlinx.android.synthetic.main.activity_cadastro_cliente.*
import kotlinx.android.synthetic.main.activity_estoque.*

class TelaCadastroClienteActivity: DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_cadastro_cliente)
        supportActionBar?.title = "Cadastrar Cliente"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nome_cliente.setText("Cadastro de Clientes")
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
