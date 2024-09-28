package ph.edu.auf.navigationdrawerlesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentQuotesOfTheDayBinding
import kotlin.random.Random

class QuotesOfTheDayFragment : Fragment() {

    private var _binding: FragmentQuotesOfTheDayBinding? = null
    private val binding get() = _binding!!

    // List of quotes
    private val quotes = listOf(
        "In all the world, there is no heart for me like yours. In all the world, there is no love for you like mine. - Maya Angelou",
        "Love is not about how many days, months, or years you have been together. Love is about how much you love each other every single day.",
        "The best and most beautiful things in this world cannot be seen or even heard, but must be felt with the heart. - Helen Keller",
        "You are the source of my joy, the center of my world and the whole of my heart.",
        "Your love is all I need to feel complete."
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuotesOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the button click listener to randomize the quote
        binding.btnRandomizeQuote.setOnClickListener {
            val randomIndex = Random.nextInt(quotes.size)
            binding.txtQuoteOfDay.text = quotes[randomIndex]
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
