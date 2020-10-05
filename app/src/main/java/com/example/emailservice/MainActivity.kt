package com.example.emailservice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_new_email.view.*


class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Email>()
    private lateinit var mDialogView: View
    private  lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            showCreateTaskDialog()
        }

        adapter = Adapter(items)
        recycler.adapter = adapter
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_remove -> {
                Intent(this, MyService::class.java).also { intent ->
                    val bundle = Bundle()
                    bundle.putSerializable("list", items)
                    intent.putExtras(bundle)
                    startService(intent)
                }
            }
        }
        return true
    }
}