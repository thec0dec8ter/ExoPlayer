package dev.thec0dec8ter.exoplayer.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.thec0dec8ter.exoplayer.R
import dev.thec0dec8ter.exoplayer.databinding.ItemFolderBinding
import dev.thec0dec8ter.exoplayer.model.Folder

class FolderAdapter(val activity: FragmentActivity?) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    private lateinit var binding :ItemFolderBinding

    val folders :ArrayList<Folder> = ArrayList()
    val folderNames : HashMap<String, Int> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        binding = ItemFolderBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return FolderViewHolder(activity!!, binding.root)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(folders.get(position))
    }

    override fun getItemCount(): Int {
        return folders.size
    }

    fun getFolder(folderName: String) :Folder{
        return folders.get(folderNames.get(folderName)!!)
    }

    fun addFolder(folder: Folder){
        folders.add(folder)
        folderNames[folder.name] = folderNames.keys.size
        notifyDataSetChanged()
    }

    fun containsFolder(folderName :String) : Boolean{
        return folderNames.contains(folderName)
    }


    class FolderViewHolder(val activity: FragmentActivity, itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding :ItemFolderBinding = ItemFolderBinding.bind(itemView)

        fun bind(folder: Folder){
            binding.txtFolderName.text = folder.name
            binding.txtFileCount.text = folder.folderVideos.size.toString() + " videos"

            itemView.setOnClickListener(){
                val args = Bundle()
                args.putString("folder_name", folder.name)
                args.putSerializable("videos", folder.folderVideos)
                activity.findNavController(R.id.nav_host_fragment).navigate(R.id.action_folder_to_video, args)
            }
        }
    }
}