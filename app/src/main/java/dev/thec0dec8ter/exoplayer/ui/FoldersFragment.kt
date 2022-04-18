package dev.thec0dec8ter.exoplayer.ui

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import dev.thec0dec8ter.exoplayer.R
import dev.thec0dec8ter.exoplayer.adapter.FolderAdapter
import dev.thec0dec8ter.exoplayer.databinding.FragmentFoldersBinding
import dev.thec0dec8ter.exoplayer.model.Folder
import dev.thec0dec8ter.exoplayer.model.Video


class FoldersFragment : Fragment() {

    private lateinit var binding :FragmentFoldersBinding

    private lateinit var folderAdapter :FolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFoldersBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu);
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        folderAdapter = FolderAdapter(activity)
        binding.folderRecycler.adapter = folderAdapter
        getMediaFiles()
    }

    fun getMediaFiles() {
        val externalStorageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_MODIFIED,

        )

        val cursorLoader = CursorLoader(
            requireContext(),
            externalStorageUri,
            projection,
            null,
            null,  // Selection args (none).
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME + " ASC" // Sort order.
        )

        val cursor = cursorLoader.loadInBackground()
        if (cursor != null && cursor.moveToFirst()) {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val bucketNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val bucketName = cursor.getString(bucketNameColumn)
                val name = cursor.getString(nameColumn)
                val uri = Uri.withAppendedPath(externalStorageUri, cursor.getLong(idColumn).toString())
                val video = Video(name, uri)

                if(folderAdapter.containsFolder(bucketName)){
                    folderAdapter.getFolder(bucketName).folderVideos.add(video)
                }else{
                    val folder = Folder(bucketName)
                    folder.folderVideos.add(video)
                    folderAdapter.addFolder(folder)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}