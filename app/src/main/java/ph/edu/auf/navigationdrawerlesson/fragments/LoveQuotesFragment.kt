package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ph.edu.auf.navigationdrawerlesson.R
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentLoveQuotesBinding
import kotlin.random.Random

class LoveQuotesFragment : Fragment() {

    private var _binding: FragmentLoveQuotesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // List of love quotes
    private val loveQuotes = listOf(
        "In all the world, there is no heart for me like yours. In all the world, there is no love for you like mine. - Maya Angelou",
        "Love is composed of a single soul inhabiting two bodies. - Aristotle",
        "You know you're in love when you can't fall asleep because reality is finally better than your dreams. - Dr. Seuss",
        "The best thing to hold onto in life is each other. - Audrey Hepburn",
        "To love and be loved is to feel the sun from both sides. - David Viscott"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoveQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial quote
        binding.txtLoveQuote.text = loveQuotes[0]

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set an OnClickListener for the "Randomize quote" button
        binding.btnRandomizeQuote.setOnClickListener {
            // Randomly pick a quote from the list and set it to the TextView
            val randomQuote = loveQuotes[Random.nextInt(loveQuotes.size)]
            binding.txtLoveQuote.text = randomQuote
        }

        binding.btnSaveQuote.setOnClickListener {
            val currentQuote = binding.txtLoveQuote.text.toString()
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
