package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.adapter.NotificationAdapter
import br.com.toodoo.fipay.model.Bill
import br.com.toodoo.fipay.model.Notification

class NotificationsFragment : Fragment() {

    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var rvNotification: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        notificationAdapter = NotificationAdapter(notifications())
        rvNotification = view.findViewById(R.id.rvNotifications)
        rvNotification.adapter = notificationAdapter
        rvNotification.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        return view
    }

    private fun notifications(): List<Notification> {
        return arrayListOf<Notification>(
            Notification("You received a payment of \$450.00 from Andre McKiney", 10, 30),
            Notification("You received a payment of \$450.00 from Andre McKiney", 12, 30),
            Notification("You received a payment of \$450.00 from Andre McKiney", 13, 48),
            Notification("You received a payment of \$450.00 from Andre McKiney", 20, 58),
            Notification("You received a payment of \$450.00 from Andre McKiney", 21, 18),
            Notification("You received a payment of \$450.00 from Andre McKiney", 21, 40),
        )
    }

}