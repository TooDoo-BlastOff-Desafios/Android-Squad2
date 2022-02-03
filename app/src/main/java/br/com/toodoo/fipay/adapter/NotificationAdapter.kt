package br.com.toodoo.fipay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.model.Notification

class NotificationAdapter(
    private val notifications: List<Notification> = ArrayList()
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bindView(notification)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(notification: Notification) {
            val title: TextView = itemView.findViewById(R.id.txtNotificationTitle)
            val time: TextView = itemView.findViewById(R.id.txtNotificationTime)

            val notificationHour = notification.hour

            val sufix = if (notificationHour!! < 12) "AM" else "PM"
            val timeString = "${notification.hour}:${notification.minutes} $sufix"

            title.text = notification.title
            time.text = timeString
        }
    }

}