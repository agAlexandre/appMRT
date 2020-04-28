package com.example.mrtOficinas
import android.widget.Toast
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login)

        botao_login.setOnClickListener {
            var intent = Intent(this, TelaInicialActivity::class.java)
            val valor_usuario = user.text.toString()
            val valor_senha = password.text.toString()

            val params = Bundle()
            startActivity(intent)
            params.putString("nome", "$valor_usuario")
/*
            intent.putExtras(params)
            if (valor_usuario == "aluno" && valor_senha == "impacta") {
                startActivity(intent)
            }

            else {
                Toast.makeText(this, "Usuário inválido ou senha incorreta, Por favor verificar!", Toast.LENGTH_LONG).show()
            }*/
        }


    }
}
