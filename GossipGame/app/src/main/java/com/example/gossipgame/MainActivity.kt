package com.example.gossipgame

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var btnPlay: Button
    private lateinit var btnRegister: Button
    private lateinit var tvGossipCounter: TextView
    private var repository = GossipRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnPlay = findViewById(R.id.btnPlay)
        this.btnRegister = findViewById(R.id.btnRegister)
        this.tvGossipCounter = findViewById(R.id.tvGossipCounter)

        this.tvGossipCounter.text = "${repository.size()} Gossip(s) so far"

        val addGossipResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                val gossip = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("GOSSIP", Gossip::class.java)
                } else {
                    it.data?.getSerializableExtra("GOSSIP")
                } as Gossip

                this.repository.addGossip(gossip)
                this.tvGossipCounter.text = "${repository.size()} Gossip(s) so far"
                Toast.makeText(this, "Gossip added!", Toast.LENGTH_SHORT).show()
            }
        }

        val guessIfTrueResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                Toast.makeText(this, "You were right!!", Toast.LENGTH_SHORT).show()
            } else if(it.resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Given up so easy, huh?", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not good enough, try again!", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnRegister.setOnClickListener {
            val intent = Intent(this, AddGossipActivity::class.java)
            addGossipResult.launch(intent)
        }

        this.btnPlay.setOnClickListener{
            val intent = Intent(this, GuessIfTrueActivity::class.java).apply {
                putExtra("GOSSIP", repository.getRandomGossip())
            }
            guessIfTrueResult.launch(intent)
        }
    }
}