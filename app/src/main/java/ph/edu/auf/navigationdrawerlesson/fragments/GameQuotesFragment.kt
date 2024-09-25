package ph.edu.auf.navigationdrawerlesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentLoveQuotesBinding
import kotlin.random.Random

class GameQuotesFragment : Fragment() {

    private var _binding: FragmentLoveQuotesBinding? = null
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
        _binding = FragmentLoveQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial quote
        binding.txtLoveQuote.text = gameQuotes[0]

        // Set an OnClickListener for the "Randomize quote" button
        binding.btnRandomizeQuote.setOnClickListener {
            // Randomly pick a quote from the list and set it to the TextView
            val randomQuote = gameQuotes[Random.nextInt(gameQuotes.size)]
            binding.txtLoveQuote.text = randomQuote
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}