package com.small.smyracula.broadcastreceiversmssample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.smyracula.broadcastreceiversmssample.databinding.FragmentPasswordShowBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Osman Korcan ANDAÇ
 */
class PasswordShowFragment : Fragment() {

    private lateinit var binding: FragmentPasswordShowBinding

    private val smsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val bundle = intent.extras
            val pinCode = bundle!!.getString(SMSReceiver.INTENT_MESSAGE)
            if (!TextUtils.isEmpty(pinCode)) {
                binding.textSMSMessage.text = pinCode
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password_show, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // context?.registerReceiver(smsReceiver, IntentFilter(SMSReceiver.INTENT_ACTION))
    }

    override fun onResume() {
        super.onResume()
        context?.registerReceiver(smsReceiver, IntentFilter(SMSReceiver.INTENT_ACTION))
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(smsReceiver)
    }


    companion object {
        @JvmStatic
        fun newInstance(): PasswordShowFragment {
            return PasswordShowFragment()
        }
    }

}