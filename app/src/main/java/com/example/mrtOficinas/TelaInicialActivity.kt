package com.example.mrtOficinas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private var servicos = listOf<Servicos>()
    @SuppressLint("WrongConstant")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var args = intent.extras
        val nome = args?.getString("nome")

        setSupportActionBar(toolbar)
        supportActionBar?.title = "MRT Oficina"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler_servicos?.layoutManager = LinearLayoutManager(context)
        recycler_servicos?.itemAnimator= DefaultItemAnimator()
        recycler_servicos?.setHasFixedSize(true)
        configuraMenuLateral()
    }

    override fun onResume() {
        super.onResume()
        taskServicos()
    }
    fun taskServicos(){
        Thread{
        this.servicos = ServicosService.getServicos(context)
            runOnUiThread {
                recycler_servicos?.adapter = ServicosAdapter(servicos) { onClickServicos(it) }
                enviaNotificacao(servicos.get(0))
            }
        }.start()
    }

    fun enviaNotificacao(servicos: Servicos){
        val intent = Intent(this,Servicos::class.java)
        intent.putExtra("servicos",servicos)
        NotificationUtil.create(this,1,intent, "MRTApp","Você tem um novo serviço ${servicos.nome}")
    }

    fun onClickServicos(servicos: Servicos){
        Toast.makeText(context,"Clicou em ${servicos.nome}", Toast.LENGTH_LONG).show()
    }

    private fun cliqueSair() {
        Toast.makeText(this, "Você saiu do App MRT Oficina", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun buttons(str: String){
        var intent = Intent(this, TelaBotoesOperacionaisActivity()::class.java)
        val params = Bundle()
        params.putString("str", "$str")
        intent.putExtras(params)
        startActivity(intent)
    }

    private fun showSettings(){
        var intent = Intent(this, TelaConfigActivity()::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        /* #TODO FUNCIONALIDADE DE PESQUISAR DESATIVADA
        CODE DO MENU MAIN ICON:
        <item android:id="@+id/action_buscar"
        android:title="Buscar"
        app:showAsAction="always"
        app:actionViewClass="androidx.appcompat.widget.SearchView"
        android:icon="@drawable/ic_buscar_white"
        />
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 0) {
                    Toast.makeText(applicationContext, newText, Toast.LENGTH_SHORT).show()
                }
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                return false
            }

        }*/
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == R.id.action_atualizar){
            if (AndroidUtils.isInternetDisponivel(context)){
                kotlin.run{taskServicos()}
                Toast.makeText(this, "Serviços atualizados!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Não é possível atualizar, sem conexão...", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.nav_open, R.string.nav_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }
    private fun cadastrarCliente(){
        var intent = Intent(this, TelaCadastroClienteActivity()::class.java)
        startActivity(intent)
    }
    private fun mostrarEstoque(){
        var intent = Intent(this, TelaEstoqueActivity()::class.java)
        startActivity(intent)
    }
    private fun showServices(){
        var intent = Intent(this, TelaServicosActivity()::class.java)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_servicos ->{
                kotlin.run {showServices()}}
            R.id.nav_estoque ->{
                kotlin.run {mostrarEstoque()}
            }
            R.id.nav_clients ->{
                kotlin.run {cadastrarCliente()}
            }
            R.id.nav_sair ->{
                cliqueSair()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}
