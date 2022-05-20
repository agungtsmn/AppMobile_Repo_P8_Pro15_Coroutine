package id.ac.polbeng.amandaagungpermata.p8pro15

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var lagu1: MediaPlayer
    private lateinit var lagu2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lagu1 = MediaPlayer.create(this, R.raw.lagu1)
        lagu2 = MediaPlayer.create(this, R.raw.lagu2)

        btn.setOnClickListener {
            runBlocking {
                launch { playBeats("x-x-x-x-x-x-x-x-x-x-x", R.raw.lagu2) }
                         playBeats("x----x----x----x----x", R.raw.lagu1)
            }
        }
    }

    suspend fun playBeats(beats: String, fileId: Int) {
        val parts = beats.split("x")
        var count = 0
        for(part in parts){
            count += part.length + 1
            if(part == ""){
                if(fileId == R.raw.lagu1)
                    lagu1.start()
                else
                    lagu2.start()
            }else{
                delay(1000 * (part.length + 1L))
                if(count < beats.length){
                    if(fileId == R.raw.lagu1)
                        lagu1.start()
                    else
                        lagu2.start()
                }
            }
        }

    }

    override fun onStop() {
        super.onStop()
        lagu1.stop()
        lagu2.stop()
    }
}