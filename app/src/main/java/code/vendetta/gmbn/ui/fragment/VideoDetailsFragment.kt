package code.vendetta.gmbn.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import code.vendetta.gmbn.R
import code.vendetta.gmbn.model.Snippet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_video_details.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri


class VideoDetailsFragment : Fragment() {

    private var data: Snippet = Snippet()
    private val inputFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK)
    private val outputFormat: SimpleDateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.UK)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            data = it.getParcelable(KEY_VIDEO_VIDEO_DETAILS)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(data.thumbnails.high.url)
            .into(videoImage)

        titleVideoDetails.text = data.title
        descriptionVideoDetails.text = data.description
        val date = inputFormat.parse(data.published)
        publishedVideoDuration.text = getString(R.string.published, outputFormat.format(date))

        playVideoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + data.resourceId.videoId))
            intent.putExtra("force_fullscreen",true)
            startActivity(intent)
        }
    }

    companion object {

        private const val KEY_VIDEO_VIDEO_DETAILS = "key_video_details"

        fun newInstance(
            videoDetails: Snippet
        ): VideoDetailsFragment {
            val args = Bundle()
            args.putParcelable(KEY_VIDEO_VIDEO_DETAILS, videoDetails)
            val fragment = VideoDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}