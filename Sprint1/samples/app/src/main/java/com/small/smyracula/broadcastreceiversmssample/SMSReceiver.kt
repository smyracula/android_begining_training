package com.small.smyracula.broadcastreceiversmssample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import timber.log.Timber

/**
 * Created by Osman Korcan ANDAÇ
 */
class SMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val bundle = intent.extras
            if (bundle != null) {
                try {
                    val pdus = bundle.get(BUNDLE_NAME_PDUS) as Array<Any>
                    if (pdus.size > 0) {
                        val messages = SmsMessage.createFromPdu(pdus[0] as ByteArray)
                        if (ORIGINATING_ADDRESS == messages.displayOriginatingAddress) {
                            val message = messages.displayMessageBody
                            if (message != null && message.length >= 6) {
                                val pinCode = message.substring(0, 6)
                                val i = Intent(INTENT_ACTION)
                                i.putExtra(INTENT_MESSAGE, pinCode)
                                context.sendBroadcast(i)
                            }
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                }

            }
        }
    }

    companion object {

        val INTENT_ACTION = "smsListenerIntentAction"
        val INTENT_MESSAGE = "smsListenerIntentMessage"

        private val ORIGINATING_ADDRESS = "KORCAN"
        private val BUNDLE_NAME_PDUS = "pdus"
    }
}
