package com.binaracademy.binar_sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binaracademy.binar_sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val sharedPref = "kotlinsharedpreference"


    @SuppressLint("SetTextIi8n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreferences : SharedPreferences = this.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()

        var id : Int
        var name : String

        var idPref : Int
        var namePref : String


        binding.btnSave.setOnClickListener{
         id = Integer.parseInt(binding.etInputId.text.toString())
         name = binding.etInputName.text.toString()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show()
        }


        binding.btnView.setOnClickListener{
            idPref = sharedPreferences.getInt("id_key",0)
            namePref = sharedPreferences.getString("name_key","defaultValue").toString()
            if(idPref == 0 && namePref.equals("defaultValue")){
                binding.tvShowId.text = "default id : $idPref"
                binding.tvShowName.text = "default name : $namePref"

                Toast.makeText(this,"Empty Data",Toast.LENGTH_LONG).show()
            }else{
                binding.tvShowId.text = idPref.toString()
                binding.tvShowName.text = namePref

                Toast.makeText(this,"Showing Data",Toast.LENGTH_LONG).show()
            }

            binding.btnClear.setOnClickListener{
                editor.clear()
                editor.apply()
                binding.tvShowName.text =""
                binding.tvShowId.text = ""

                Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show()
            }
        }
    }
}