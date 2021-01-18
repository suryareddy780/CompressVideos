package edmt.dev.compressvideos

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class PreviewActivity : AppCompatActivity() {
    val videoView : VideoView ?= null
    private var mPath: String? = null
    private var mViewModel: PreviewViewModel? = null
    private var mProgressDialog: ProgressDialog? = null

    companion object {
        const val TAG = "PreviewActivity"
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setMessage("Compressing, Please wait...")
        mProgressDialog?.setCancelable(false)

        mViewModel = ViewModelProvider(this).get(PreviewViewModel::class.java)

        val intent = intent
        mPath = intent.getStringExtra("path")

        videoView!!.setVideoPath(mPath)
        videoView.start()

        val ButtonCompress = findViewById<Button>(R.id.btn_compress)
        val ButtonBitrate = findViewById<Button>(R.id.et_bitrate)
        ButtonCompress.setOnClickListener {
            if (ButtonBitrate.text.isNotEmpty()) {
                mViewModel?.startCompression(this.filesDir, ButtonBitrate.text.toString(), mPath)
            }
        }

        mViewModel?.mOutputPath?.observe(this, Observer { path ->
            if (path != null && path.isNotEmpty()) {
                val i = Intent(this, ResultActivity::class.java)
                i.putExtra("path", path)
                startActivity(i)
            }
        })

        mViewModel?.mIsCompressing?.observe(this, Observer { isCompressing ->
            if (isCompressing) {
                mProgressDialog?.show()
            } else {
                mProgressDialog?.dismiss()
            }
        })


    }
}


