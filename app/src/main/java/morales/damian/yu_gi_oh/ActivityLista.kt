package morales.damian.yu_gi_oh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import morales.damian.yu_gi_oh.ClasesNecesarias.Monstruo
import morales.damian.yu_gi_oh.adaptador.MonstruosAdapter

class ActivityLista : AppCompatActivity() {
    lateinit var miRecyclerView: RecyclerView
    val lisMonstruos = ArrayList<Monstruo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        miRecyclerView = findViewById(R.id.RecyclerMonstruos)
        miRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        getMonstruos()
    }

    fun getMonstruos(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://db.ygoprodeck.com/api/v7/cardinfo.php"
        val jsonObject = JsonObjectRequest(Request.Method.GET,url,null,
            {respuesta->
                val newjson = respuesta.getJSONArray("data")
                for(i in 0..100){
                    val newobj= newjson.getJSONObject(i)
                    val newimg = newobj.getJSONArray("card_images")
                    val urlJson = newimg.getJSONObject(0)
                    val monstruo = Monstruo(newobj.getString("name"), newobj.getString("type"),
                        newobj.getString("race"),urlJson.getString("image_url"))
                    lisMonstruos.add(monstruo)
                }
                miRecyclerView.adapter = MonstruosAdapter(lisMonstruos)
            },{error->
                Log.e("Exam","error")
            }
        )
        queue.add((jsonObject))
    }
}