package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentMovieQuotesBinding
import kotlin.random.Random

class MovieQuotesFragment : Fragment() {

    private var _binding: FragmentMovieQuotesBinding? = null
    private val binding get() = _binding!!

    // List of movie quotes
    private val movieQuotes = listOf(
        "May the Force be with you. - Star Wars",
        "Here's looking at you, kid. - Casablanca",
        "I'm gonna make him an offer he can't refuse. - The Godfather",
        "You had me at hello. - Jerry Maguire",
        "I’ll be back. - The Terminator",
        "To infinity and beyond! - Toy Story",
        "Life is like a box of chocolates. You never know what you're gonna get. - Forrest Gump",
        "I'm king of the world! - Titanic",
        "There's no place like home. - The Wizard of Oz",
        "Houston, we have a problem. - Apollo 13"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial quote
        binding.txtMovieQuote.text = movieQuotes[0]

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set an OnClickListener for the "Randomize quote" button
        binding.btnRandomizeQuote.setOnClickListener {
            // Randomly pick a quote from the list and set it to the TextView
            val randomQuote = movieQuotes[Random.nextInt(movieQuotes.size)]
            binding.txtMovieQuote.text = randomQuote  // Corrected this line
        }

        binding.btnSaveQuote.setOnClickListener {
            val currentQuote = binding.txtMovieQuote.text.toString()  // Corrected this line
            val savedQuote = sharedPref.getString("SAVED_QUOTE", "")

            // Check if there's already a saved quote
            if (savedQuote.isNullOrEmpty()) {
                // Save the current quote
                sharedPref.edit().putString("SAVED_QUOTE", currentQuote).apply()
                Toast.makeText(requireContext(), "Quote saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "You need to remove the existing saved quote first!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
