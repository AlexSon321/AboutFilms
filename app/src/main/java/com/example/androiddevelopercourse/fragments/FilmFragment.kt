package com.example.androiddevelopercourse.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androiddevelopercourse.adapters.MainAdapter
import com.example.androiddevelopercourse.adapters.RateAdapter
import com.example.androiddevelopercourse.data.RetrofitInstance
import com.example.androiddevelopercourse.databinding.FragmentWeatherBinding
import com.example.androiddevelopercourse.viewmodel.FilmViewModel
import com.squareup.picasso.Picasso
import retrofit2.HttpException
import java.io.IOException


class FilmFragment : Fragment() {
    private lateinit var model: FilmViewModel
    private lateinit var binding: FragmentWeatherBinding
    private var adapter = MainAdapter()
    private var rateAdapter = RateAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        model = ViewModelProvider(this)[FilmViewModel::class.java]

        Handler(Looper.myLooper()!!).postDelayed({
            binding.animationView.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
        }, 2500)

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getFilm()
            } catch(e: IOException){
                binding.textView.text  = "ADSDAS"
                return@launchWhenCreated
            } catch(e: HttpException){
                binding.textView.text  = "DSADADS"
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body() != null){
                model.getFilm()
                model.list.observe(viewLifecycleOwner){ list ->
                    list.body()?.let {
                        binding.textView4.text  = it.original_title
                        binding.textView6.text = it.overview
                        Picasso.get().load("https://image.tmdb.org/t/p/w500${it.backdrop_path}").into(binding.imageView2)
                        binding.textView12.text = it.release_date
                        binding.textView7.text = "${it.budget}$"
                    }
                }
            }

            val popular = try {
                RetrofitInstance.api.getPopular()
            } catch(e: IOException){
                return@launchWhenCreated
            } catch(e: HttpException){
                return@launchWhenCreated
            }

            if(popular.isSuccessful && popular.body() != null){
                model.getPopular()
                model.newList.observe(viewLifecycleOwner){list ->
                    list.body()?.let{

                        binding.rcView.adapter = adapter
                        binding.rcView.layoutManager = GridLayoutManager(activity, 2)

                        adapter.addElem(it.results)
                    }
                }
            }

            val rated = try {
                RetrofitInstance.api.getRated()
            } catch(e: IOException){
                return@launchWhenCreated
            } catch(e: HttpException){
                return@launchWhenCreated
            }

            if(rated.isSuccessful && rated.body() != null){
                model.getRated()
                model.rateList.observe(viewLifecycleOwner){list ->
                    list.body()?.let{

                        binding.rcRated.adapter = rateAdapter
                        binding.rcRated.layoutManager = GridLayoutManager(activity, 2)

                        rateAdapter.addElem(it.results)
                    }
                }
            }

        }
//
        binding.button.setOnClickListener {
            val id = binding.editTextTextPersonName2.text.toString()


            lifecycleScope.launchWhenCreated {
                val response = try {
                    RetrofitInstance.api.getNew(Integer.parseInt(id))
                } catch(e: IOException){
                    binding.textView.text  = "ADSDAS"
                    return@launchWhenCreated
                } catch(e: HttpException){
                    binding.textView.text  = "DSADADS"
                    return@launchWhenCreated
                }

                if(response.isSuccessful && response.body() != null){
                    model.getNew(Integer.parseInt(id))
                    model.list.observe(viewLifecycleOwner){ list ->
                        list.body()?.let {
                            binding.textView4.text  = it.original_title
                            binding.textView6.text = it.overview
                            Picasso.get().load("https://image.tmdb.org/t/p/w500${it.backdrop_path}").into(binding.imageView2)
                            binding.textView12.text = it.release_date
                            binding.textView7.text = "${it.budget}$"
                        }
                    }



                }
            }

        }


        return binding.root
    }
}