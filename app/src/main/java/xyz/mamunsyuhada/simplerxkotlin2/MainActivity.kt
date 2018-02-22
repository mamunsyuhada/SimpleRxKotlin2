package xyz.mamunsyuhada.simplerxkotlin2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RxTextView.textChanges(etNama)
                .map { t: CharSequence -> t.toString() }
                .debounce(2, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

//                kalau mau langsung tanpa delay, implement func yang bawah aja, yang atas dibauang jauh-jauh, kayak mantan
                .subscribe { t: String? -> tvOutput.text = t }
    }
}
