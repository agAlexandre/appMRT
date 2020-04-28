package com.example.mrtOficinas

import android.os.Bundle
import android.view.MenuItem


import kotlinx.android.synthetic.main.toolbar.*

class TelaBotoesOperacionaisActivity: DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var args = intent.extras
        val str = args?.getString("str")

        setContentView(R.layout.activity_botoes_operacionais)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "$str"
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
