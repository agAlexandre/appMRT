package com.example.mrtOficinas

import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tela_config.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaConfigActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_config)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Configurações"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        msg_inicial_config.setText("Conigurações do aplicativo")

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}


