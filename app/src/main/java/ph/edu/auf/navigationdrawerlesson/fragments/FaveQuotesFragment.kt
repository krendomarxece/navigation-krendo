package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentFaveQuotesBinding

class FaveQuotesFragment : Fragment() {

    private var _binding: FragmentFaveQuotesBinding? = null
    private val binding get() = _binding!!

    private val quotes = listOf(
        "In all the world, there is no heart for me like yours. - Maya Angelou",
        "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela",
        "The way to get started is to quit talking and begin doing. - Walt Disney",
        "Your time is limited, so don't waste it living someone else's life. - Steve Jobs"
    )

    private var currentQuote = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFaveQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load saved bookmark
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val savedQuote = sharedPref.getString("BOOKMARKED_QUOTE", "")
        if (!savedQuote.isNullOrEmpty()) {
            binding.txtFaveQuote.text = savedQuote
            currentQuote = savedQuote
        }

        // Randomize quote on button click
        binding.btnRandomize.setOnClickListener {
            val randomQuote = quotes.random()
            binding.txtFaveQuote.text = randomQuote
            currentQuote = randomQuote

            // Save the quote as a bookmark
            /*sharedPref.edit().putString("BOOKMARKED_QUOTE", currentQuote).apply()
            Toast.makeText(requireContext(), "Quote bookmarked!", Toast.LENGTH_SHORT).show()*/
        }

        // Remove this code to fix the error
        /*
        binding.btnRemoveFave.setOnClickListener {
            sharedPref.edit().remove("BOOKMARKED_QUOTE").apply()
            binding.txtFaveQuote.text = ""
            Toast.makeText(requireContext(), "Bookmark removed!", Toast.LENGTH_SHORT).show()
        }
        */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
