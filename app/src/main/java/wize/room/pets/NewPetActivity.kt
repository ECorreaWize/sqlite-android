package wize.room.pets

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewPetActivity : AppCompatActivity() {
    private lateinit var editPetView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pet)
        editPetView = findViewById(R.id.edit_pet)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editPetView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val pet = editPetView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, pet)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.petlistsql.REPLY"
    }
}