package com.example.homework.adapters.song

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.data.entities.Singer
import com.example.homework.data.entities.Song
import com.example.homework.databinding.ItemSongBinding

class SongAdapter(
    val callback: (Singer) -> Unit
) : ListAdapter<Song, SongAdapter.SongViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song) = newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: Song, newItem: Song) = newItem == oldItem
        }
    }

    inner class SongViewHolder(
        private val binding: ItemSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(song: Song) {
            val songDurationInMin: Int = song.songDuration / 60
            val songDurationInSec: Int = song.songDuration % 60

            if (adapterPosition != RecyclerView.NO_POSITION) {
                binding.root.setOnClickListener {
                    callback.invoke(song.singer)
                }
            }

            binding.apply {
                singerName.text = song.singer.fullName
                singerStageName.text = song.singer.stageName
                songName.text = "\"${song.songName}\""
                songDuration.text = "$songDurationInMin:$songDurationInSec"
                albumCover.setImageResource(song.albumCover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val currentSong = getItem(position)
        holder.bind(currentSong)
    }
}