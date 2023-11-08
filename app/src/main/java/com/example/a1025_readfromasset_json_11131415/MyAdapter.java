package com.example.a1025_readfromasset_json_11131415;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

	private ArrayList<String> bookName;
	private ArrayList<String> bookTitle;
	private ArrayList<String> author;
	private ArrayList<String> price;
	private  static OnItemClickListenter listen;
	public MyAdapter(Context context, ArrayList<String> bookName,ArrayList<String> bookTitle,ArrayList<String> author,ArrayList<String> price){
		this.bookName = bookName;
		this.bookTitle = bookTitle;
		this.author = author;
		this.price = price;
		//this.listen = listen;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbook, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		//holder.imageViewItem.setImageResource(item.getImg());
		holder.bookName.setText(bookName.get(position));
		holder.bookTitle.setText(bookTitle.get(position));
		holder.author.setText(author.get(position));
		holder.price.setText(price.get(position));
		holder.relativeLayout.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Toast.makeText(view.getContext(), "CLicked" , Toast.LENGTH_LONG).show();
			}
		});
	}


	@Override
	public int getItemCount() {
		return bookName.size();
	}
	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		ImageView imageViewItem;
		TextView bookName, bookTitle, author, price;
		RelativeLayout relativeLayout;
		public ViewHolder(View itemView) {
			super(itemView);
			imageViewItem = itemView.findViewById(R.id.imageView); // Sử dụng ID img từ list_item.xml
			bookName = itemView.findViewById(R.id.bookName);
			bookTitle = itemView.findViewById(R.id.bookTitle);
			author = itemView.findViewById(R.id.author);
			price = itemView.findViewById(R.id.price);
			relativeLayout = itemView.findViewById(R.id.relativeLayout);
		}

		@Override
		public void onClick(View view) {
			int position = getAdapterPosition();
			if (listen != null){
				listen.OnItemCLick(position);
			}
		}
	}
	interface OnItemClickListenter{
		public void OnItemCLick(int position);
	}



}

