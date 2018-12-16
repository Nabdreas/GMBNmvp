package code.vendetta.gmbn.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import code.vendetta.gmbn.R
import code.vendetta.gmbn.ui.fragment.UploadsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, UploadsFragment.newInstance())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }


}
