package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentEducationalQuotesBinding
import kotlin.random.Random

class EducationalQuotesFragment : Fragment() {

    private var _binding: FragmentEducationalQuotesBinding? = null
    private val binding get() = _binding!!

    // List of educational quotes (from game)
    private val educationalQuotes = listOf(
        "Education is the most powerful weapon which you can use to change the world. - Nelson Mandela",
        "The roots of education are bitter, but the fruit is sweet. - Aristotle",
        "The beautiful thing about learning is that no one can take it away from you. - B.B. King",
        "Develop a passion for learning. If you do, you will never cease to grow. - Anthony J. D'Angelo",
        "Live as if you were to die tomorrow. Learn as if you were to live forever. - Mahatma Gandhi"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEducationalQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial quote
        binding.txtEducationalQuote.text = educationalQuotes[0]

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set an OnClickListener for the "Randomize quote" button
        binding.btnRandomizeQuote.setOnClickListener {
            // Randomly pick a quote from the list and set it to the TextView
            val randomQuote = educationalQuotes[Random.nextInt(educationalQuotes.size)]
            binding.txtEducationalQuote.text = randomQuote
        }

        binding.btnSaveQuote.setOnClickListener {
            val currentQuote = binding.txtEducationalQuote.text.toString()
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
