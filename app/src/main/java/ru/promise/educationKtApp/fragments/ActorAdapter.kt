package ru.promise.educationKtApp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.promise.educationKtApp.R
import ru.promise.educationKtApp.model.Actor


class ActorAdapter(
        private var actors : List<Actor>
) : RecyclerView.Adapter<ActorViewHolder>() {
    private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(imageOption, actors[position])
    }
    override fun getItemCount(): Int = actors.size
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.actorName)
    private val poster: ImageView = itemView.findViewById(R.id.actorImage)

    fun onBind(options: RequestOptions, actor: Actor) {
        Glide.with(context)
                .load(actor.imageUrl)
                .apply(options)
                .into(poster)

        name.text = actor.name
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

