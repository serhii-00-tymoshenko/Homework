package com.example.homework.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.adapters.song.SongAdapter
import com.example.homework.adapters.song.SongAdapterJava
import com.example.homework.data.entities.Singer
import com.example.homework.data.entities.Song
import com.example.homework.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {
    private var _binding: FragmentPlaylistBinding? = null
    private val binding get() = _binding!!
    private lateinit var songs: List<Song>
    private lateinit var songRecycler: RecyclerView
    private lateinit var songAdapter: SongAdapterJava

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSongRecycler()
        getSongs()
    }

    private fun setupSongRecycler() {
        songAdapter = SongAdapterJava()
        songRecycler = binding.songRecyclerView
        songRecycler.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun showSingerDetails(singer: Singer) {
        Toast.makeText(requireContext(), singer.fullName, Toast.LENGTH_SHORT).show()
    }

    private fun getSongs() {
        songs = listOf(
            Song(
                Singer("John Doe", "John", "18 y.o. male singer"),
                "John's song",
                150,
                R.drawable.ic_launcher_foreground,
                0
            ),
            Song(
                Singer("Jane Doe", "Jane", "18 y.o. female singer"),
                "Jane's song",
                150,
                R.drawable.ic_launcher_foreground,
                1
            )
        )
        songAdapter.submitList(songs)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}