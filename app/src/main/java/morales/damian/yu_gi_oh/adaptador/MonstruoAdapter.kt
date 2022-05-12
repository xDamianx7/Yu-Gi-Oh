package morales.damian.yu_gi_oh.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import morales.damian.yu_gi_oh.ClasesNecesarias.Monstruo
import morales.damian.yu_gi_oh.R

class MonstruosAdapter(val listaMonstruos:ArrayList<Monstruo>): RecyclerView.Adapter<MonstruosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.visualizar,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Nombre.text = listaMonstruos[position].nombre
        holder.Tipo.text = listaMonstruos[position].tipo
        holder.Raza.text = listaMonstruos[position].raza
        Picasso.get()
            .load(listaMonstruos[position].imagen)
            .into(holder.Imagen)

    }

    override fun getItemCount(): Int {
        return listaMonstruos.size
    }

    class ViewHolder(visualizar: View): RecyclerView.ViewHolder(visualizar){
        val Nombre: TextView
        val Tipo: TextView
        val Raza: TextView
        val Imagen: ImageView

        init {
            Nombre = visualizar.findViewById(R.id.NomMonstruo)
            Tipo = visualizar.findViewById(R.id.TipoMonstruo)
            Raza = visualizar.findViewById(R.id.RazaMonstruo)
            Imagen = visualizar.findViewById(R.id.imgMonstruo)
        }
    }
}