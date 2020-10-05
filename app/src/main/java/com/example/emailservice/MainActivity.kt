package com.example.emailservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_new_email.view.*


class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Email>()
    private lateinit var mDialogView: View
    private  lateinit var adapter: Adapter
    private var service = MyService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            showCreateTaskDialog()
        }

        adapter = Adapter(items)
        recycler.adapter = adapter

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(receiver, IntentFilter(service.ACTION))
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(service.ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }


    private fun showCreateTaskDialog() {
        mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_new_email, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Novo Email")

        val  mAlertDialog = mBuilder.show()

        mDialogView.dialogAddBtn.setOnClickListener {
            mAlertDialog.dismiss()
            val title = mDialogView.title_text.text.toString()
            val subject = mDialogView.sub_text.text.toString()

            adapter = Adapter(items)
            recycler.adapter = adapter
            adapter.addItem(Email(title, subject))
        }
        mDialogView.dialogCancelBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val newList = intent.getParcelableArrayListExtra<Email>("newList")

            if (newList != null){
                adapter = Adapter(newList)
                recycler.adapter = adapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_remove -> {
                Intent(this, MyService::class.java).also { intent ->
                    intent.putExtra("list", items)
                    startService(intent)
                }
            }
        }
        return true
    }
}