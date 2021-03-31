package hu.bme.aut.aitforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun registerClick(v: View){
        if (!isFormValid()){
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                etEmail.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {
            Toast.makeText(this@MainActivity,
                    "Registration OK",
                    Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this@MainActivity,
                    "Error: ${it.message}",
                    Toast.LENGTH_LONG).show()
        }
    }

    fun isFormValid(): Boolean {
        return when {
            etEmail.text.isEmpty() -> {
                etEmail.error = "This field can not be empty"
                false
            }
            etPassword.text.isEmpty() -> {
                etPassword.error = "The password can not be empty"
                false
            }
            else -> true
        }
    }


}