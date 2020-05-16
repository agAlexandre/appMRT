package com.example.mrtOficinas

import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_servicos.*

class TelaServicosActivity:DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicos)
        supportActionBar?.title = "Nova ordem de serviço"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}