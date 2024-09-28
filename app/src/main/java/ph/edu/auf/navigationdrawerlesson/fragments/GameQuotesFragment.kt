package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentGameQuotesBinding
import kotlin.random.Random

class GameQuotesFragment : Fragment() {

    private var _binding: FragmentGameQuotesBinding? = null
    private val binding get() = _binding!!

    // List of game quotes
    private val gameQuotes = listOf(
        "Life is a game, play it. - Mother Teresa",
        "Games are won by players who focus on the playing field – not by those whose eyes are glued to the scoreboard. - Warren Buffett",
        "It’s not whether you get knocked down, it’s whether you get up. - Vince Lombardi",
        "Do a little more each day than you think you possibly can. - Lowell Thomas",
        "Success is no accident. It is hard work, perseverance, learning, studying, sacrifice and most of all, love of what you are doing or learning to do. - Pele"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentGameQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial quote
        binding.txtGameQuote.text = gameQuotes[0]

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set an OnClickListener for the "Randomize quote" button
        binding.btnRandomizeQuote.setOnClickListener {
            // Randomly pick a quote from the list and set it to the TextView
            val randomQuote = gameQuotes[Random.nextInt(gameQuotes.size)]
            binding.txtGameQuote.text = randomQuote
        }

        binding.btnSaveQuote.setOnClickListener {
            val currentQuote = binding.txtGameQuote.text.toString()
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