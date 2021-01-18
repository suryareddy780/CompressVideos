package edmt.dev.compressvideos

import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    val videoView : VideoView ?= null
    private var mIsPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = intent
        val path = intent.getStringExtra("path")
        videoView!!.setVideoPath(path)
        videoView.start()
    }

    fun onPausePlayClick(view: View) {
        if (mIsPaused) {
            playVideo()
            mIsPaused = false
        } else {
            pauseVideo()
            mIsPaused = true
        }
    }

    private fun pauseVideo() {
        val videoView : VideoView ?= null
        if (videoView!!.isPlaying) {
            videoView.pause()
        }
    }

    private fun playVideo() {
        val videoView : VideoView?=null
        if (!videoView!!.isPlaying) {
            videoView.start()
        }
    }


}
