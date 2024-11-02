package com.example.testhero

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import com.example.testhero.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private var score =0
    private var result : String = ""
    private var userAnswer: String = ""
override fun onCreate(savedInstanceState:Bundle?)
{
    super.onCreate(savedInstanceState)
    binding=ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    //init views
    binding.apply {
        panel1.setOnClickListener(this@MainActivity)
        panel2.setOnClickListener(this@MainActivity)
        panel3.setOnClickListener(this@MainActivity)
        panel4.setOnClickListener(this@MainActivity)
        StartGame()
    }
}

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }

    private fun disableButtons(){
        binding.root.forEach { view ->
            if (view is AppCompatButton)
            {
                view.isEnabled = false
            }
        }
    }
    private fun enableButtons(){
        binding.root.forEach { view ->
            if (view is AppCompatButton)
            {
                view.isEnabled = true
            }
        }
    }
    private fun StartGame()
    {
        result=""
        userAnswer=""
        disableButtons()
        lifecycleScope.launch {
            val round =(3 .. 5).random()
            repeat(round)
            {
                delay(400)
                val randomPanel = (1..4).random()
                result += randomPanel
                var panel = when(randomPanel)
                {
                    1 -> binding.panel1
                    2 ->binding.panel2
                    3 ->binding.panel3
                    else ->binding.panel4

                }
                val drawableYellow = ActivityCompat.getDrawable(this@MainActivity,R.drawable.btn_yellow)
                val drawableDefault = ActivityCompat.getDrawable(this@MainActivity,R.drawable.btn_state)
                panel.background =drawableYellow
                delay(1000)
                panel.background =drawableDefault


            }
            enableButtons()

        }

    }
    private fun loseAnimation()
    {
        binding.apply {
            score= 0
            tvScore.text=0
            disableButtons()
            val drawableLose = ActivityCompat.getDrawable(this@MainActivity,R.drawable.btn_lose)
            val drawableDefault = ActivityCompat.getDrawable(this@MainActivity,R.drawable.btn_state)
            lifecycleScope.launch {
                binding.root.forEach { 
                    view ->
                    if (view is AppCompatButton)
                    {
                        view.background= drawableLose
                        delay(300)
                        view.background=drawableDefault


                    }
                }
                delay(1000)
                StartGame()
            }
        }
    }
}