package com.example.homework.adapters.song;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.data.entities.Song;
import com.example.homework.databinding.ItemSongBinding;

public class SongAdapterJava extends ListAdapter<Song, SongAdapterJava.SongViewHolder> {

    public SongAdapterJava() {
        super(new DiffCallback());
    }

    static class DiffCallback extends DiffUtil.ItemCallback<Song> {
        @Override
        public boolean areItemsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
            return newItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
            return oldItem.equals(newItem);
        }
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        ItemSongBinding binding;
        public SongViewHolder(ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Song song) {
            binding.singerName.setText(song.getSinger().getFullName().toString());
        }
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding binding = ItemSongBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SongViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song currentSong = getItem(position);
        holder.bind(currentSong);
    }
}
