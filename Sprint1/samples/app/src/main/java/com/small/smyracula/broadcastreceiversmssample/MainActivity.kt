package com.small.smyracula.broadcastreceiversmssample

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat


/**
 * Created by Osman Korcan ANDAÇ
 */
class MainActivity : AppCompatActivity() {

    private var addToBackStack: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listPermissionsNeeded = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS)
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 1)
        }

        val fragmentManager = supportFragmentManager
        val currentTag = PasswordShowFragment.javaClass.name
        if (!fragmentManager.popBackStackImmediate(currentTag, 0)) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.content, PasswordShowFragment.newInstance())
            /*if (addToBackStack) {
                transaction.addToBackStack(currentTag)
            }*/
            transaction.commit()
        }
    }
}
