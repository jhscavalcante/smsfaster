package br.com.smsfaster

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_message.addTextChangedListener(this)
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        before: Int, Count: Int) {

        tv_counter.text = String.format("%d / %s", text.length, getString( R.string.max ))
    }

    override fun afterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) { }

    fun clearMessage(view: View){
        et_message.text.clear()
    }

    fun sendSMS(view: View){
        try {
            val number = String
                            .format("+/%s%s%s", et_ddi.text, et_ddd.text, et_number.text)
            val message = et_message.text.toString()

            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(number, null, message, null, null)

            toast( R.string.sms_sent_successfully )

        }catch (e: Exception){
            e.printStackTrace()
            toast(R.string.sms_error)
        }
    }

    private fun toast(messageId: Int){
        Toast
            .makeText(this, getString(messageId), Toast.LENGTH_LONG)
            .show()
    }
}
