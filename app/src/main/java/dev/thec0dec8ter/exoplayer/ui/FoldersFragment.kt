package dev.thec0dec8ter.exoplayer.ui

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import dev.thec0dec8ter.exoplayer.adapter.FolderAdapter
import dev.thec0dec8ter.exoplayer.databinding.FragmentFoldersBinding
import dev.thec0dec8ter.exoplayer.model.Folder
import java.lang.StringBuilder

class FoldersFragment : Fragment() {

    private lateinit var binding :FragmentFoldersBinding

    private lateinit var folderAdapter :FolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        folderAdapter = FolderAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFoldersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.folderRecycler.adapter = folderAdapter
        getMediaFiles()
    }

    fun getMediaFiles() {
        val externalStorageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DISPLAY_NAME
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

            while (cursor.moveToNext()) {
                val bucketName = cursor.getString(bucketNameColumn)
                if(folderAdapter.containsFolder(bucketName)){
                    folderAdapter.getFolder(bucketName).fileIdList.add(cursor.getLong(idColumn).toString())
                }else{
                    val folder = Folder(bucketName)
                    folder.fileIdList.add(cursor.getLong(idColumn).toString())
                    folderAdapter.addFolder(folder)
                }
            }
        }
    }
}