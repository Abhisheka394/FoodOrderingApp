package com.abhishek.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*

class RegAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        reg_sign.setOnClickListener {

            if(reg_password.text.toString().equals(reg_confirm.text.toString())) {
                var url =
                    "http://192.168.43.17/SalesWeb/add_user.php?mobile=" + reg_mobile.text.toString() + "&password=" + reg_password.text.toString() + "&name=" + reg_name.text.toString() + "&address=" + reg_address.text.toString()
                var rq:RequestQueue=Volley.newRequestQueue(this)
                var sr=StringRequest(Request.Method.GET,url,Response.Listener {
                        response ->
                        if(response.equals("0"))
                            Toast.makeText(this,"Mobile already used",Toast.LENGTH_SHORT).show()
                    else
                        {
                            UserInfo.mobile=reg_mobile.text.toString()
                            var i=Intent(this,HomeAct::class.java)
                            startActivity(i)
                        }
                }, Response.ErrorListener {
                    error ->  Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                })
                     rq.add(sr)
            }
            else
                Toast.makeText(this,"Password does not match",Toast.LENGTH_SHORT).show()

        }


    }
}
