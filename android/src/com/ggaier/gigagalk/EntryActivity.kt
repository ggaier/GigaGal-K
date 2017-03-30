package com.ggaier.gigagalk

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_entry.*
const val EXTRA_FLAG="extra_flag"
const val FLAG_DRAW_A_LOGO =1
class EntryActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener {
                item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        mTextMessage!!.setText(R.string.title_home)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_dashboard -> {
                        mTextMessage!!.setText(R.string.title_dashboard)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_notifications -> {
                        mTextMessage!!.setText(R.string.title_notifications)
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        mTextMessage = findViewById(R.id.message) as TextView
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        draw_logo.setOnClickListener {
            startActivityWithFlag(FLAG_DRAW_A_LOGO)
        }
    }

    fun startActivityWithFlag(flag:Int){
        val intent= Intent(this,AndroidLauncher::class.java)
        intent.putExtra(EXTRA_FLAG,flag)
        startActivity(intent)
    }

}
