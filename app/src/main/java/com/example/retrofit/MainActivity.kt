package com.example.retrofit

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        GlobalScope.launch(Dispatchers.IO) {
            val call = UserRetrofitService.retrofit.create(UserApi::class.java).getProducts()
            val response = call.execute().body()
            withContext(Dispatchers.Main){
                binding.id.text = response?.data?.id.toString()
                binding.name.text = response?.data?.name
                binding.description.text = response?.data?.description
                binding.price.text = response?.data?.price
//                val bmp = BitmapFactory.decodeStream(URL(response?.data?.image).openConnection().getInputStream())
//                binding.image.setImageBitmap(bmp)
                Picasso.get()
                        .load(response?.data?.image)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.image)
                binding.discount.text = response?.data?.discount_amount
                binding.status.text = response?.data?.status.toString()

                val category = response?.data?.categories
                binding.id1.text = category?.get(0)?.id.toString()
                binding.name1.text = category?.get(0)?.name

                binding.id2.text = category?.get(1)?.id.toString()
                binding.name2.text = response?.data?.categories?.get(1)?.name
            }
        }

    }
}